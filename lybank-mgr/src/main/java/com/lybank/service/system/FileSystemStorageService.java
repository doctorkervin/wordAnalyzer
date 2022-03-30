package com.lybank.service.system;

import com.lybank.common.util.DateUtils;
import com.lybank.exception.StorageException;
import com.lybank.exception.StorageFileNotFoundException;
import com.lybank.model.Result;
import com.lybank.model.constant.PacketBoundState;
import com.lybank.model.constant.PacketHashType;
import com.lybank.model.constant.PacketReceptionType;
import com.lybank.pcapdecoder.PcapDecoder;
import com.lybank.pcapdecoder.structure.options.inter.IOptionSectionHeader;
import com.lybank.pcapdecoder.structure.options.inter.IOptionsDescriptionHeader;
import com.lybank.pcapdecoder.structure.options.inter.IOptionsEnhancedPacketHeader;
import com.lybank.pcapdecoder.structure.options.inter.IOptionsStatisticsHeader;
import com.lybank.pcapdecoder.structure.types.IPcapngType;
import com.lybank.pcapdecoder.structure.types.inter.*;
import com.lybank.pcapdecoder.utils.DecoderStatus;
import com.lybank.pcapdecoder.utils.UtilFunctions;
import com.lybank.repository.StorageRep;
import com.lybank.util.CommonUtil;
import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import org.pcap4j.packet.EthernetPacket;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.namednumber.EtherType;
import org.pcap4j.packet.namednumber.IpNumber;
import org.pcap4j.packet.namednumber.TcpPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageRep {

    @Value("${spring.upload-dir}")
    private String upload_dir;

    @Value("${spring.upload-dir-pacp}")
    private String upload_pacp_dir;

    public Path getRootLocation() {
        return Paths.get(upload_dir);
    }

    /**
     * pacp Path
     * @return
     */
    public Path getRootPacpLocation() {
        return Paths.get(upload_pacp_dir);
    }
    /**
     * 保存文件
     *
     * @param file 文件
     */
    @Override
    public String store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException("Cannot store file with relative path outside current directory " + filename);
            }
            Path path = this.getRootLocation().resolve(filename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        return filename;
    }

    /**
     * 列出upload-dir下面所有文件
     *
     * @return
     */
    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.getRootLocation(), 1) //path -> !path.equals(this.rootLocation)
                    .filter(new Predicate<Path>() {
                        @Override
                        public boolean test(Path path) {
                            return !path.equals(getRootLocation());
                        }
                    });
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename) {
        return getRootLocation().resolve(filename);
    }

    /**
     * 获取文件资源
     *
     * @param filename 文件名
     * @return Resource
     */
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    /**
     * 删除upload-dir目录所有文件
     */
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(getRootLocation().toFile());
    }

    /**
     * 初始化
     */
    @Override
    public void init() {
        try {
            Files.createDirectories(getRootLocation());
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    /**
     * file scan
     */
    public String scan(MultipartFile file){
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        byte[] bytes = null;
        File f = null;
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException("Cannot store file with relative path outside current directory " + filename);
            }

            bytes = file.getBytes();
            String filePath = upload_pacp_dir+ DateUtils.getCurrentTimeStrDefault()+".txt";
            f = new File(filePath);
            if (!f.exists()){
                f.createNewFile();
            }
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF-8"))){
            if (bytes != null){
                PcapDecoder decoder = new PcapDecoder(bytes);
                int status = decoder.decode();
                List<IPcapngType> sectionList = decoder.getSectionList();
                if (status == DecoderStatus.SUCCESS_STATUS) {
                    for (int i = 0; i < sectionList.size(); i++) {
                        IPcapngType type = sectionList.get(i);
                        if (type instanceof ISectionHeaderBlock){
                            ISectionHeaderBlock isType = (ISectionHeaderBlock) type;
                            bw.write("SECTION HEADER BLOCK \t\n");
                            if (isType.getMajorVersion() != -1)
                                bw.write("Major version      : " + isType.getMajorVersion());
                                bw.newLine();
                            if (isType.getMinorVersion() != -1)
                                bw.write("Minor version      : " + isType.getMinorVersion());
                                bw.newLine();
                            IOptionSectionHeader optionsList = isType.getOptions();

                            if (optionsList != null) {
                                if (!optionsList.getHardware().equals(""))
                                    bw.write("hardware interface : " + optionsList.getHardware());
                                    bw.newLine();
                                if (!optionsList.getOS().equals(""))
                                    bw.write("OS                 : " + optionsList.getOS());
                                    bw.newLine();
                                if (!optionsList.getUserAppl().equals(""))
                                    bw.write("user application   : " + optionsList.getUserAppl());
                                    bw.newLine();
                                if (!optionsList.getComment().equals(""))
                                    bw.write("comment            : " + optionsList.getComment());
                                    bw.newLine();
                            }
                            bw.write("##########################################################");
                            bw.newLine();
                        }
                        if (type instanceof IDescriptionBlock){
                            IDescriptionBlock idType = (IDescriptionBlock) type;
                            bw.write("SECTION INTERFACE DESCRIPTION BLOCK");

                            if (!idType.getLinkType().equals(""))
                                bw.write("Link type             : " + idType.getLinkType());
                            if (idType.getSnapLen() != -1)
                                bw.write("Snap len              : " + idType.getSnapLen());

                            IOptionsDescriptionHeader optionsList = idType.getOptions();

                            if (optionsList != null) {
                                if (!optionsList.getInterfaceName().equals(""))
                                    bw.write("interface name        : " + optionsList.getInterfaceName());
                                if (!optionsList.getInterfaceDescription().equals(""))
                                    bw.write("interface description : " + optionsList.getInterfaceDescription());
                                if (!optionsList.getInterfaceIpv4NetworkAddr().equals(""))
                                    bw.write("interface ipv4 addr   : " + optionsList.getInterfaceIpv4NetworkAddr());
                                if (!optionsList.getInterfaceNetmask().equals(""))
                                    bw.write("interface netmask     : " + optionsList.getInterfaceNetmask());
                                if (!optionsList.getIpv6NetworkAddr().equals(""))
                                    bw.write("interface ipv6 addr   : " + optionsList.getIpv6NetworkAddr());
                                if (!optionsList.getInterfaceMacAddr().equals(""))
                                    bw.write("interface mac addr    : " + optionsList.getInterfaceMacAddr());
                                if (!optionsList.getInterfaceEuiAddr().equals(""))
                                    bw.write("interface EUI addr    : " + optionsList.getInterfaceEuiAddr());
                                if (optionsList.getInterfaceSpeed() != -1)
                                    bw.write("interface speed       : " + optionsList.getInterfaceSpeed() + "bps");
                                if (optionsList.getTimeStampResolution() != -1) {
                                    bw.write("timestamp resolution  : " + optionsList.getTimeStampResolution());
                                }
                                if (optionsList.getTimeBias() != -1)
                                    bw.write("time offset from UTC  : " + optionsList.getTimeBias());
                                if (!optionsList.getInterfaceFilter().equals(""))
                                    bw.write("interface filter      : " + optionsList.getInterfaceFilter());
                                if (!optionsList.getInterfaceOperatingSystem().equals(""))
                                    bw.write("interface OS name     : " + optionsList.getInterfaceOperatingSystem());
                                if (optionsList.getInterfaceFrameCheckSequenceLength() != -1)
                                    bw.write("interface FCS length  : " + optionsList
                                            .getInterfaceFrameCheckSequenceLength());
                                if (optionsList.getTimeStampOffset() != -1)
                                    bw.write("timestamp offset      : " + optionsList.getTimeStampOffset());
                                if (!optionsList.getComment().equals(""))
                                    bw.write("comment               : " + optionsList.getComment());
                            }
                            bw.write("##########################################################");
                        }
                        if (type instanceof IEnhancedPacketBLock){
                            IEnhancedPacketBLock ieType = (IEnhancedPacketBLock) type;
                            bw.write("SECTION ENHANCED PACKET BLOCK");
                            if (ieType.getInterfaceId() != -1)
                                bw.write("interface id             : " + ieType.getInterfaceId());

                            if (ieType.getTimeStamp() != -1) {
                                bw.write("timestamp in millis      : " + new Date(ieType.getTimeStamp()));
                            }
                            if (ieType.getCapturedLength() != -1)
                                bw.write("captured length          : " + ieType.getCapturedLength());
                            if (ieType.getPacketLength() != -1)
                                bw.write("packet length            : " + ieType.getPacketLength());
                            if (ieType.getPacketData() != null)
                                bw.write(UtilFunctions.byteArrayToStringMessage("packet data             ", ieType
                                        .getPacketData(), '|'));

                            IOptionsEnhancedPacketHeader optionsList = ieType.getOptions();

                            if (optionsList != null) {
                                if (optionsList.getPacketBound() != PacketBoundState.UNKNOWN)
                                    bw.write("packet bound state     : " + optionsList.getPacketBound());
                                if (optionsList.getPacketReceptionType() != PacketReceptionType.UNKNOWN)
                                    bw.write("packet reception type  : " + optionsList.getPacketReceptionType());
                                if (optionsList.getFrameCheckSumLength() != -1)
                                    bw.write("packet FCS length      : " + optionsList.getFrameCheckSumLength());
                                if (optionsList.getDropPacketCount() != -1)
                                    bw.write("packet drop count      : " + optionsList.getDropPacketCount());
                                if (optionsList.getPacketHashType() != PacketHashType.UNKNOWN)
                                    bw.write("packet hash type       : " + optionsList.getPacketHashType());
                                if (optionsList.getPacketHashBigEndian() != null)
                                    bw.write(UtilFunctions.byteArrayToStringMessage("packet hash type", optionsList
                                            .getPacketHashBigEndian(), '|'));

                                for (int j = 0; j < optionsList.getLinkLayerErrorList().size(); j++) {
                                    bw.write(optionsList.getLinkLayerErrorList().get(i) + " detected");
                                }
                            }
                            bw.write("##########################################################");
                        }
                        if (type instanceof IStatisticsBlock){
                            IStatisticsBlock isType = (IStatisticsBlock) type;
                            bw.write("SECTION INTERFACE STATISTICS BLOCK");

                            if (isType.getInterfaceId() != -1)
                                bw.write("interface id             : " + isType.getInterfaceId());

                            if (isType.getTimeStamp() != -1) {
                                bw.write("timestamp in millis      : " + new Date(isType.getTimeStamp()));
                            }

                            IOptionsStatisticsHeader optionsList = isType.getOptions();

                            if (optionsList != null) {
                                if (optionsList.getCaptureStartTime() != -1) {
                                    bw.write("capture start time       : " + new Date(optionsList
                                            .getCaptureStartTime()));
                                }
                                if (optionsList.getCaptureEndTime() != -1) {
                                    bw.write("capture end time         : " + new Date(optionsList.getCaptureEndTime
                                            ()));
                                }
                                if (optionsList.getPacketReceivedCount() != -1)
                                    bw.write("packet received count    : " + optionsList.getPacketReceivedCount());
                                if (optionsList.getPacketDropCount() != -1)
                                    bw.write("packet drop count        : " + optionsList.getPacketDropCount());
                                if (optionsList.getPacketAcceptedByFilterCount() != -1)
                                    bw.write("packet accepted by filter   : " + optionsList
                                            .getPacketAcceptedByFilterCount());
                                if (optionsList.getPacketDroppedByOS() != -1)
                                    bw.write("packet dropped by OS        : " + optionsList.getPacketDroppedByOS());
                                if (optionsList.getPacketDeliveredToUser() != -1)
                                    bw.write("packet delivered to user    : " + optionsList.getPacketDeliveredToUser());
                            }

                            bw.write("##########################################################");
                        }
                        if (type instanceof INameResolutionBlock){
                            INameResolutionBlock inType = (INameResolutionBlock) type;
                            bw.write("SECTION NAME RESOLUTION BLOCK");

                            if (inType.getRecords() != null) {
                                bw.write("IPV4 ENTRIES -------------------");
                                for (int j = 0; j < inType.getRecords().getIpv4DnsEntries().size(); j++) {
                                    bw.write(inType.getRecords().getIpv4DnsEntries().get(j).getIpAddr());

                                    /*for (int k = 0; k < inType.getRecords().getIpv4DnsEntries().get(j).getDnsEntries().size(); k++) {
                                        System.out.print("\t" + inType.getRecords().getIpv4DnsEntries().get(j).getDnsEntries().get
                                                (k) + ";");
                                    }*/
                                    bw.newLine();
                                }

                                bw.write("IPV6 ENTRIES -------------------");
                                for (int j = 0; j < inType.getRecords().getIpv6DnsEntries().size(); j++) {
                                    bw.write(inType.getRecords().getIpv6DnsEntries().get(j).getIpAddr());

                                    /*for (int k = 0; k < inType.getRecords().getIpv6DnsEntries().get(j).getDnsEntries().size(); k++) {
                                        System.out.print("\t" + inType.getRecords().getIpv6DnsEntries().get(j).getDnsEntries().get
                                                (k) + ";");
                                    }*/
                                    bw.newLine();
                                }
                            }

                            if (inType.getOptions() != null) {
                                if (!inType.getOptions().getDnsIpv4Addr().equals(""))
                                    bw.write("DNS IPV4 address    : " + inType.getOptions().getDnsIpv4Addr());
                                if (!inType.getOptions().getDnsIpv6Addr().equals(""))
                                    bw.write("DNS IPV6 address    : " + inType.getOptions().getDnsIpv6Addr());
                                if (!inType.getOptions().getDnsName().equals(""))
                                    bw.write("DNS SERVER NAME     : " + inType.getOptions().getDnsName());
                            }
                            bw.write ("##########################################################");
                        }
                    }
                }
            }
        }catch (IOException ee){
            ee.printStackTrace();
        }

        return new Result().ResultSuccess().toJsonString();
    }

    /**
     * 扫描结果存放在excel里面
     * @return
     */
    public String scan2(MultipartFile file) {
        String filename = StringUtils.cleanPath(CommonUtil.reqDate() + file.getOriginalFilename());

        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException("Cannot store file with relative path outside current directory " + filename);
            }
            Path path = this.getRootLocation().resolve(filename);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
        PcapHandle handle = null;
        String PCAP_FILE= upload_dir + filename;
        try {
            handle = Pcaps.openOffline(PCAP_FILE);
        } catch (PcapNativeException e) {
           e.printStackTrace();
        }
        String filePath = upload_pacp_dir+ DateUtils.getCurrentTimeStrDefault()+".txt";
        File f = new File(filePath);
        if (!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f),"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        };
        for (int i = 0; i < 5000; i++) {
            try {
                Packet packet = null;
                try {
                    packet = handle.getNextPacketEx();
                } catch (PcapNativeException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                } catch (NotOpenException e) {
                    e.printStackTrace();
                }
                if (packet == null) {
                    break;
                }
                // 可以直接get你想要的报文类型，只要Pcap4J库原生支持
                EthernetPacket ethernetPacket = packet.get(EthernetPacket.class); // 以太网报文
                EtherType eth_type = ethernetPacket.getHeader().getType();
                if (eth_type == EtherType.IPV4) {
                    IpV4Packet ipv4_packet = packet.get(IpV4Packet.class);
                    IpV4Packet.IpV4Header ipV4Packet_header = ipv4_packet.getHeader();
                    if (ipV4Packet_header.getProtocol() == IpNumber.TCP) {
                        TcpPacket tcp_packet = packet.get(TcpPacket.class);
                        TcpPacket.TcpHeader tcp_header = tcp_packet.getHeader();
                        if (!tcp_header.getPsh()) {
                            continue;
                        }
                        Inet4Address srcaddr = ipV4Packet_header.getSrcAddr();
                        Inet4Address dstaddr = ipV4Packet_header.getDstAddr();
                        TcpPort dstport = tcp_header.getDstPort();
                        TcpPort srcport = tcp_header.getSrcPort();
                        bw.write("src ip:" + srcaddr);
                        bw.write("src port:"+ srcport);
                        bw.write(",dst ip:"+dstaddr);
                        bw.write(",dst port:"+dstport);
                        bw.newLine();
                        }
                }
            }catch (IOException e){
                break;
            }
        }
        handle.close();
        try {
            bw.close();
        } catch (IOException e) {
            bw = null;
            e.printStackTrace();
        }
        return new Result().ResultSuccess().toJsonString();
    }

}
