package org.stackoverflow.entity;

import org.stackoverflow.enums.VoteType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question extends Post {
    private String questionId;
    private String question;
    private User user;
    private List<Answer> answers;
    private Answer acceptedAnswer;
    private Set<Tag> tags;
    private LocalDateTime createdOn;

    public Question(String questionId, String question, User user, Set<Tag> tags) {
        super();
        this.questionId = questionId;
        this.question = question;
        this.user = user;
        this.answers = new ArrayList<>();
        this.acceptedAnswer = null;
        this.tags = tags;
        this.createdOn = LocalDateTime.now();
    }

    public void markAnswerAccepted(String answerId){
        for(Answer answer: answers){
            if(answer.getAnswerId().equals(answerId)){
                User postOwner = answer.getUser();
                answer.markAccepted();
                this.acceptedAnswer = answer;
                postVote(this.user, VoteType.ACCEPTED_ANSWER, postOwner);
                System.out.println("Marked answer as accepted with id: " + answerId);
            } else{
                System.out.println("Answer is not present with id: " + answerId);
            }
        }
    }

    public void postAnswer(Answer answer){
        answers.add(answer);
    }

    public void vote(User voter, VoteType voteType){
        if(voter.getUserId().equals(this.user.getUserId())){
            System.out.println("Owner of the Question can't make vote on their Question");
            return;
        }
        postVote(voter, voteType, this.user);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public Answer getAcceptedAnswer() {
        return acceptedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public User getUser() {
        return user;
    }

    public Set<Tag> getTags() {
        return tags;
    }


}
