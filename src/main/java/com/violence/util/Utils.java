package com.violence.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

public class Utils {

    public static final SimpleDateFormat dateFromHtml = new SimpleDateFormat("yyyy-MM-dd");

    public static Long getUserIdBySession(HttpSession session) {
        return session.getAttribute("user_id") != null ? Long.valueOf(session.getAttribute("user_id").toString()) : 0;
    }

    public static Long getLongParamFromReq(HttpServletRequest httpServletRequest, String attr) {
        return httpServletRequest.getParameter(attr) != null ? Long.valueOf(httpServletRequest.getParameter(attr)) : 0;
    }

    public static String getStringParamFromReq(HttpServletRequest httpServletRequest, String attr) {
        return httpServletRequest.getParameter(attr) != null ? httpServletRequest.getParameter(attr) : "";
    }

    public static String getUserRoleBySession(HttpServletRequest session) {
        return session.getSession().getAttribute("user_role") != null ? session.getSession().getAttribute("user_role").toString() : "";
    }
}
