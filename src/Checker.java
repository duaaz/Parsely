import java.io.*;
import java.util.*;

public class Checker {

   public static void main(String[] args) throws FileNotFoundException {
      Resume Bob = new Resume(new File("BobBuilderResume.txt"));
      Map<String, Integer> experience = new TreeMap<String, Integer>();
      experience = Bob.getExperience();
      System.out.print("Potential canidates name: ");
      System.out.println(Bob.getName());
      System.out.println("Here are all the keyWords with all of their associated times!");
      for (String keyWord : experience.keySet()) {
         System.out.print(keyWord + ": ");
         System.out.println(experience.get(keyWord));
      }
      System.out.println(Bob.getKeyWords());
   }
}
