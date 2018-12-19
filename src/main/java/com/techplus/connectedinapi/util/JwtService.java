package com.techplus.connectedinapi.util;

import org.apache.tomcat.util.codec.binary.Base64;

public class JwtService {

    public String decode(String jwt){
        String[] split_string = jwt.split("\\.");
        String base64EncodedBody = split_string[1];
        Base64 base64Url = new Base64(true);
        return new String(base64Url.decode(base64EncodedBody));
    }

}
