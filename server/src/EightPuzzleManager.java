import java.util.Arrays;

public class EightPuzzleManager {
  static final Integer[] answers = {1,2,3,8,0,4,7,6,5};
  static final Integer[] direction = {-3, 1, 3, -1};
  public static void main(String[] args) {
    // 初期盤面のインスタンスをnewして、内容読み込み
    EightPuzzleBoard initBoard = new EightPuzzleBoard();
    initBoard.readInitBoard();

    dfs(initBoard, 2);
  }

  public static boolean ansCheck(Integer[] boards) {
    for (int i = 0; i < 9; i++) {
      if (boards[i] != answers[i]) return false;
    }
    return true;
  }

  public static void dfs(EightPuzzleBoard prevBoard, int depth) {
    prevBoard.printBoard();
    System.out.println("---------------------");
    if (ansCheck(prevBoard.board)) {
      System.out.println("----------- Successful! ---------");
      return;
    }
    if (depth == 0) return;

    int emptyIndex = Arrays.asList(prevBoard.board).indexOf(0);
    boolean[] availableDirection = {emptyIndex > 2, emptyIndex % 3 != 2, emptyIndex < 6, emptyIndex % 3 != 0};

    for (int i = 0; i < 4; i++) {
      if (availableDirection[i]) {
        int directionDiff = direction[i];
        Integer[] newBoardArray = new Integer[9];

        newBoardArray = Arrays.copyOf(prevBoard.board, 9);
        newBoardArray[emptyIndex] = newBoardArray[emptyIndex + directionDiff];
        newBoardArray[emptyIndex + directionDiff] = 0;

        EightPuzzleBoard boardNext = new EightPuzzleBoard();
        boardNext.setBoard(newBoardArray);

        dfs(boardNext, depth-1);
      }
    }
  }
}
