package com.lybank.service.system;

import com.lybank.config.validate.GoogleAuthenticator;
import com.lybank.entity.system.GoogleV;
import com.lybank.repository.system.GoogleVRep;
import com.lybank.util.MyBeanUtils;
import com.lybank.util.SpringCacheKeyGenerator;
import com.lybank.util.code.DictCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoogleVService {
    @Autowired
    private GoogleVRep googleRep;

    /**
     * 查找用户secret
     *
     * @param userName
     * @return
     */
    @Cacheable(value = "googlev", keyGenerator = SpringCacheKeyGenerator.KEYGENERATOR)
    public GoogleV getGoogleVByUserName(String userName) {

        return googleRep.findOneByUserName(userName);
    }

    @Transactional
    @CacheEvict(value = "googlev", allEntries = true)
    public GoogleV saveGoogleV(GoogleV gv) {
        if (gv.getUserName() != null) {
            GoogleV gvv = googleRep.findOneByUserName(gv.getUserName());
            if (gvv != null) {
                MyBeanUtils.copyPropertiesIgnoreNull(gv, gvv);
                googleRep.save(gvv);
            }
        }
        return googleRep.save(gv);
    }

    @CacheEvict(value = "googlev", allEntries = true)
    public GoogleV updateGoogleV(GoogleV gv) {
        String secret = GoogleAuthenticator.generateSecretKey();
        String qrcode = GoogleAuthenticator.getQRBarcode(gv.getUserName(), secret);
        gv.setQrcodeStr(qrcode);
        gv.setSecret(secret);
        gv.setStatus(DictCode.ENABLE);
        GoogleV v = googleRep.findOneByUserName(gv.getUserName());
        if (v != null) {
            MyBeanUtils.copyPropertiesIgnoreNull(gv, v);
            return gv = googleRep.saveAndFlush(v);
        }
        return googleRep.saveAndFlush(gv);

    }
}
