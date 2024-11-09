package org.example.dao;

import org.example.entity.User;
import org.example.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    /**
     * @param userName 用户名
     * @return boolean
     * @description: 判断用户名是否已存在
     */
    public boolean selectByUserName(String userName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from users where userName=?";
        boolean isExist = false;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isExist = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return isExist;
    }

    /**
     * @param user 用户信息
     * @description: 新增用户
     */
    public void insert(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into users values(?,?,?,?,default,default,?,default)";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getNickName());
            preparedStatement.setString(3, user.getTwiceMD5Password());
            preparedStatement.setString(4, user.getTelephone());
            preparedStatement.setString(5, user.getBrief());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, null);
        }
    }

    /**
     * @param userName 用户名
     * @param password 密码
     * @return User
     * @description: 根据用户名和密码查询用户信息
     */
    public User selectByUserAndPassword(String userName, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from users where userName=? and password=?";
        User user = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserName(resultSet.getString("userName"));
                user.setBrief(resultSet.getString("brief"));
                user.setCreateTime(resultSet.getString("createTime"));
                user.setIsAuthor(resultSet.getString("isAuthor"));
                user.setNickName(resultSet.getString("nickName"));
                user.setPhoto(resultSet.getString("photo"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return user;
    }
    /**
     * @param userName 用户名
     * @param fileName 文件名
     * @description: 更新用户头像
     */
    public void updateUserPhoto(String userName, String fileName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update users set photo=? where userName=?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, fileName);
            preparedStatement.setString(2, userName);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBUtils.close(connection, preparedStatement, null);
        }

    }
    /**
     * @param userName 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return int
     * @description: 修改密码
     */
    public int updatePassword(String userName, String oldPassword, String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update users set password=? where userName=? and password=?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, oldPassword);
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            DBUtils.close(connection, preparedStatement, null);
        }
    }
}
