package dev.ulman.minesweeper;

public class MineSweeperImpl implements MineSweeper {

    private int maxCol;
    private int maxRow;
    private char[][] mineField;
    private char[][] hintField;
    private final char MINE_CHAR = '*';

    @Override
    public void setMineField(String mineField) throws IllegalArgumentException {
        String[] divided = mineField.split("\n");
        maxCol = divided[0].length();
        maxRow  = divided.length;

        dataChecker(divided);

        this.mineField = new char[maxRow][maxCol];

        for (int i = 0; i < maxRow; i++){
            this.mineField[i] = divided[i].toCharArray();
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
        if (mineField == null) throw new IllegalStateException("mine-field has not been initialised");

        initiateHintField();

        /* neighbors
         * NW  |   N    |   NE
         * --------------------
         * W   | >cell< |   E
         * --------------------
         * SW  |   S    |   SE
         */
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {

                if (mineField[row][col] == MINE_CHAR) {
                    hintField[row][col] = MINE_CHAR;

                    //1 - NW
                    if (isValid(row - 1, col - 1)) {
                        hintField[row - 1][col - 1]++;
                    }
                    //2 - N
                    if (isValid(row - 1, col)) {
                        hintField[row - 1][col]++;
                    }
                    //3 - NE
                    if (isValid(row - 1, col + 1)) {
                        hintField[row - 1][col + 1]++;
                    }
                    //4 - W
                    if (isValid(row, col - 1)) {
                        hintField[row][col - 1]++;
                    }
                    //5 - E
                    if (isValid(row, col + 1)) {
                        hintField[row][col + 1]++;
                    }
                    //6 - SW
                    if (isValid(row + 1, col - 1)) {
                        hintField[row + 1][col - 1]++;
                    }
                    //7 - S
                    if (isValid(row + 1, col)) {
                        hintField[row + 1][col]++;
                    }
                    //8 - SE
                    if (isValid(row + 1, col + 1)) {
                        hintField[row + 1][col + 1]++;
                    }
                }
            }
        }

        return fieldToString(hintField);
    }

    private String fieldToString(char[][] field) {
        StringBuilder stringBuilder = new StringBuilder(String.copyValueOf(field[0]));
        for (int row = 1; row < maxRow; row++) {
            stringBuilder.append("\\n");
            stringBuilder.append(field[row]);
        }
        return stringBuilder.toString();
    }

    private boolean isValid(int row, int col) {
        if (row < 0 || row >= maxRow) return false;
        if (col < 0 || col >= maxCol) return false;
        return mineField[row][col] != MINE_CHAR;
    }

    private void initiateHintField() {
        hintField = new char[maxRow][maxCol];
        for (int row = 0; row < maxRow; row++) {
            for (int col = 0; col < maxCol; col++) {
                hintField[row][col] = '0';
            }
        }
    }
}
