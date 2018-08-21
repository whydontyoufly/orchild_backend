package com.orchid.tools;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * Created by ljg on 2018/5/4.
 */
public class PasswordUtils {
    public static String encrypt(String accountName,String password){
        String salt = "orchid"+new StringBuilder(accountName).reverse().toString();
        ByteSource credentialsSalt = ByteSource.Util.bytes(salt);
        String res = String.valueOf(new SimpleHash("MD5",password,credentialsSalt,1));
        return res;
    }
}
