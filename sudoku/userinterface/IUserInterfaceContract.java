package sudoku.userinterface;

public interface IUserInterfaceContract {
    interface EventListener {
        void onSudokuInput (int x, int y, int input); 
        void onDialogClick();
    }

    interface View {
        void SetListener(IUserInterfaceContract.EventListener listener);
        void updateSquare(int x, int y, int input);
        void updateBoard(sudokuGame game);
        void showDialog(String message);
        void showError(String message);
    }
}