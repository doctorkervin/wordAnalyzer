package com.lybank.repository.system;

import com.lybank.entity.system.Dict;
import com.lybank.repository.BaseRep;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DictRep extends BaseRep<Dict, Long> {

    /**
     * 根据key查询
     *
     * @param key
     * @return
     */
    public Dict findByKey(String key);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    @Transactional //事物注解
    public int deleteByIdIn(List<Long> idList);

    /**
     * 根据唯一编码查询是否存在相同
     *
     * @param key
     * @return
     */
    public int countByKey(String key);

    /**
     * 根据编号和唯一编码查询是否存在
     *
     * @param id
     * @param key
     * @return
     */
    public int countByIdNotAndKey(Long id, String key);


}
