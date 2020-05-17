package daichongweb.blog.util;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Helper {

    // md5加密
    public String md5(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(str.getBytes());
        byte[] llll = md.digest();
        BigInteger bigInteger = new BigInteger(1, llll);
        String tmp;
        for (tmp = bigInteger.toString(16); tmp.length() < 32; tmp = "0" + tmp) {
            ;
        }
        return tmp;
    }
}
