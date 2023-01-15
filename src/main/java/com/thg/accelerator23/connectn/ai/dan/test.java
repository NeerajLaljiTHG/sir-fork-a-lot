package com.thg.accelerator23.connectn.ai.dan;

import OurAnalyserStuff.BoardAnalyser;
import com.thehutgroup.accelerator.connectn.player.*;

import java.io.Console;

public class test{

    public static void main(String[] args) {
        GameConfig config = new GameConfig(10, 8, 4);
        Board board = new Board(config);

        try {
            Player player = new SirForkALot(Counter.X);
            Board newBoard1 = new Board(board, 5, Counter.X);
            Board newBoard2 = new Board(newBoard1, 5, Counter.X);
            ConsoleRender consoleRender = new ConsoleRender();
            consoleRender.render(player, newBoard2);
            Windows tester = new Windows();
            int score = tester.scorePosition(newBoard2, Counter.X);
            System.out.println("The score is: " + score);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
