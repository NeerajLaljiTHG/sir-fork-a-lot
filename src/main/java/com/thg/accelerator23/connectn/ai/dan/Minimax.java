package com.thg.accelerator23.connectn.ai.dan;

import OurAnalyserStuff.BoardAnalyser;
import OurAnalyserStuff.GameState;
import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.lang.*;

public class Minimax {

    private List<Integer> getValidCols (Board board) {
        List<Integer> emptyCols = new ArrayList<>();

        for (int i=0; i<board.getConfig().getWidth(); i++) {
            Position checkTop = new Position(i,(board.getConfig().getHeight()-1));
            boolean isFull = board.hasCounterAtPosition(checkTop);
            if (!isFull) {
                emptyCols.add(i);
            }
        }
        return emptyCols;
    }

    public ColumnValueHolder minimax(Board board , int depth , int alpha , int beta, boolean maximisingPlayer, Counter c){
        List<Integer> validCols = getValidCols(board);
        BoardAnalyser boardAnalyser = new BoardAnalyser(board.getConfig());
        GameState gameState = boardAnalyser.calculateGameState(board);

        if (depth == 0 || gameState.isEnd()) {
            if (gameState.isEnd()) {
                if (gameState.getWinner() == c) {
                    return new ColumnValueHolder(9999999,0 );
                }
                else if (gameState.getWinner() == c.getOther()) {
                    return new ColumnValueHolder(-9999999, 0);
                } else {
                    return new ColumnValueHolder(0, 0);
                }
            } else {
                Windows windows = new Windows();
                int score = windows.scorePosition(board,c);
                System.out.println(score);
                return new ColumnValueHolder(windows.scorePosition(board, c), 0);
            }
        }

        if (maximisingPlayer) {
            ColumnValueHolder columnValueHolder = new ColumnValueHolder(-9999999,0);
            for (int col:validCols) {
                try {
                    Board newBoard = new Board(board, col, Counter.O);
                    ColumnValueHolder newColumnValueHolder = minimax(newBoard, depth - 1, alpha, beta, false, c);
                    if (newColumnValueHolder.getValue() > columnValueHolder.getValue()) {
                        columnValueHolder.setValue(newColumnValueHolder.getValue());
                        columnValueHolder.setColumn(col);
                    }
                    alpha = Math.max(alpha, columnValueHolder.getValue());
                    if (alpha >= beta) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Something goes wrong - find out what :)");
                }
            }

            return columnValueHolder;

        }
        //else if minimising player
        else{
            ColumnValueHolder columnValueHolder = new ColumnValueHolder(9999999,0);
            for (int col:validCols) {
                try {
                    Board newBoard = new Board(board, col, Counter.X);
                    ColumnValueHolder newColumnValueHolder = minimax(newBoard, depth - 1, alpha, beta, true, c);
                    if (newColumnValueHolder.getValue() < columnValueHolder.getValue()) {
                        columnValueHolder.setValue(newColumnValueHolder.getValue());
                        columnValueHolder.setColumn(col);
                    }
                    beta = Math.min(beta, columnValueHolder.getValue());
                    if (alpha >= beta) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Something goes wrong - find out what :)");
                }
            }
            return columnValueHolder;
        }

    }

}
