package com.lybank.test;

import com.lybank.util.Validation;
import org.junit.jupiter.api.Test;
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

import java.io.EOFException;
import java.net.Inet4Address;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final int COUNT = 5000;
    private static String file = "/Users/mac/Downloads/xxxx.pcapng";
    private static final String PCAP_FILE_KEY = Main.class.getName() + ".pcapFile";
    private static final String PCAP_FILE =
            System.getProperty(PCAP_FILE_KEY, file);

    private Main() {
    }

    public static void main(String[] args) throws PcapNativeException, NotOpenException {
        PcapHandle handle;
        try {
            System.out.println(PCAP_FILE);
            handle = Pcaps.openOffline(PCAP_FILE);
        } catch (PcapNativeException e) {
            handle = Pcaps.openOffline(PCAP_FILE);
        }

        for (int i = 0; i < COUNT; i++) {
            try {
                Packet packet = handle.getNextPacketEx();
                if (packet == null) {
                    System.out.println("packet is null");
                    break;
                }
                // 可以直接get你想要的报文类型，只要Pcap4J库原生支持
                EthernetPacket ethernetPacket = packet.get(EthernetPacket.class); // 以太网报文
                EtherType eth_type = ethernetPacket.getHeader().getType();
                //System.out.println(i);
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
                        System.out.println(i);
                        System.out.println("seqno=" + i + ",（src ip,dst ip, src port,dst port):(" + srcaddr + "," + dstaddr + "," + srcport + "," + dstport + ")");
                        String tcpdata = byte2String(ipv4_packet.getPayload().getRawData());
                        System.out.println("tcp data is:" + tcpdata);

                    }

                } else {

                }
            } catch (TimeoutException e) {
            } catch (EOFException e) {
                System.out.println("EOF");
                break;
            }
        }

        handle.close();
    }

    static String byte2String(byte[] bytes){
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i <bytes.length ; i++) {
            if (bytes[i]!=0){
                strBuilder.append((char)bytes[i]);
            }else {
                break;
            }

        }
        return strBuilder.toString();
    }

    @Test
    public void testID(){
        //testOfficerID();
        //testBankCard();
        //testHuZhao();
        //testSheHuiXinYong();
        //testZuZhiJiGou();
        //testYingYeZhiZhao();
        //testSFZH();
        //testYouZhengBianHao();
        //testIPV4();
        //testIPV6();
        //testMac();
        //testDate();
        //testGangAo();
        //testPhone();
        //testMobile();
        //testEmail();
        //testTW();
        //testUrl();
        //testCarID();
        //testCarVin();
        //testSex();
        testDateChina();
    }

    /**
     * test 2021年01月12日
     */
    private void testDateChina() {
        String regexOfficerCard = Validation.DATE_CHINA;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "2021年01月12日";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    /**
     * 发现匹配一行String，含有多个结果它只返回一个结果
     */
    private void testSex() {
        String regexOfficerCard = Validation.SEX;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "lily 女 mary 男";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testCarVin() {
        String regexOfficerCard = Validation.CAR_ID;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "LBV8V3106GMG03526";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testCarID() {
        String regexOfficerCard = Validation.CAR_ID;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "Y沪A00001";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testUrl() {
        String regexOfficerCard = Validation.URL;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "https://www.baidu.com";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }
    private void testTW() {
        String regexOfficerCard = Validation.REGEX_TW_CARD;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "12345678";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }
    private void testEmail() {
        String regexOfficerCard = Validation.EMAIL;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "wensdf@143.com";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testMobile() {
        String regexOfficerCard = Validation.MOBILE;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "13277869876";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }
    private void testPhone() {
        String regexOfficerCard = Validation.PHONE;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "0728-4701654";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testGangAo() {
        String regexOfficerCard = Validation.REGEX_HK_CARD;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "H1234567890";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testDate() {
        String regexOfficerCard = Validation.DATE_ALL;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "2021-01-21";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testMac() {
        String regexOfficerCard = Validation.patternMac;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "48-5D-60-61-3D-C5";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }
    private void testIPV6() {
        String regexOfficerCard = Validation.IPV6;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "2001:3CA1:010F:001A:121B:0000:0000:0010";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }
    private void testIPV4() {
        String regexOfficerCard = Validation.IPV4;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "128.231.43.21";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testYouZhengBianHao() {
        String regexOfficerCard = Validation.CODE;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "430000";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testSFZH() {
        String regexOfficerCard = Validation.IDCARD;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "429006197603218976";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testYingYeZhiZhao() {
        String regexOfficerCard = Validation.BUSINESS_CODE;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "913505215532300332";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testZuZhiJiGou() {
        String regexOfficerCard = Validation.JIGOU_CODE;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "68823369-9";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testSheHuiXinYong() {
        String regexOfficerCard = Validation.SOCIETY_CODE;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "913505215532300332";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    private void testHuZhao() {
        String regexOfficerCard = Validation.REGEX_PASSPORT_CARD;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "P1234567";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    //bankCard
    private void testBankCard() {
        String regexOfficerCard = Validation.REGEX_BANKCARD;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "6222081812002934027";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }

    //verify 军官证件号
    private void testOfficerID() {
        String regexOfficerCard = Validation.REGEX_OFFICER_CARD;
        Pattern p = Pattern.compile(regexOfficerCard);
        String str = "军字第2001988号 士字第P011816X号";
        Matcher m = p.matcher(str);
        while(m.find()){
            System.out.println(m.group(0));
        }
    }
}
