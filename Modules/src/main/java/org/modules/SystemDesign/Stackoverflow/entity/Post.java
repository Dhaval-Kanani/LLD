package org.modules.SystemDesign.Stackoverflow.entity;



import org.modules.SystemDesign.Stackoverflow.enums.ReputationManager;
import org.modules.SystemDesign.Stackoverflow.enums.VoteType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Post {
    private List<Comment> comments;
    private AtomicInteger voteCount;
    private Map<String, VoteType> voters;

    public Post() {
        this.comments = new ArrayList<>();
        this.voteCount = new AtomicInteger(0);
        this.voters = new ConcurrentHashMap<>();
    }

    public void addComment(String commentBody, String userId) {
        Comment comment  = new Comment(UUID.randomUUID().toString(), commentBody, userId);
        comments.add(comment);
    }

    synchronized void postVote(User user, VoteType voteType, User postOwnerUser){
        if(voters.get(user.getUserId())==voteType){
            System.out.println("User already voted");
            return;
        }

        int countChange = 0;

        if(voters.containsKey(user.getUserId())){
            if(voteType==VoteType.UPVOTE_QUESTION || voteType==VoteType.UPVOTE_ANSWER){
                countChange = 2;
            } else if(voteType==VoteType.DOWNVOTE_QUESTION || voteType == VoteType.DOWNVOTE_ANSWER){
                countChange = -2;
                ReputationManager.changeReputation(user, voteType);
            } else ReputationManager.changeReputation(postOwnerUser, voteType);
        } else{
            if(voteType==VoteType.UPVOTE_QUESTION || voteType==VoteType.UPVOTE_ANSWER){
                countChange = 1;
            } else if(voteType==VoteType.DOWNVOTE_QUESTION || voteType == VoteType.DOWNVOTE_ANSWER){
                countChange = -1;
                ReputationManager.changeReputation(user, voteType);
            } else ReputationManager.changeReputation(postOwnerUser, voteType);
        }

        voteCount.addAndGet(countChange);
        for(int i=0; i<Math.abs(countChange); i++)
            ReputationManager.changeReputation(postOwnerUser, voteType);

        voters.put(user.getUserId(), voteType);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
