package com.tragent.inventory.auth.service;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class TokenService {

    private static final Cache restApiAuthTokenCache = CacheManager.getInstance().getCache("restApiAuthTokenCache");

    public void evictTokens() {
        restApiAuthTokenCache.removeAll();
    }

    public String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    public void store(String token, Authentication authentication) {
        restApiAuthTokenCache.put(new Element(token, authentication));
    }

    public boolean contains(String token) {
        return restApiAuthTokenCache.get(token) != null;
    }

    public Authentication retrieve(String token) {
        return (Authentication) restApiAuthTokenCache.get(token).getObjectValue();
    }
    
}
