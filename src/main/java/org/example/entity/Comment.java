package org.example.entity;

import lombok.Data;

@Data
public class Comment {
    protected Integer commentID;
    protected Integer noteID;
    protected String userName;
    protected String commentTitle;
    protected String commentContent;
    protected String remoteIP;
    protected String createTime;
    protected User user;
}
