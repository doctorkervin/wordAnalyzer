package com.lybank.service.system;

import com.lybank.entity.system.QWhitelistIp;
import com.lybank.entity.system.WhitelistIp;
import com.lybank.repository.system.WhitelistIpRep;
import com.lybank.service.BaseService;
import com.lybank.util.Result;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author 阿布
 * @ClassName com.lybank.service.system.WhitelistIpService
 * @Description 商户白名单IP限制业务接口类
 * @Date 2019-02-18
 * @Version 1.0
 */
@Service
public class WhitelistIpService extends BaseService {
    @Autowired
    private WhitelistIpRep whitelistIpRep;

    /**
     * 分页查询
     *
     * @return
     */
    public Page<WhitelistIp> getPageList(WhitelistIp p, Pageable page) {
        QWhitelistIp qp = QWhitelistIp.whitelistIp;
        BooleanBuilder b = new BooleanBuilder();
        if (!StringUtils.isEmpty(p.getFuzzyValue())) {
            b.and(qp.merchantCode.like("%" + p.getFuzzyValue() + "%")).or(qp.ip.like("%" + p.getFuzzyValue() + "%"));
        }
        if (!StringUtils.isEmpty(p.getIp())) {
            b.and(qp.ip.eq(p.getIp()));
        }
        return whitelistIpRep.findAll(b, page);

    }

    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public WhitelistIp getById(Long id) {
        return whitelistIpRep.findById(id).get();
    }

    /**
     * 根据编号集合删除数据
     *
     * @param idsList
     * @return
     */
    @Transactional
    public Result batchDel(List<Long> idsList) {
        Result r = new Result();
        try {
            whitelistIpRep.deleteByIdIn(idsList);
            return r.ResultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResultFail();
        }
    }

    /**
     * 添加或修改
     *
     * @param param
     */
    @Transactional
    public Result saveOrUpdate(WhitelistIp param) {
        Result r = new Result();
        if (param == null) {
            return r.ResultFail();
        }
        Date date = new Date();
        Long userId = getUserId();
        param.setOperaterId(userId);
        param.setOperaterTime(date);
        if (param.getId() != null && param.getId() > 0) {
            param.setStatus(2);
            try {
                WhitelistIp whitelistIp = this.getById(param.getId());
                param.setCreateTime(whitelistIp.getCreateTime());
                whitelistIpRep.saveAndFlush(param);
                return r.ResultSuccess();
            } catch (Exception e) {
                e.printStackTrace();
                return r.ResultFail();
            }
        }
        param.setCreateId(userId);
        param.setStatus(1);
        param.setCreateTime(date);
        try {
            whitelistIpRep.save(param);
            return r.ResultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResultFail();
        }
    }

    /**
     * 根据商户ID查询限制ip集合
     *
     * @param merchantCode
     * @return
     */
    public List<WhitelistIp> findByMerchantCodeList(String merchantCode) {
        return whitelistIpRep.findByMerchantCode(merchantCode);
    }

    /**
     * 根据编号查询
     *
     * @param id
     * @return
     */
    public WhitelistIp getWhitelistIpByIdProcess(Long id) {
        WhitelistIp param = getById(id);
        return param;
    }

    /**
     * @param ip   1 ip
     * @param type 2 类型
     * @return WhitelistIp
     * @Titel 根据IP和类型查询IP信息
     * @Description 根据IP和类型查询IP信息
     * @Author ABu
     * @DateTime 2019/7/29 13:29
     */
//    @CacheEvict(value = "whitelistIp", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public WhitelistIp findByIpAndType(String ip, Integer type) {
        return whitelistIpRep.findByIpAndType(ip, type);
    }
}
