package com.a.SSH.common.util;

/**
 * Created by zh_ge on 2017/3/30.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String transformMD5(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(text.getBytes());
            byte b[] = md.digest();
            int x;
            StringBuffer buf = new StringBuffer("");
            for (int i = 0; i < b.length; i++) {
                x = b[i];
                if (x < 0)
                    x += 256;
                if (x < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(x));
            }
            return buf.toString().substring(8,24);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
