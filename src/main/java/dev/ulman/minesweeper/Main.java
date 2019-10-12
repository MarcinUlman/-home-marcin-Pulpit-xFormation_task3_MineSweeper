package dev.ulman.minesweeper;

public class Main {

    public static void main(String[] args) {

        String mineField = "*...\n..*.\n....";

        MineSweeper mineSweeper = new MineSweeperImpl();
        mineSweeper.setMineField(mineField);
        System.out.println(mineSweeper.getHintField());
    }
}
