package com.lybank.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.ehcache.CacheManager;

@Service
public class CacheService {

	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * 清除缓存
	 */
	public void clearAllCache() {
		cacheManager.clearAll();
		System.out.println("clear All Cache");
	}
}
