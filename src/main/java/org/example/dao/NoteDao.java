package org.example.dao;

import org.example.entity.Note;
import org.example.entity.dto.NoteForm;
import org.example.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoteDao {
    /**
     * @return Map<Integer>
     * @description: 查询所有笔记类别及类别对应的笔记篇幅
     */
    public Map<String, Integer> selectCategoryNameAndNoteNum() throws Exception {
        Map<String, Integer> categoryNameMap = new HashMap<>();
        Connection connection = DBUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select categoryName,count(*) as num from note group by categoryName";
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String categoryName = resultSet.getString("categoryName");
                int num = resultSet.getInt("num");
                categoryNameMap.put(categoryName, num);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return categoryNameMap;
    }

    /**
     * @return List<Note>
     * @description: 查询所有笔记
     */
    public List<Note> selectAllNote() {
        List<Note> noteList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from note order by createTime desc";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Note note = new Note();
                note.setNoteID(resultSet.getInt("noteID"));
                note.setAuthor(resultSet.getString("author"));
                note.setNoteTitle(resultSet.getString("noteTitle"));
                note.setNoteContent(resultSet.getString("noteContent"));
                note.setVisit(resultSet.getInt("visit"));
                note.setCategoryName(resultSet.getString("categoryName"));
                note.setCreateTime(resultSet.getString("createTime"));
                note.setUpdateTime(resultSet.getString("updateTime"));
                noteList.add(note);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return noteList;
    }

    /**
     * @return List<String>
     * @description: 查询所有笔记类别的名字
     */
    public List<String> selectCategroyNameList() {
        List<String> categoryNameList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select distinct categoryName from note";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String categoryName = resultSet.getString("categoryName");
                categoryNameList.add(categoryName);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return categoryNameList;
    }

    /**
     * @param noteForm 笔记表单
     * @return int
     * @description: 添加笔记
     */
    public int insert(NoteForm noteForm) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "insert into note values(null,?,?,?,0,?,default,null)";
        int insertID = 0;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, noteForm.getAuthor());
            preparedStatement.setString(2, noteForm.getNoteTitle());
            preparedStatement.setString(3, noteForm.getNoteContent());
            preparedStatement.setString(4, noteForm.getCategoryName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                insertID = resultSet.getInt(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return insertID;
    }

    /**
     * @param noteID 笔记ID
     * @return void
     * @description: 更新笔记的浏览量
     */
    public void updateVisit(int noteID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update note set visit=visit+1 where noteID=?";
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

    /**
     * @param noteID 笔记ID
     * @return Note
     * @description: 根据笔记ID查询笔记
     */
    public Note selectByNoteID(int noteID) {
        Note note = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from note where noteID=?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, noteID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                note = new Note();
                note.setNoteID(resultSet.getInt("noteID"));
                note.setAuthor(resultSet.getString("author"));
                note.setNoteTitle(resultSet.getString("noteTitle"));
                note.setNoteContent(resultSet.getString("noteContent"));
                note.setVisit(resultSet.getInt("visit"));
                note.setCategoryName(resultSet.getString("categoryName"));
                note.setCreateTime(resultSet.getString("createTime"));
                note.setUpdateTime(resultSet.getString("updateTime"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return note;
    }

    /**
     * @param noteID 笔记ID
     * @param flag   标志，大于或小于
     * @return Note
     * @description: 根据笔记ID查询上一篇或下一篇笔记
     */
    public Note selectNextOrLastByNoteID(int noteID, String flag) {
        Note note = null;
        String sql = "";
        if (">".equals(flag)) {
            sql = "select * from note where noteID > ? order by noteID asc limit 1";
        } else {
            sql = "select * from note where noteID < ? order by noteID desc limit 1";
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, noteID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                note = new Note();
                note.setNoteID(resultSet.getInt("noteID"));
                note.setAuthor(resultSet.getString("author"));
                note.setNoteTitle(resultSet.getString("noteTitle"));
                note.setNoteContent(resultSet.getString("noteContent"));
                note.setVisit(resultSet.getInt("visit"));
                note.setCategoryName(resultSet.getString("categoryName"));
                note.setCreateTime(resultSet.getString("createTime"));
                note.setUpdateTime(resultSet.getString("updateTime"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return note;
    }


    /**
     * @param note
     * @description: 更新笔记
     */
    public void updateNote(Note note) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update note set author=?,noteTitle=?,noteContent=?,categoryName=?,updateTime=now() where noteID=?";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, note.getAuthor());
            preparedStatement.setString(2, note.getNoteTitle());
            preparedStatement.setString(3, note.getNoteContent());
            preparedStatement.setString(4, note.getCategoryName());
            preparedStatement.setInt(5, note.getNoteID());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, null);
        }
    }

    /**
     * @param noteID
     * @description: 根据笔记ID删除笔记
     */
    public void deleteNoteByNoteID(int noteID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "delete from note where noteID=?";
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

    /**
     * @param keyword 关键词
     * @return List<Note>
     * @description: 根据关键词查询笔记
     */
    public List<Note> selectAllNoteByKeyword(String keyword) {
        List<Note> noteListByKeyword = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from note where noteTitle like ? or noteContent like ? order by createTime desc";
        try {
            connection = DBUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Note note = new Note();
                note.setNoteID(resultSet.getInt("noteID"));
                note.setAuthor(resultSet.getString("author"));
                note.setNoteTitle(resultSet.getString("noteTitle"));
                note.setNoteContent(resultSet.getString("noteContent"));
                note.setVisit(resultSet.getInt("visit"));
                note.setCategoryName(resultSet.getString("categoryName"));
                note.setCreateTime(resultSet.getString("createTime"));
                note.setUpdateTime(resultSet.getString("updateTime"));
                noteListByKeyword.add(note);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(connection, preparedStatement, resultSet);
        }
        return noteListByKeyword;
    }
}
