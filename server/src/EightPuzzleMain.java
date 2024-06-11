public class EightPuzzleMain {
  public static void main(String[] args) {
    EightPuzzleBoard board = new EightPuzzleBoard();
    board.setExampleBoard();

    EightPuzzleAstar astar = new EightPuzzleAstar();
    astar.searchMethod(board, 0);
  }
}
