package com.lybank.service.system;

import com.lybank.entity.system.Dict;
import com.lybank.entity.system.QDict;
import com.lybank.repository.system.DictRep;
import com.lybank.util.Result;
import com.lybank.util.SpringCacheKeyGenerator;
import com.lybank.util.code.DictCode;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class DictService {

    @Autowired
    private DictRep dicRep;

    /**
     * 根据编号查询数据
     *
     * @param id
     * @return
     */
    public Dict getDictById(Long id) {
        return dicRep.findById(id).get();
    }


    //    @Cacheable(value = "listDict", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Page<Dict> listDict(Dict p, Pageable page) {
        QDict qp = QDict.dict;
        BooleanBuilder b = new BooleanBuilder();
        if (!StringUtils.isEmpty(p.getKey())) {
            b.and(qp.key.like("%" + p.getKey() + "%")).or(qp.value.like("%" + p.getKey() + "%"));
        }
        return dicRep.findAll(b, page);
    }

    /**
     * 通过ID查询数据
     *
     * @param id
     * @return
     */
    public Dict getDictByIdProcess(Long id) {
        Dict param = getDictById(id);
        return param;
    }

    /**
     * 批量删除方法
     *
     * @param ids
     * @return
     */
    @CacheEvict(value = "dict", allEntries = true)
    public Result batchDel(Long[] ids) {
        Result r = new Result();
        if (ids == null || ids.length == 0) {
            return r.ResultFail();
        }
        List<Long> idsList = Arrays.asList(ids);
        try {
            dicRep.deleteByIdIn(idsList);
            return r.ResultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return r.ResultFail();
        }
    }

    /**
     * 新增或修改方法
     *
     * @param param
     * @return
     */
    @CacheEvict(value = "dict", allEntries = true)
    public Result saveOrUpdate(Dict param) {
        Result r = new Result();
        if (param == null) {
            return r.ResultFail();
        }
        int count = 0;
        try {
            if (param.getId() != null) {
                count = dicRep.countByIdNotAndKey(param.getId(), param.getKey());
                if (count > 0) {
                    return r.ResultFail().setMsg("唯一编码重复添加!");
                }
                //修改
                //MyBeanUtils.copyPropertiesIgnoreNull(param, xxx);//param前台传来的信息， xxx数据库查询的信息
                dicRep.saveAndFlush(param);
            } else {
                count = dicRep.countByKey(param.getKey());
                if (count > 0) {
                    return r.ResultFail().setMsg("唯一编码重复添加!");
                }
                //新增
                dicRep.save(param);
            }
            return r.ResultSuccess();
        } catch (Exception e) {
            return r.ResultFail();
        }
    }

    @Cacheable(value = "dict", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Dict getLoginGoogleV() {
        return getDict(DictCode.LOGIN_GOOGLE_CODE);
    }

    @Cacheable(value = "dict", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Dict getDFGoogleV() {
        return getDict(DictCode.DF_GOOGLE_CODE);
    }

    @Cacheable(value = "dict", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public Dict getDict(String code) {
        return dicRep.findByKey(code);
    }
}
