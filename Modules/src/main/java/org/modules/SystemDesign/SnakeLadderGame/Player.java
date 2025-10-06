package org.modules.SystemDesign.SnakeLadderGame;

public class Player {
    private String userId;
    private Integer currPos;

    public Player(Integer currPos, String userId) {
        this.currPos = currPos;
        this.userId = userId;
    }

    public Integer getCurrPos() {
        return currPos;
    }

    public void setCurrPos(Integer currPos) {
        this.currPos = currPos;
    }

    public String getUserId() {
        return userId;
    }
}
