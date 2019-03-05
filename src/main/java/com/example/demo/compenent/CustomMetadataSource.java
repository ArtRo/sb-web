package com.example.demo.compenent;

import com.example.demo.service.PrFuncsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;
import java.util.List;

/**
 * Created by dhf_ndm on 2019/2/27
 * the previous bug derived from the previous
 */
@Deprecated
//@Component
public class CustomMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    PrFuncsService prFuncsService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //TODO 获取能够访问这个路径的权限

        //没有匹配上的资源，都是随意访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}

