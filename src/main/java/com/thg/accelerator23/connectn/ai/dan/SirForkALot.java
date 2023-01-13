package com.thg.accelerator23.connectn.ai.dan;

import com.thehutgroup.accelerator.connectn.player.*;

import java.util.Random;
import java.util.Arrays;

public class SirForkALot extends Player {
  Random rand = new Random();
  Counter counter = getCounter();
  String counterString = counter.getStringRepresentation();
  String piece = String.valueOf(getCounter());

  int a = 10;
  int b = 6;
  Position position = new Position(a, b);
  GameConfig configs = new GameConfig(8, 10 , 4);
  int upperBound = 10;
  public SirForkALot(Counter counter) {
    //TODO: fill in your name here
    super(counter, SirForkALot.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it

    Random random = new Random();
    int x = random.nextInt(1);
    System.out.print(counter);
    System.out.println("Testing Aarjav's code");

    if (ourOwnMinVacantY(x, board) < 8) {
      System.out.println(ourOwnMinVacantY(x, board));
      System.out.println(x);
      System.out.println(configs.getHeight());
      return x;
    } else {;
      return makeMove(board);
    }
  }


  public int ourOwnMinVacantY(int x, Board board) {
    for (int i = 7; i >= 0; i--) {
      Position pos = new Position(x, i);
      System.out.println(i);
      System.out.println(board.hasCounterAtPosition(pos));
      if (i == 0 && board.hasCounterAtPosition(pos) == false) {
        return i;
      } else if (board.hasCounterAtPosition(pos)) {
        return i + 1;
      }
    }
    throw new RuntimeException("no y is vacant");
  }

}
