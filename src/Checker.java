import java.io.*;
import java.util.*;

public class Checker {

   public static void main(String[] args) throws FileNotFoundException {
      Scanner console = new Scanner(System.in);
      Resume Bob = new Resume(new File("BobBuilderResume"));
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
      Map<String, Set<String>> results = new TreeMap<String, Set<String>>();
      results = keyWords(console);
      Map<String, Set<String>> wordsShown = new TreeMap<String, Set<String>>();
      wordsShown = Bob.containsWanted(results);
      System.out.println(wordsShown);
   }
   
   public static Map<String, Set<String>> keyWords(Scanner console) {
      Map<String, Set<String>> wantedWords = new TreeMap<String, Set<String>>();
      console = new Scanner(System.in);
      System.out.print("Would you like to start? :");
      String choice = console.next();
      while (choice.equals("Yes")) {
         System.out.print("What type of word would you like? : ");
         String key = console.next();
         //have to at least have one word for each of the type of the words  
         System.out.print("What is your first word? :");
         String word = console.next();
         if (!wantedWords.containsKey(key)) {
            wantedWords.put(key, new TreeSet());
         }
         wantedWords.get(key).add(word);
         System.out.print("Do you want to continue on? :");
         choice = console.next();
         while (choice.equals("Yes")) {
            System.out.print("What is your next word? :");
            word = console.next();
            wantedWords.get(key).add(word);
            System.out.print("Do you want to continue on? :");
            choice = console.next();
         }
         System.out.print("Do you want to choose another type of word? :");
         choice = console.next();
      }
      return wantedWords;
   }
}