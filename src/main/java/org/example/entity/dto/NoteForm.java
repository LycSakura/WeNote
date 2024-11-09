package org.example.entity.dto;


import lombok.Data;
import org.example.entity.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class NoteForm extends Note {
    private String tagNames;

    public List<String> getTagNameList() {
        return Arrays.asList(tagNames.split("#"));
    }

    public List<String> validate() {
        List<String> msgs = new ArrayList<>();
        if (noteTitle != null){
            if (noteTitle.trim().length() > 100 || noteTitle.trim().length() < 2) {
                msgs.add("标题长度必须在2-100位之间");
            }
        }
        if (noteContent != null){
            if (noteContent.trim().length() == 0) {
                msgs.add("笔记内容不能为空");
            }
        }
        if (categoryName != null){
            if (categoryName.trim().length() == 0 || categoryName.trim().length() > 50) {
                msgs.add("笔记类别不能为空且长度不能超过50位");
            }
        }
        if (tagNames != null){
            if (tagNames.trim().length() == 0) {
                msgs.add("笔记标签不能为空");
            }
            for (String tagName: getTagNameList()){
                if (tagName.trim().length() > 50) {
                    msgs.add("笔记标签长度不能超过50位");
                    break;
                }
            }
        }
        return msgs;
    }
}
