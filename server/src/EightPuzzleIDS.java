import java.util.Arrays;

public class EightPuzzleIDS extends EightPuzzleManager {
  // public static void main(String[] args) {
  //   // 初期盤面のインスタンスをnewして、内容読み込み
  //   EightPuzzleBoard initBoard = new EightPuzzleBoard();
  //   initBoard.readInitBoard();

  //   dfs(initBoard, 2);
  // }

  public void searchMethod(EightPuzzleBoard prevBoard, int depth) {
    prevBoard.printBoard();
    System.out.println("depth: " + depth);

    if (ansCheck(prevBoard.board)) {
      System.out.println("----------- Successful! ---------");
      return;
    }
    System.out.println("---------------------");

    // 最深部まで到達していたら終了
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
        
        System.out.println("方向：" + i);
        searchMethod(boardNext, depth-1);
      }
    }
  }
}
