package org.modules.SystemDesign.Stackoverflow.entity;

public class Comment {
    private String commentId;
    private String comment;
    private String userId;

    public Comment(String commentId, String comment, String userId) {
        this.commentId = commentId;
        this.comment = comment;
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
