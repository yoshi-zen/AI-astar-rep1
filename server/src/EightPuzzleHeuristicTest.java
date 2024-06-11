import java.util.Arrays;

public class EightPuzzleHeuristicTest {
  EightPuzzleBoard board;

  EightPuzzleHeuristicTest(Integer[] testCase) {
    this.board = new EightPuzzleBoard();
    board.board = Arrays.copyOf(testCase, testCase.length);
  }

  void test() {
    timeMeasure();
  }

  long timeMeasure() {
    EightPuzzleAstar aStar = new Heuristic2();
    long startTime = System.nanoTime();
    aStar.searchMethod(board, 0);
    long endTime = System.nanoTime();
    System.out.println((endTime - startTime) + " ns");

    return (endTime - startTime);
  }
}

class Heuristic0 extends EightPuzzleAstar {
  int heuristic(Integer[] board, int depth) {
    return 0 + depth;
  }
}

class Heuristic1 extends EightPuzzleAstar {
  int heuristic(Integer[] board, int depth) {
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
}
class Heuristic2 extends EightPuzzleAstar { 
  int heuristic(Integer[] board, int depth) {
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
}