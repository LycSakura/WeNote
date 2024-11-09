package org.example.entity;

import lombok.Data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
public class User {
    protected String userName;
    protected String nickName;
    protected String password;
    protected String telephone;
    protected String photo;
    protected String isAuthor;
    protected String brief;
    protected String createTime;

    public String getOnceMD5Password() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTwiceMD5Password() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(getOnceMD5Password().getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
