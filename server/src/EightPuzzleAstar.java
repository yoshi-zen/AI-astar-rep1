import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Triple<A, B, C> {
  private A first;
  private B second;
  private C third;

  public Triple(A first, B second, C third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  public A getFirst() {
    return first;
  }

  public void setFirst(A first) {
    this.first = first;
  }

  public B getSecond() {
    return second;
  }

  public void setSecond(B second) {
    this.second = second;
  }

  public C getThird() {
    return third;
  }

  public void setThird(C third) {
    this.third = third;
  }

  @Override
  public String toString() {
    return "Triple{" +
            "first=" + first +
            ", second=" + second +
            ", third=" + third +
            '}';
  }
}

class OpenList extends Triple<EightPuzzleBoard, EightPuzzleBoard, Integer> implements Serializable, Comparable<OpenList> {

  public OpenList(EightPuzzleBoard next, EightPuzzleBoard prev, int heur) {
    super(next, prev, heur);
  }

  @Override
  public int compareTo(OpenList o) {
    return this.getThird() - o.getThird();
  }
}

public class EightPuzzleAstar extends EightPuzzleManager {

  private static final int[] direction = {-3, 1, 3, -1}; // 上右下左の方向の移動

  public void searchMethod(EightPuzzleBoard initBoard, int depth) {
    ArrayList<OpenList> openList = new ArrayList<>();
    ArrayList<OpenList> closedList = new ArrayList<>();

    int f_depth = 0;

    openList.add(new OpenList(initBoard, null, heuristic0(initBoard.board, 0)));

    while (true) {
      if (openList.isEmpty()) {
        return;
      }

      Collections.sort(openList);
      OpenList targetBoard = openList.get(0);
      openList.remove(0);

      targetBoard.getFirst().printBoard();

      if (ansCheck(targetBoard.getFirst().board)) {
        targetBoard.getFirst().printBoard();
        System.out.println("========== goal ==========");
        return;
      }

      ArrayList<EightPuzzleBoard> tempBoardsList = new ArrayList<>();

      int emptyIndex = Arrays.asList(targetBoard.getFirst().board).indexOf(0);
      boolean[] availableDirection = {emptyIndex > 2, emptyIndex % 3 != 2, emptyIndex < 6, emptyIndex % 3 != 0};

      for (int i = 0; i < 4; i++) {
        if (availableDirection[i]) {
          int directionDiff = direction[i];
          Integer[] swappedBoardList = swapBoardIndex(emptyIndex, directionDiff, targetBoard.getFirst().board);
          EightPuzzleBoard tempBoard = new EightPuzzleBoard();
          tempBoard.board = Arrays.copyOf(swappedBoardList, swappedBoardList.length);
          tempBoardsList.add(tempBoard);
        }
      }

      closedList.add(targetBoard);

      for (EightPuzzleBoard brd : tempBoardsList) {
        if (!isClosedListContains(closedList, brd)) {
          int heuris_value = heuristic0(brd.board, f_depth) + 1;
          OpenList newOpenList = new OpenList(brd, targetBoard.getFirst(), heuris_value);

          boolean foundInOpenList = false;
          for (OpenList tmp : openList) {
            if (Arrays.equals(tmp.getFirst().board, brd.board)) {
              foundInOpenList = true;
              if (heuris_value < tmp.getThird()) {
                openList.remove(tmp);
                openList.add(newOpenList);
                break;
              }
            }
          }

          if (!foundInOpenList) {
            openList.add(newOpenList);
          }
        }
      }
      System.out.println("========= depth: " + f_depth + "==========");
      f_depth++;
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

  static boolean isClosedListContains(ArrayList<OpenList> closedList, EightPuzzleBoard brd) {
    for (OpenList closedBrd : closedList) {
      boolean chker = true;
      EightPuzzleBoard closedBoard = closedBrd.getFirst();
      for (int i = 0; i < closedBoard.board.length; i++) {
        if (!closedBoard.board[i].equals(brd.board[i])) {
          chker = false;
          break;
        }
      }
      if (chker) {
        return true;
      }
    }
    return false;
  }
}