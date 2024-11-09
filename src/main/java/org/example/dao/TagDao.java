package org.example.dao;

import org.example.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDao {
    /**
     * @return Map<Integer>
     * @description: 查询所有笔记标签和标签对应的笔记数量
     */
    public Map<String, Integer> selectTagNameAndNoteNum() throws Exception {
        Map<String, Integer> tagNameMap = new HashMap<>();
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select tagName,count(*) as num from tag join note on tag.noteID=note.noteID group by tagName";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tagName = resultSet.getString("tagName");
                int num = resultSet.getInt("num");
                tagNameMap.put(tagName, num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return null;
    }

    /**
     * @return List<String>
     * @description: 查询所有笔记标签名称
     */
    public List<String> selectAllTagName() throws Exception {
        List<String> tagNameList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select distinct tagName from tag";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tagName = resultSet.getString("tagName");
                tagNameList.add(tagName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return tagNameList;
    }

    /**
     * @param tagName
     * @param noteID
     * @description: 插入笔记标签
     */
    public void insert(String tagName, int noteID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into tag values(?,?)";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tagName);
            preparedStatement.setInt(2, noteID);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, null);
        }
    }
    /**
     * @param noteID
     * @return List<String>
     * @description: 根据笔记ID查询所有笔记标签
     */
    public List<String> selectTagNameListByNoteID(int noteID){
        List<String> tagNameList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select tagName from tag where noteID=?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, noteID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tagName = resultSet.getString("tagName");
                tagNameList.add(tagName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return tagNameList;
    }
    /**
     * @param noteID
     * @description: 根据笔记ID删除笔记标签
     */
    public void deleteTagByNoteID(Integer noteID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "delete from tag where noteID=?";
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
