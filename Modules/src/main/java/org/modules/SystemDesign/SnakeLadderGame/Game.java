package org.modules.SystemDesign.SnakeLadderGame;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
    private Board board;
    private Dices dices;
    private Deque<Player> players;
    private Player winner;

    public Game() {
        this.winner = null;
        initializeGame();
        startGame();
    }

    private void initializeGame() {
        this.board = new Board(10, 20, 5, 6);
        this.dices = new Dices(1, 1, 6);
        this.players = new LinkedList<>();

        Player P1 = new Player(0, "P1");
        Player P2 = new Player(0, "P2");

        players.add(P1);
        players.add(P2);
    }

    private void startGame(){
        while (winner==null){
            Player player = nextPlayer();

            int number = dices.rollDice();
            System.out.println("Player turn: " + player.getUserId() + " current Position: " + player.getCurrPos());

            int nextPos = player.getCurrPos() + number;

            nextPos = checkJump(nextPos);
            player.setCurrPos(nextPos);

            System.out.println("Player: " + player.getUserId() + "moved to Position: " + player.getCurrPos());

            if(nextPos>=board.getCells().length*board.getCells()[0].length-1){
                winner = player;
            }
        }
        System.out.println("Winner is: " + winner.getUserId());
    }

    private int checkJump(int position){
        if(position>=board.getCells().length*board.getCells()[0].length-1) return position;
        Cell cell = board.getCell(position);

        if(cell.getJump()!=null && cell.getJump().getStart()==position){
            String jumpType = cell.getJump().getStart() > cell.getJump().getEnd() ? "Snake" : "Ladder";
            System.out.println("Player moved by--------- " + jumpType);
            return cell.getJump().getEnd();
        }
        return position;
    }

    private Player nextPlayer(){
        Player player = players.getFirst();
        players.removeFirst();
        players.addLast(player);
        return player;
    }
}
