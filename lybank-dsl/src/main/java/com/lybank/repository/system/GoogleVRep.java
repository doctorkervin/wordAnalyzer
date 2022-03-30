package com.lybank.repository.system;

import com.lybank.entity.system.GoogleV;
import com.lybank.repository.BaseRep;

public interface GoogleVRep extends BaseRep<GoogleV, Long> {

    GoogleV findOneByUserName(String userName);
}
