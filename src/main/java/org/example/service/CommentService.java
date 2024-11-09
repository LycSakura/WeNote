package org.example.service;

import org.example.dao.CommentDao;
import org.example.entity.Comment;

import java.util.List;

public class CommentService {
    /**
     * @param noteID
     * @return List<Comment>
     * @description: 查询某篇笔记的所有评论
     */
    public List<Comment> fetchCommentListByNoteID(int noteID) {
        CommentDao commentDao = new CommentDao();
        return commentDao.selectCommentListByNoteID(noteID);
    }
    /**
     * @param noteID
     * @description: 删除谋篇笔记下的所有评论
     */
    public void deleteCommentByNoteID(int noteID) {
        CommentDao commentDao = new CommentDao();
        commentDao.deleteCommentByNoteID(noteID);
    }
}
