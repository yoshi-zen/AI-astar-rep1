import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class EightPuzzleMain {
  public static void main(String[] args) {
    for (int idx = 1; idx < 150; idx++) {
      ArrayList<Long> branchesList = new ArrayList<>();
      try {
        File file = new File("./sample_output" + idx + ".txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String data;
        while ((data = bufferedReader.readLine()) != null) {
          Integer[] testcase = new Integer[data.length()];
          for (int i = 0; i < data.length(); i++) {
            testcase[i] = Character.getNumericValue(data.charAt(i));
          }
          // EightPuzzleHeuristicTest test = new EightPuzzleHeuristicTest(testcase);
          EightPuzzleIDSTest test = new EightPuzzleIDSTest(testcase);
          branchesList.add(test.test());
        }
  
        bufferedReader.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      double avg = 0.;
      for (int i = 0; i < 1000; i++) {
        avg += branchesList.get(i);
      }
      System.out.println(avg / 1000);
    }
  }
}
