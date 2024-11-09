package org.example.service;


import org.example.dao.TagDao;
import org.example.entity.dto.NoteForm;

import java.util.List;
import java.util.Map;

public class TagService {
    /**
     * @return Map<Integer>
     * @description: 获取所有笔记标签和标签对应的笔记数量
     */
    public Map<String, Integer> fetchTagNameAndNoteNum() throws Exception {
        return new TagDao().selectTagNameAndNoteNum();
    }

    /**
     * @return List<String>
     * @description: 获取所有笔记标签名
     */
    public List<String> fetchTagNameList() throws Exception {
        return new TagDao().selectAllTagName();
    }
    /**
     * @param noteForm
     * @param noteID
     * @description: 保存笔记标签
     */
    public void saveTag(NoteForm noteForm, int noteID) {
        TagDao tagDao = new TagDao();
        for (String tagName : noteForm.getTagNameList()) {
            tagName = tagName.trim();
            if (!"".equals(tagName)) {
                tagDao.insert(tagName, noteID);
            }
        }
    }

    /**
     * @param noteID
     * @return List<String>
     * @description: 查询谋篇笔记的所有笔记标签
     */
    public List<String> fetchTagNameListByNoteID(int noteID) {
        return new TagDao().selectTagNameListByNoteID(noteID);
    }
    /**
     * @param noteID
     * @description: 删除谋篇笔记的所有笔记标签
     */
    public void deleteTagByNoteID(Integer noteID) {
        new TagDao().deleteTagByNoteID(noteID);
    }
}
