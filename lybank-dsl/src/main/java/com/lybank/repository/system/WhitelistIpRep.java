package com.lybank.repository.system;

import com.lybank.entity.system.WhitelistIp;
import com.lybank.repository.BaseRep;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 阿布
 * @ClassName com.lybank.repository.system.WhitelistIpRep
 * @Description 商户白名单IP限制dao接口类
 * @Date 2019-02-18
 * @Version 1.0
 */
public interface WhitelistIpRep extends BaseRep<WhitelistIp, Long> {

    /**
     * 根据商户ID查询数据集合
     *
     * @param merchantCode 商户代码
     * @return
     */
    public List<WhitelistIp> findByMerchantCode(String merchantCode);

    /**
     * 根据编号集合删除数据
     *
     * @param list
     * @return
     */
    @Transactional
    public int deleteByIdIn(List<Long> list);


    /**
     * @Titel 根据IP和类型查询IP信息
     * @Description 根据IP和类型查询IP信息
     * @Author ABu
     * @DateTime 2019/7/29 13:29
     * @param ip 1 ip
     * @param type 2 类型
     * @return WhitelistIp
     */
    WhitelistIp findByIpAndType(String ip, Integer type);
}
