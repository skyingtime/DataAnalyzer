package com.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by yangliu on 11/12/2016.
 */
@Component
public class WebAccessVoter implements AccessDecisionVoter {
    @Override
    public int vote(Authentication authentication, Object o, Collection collection) {
        return ACCESS_GRANTED;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class aClass) {
        return false;
    }
}
