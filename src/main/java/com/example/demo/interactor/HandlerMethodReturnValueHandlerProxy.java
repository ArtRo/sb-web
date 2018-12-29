package com.example.demo.interactor;


import com.example.demo.util.InfoCode;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

//TODO 数据格式化

public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {
    private HandlerMethodReturnValueHandler proxyObject;

    public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        resultMap.put("code", InfoCode.REQUEST_OK.getCode());
        resultMap.put("msg", InfoCode.REQUEST_OK.getDesc());
        resultMap.put("data", returnValue);

        proxyObject.handleReturnValue(resultMap, returnType, mavContainer, webRequest);
    }
}
