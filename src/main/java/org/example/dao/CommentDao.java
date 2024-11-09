package org.example.dao;

import org.example.entity.Comment;
import org.example.entity.User;
import org.example.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    /**
     * @param noteID
     * @return List<Comment>
     * @description: 根据笔记ID查询评论
     */
    public List<Comment> selectCommentListByNoteID(int noteID) {
        List<Comment> commentList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select commentID,commentTitle,commentContent,comment.createTime createTime,remoteIP,noteID,comment.userName username,nickName,photo,brief" +
                " from comment join users on comment.userName=users.userName where noteID=? order by createTime desc";
        Comment comment = null;
        User user = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, noteID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = new Comment();
                comment.setCommentID(resultSet.getInt("commentID"));
                comment.setCommentTitle(resultSet.getString("commentTitle"));
                comment.setCommentContent(resultSet.getString("commentContent"));
                comment.setCreateTime(resultSet.getString("createTime"));
                comment.setRemoteIP(resultSet.getString("remoteIP"));
                comment.setNoteID(resultSet.getInt("noteID"));
                comment.setUserName(resultSet.getString("username"));
                user = new User();
                user.setUserName(resultSet.getString("username"));
                user.setNickName(resultSet.getString("nickName"));
                user.setPhoto(resultSet.getString("photo"));
                user.setBrief(resultSet.getString("brief"));
                comment.setUser(user);
                commentList.add(comment);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return commentList;
    }
    /**
     * @param noteID
     * @return void
     * @description: 根据笔记ID删除评论
     */
    public void deleteCommentByNoteID(int noteID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "delete from comment where noteID=?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, noteID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, null);
        }
    }
}
