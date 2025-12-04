package org.modules.SystemDesign.SnakeLadderGame;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private Cell[][] cells;

    public Board(int rows, int columns, int snakes, int ladders) {
        initialize(rows, columns);
        addSnakedAndLadders(snakes, ladders);
    }

    private void initialize(int rows, int columns){
        cells = new Cell[rows][columns];
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                cells[i][j] = new Cell();
            }
        }
    }

    private void addSnakedAndLadders(int snakes, int ladders){
        while (snakes>0){
            int start = ThreadLocalRandom.current().nextInt(1, cells.length*cells[0].length-1);
            int end = ThreadLocalRandom.current().nextInt(1, cells.length*cells[0].length-1);

            if(start <= end) continue;
            Cell cell = getCell(start);
            Jump jump = new Jump(start, end);
            cell.setJump(jump);
            snakes--;
        }

        while (ladders>0){
            int start = ThreadLocalRandom.current().nextInt(1, cells.length*cells[0].length-1);
            int end = ThreadLocalRandom.current().nextInt(1, cells.length*cells[0].length-1);

            if(start >= end) continue;
            Cell cell = getCell(start);
            Jump jump = new Jump(start, end);
            cell.setJump(jump);
            ladders--;
        }
    }

    public Cell getCell(int number){
        int row = number/cells[0].length;
        int col = number%cells[0].length;
        return cells[row][col];
    }

    public Cell[][] getCells() {
        return cells;
    }
}
