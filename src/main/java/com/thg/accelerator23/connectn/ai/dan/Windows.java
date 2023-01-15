package com.thg.accelerator23.connectn.ai.dan;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
//import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;
import java.util.List;
import java.util.ArrayList;
//import java.util.Random;
import java.util.Collections;


public class Windows {

    private int evaluateWindow(List<Position> window, Counter piece , Board board){
        int score = 0;
        List<Counter> window1 = listOfPositionsToCounters(window,board);
        int occurrencesPiece = Collections.frequency( window1, piece);
        int occurrencesNull = Collections.frequency( window1, null);
        int occurrencesOppPiece = Collections.frequency( window1, piece.getOther());

        if (occurrencesPiece == 4 ){
            score += 1000;

        }
        else if (occurrencesPiece == 3 && occurrencesNull== 1){
            score += 5;
        }
        else if (occurrencesPiece == 2 && occurrencesNull== 2){
            score += 2;
        }
        else if (occurrencesOppPiece == 3 && occurrencesNull== 1){
            score -= 4;
        }

        return score;
    }

    public List<Counter> listOfPositionsToCounters(List<Position> listPositions , Board board){
        List<Counter> listOfCounters= new ArrayList<>();
        for(Position i:listPositions){

            listOfCounters.add(board.getCounterAtPosition(i));
        }
        return listOfCounters;
    }

    public int scorePosition(Board board , Counter piece){
        int n = 4;
        int score = 0;
        // need to add search for diagonal windows

        //score horizontal positions
        for (int row=0;row<board.getConfig().getHeight();row++){

            List<Position> positionsOnThisRow = new ArrayList<>();
            for (int col=0;col<board.getConfig().getWidth();col++){
                Position pos = new Position(col , row);
                positionsOnThisRow.add(pos);
            }
            for (int colWindow = 0;colWindow<board.getConfig().getWidth()-3;colWindow++){
                List<Position> window = positionsOnThisRow.subList(colWindow,colWindow+n);
                score += evaluateWindow(window,piece,board);
            }

        }
        //score vertical positions
        for (int col=0;col<board.getConfig().getWidth();col++){

            List<Position> positionsOnThisRow = new ArrayList<>();
            for (int row=0;row<board.getConfig().getHeight();row++){
                Position pos = new Position(col , row);
                positionsOnThisRow.add(pos);
            }
            for (int rowWindow = 0;rowWindow<board.getConfig().getHeight()-3;rowWindow++){
                List<Position> window = positionsOnThisRow.subList(rowWindow,rowWindow+n);
                score += evaluateWindow(window,piece,board);
            }
        }

        // score positive diagonal
        for (int row = 0; row<board.getConfig().getHeight()-3;row++ ){

            for (int col = 0; col<board.getConfig().getWidth()-3;col++ ){
                List<Position> window =  new ArrayList<>();
                for(int i = 0;i < n;i++){
                    Position pos = new Position(col+i , row+i);
                    window.add(pos);
                }
                score += evaluateWindow(window,piece,board);

            }
        }

        // score negative diagonal
        for (int row = 0; row<board.getConfig().getHeight()-3;row++ ){
            for (int col = 0; col<board.getConfig().getWidth()-3;col++ ){
                List<Position> window =  new ArrayList<>();
                int h = board.getConfig().getHeight();
                for(int i = 0;i < n;i++){
                    Position pos = new Position(col+i , h-1-row - i);
                    window.add(pos);
                }
                score += evaluateWindow(window,piece,board);

            }
        }

        //score the centre winning pieces (cols with possible wins from left and right)
        for (int col= 3;col<board.getConfig().getWidth()-3;col++){
            List<Position> positionsOnThisCol =  new ArrayList<>();
            for (int row=0;row<board.getConfig().getHeight();row++){
                Position pos = new Position(col , row);
                positionsOnThisCol.add(pos);
            }
            List<Counter> positionsOnThisCol1=  listOfPositionsToCounters(positionsOnThisCol,board);
            //System.out.println(listOfPositionsToCounters(positionsOnThisCol,board));
            int numberOurInCol =  Collections.frequency( positionsOnThisCol1, piece);

            score += numberOurInCol * 3;

//            int numberOppInCol = Collections.frequency( positionsOnThisCol1, piece.getOther());
//            score += numberOurInCol - numberOppInCol;

        }

        return score;
    }

}
