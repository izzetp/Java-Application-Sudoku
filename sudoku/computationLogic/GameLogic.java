package sudoku.computationLogic;

import com.sun.tools.javac.util.List;

import sudoku.constants.GameState;
import sudoku.problemdomain.SudokuGame;

public class GameLogic {
    
    public static SudokuGame getNewGame() {
        return new SudokuGame(
            GameState.NEW,
            GameGenerator.getNewGameGrid()
        );
    }

    public static GameState checkForCompletion(int[][] grid) {
        if (sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if (tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    public static boolean sudokuIsInvalid(int[][] grid) {
        if (rowsAreInvalid(grid)) return true;
        if (columnsAreInvalid(grid)) return true;
        if (squaresAreInvalid(grid)) return true;
        else return false;
    }

    private static boolean columnsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        if (rowsOfSquaresIsInvalid(Rows.TOP, grid)) return true;

        if (rowsOfSquaresIsInvalid(Rows.MIDDLE, grid)) return true;

        if (rowsOfSquaresIsInvalid(Rows.BOTTOM, grid)) return true;    
        
        return false;
    }

    private static boolean rowOfSquaresIsInvalid(Rows values, int[][] grid) {
        switch(value) {
            case TOP:
                if (SquareIsInvalid(0, 0, grid)) return true;
                if (SquareIsInvalid(0, 3, grid)) return true;
                if (SquareIsInvalid(0, 6, grid)) return true;
                return false;
            case MIDDLE:
                if (SquareIsInvalid(3, 0, grid)) return true;
                if (SquareIsInvalid(3, 3, grid)) return true;
                if (SquareIsInvalid(3, 6, grid)) return true;
                return false;
            case BOTTOM:
                if (SquareIsInvalid(6, 0, grid)) return true;
                if (SquareIsInvalid(6, 3, grid)) return true;
                if (SquareIsInvalid(6, 6, grid)) return true;
                return false;
            default:
                return false;
        }
    }

    private static boolean squaresIsInvalid(int xIndex, int yIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {
            while (xIndex < xIndex) {
                square.add(
                    grid[xIndex][yIndex]
                );

                xIndex++;
            }

            xIndex -= 3;

            yIndex++;
        }
        if (collectionHasRepeated(square)) return true;
        return false;
    }

    public static boolean collectionHasRepeated(List<Integer> collection) {
        for (int index = 1; index <= GRID_BOUNDARY; index++) {
            if (Collections.frequency(collection, index) > 1) return true;
        }

        return false;
    }

    public static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }

        return false;
    }


}
