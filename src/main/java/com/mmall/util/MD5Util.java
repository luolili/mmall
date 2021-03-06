package com.mmall.util;

import java.security.MessageDigest;

public class MD5Util {
    private static final String hexDigits[] = {"0","1","2","3","4","5","6",
            "7","8","9","a",
            "b","c","d","e","f"

    };
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n +=256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1]+hexDigits[d2];
    }

    private static String byteArrayToHexString(byte b[]){
        StringBuffer resultSb = new StringBuffer();
        for (byte b1 : b) {
            resultSb.append(byteToHexString(b1));
        }
        return resultSb.toString();
    }

    private static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try{
            resultString = new String(origin);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //字符集
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md5.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md5.digest(resultString.getBytes(charsetname)));
            }

        }catch(Exception e){

        }
        return resultString.toUpperCase();
    }

    public static String MD5EncodeUtf8(String origin) {
        return MD5Encode(origin, "utf-8");
    }
}

