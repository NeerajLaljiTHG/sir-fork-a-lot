package com.thg.accelerator23.connectn.ai.dan;

import com.thehutgroup.accelerator.connectn.player.*;
import java.lang.*;

public class SirForkALot extends Player {

  public SirForkALot(Counter counter) {
    //TODO: fill in your name here
    super(counter, SirForkALot.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    int columnChoice;

    long startTime = System.currentTimeMillis();
    Minimax moveMaker = new Minimax();
    ColumnValueHolder columnValueHolder = moveMaker.minimax(board, 3, -9999999, 9999999, true, getCounter());
    columnChoice = columnValueHolder.getColumn();
    System.out.println("This is the column: " + columnValueHolder.getColumn());
    System.out.println("This is the value: " + columnValueHolder.getValue());
    long endTime = (System.currentTimeMillis() - startTime )/ 1000;
    System.out.println("It took: " + endTime + " seconds!");

    return columnChoice;
  }
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it

}
