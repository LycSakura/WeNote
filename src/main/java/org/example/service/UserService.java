package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;

public class UserService {
    /**
     * @param userName 用户名
     * @return boolean
     * @description: 判断用户名是否已存在
     */
    public boolean isExists(String userName) throws Exception {
        return new UserDao().selectByUserName(userName);
    }
    /**
     * @param user 用户信息
     * @description: 用户注册
     */
    public void register(User user) {
        UserDao userDao = new UserDao();
        if (!userDao.selectByUserName(user.getUserName())){
            userDao.insert(user);
        }
    }
    /**
     * @param userName 用户名
     * @param password 密码
     * @return User
     * @description: 用户登录
     */
    public User login(String userName, String password) {
        return new UserDao().selectByUserAndPassword(userName, password);
    }
    /**
     * @param userName 用户名
     * @param fileName 文件名
     * @description: 修改用户头像
     */
    public void changeUserPhoto(String userName, String fileName) {
        new UserDao().updateUserPhoto(userName, fileName);
    }
    /**
     * @param userName 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return int
     * @description: 修改密码
     */
    public int changePassword(String userName, String oldPassword, String newPassword) {
        return new UserDao().updatePassword(userName, oldPassword, newPassword);
    }
}
