import java.util.Arrays;

public class EightPuzzleIDS extends EightPuzzleManager {
  boolean flag = false;
  int count = 0;
  int ansDepth = 0;

  public void ids(EightPuzzleBoard prevBoard, int depth) {
    // prevBoard.printBoard();
    // System.out.println("depth: " + depth);

    if (ansCheck(prevBoard.board)) {
      // System.out.println("----------- Successful! ---------");
      ansDepth = prevBoard.g;
      flag = true;
      return;
    }
    // System.out.println("---------------------");

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

        this.count++;

        EightPuzzleBoard boardNext = new EightPuzzleBoard();
        boardNext.setBoard(newBoardArray);
        
        // System.out.println("方向：" + i);
        ids(boardNext, depth-1);
      }
    }
  }

  public int getCount() {
    return this.count;
  }

  public long getDepth() {
    return this.ansDepth;
  }

  public void searchMethod(EightPuzzleBoard board) {
    int depth = 0;
    while (!flag) {
      // System.out.println("==========depth: " + depth + "========");
      ids(board, depth);
      depth++;
    }
  }
}
