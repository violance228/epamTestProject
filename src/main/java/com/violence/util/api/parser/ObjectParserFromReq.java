package com.violence.util.api.parser;

import javax.servlet.http.HttpServletRequest;

public interface ObjectParserFromReq {
    Object getObjectFromRequest(HttpServletRequest request, Class aClass);
}
