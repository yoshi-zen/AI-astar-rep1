import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class EightPuzzleAstar extends EightPuzzleManager {
  public void searchMethod(EightPuzzleBoard initBoard, int depth) {
    // ある深さで貯めておくキュー <盤面、スコア>保持
    Queue<EightPuzzleBoard> openList = new ArrayDeque<>();
    // 通った経路を貯めておく
    ArrayList<Integer[]> closedList = new ArrayList<>();
    
    int f_depth = 0;

    // ---- ボードとキューの初期化 ---
    boolean flag = false;
    openList.add(initBoard);

    while (!flag) {
      // 発見フラグが立つまで繰り返し
      // まずキューに貯めてく
      ArrayList<EightPuzzleBoard> depthBoards = new ArrayList<>();

      // openが空なら失敗で
      if (openList.isEmpty()) {
        break;
      }
      // ある深さにおいてキューに溜まっている盤面を全て吐き出す
      while (!openList.isEmpty()) {
        depthBoards.add(openList.poll());
      } 
      // ある深さにおける先読みとキュー積み
      for (EightPuzzleBoard headBoard: depthBoards) {
        if (ansCheck(headBoard.board)) {
          flag = true;
          System.out.println("深さ"+f_depth+"で発見！");
          break;  // TODO: 深さを返せるようにしたい！
        }
        
        // ★closedリストに追加
        closedList.add(headBoard.board);

        int min_heuristic = 100000;
        int emptyIndex = Arrays.asList(headBoard.board).indexOf(0);
        boolean[] availableDirection = {emptyIndex > 2, emptyIndex % 3 != 2, emptyIndex < 6, emptyIndex % 3 != 0};

        Integer[] heur_values = {-1, -1, -1, -1};

        for (int i = 0; i < 4; i++) {
          if (availableDirection[i]) {
            // 各移動方向について、移動可能であれば先読みしてヒューリスティック関数計算
            // その最小値についても同様に走査
            int directionDiff = direction[i];
            Integer[] swappedBoardList = swapBoardIndex(emptyIndex, directionDiff, headBoard.board);
            
            if (!isClosedListContains(closedList, swappedBoardList)) {
              heur_values[i] = heuristic2(swappedBoardList, f_depth+1);
              if (heur_values[i] < min_heuristic) {
                min_heuristic = heur_values[i];
              }
            }
          }
        }

        // for (int i: heur_values) {
        //   System.out.print(i + " ");
        // }
        // System.out.println();;

        for (int i = 0; i < 4; i++) {
          if (availableDirection[i]) {
            int directionDiff = direction[i];
            EightPuzzleBoard newBoard = new EightPuzzleBoard();
            Integer[] swappedBoardList = swapBoardIndex(emptyIndex, directionDiff, headBoard.board);
            newBoard.board = Arrays.copyOf(swappedBoardList, swappedBoardList.length);
            
            
            // System.out.println("-----------------");
            // System.out.println("heuristic:" + heur_values[i] + ", min_heur: " + min_heuristic);
            // for (int bnum: newBoard.board) {
            //   System.out.print(bnum + " ");
            // }
            // System.out.println();

            // newBoard.printBoard();
            
            if (!isClosedListContains(closedList, swappedBoardList)) {
              if (heur_values[i] == min_heuristic) {
                openList.add(newBoard);
                System.out.println("--------- selected --------");
                newBoard.printBoard();
              } else {
                System.out.println("-----------------");
                newBoard.printBoard();
              }
            }
          }          
        }
      }
      f_depth++;
      System.out.println("============= depth: " + f_depth + "============");
    }
  }

  static int heuristic0(Integer[] board, int depth) {
    return 0 + depth;
  }

  static int heuristic1(Integer[] board, int depth) {
    int ans = 0;
    Integer[] correctBoard = {1,2,3,8,0,4,7,6,5};
    for (int i = 0; i < board.length; i++) {
      if (board[i] != 0 && board[i] != correctBoard[i]) {
        ans++;
      }
    }
    // System.out.println(depth + " + " + ans);
    return ans + depth;
  }

  static int heuristic2(Integer[] board, int depth) {
    int ans = 0;
    for (int i = 1; i < board.length; i++) {
      Integer[] correctBoard = {1,2,3,8,0,4,7,6,5};
      int indexOfI = Arrays.asList(board).indexOf(i);
      int ansOfI = Arrays.asList(correctBoard).indexOf(i);
      ans += Math.abs(indexOfI / 3 - ansOfI / 3) + Math.abs(indexOfI % 3 - ansOfI % 3);
    }
    // System.out.println(depth + " + " + ans);
    return ans + depth;
  }

  static boolean isClosedListContains(ArrayList<Integer[]> closedList, Integer[] list) {
    for (Integer[] closed: closedList) {
      boolean chker = true;
      for (int i = 0; i < closed.length; i++) {
        if (list[i] != closed[i]) chker = false;
      }
      if (chker) return true;
    }
    return false;
  }
}
