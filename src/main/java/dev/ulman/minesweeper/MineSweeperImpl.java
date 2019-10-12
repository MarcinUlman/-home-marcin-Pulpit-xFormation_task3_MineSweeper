package dev.ulman.minesweeper;

public class MineSweeperImpl implements MineSweeper {

    private int maxCol;
    private int maxRow;
    private char[][] field;

    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {
        String[] divided = mineField.split("\n");
        maxCol = divided[0].length();
        maxRow  = divided.length;

        dataChecker(divided);

        field = new char[maxRow][maxCol];

        for (int i = 0; i < maxRow; i++){
            field[i] = divided[i].toCharArray();
        }
    }

    private void dataChecker(String[] divided) throws IllegalArgumentException {
        if (maxRow <=1 || maxCol <= 1) throw new IllegalArgumentException("board is too small");
        for (String s: divided) {
            if (maxCol != s.length()) throw new IllegalArgumentException("rows aren't equals");
        }
    }

    @Override
    public String getHintField() throws IllegalStateException {
        return null;
    }
}
