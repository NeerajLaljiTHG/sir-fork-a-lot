package com.thg.accelerator23.connectn.ai.dan;

import com.thehutgroup.accelerator.connectn.player.Board;
import com.thehutgroup.accelerator.connectn.player.Counter;
import com.thehutgroup.accelerator.connectn.player.Player;
import com.thehutgroup.accelerator.connectn.player.Position;

public class ConsoleRender {


    public void render(Player activePlayer, Board board) {
        printLine(board);
        for (int y = board.getConfig().getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < board.getConfig().getWidth(); x++) {
                System.out.print(" | ");
                Counter counterAtPosition = board.getCounterAtPosition(new Position(x, y));
                String counterString =
                        counterAtPosition != null ? counterAtPosition.getStringRepresentation() : " ";
                System.out.print(counterString);
            }
            System.out.print(" | ");
            System.out.println();
            printLine(board);
        }
        if (activePlayer != null) {
            System.out.println(activePlayer.getName() + " to play " +
                    activePlayer.getCounter().getStringRepresentation());
        }
    }
    private void printLine(Board board) {
        for (int x = 0; x < board.getConfig().getWidth(); x++) {
            System.out.print("----");
        }
        System.out.print("---");
        System.out.println();
    }
}
