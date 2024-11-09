package org.example.service;

import org.example.dao.NoteDao;
import org.example.entity.Note;
import org.example.entity.dto.NoteForm;

import java.util.List;
import java.util.Map;

public class NoteService {
    /**
     * @return Map<Integer>
     * @description: 获取所有笔记类别及类别对应的笔记篇幅
     */
    public Map<String, Integer> fetchCategoryNameAndNoteNum() throws Exception {
        return new NoteDao().selectCategoryNameAndNoteNum();
    }
    /**
     * @return List<Note>
     * @description: 获取所有笔记
     */
    public List<Note> fetchAllNote() throws Exception {
        return new NoteDao().selectAllNote();
    }
    /**
     * @return List<String>
     * @description: 获取所有笔记类别名
     */
    public List<String> fetchCategoryNameList() throws Exception {
        return new NoteDao().selectCategroyNameList();
    }
    /**
     * @return int
     * @description: 保存笔记
     */
    public int saveNote(NoteForm noteForm) {
        return new NoteDao().insert(noteForm);
    }
    /**
     * @return Note
     * @description: 为谋篇笔记增加访问量
     */
    public void increaseVisit(int noteID) {
        new NoteDao().updateVisit(noteID);
    }
    /**
     * @return int
     * @description: 查询谋篇笔记的详细信息
     */
    public Note fetchNoteByNoteID(int noteID) {
        return new NoteDao().selectByNoteID(noteID);
    }
    /**
     * @return Note
     * @description: 查询谋篇笔记上一篇和下一篇
     */
    public Note fetchNextOrLastNoteByNoteID(int noteID, String flag) {
        return new NoteDao().selectNextOrLastByNoteID(noteID, flag);
    }
    /**
     * @Param note
     * @description: 更新谋篇笔记
     */
    public void changeNote(Note note) {
        new NoteDao().updateNote(note);
    }
    /**
     * @Param noteID
     * @description: 删除谋篇笔记
     */
    public void deleteNoteByNoteID(int noteID) {
        new NoteDao().deleteNoteByNoteID(noteID);
    }
    /**
     * @return List<Note>
     * @description: 根据关键字查询笔记
     */
    public List<Note> fetchAllNoteByKeyword(String keyword) {
        return new NoteDao().selectAllNoteByKeyword(keyword);
    }
}
