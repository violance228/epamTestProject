package com.violence.util.api.parser;

import com.violence.util.api.annotation.ReflectionApi;
import com.violence.util.api.annotation.ReflectionApiImpl;

import javax.servlet.http.HttpServletRequest;

public class ObjectParserFromReqImpl implements ObjectParserFromReq {
    private ReflectionApi reflectionApi = new ReflectionApiImpl();

    public Object getObjectFromRequest(HttpServletRequest request, Class aClass) {
        return reflectionApi.getObjectFromRequest(request, aClass);
    }
}
