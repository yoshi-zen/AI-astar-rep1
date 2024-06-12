import java.util.Arrays;

public class EightPuzzleIDSTest {
  EightPuzzleBoard board;

  EightPuzzleIDSTest(Integer[] testCase) {
    this.board = new EightPuzzleBoard();
    board.board = Arrays.copyOf(testCase, testCase.length);
  }

  long test() {
    return timeMeasure();
    // return depthMeasure();
  }

  long timeMeasure() {
    EightPuzzleIDS ids = new EightPuzzleIDS();
    long startTime = System.nanoTime();
    ids.searchMethod(board);
    long endTime = System.nanoTime();
    return (endTime - startTime);
  }

  long costMeasure() {
    EightPuzzleIDS ids = new EightPuzzleIDS();
    ids.searchMethod(board);
    return ids.getCount();
  }

  long depthMeasure() {
    EightPuzzleAstar aStar = new Heuristic0();
    aStar.searchMethod(board);
    return aStar.getDepth();
  }
}
