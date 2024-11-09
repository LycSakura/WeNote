package org.example.entity;

import lombok.Data;

@Data
public class Note {
    protected Integer noteID;

    protected String author;
    protected String noteTitle;
    protected String noteContent;
    protected Integer visit;
    protected String categoryName;
    protected String createTime;
    protected String updateTime;
}
