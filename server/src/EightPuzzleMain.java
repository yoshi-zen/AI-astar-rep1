import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EightPuzzleMain {
  public static void main(String[] args) {
    try {
      File file = new File("./sample_output30.txt");
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String data;
      while ((data = bufferedReader.readLine()) != null) {
        Integer[] testcase = new Integer[data.length()];
        for (int i = 0; i < data.length(); i++) {
          testcase[i] = Character.getNumericValue(data.charAt(i));
        }
        EightPuzzleHeuristicTest test = new EightPuzzleHeuristicTest(testcase);
        test.test();
      }
      bufferedReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // EightPuzzleHeuristicTest test = new EightPuzzleHeuristicTest(testcase);
    // test.test();
  }
}
