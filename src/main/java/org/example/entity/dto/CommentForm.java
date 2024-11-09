package org.example.entity.dto;

import lombok.Data;
import org.example.entity.Comment;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentForm extends Comment {
    public List<String> validate() {
        List<String> msgs = new ArrayList<>();
        if(commentTitle != null){
            if(commentTitle.trim().length() > 100 || commentTitle.trim().length() < 2){
                msgs.add("评论标题长度2-100位");
            }
        }
        if(commentContent != null){
            if(commentContent.trim().length() == 0){
                msgs.add("评论内容不能为空");
            }
        }
        return msgs;
    }
}
