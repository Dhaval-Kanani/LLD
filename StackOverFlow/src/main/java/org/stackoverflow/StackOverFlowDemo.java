package org.stackoverflow;


import org.stackoverflow.controller.StackOverFlowController;
import org.stackoverflow.entity.Tag;
import org.stackoverflow.entity.User;
import org.stackoverflow.enums.PostType;
import org.stackoverflow.enums.VoteType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class StackOverFlowDemo {
    public static void main(String[] args) {
        System.out.println("Welcome to Stack OverFlow Demo!!");

        StackOverFlowController controller = new StackOverFlowController();

        User user1 = controller.registerUser("U01", "abc01");
        User user2 = controller.registerUser("U02", "abc02");
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Coding"));
        String questionId1 = controller.postQuestion("What is java", user1, tags);
        String questionId2 = controller.postQuestion("What is Python", user2, Collections.emptySet());

        String answerId1 = controller.postAnswer("Programming language", user2, questionId1);
        controller.postComment("Maybe", user1, PostType.QUESTION, questionId1);

        controller.vote(PostType.QUESTION, questionId1, user2, VoteType.UPVOTE_QUESTION);
        controller.vote(PostType.ANSWER, answerId1, user1, VoteType.UPVOTE_ANSWER);

        controller.markAnswerAsAccepted(answerId1, questionId1);
        controller.QuestionDetails(questionId1);

        controller.userStatus(user1);
        controller.userStatus(user2);
    }
}