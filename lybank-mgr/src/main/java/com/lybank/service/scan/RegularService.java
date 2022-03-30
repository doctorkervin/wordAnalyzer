package com.lybank.service.scan;

import com.lybank.entity.scan.QRegular;
import com.lybank.entity.scan.Regular;
import com.lybank.repository.scan.RegularRep;
import com.lybank.service.BaseService;
import com.lybank.util.Result;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RegularService extends BaseService {
    @Autowired
    private RegularRep regularRep;

    /**
     * @param param
     * @param page
     * @return
     */
    public Page<Regular> getPageList(Regular param, Pageable page) {
        BooleanBuilder b = new BooleanBuilder();
        QRegular q = QRegular.regular;
        if (!StringUtils.isEmpty(param.getFuzzyValue())) {
            b.or(q.name.like("%" + param.getFuzzyValue() + "%"));
        }
        return regularRep.findAll(b, page);
    }

    /**
     * @param id
     * @return
     */
    public Regular findById(Long id) {
        return regularRep.getOne(id);
    }

    /**
     * @param r
     * @return
     */
    public Result save(Regular r) {
        try {
            Date date = new Date();
            Long userId = getUserId();
            r.setCreateId(userId);
            r.setOperaterId(userId);
            r.setCreateTime(date);
            r.setOperaterTime(date);
            regularRep.save(r);
        } catch (Exception e) {
            return new Result().ResultFail();
        }
        return new Result().ResultSuccess();
    }

    /**
     *
     */
    public List<Regular> findAll() {
        return regularRep.findAll();
    }

    /**
     *修改
     */
    public Result update(Regular r) {
        try {
            Regular one = regularRep.getOne(r.getId());
            Date date = new Date();
            Long userId = getUserId();
            one.setName(r.getName());
            one.setType(r.getType());
            one.setValue(r.getValue());
            one.setCode(r.getCode());
            one.setOperaterId(userId);
            one.setOperaterTime(date);
            regularRep.saveAndFlush(one);
        } catch (Exception e) {
            return new Result().ResultFail();
        }
        return new Result().ResultSuccess();
    }
}
