import java.util.Arrays;

public abstract class EightPuzzleManager {
  static final Integer[] answers = {1,2,3,8,0,4,7,6,5};
  static final Integer[] direction = {-3, 1, 3, -1};

  int g = 0;
  
  public boolean ansCheck(Integer[] boards) {
    for (int i = 0; i < boards.length; i++) {
      if (boards[i] != answers[i]) return false;
    }
    return true;
  }

  // ヒューリスティック関数の先読みに使う
  public Integer[] swapBoardIndex(int emptyIndex, int directionDiff, Integer[] board) {
    Integer[] newBoardList = Arrays.copyOf(board, board.length);
    newBoardList[emptyIndex] = newBoardList[emptyIndex + directionDiff];
    newBoardList[emptyIndex + directionDiff] = 0;
    return newBoardList;
  }

  abstract void searchMethod(EightPuzzleBoard prevBoard, int depth);
}
