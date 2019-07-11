package com.amazon.basics.controller.security.privacy;

import javax.servlet.http.Cookie;

public class Privacy {

    public Cookie checkCookieSecurity (String sessionID) {
        Cookie cookie = new Cookie("sessionID",sessionID);
        cookie.setSecure(false); // Should be set to true so that cookie is always sent over https
        cookie.setHttpOnly(false); // Should be set to true so that cookie is always sent over an http connection
        return cookie;
//        response.addCookie(cookie);
    }
}
