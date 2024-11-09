package org.example.entity.dto;


import lombok.Data;
import org.example.entity.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
public class UserForm extends User {
    private String confirmPassword;
    private String oldPassword;
    private String autoLogin;
    private String checkCodeInput;
    private String checkCodeSession;

    public List<String> validate() {
        List<String> msgs = new ArrayList<>();
        if (userName != null) {
            if (userName.trim().length() > 20 || userName.trim().length() < 6) {
                msgs.add("用户名长度必须在6-20位之间");
            }
            Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
            Matcher matcher = pattern.matcher(userName);
            if (matcher.find()) {
                msgs.add("用户名不能包含中文");
            }
        }

        if (nickName != null) {
            if (nickName.trim().length() > 10 || nickName.trim().length() < 2) {
                msgs.add("昵称长度必须在2-10位之间");
            }
        }
        if (password != null) {
            if (password.trim().length() > 32 || password.trim().length() < 6) {
                msgs.add("密码长度必须在6-32位之间");
            }
        }
        if (confirmPassword != null) {
            if (confirmPassword.trim().length() > 32 || confirmPassword.trim().length() < 6) {
                msgs.add("确认密码长度必须在6-32位之间");
            }
        }
        if (confirmPassword != null && !Objects.equals(password, confirmPassword)) {
            msgs.add("两次输入的密码不一致");
        }
        if (telephone != null) {
            Pattern pattern = Pattern.compile("^1[346789]\\d{9}$");
            Matcher matcher = pattern.matcher(telephone.trim());
            if (!matcher.find()) {
                msgs.add("手机号格式不正确");
            }
        }
        if (brief != null) {
            if (brief.trim().length() > 24) {
                msgs.add("个人简介长度不能超24个字符");
            }
        }
        if (checkCodeInput != null) {
            if (!checkCodeInput.equalsIgnoreCase(checkCodeSession)) {
                msgs.add("验证码不正确");
            }
        }
        if (oldPassword != null) {
            if (oldPassword.trim().length() > 32 || oldPassword.trim().length() < 6) {
                msgs.add("旧密码长度6-32位");
            }
        }
        return msgs;
    }

    public String getTwiceMD5PasswordOfOldPassword() {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(oldPassword.getBytes());
            String first = new BigInteger(1, md5.digest()).toString(16);
            md5 = MessageDigest.getInstance("MD5");
            md5.update(first.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

