package org.stackoverflow.controller;

import org.stackoverflow.entity.*;
import org.stackoverflow.enums.PostType;
import org.stackoverflow.enums.VoteType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class StackOverFlowController {
    private Map<String, User> users;
    private Map<String, Question> questions;
    private Map<String, Answer> answers;

    private AtomicInteger questionIdCount;
    private AtomicInteger answerIdCount;

    public StackOverFlowController() {
        this.users = new HashMap<>();
        this.questions = new HashMap<>();
        this.answers = new HashMap<>();
        this.questionIdCount = new AtomicInteger(0);
        this.answerIdCount = new AtomicInteger(0);
    }

    public User registerUser(String userId, String userName){
        if(validateUser(userId)){
            System.out.println("User is already registered for userId: " + userId);
            return null;
        }
        User user = new User(userId, userName);
        users.put(userId, user);
        System.out.println("Successfully user registered with userId: " + userId);
        return user;
    }

    private Boolean validateUser(String userId){
        return users.get(userId)!=null;
    }

    public String postQuestion(String questionContent, User user, Set<Tag> tags){
        if(!validateUser(user.getUserId())){
            System.out.println("User is not registered with userId: " + user.getUserId());
            return null;
        }
        String questionId = String.valueOf(questionIdCount.getAndIncrement());

        Question question = new Question(questionId, questionContent, user, tags);
        questions.put(questionId, question);
        System.out.println("Posted question successfully.");
        return questionId;
    }

    private Boolean validateQuestion(String questionId){
        return questions.get(questionId)!=null;
    }

    public String postAnswer(String answerContent, User user, String questionId){
        if(!validateQuestion(questionId)){
            System.out.println("Question is not present with questionId: " + questionId);
            return null;
        }
        String answerId = String.valueOf(answerIdCount.getAndIncrement());

        Answer answer = new Answer(answerId, answerContent, user);
        answers.put(answerId, answer);
        Question question = questions.get(questionId);
        question.postAnswer(answer);
        System.out.println("Posted answer successfully.");
        return answerId;
    }

    public void postComment(String commentContent, User user, PostType postType, String postId){
        if(!validateUser(user.getUserId())){
            System.out.println("User is not present with userId: " + user.getUserId());
            return;
        }

        if(postType==PostType.QUESTION){
            Question question = questions.get(postId);
            if(question==null) {
                System.out.println("Question not present with id: " + postId);
                return;
            }
            question.addComment(commentContent, user.getUserId());
        } else{
            Answer answer = answers.get(postId);
            if(answer==null){
                System.out.println("Answer not present with id: " + postId);
                return;
            }
            answer.addComment(commentContent, user.getUserId());
        }

        System.out.println("Posted comment successfully.");
    }

    public void markAnswerAsAccepted(String answerId, String questionId){
        Answer answer = answers.get(answerId);
        if(answer==null){
            System.out.println("Answer is not present with id: "+  answerId);
            return;
        }

        if(!validateQuestion(questionId)){
            System.out.println("Question is not present with questionId: " + questionId);
            return;
        }
        Question question = questions.get(questionId);
        question.markAnswerAccepted(answerId);
    }

    public void QuestionDetails(String questionId){
        Question question = questions.get(questionId);
        if(question==null){
            System.out.println("Question does not exist with id: "+ questionId);
            return;
        }
        System.out.println("Question: " + question.getQuestion() + " created by: " + question.getUser().getUserId());
        List<Answer> answerList = question.getAnswers();
        List<Comment> commentList = question.getComments();
        Answer acceptedAnswer = question.getAcceptedAnswer();
        System.out.println("Answers:");
        for (Answer answer:answerList)
            System.out.println(answer.getAnswer());

        System.out.println("Comments:");
        for (Comment comment:commentList)
            System.out.println(comment.getComment());

        System.out.println("Accepted Answer: " + acceptedAnswer.getAnswer());
    }

    public void userStatus(User user){
        System.out.println("User status of user: " + user.getUserName());
        System.out.println("User Reputation: "+  user.getReputation());
    }

    public void vote(PostType postType, String postId, User user, VoteType voteType){
        if(postType==PostType.ANSWER){
            Answer answer = answers.get(postId);
            answer.vote(user, voteType);
        } else {
            Question question = questions.get(postId);
            question.vote(user, voteType);
        }
    }
}
