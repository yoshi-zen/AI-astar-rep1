import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class TestCaseCreator {
  static EightPuzzleBoard board;
  public static void main(String[] args) {
    try {
      File file = new File("sample_output5.txt");
      FileWriter filewriter = new FileWriter(file);
      for (int j = 0; j < 100; j++) {
        Integer[] testCase = createTestCase(5);
        for (Integer direction : testCase) {
          filewriter.write(String.valueOf(direction));
        }
        filewriter.write("\r\n");
      }
      filewriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static Integer[] createTestCase(int depth) {
    Integer[] boardList = {1,2,3,8,0,4,7,6,5};
    Integer[] directionList = {-3, 1, 3, -1};
    Random rand = new Random();
    int count = 0;
    int direction = rand.nextInt(4);
    do {
      int emptyIndex = Arrays.asList(boardList).indexOf(0);
      boolean[] availableDirection = {emptyIndex > 2, emptyIndex % 3 != 2, emptyIndex < 6, emptyIndex % 3 != 0};
      availableDirection[direction] = false;
      direction = rand.nextInt(4);
      if (availableDirection[direction]) {
        boardList[emptyIndex] = boardList[emptyIndex + directionList[direction]];
        boardList[emptyIndex + directionList[direction]] = 0;
        count++;
      }
    } while (count < depth);
    return boardList;
  }
}
