package org.modules.SystemDesign.Stackoverflow.entity;


import org.modules.SystemDesign.Stackoverflow.enums.VoteType;

public class Answer extends Post {
    private String answerId;
    private String answer;
    private User user;
    private Boolean isAccepted;

    public Answer(String answerId, String answer, User user) {
        super();
        this.answerId = answerId;
        this.answer = answer;
        this.user = user;
        this.isAccepted = false;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void markAccepted() {
        isAccepted = true;
    }

    public void vote(User voter, VoteType voteType){
        if(voter.getUserId().equals(this.user.getUserId())){
            System.out.println("Owner of the Answer can't make vote on their Answer");
            return;
        }
        postVote(voter, voteType, this.user);
    }

    public User getUser() {
        return user;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
