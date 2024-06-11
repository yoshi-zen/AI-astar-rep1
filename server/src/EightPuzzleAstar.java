import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class EightPuzzleAstar extends EightPuzzleManager {
  public void searchMethod(EightPuzzleBoard initBoard, int depth) {
    // ある深さで貯めておくキュー <盤面、スコア>保持
    Queue<EightPuzzleBoard> depthQueue = new ArrayDeque<>();
    
    int min_heuristic = 100000;
    int f_depth = 0;

    // ボードとキューの初期化
    boolean flag = false;
    depthQueue.add(initBoard);

    while (!flag) {
      // 発見フラグが立つまで繰り返し
      // まずキューに貯めてく
      while (!depthQueue.isEmpty()) {
        // キューから一個とる
        EightPuzzleBoard headBoard = depthQueue.poll();

        if (ansCheck(headBoard.board)) {
          flag = true;
          System.out.println("深さ"+f_depth+"で発見！");
          return;  // TODO: 深さを返せるようにしたい！
        }

        int emptyIndex = Arrays.asList(headBoard.board).indexOf(0);
        boolean[] availableDirection = {emptyIndex > 2, emptyIndex % 3 != 2, emptyIndex < 6, emptyIndex % 3 != 0};

        Integer[] heur_values = {-1, -1, -1, -1};

        for (int i = 0; i < 4; i++) {
          if (availableDirection[i]) {
            // 各移動方向について、移動可能であれば先読みしてヒューリスティック関数計算
            // その最小値についても同様に同様に走査
            int directionDiff = direction[i];
            Integer[] swappedBoardList = swapBoardIndex(emptyIndex, directionDiff, headBoard.board);
            heur_values[i] = heuristic2(swappedBoardList);
            if (heur_values[i] < min_heuristic) {
              min_heuristic = heur_values[i];
            }
          }
        }

        for (int i = 0; i < 4; i++) {
          if (availableDirection[i]) {
            int directionDiff = direction[i];
            EightPuzzleBoard newBoard = new EightPuzzleBoard();
            Integer[] swappedBoardList = swapBoardIndex(emptyIndex, directionDiff, headBoard.board);
            newBoard.board = Arrays.copyOf(swappedBoardList, swappedBoardList.length);
            
            newBoard.printBoard();
            System.out.println("-----------------");
            if (heur_values[i] == min_heuristic) {
              depthQueue.add(newBoard);
            }
          }          
        }
      }
      f_depth++;
    
    }
  }

  static int heuristic0(Integer[] board) {
    return 0;
  }

  static int heuristic1(Integer[] board) {
    int ans = 0;
    Integer[] correctBoard = {1,2,3,8,0,4,7,6,5};
    for (int i = 0; i < board.length; i++) {
      if (board[i] != 0 && board[i] != correctBoard[i]) {
        ans++;
      }
    }
    return ans;
  }

  static int heuristic2(Integer[] board) {
    int ans = 0;
    for (int i = 1; i < board.length; i++) {
      Integer[] correctBoard = {1,2,3,8,0,4,7,6,5};
      int indexOfI = Arrays.asList(board).indexOf(i);
      int ansOfI = Arrays.asList(correctBoard).indexOf(i);
      ans += Math.abs(indexOfI / 3 - ansOfI / 3) + Math.abs(indexOfI % 3 - ansOfI % 3);
    }
    return ans;
  }
}
