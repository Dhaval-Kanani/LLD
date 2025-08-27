package org.stackoverflow.enums;

import org.stackoverflow.entity.User;

public class ReputationManager {
    private static final int UPVOTE_ANSWER_POINTS = 2;
    private static final int UPVOTE_QUESTION_POINTS = 1;
    private static final int DOWNVOTE_ANSWER_POINTS = -2;
    private static final int DOWNVOTE_QUESTION_POINTS = -1;
    private static final int ACCEPTED_ANSWER_POINTS = 5;

    public static void changeReputation(User user, VoteType voteType){
        switch (voteType){
            case UPVOTE_ANSWER -> user.changeReputation(UPVOTE_ANSWER_POINTS);
            case UPVOTE_QUESTION -> user.changeReputation(UPVOTE_QUESTION_POINTS);
            case DOWNVOTE_ANSWER -> user.changeReputation(DOWNVOTE_ANSWER_POINTS);
            case DOWNVOTE_QUESTION -> user.changeReputation(DOWNVOTE_QUESTION_POINTS);
            case ACCEPTED_ANSWER -> user.changeReputation(ACCEPTED_ANSWER_POINTS);
            default -> System.out.println("INVALID Vote Type");
        }
    }
}
