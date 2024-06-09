import java.util.*;

public class EightPuzzleBoard {
  Integer[] board = new Integer[9];

  void setBoard(Integer[] board) {
    this.board = board;
  }

  void readInitBoard() {
    Scanner sc = new Scanner(System.in);
    int inputNum;
    for (int i = 0; i < 9; i++) {
      do {
        System.out.print(i + "番目。0-8の数入力して：");
        inputNum = sc.nextInt();
      } while (Arrays.asList(board).contains(inputNum) || inputNum < 0 || inputNum > 8);
      board[i] = inputNum;
    }
    sc.close();
  }

  void printBoard() {
    for (int i = 0; i<3;i++) {
      for (int j = 0; j < 3; j++) {
        int displayNum = board[i*3+j];;
        if (displayNum == 0) {
          System.out.print(" ");
        } else {
          System.out.print(board[i*3+j]);
        }
        System.out.print(" ");
      }
      System.out.println();
    }
  }
}