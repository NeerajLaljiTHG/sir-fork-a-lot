package com.thg.accelerator23.connectn.ai.dan;

import com.thehutgroup.accelerator.connectn.player.*;

//import java.util.Random;
//import java.util.Collections;
import java.lang.*;

public class SirForkALot extends Player {

  Minimax algo = new Minimax();

  public SirForkALot(Counter counter) {
    //TODO: fill in your name here
    super(counter, SirForkALot.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it

    ColumnValueHolder correctColumn = algo.minimax(board, 10, 9999, -9999, true, getCounter());

    try{
      System.out.println(correctColumn.getValue());
      System.out.println(correctColumn.getColumn());
      System.out.println(board.getConfig().getHeight());
      System.out.println(ourOwnMinVacantY(correctColumn.getColumn(), board));
      return ourOwnMinVacantY(correctColumn.getColumn(), board);
    }
    catch (Exception e){
      return makeMove(board);
    }

  }
  public int ourOwnMinVacantY(int x , Board board){
    for(int i = 8 - 1; i >= 0; --i) {
      Position pos = new Position(x , i -1);
      if (i == 0 || !board.hasCounterAtPosition(pos) ) {
        return i;
      }
    }
    throw new RuntimeException("no y is vacant");
  }
}


