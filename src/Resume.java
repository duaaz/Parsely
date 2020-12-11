//Duaa Zaheer, Davin Kyi, Allison Li
//11/20/2020
//Resume Reader 


//Method description  
/*
This is a program that will be able to give you a short anaylsis of a resume in
which you will be looking at. This will give a company a quick anaylsis on which
resume in which they looked at is most highly ranked, in other words, which one has
more points when compared to the other resumes given. Also, the program can also 
give you the amount of experience you've had with a certain keyword, such as java.
This program will also allow you to see if the basic skills, such as Java are given
in the resume, and this is done by checking to see if it is contained within the 
resume given. Last and not least, it will give you all the words that are contained
within the resume you are looking at.  

*/ 

import java.util.*;
import java.io.*;

public class Resume {
   //name of the person who wrote the resume  
   private String name;
   //this most likely will just tell you all the words it contains
   private Set<String> keyWords;
   //<Keyword, Experience>
   private Map<String, Integer> experience; 
   //these are the months in the years, and they are abbreviated  
   private ArrayList<String> months;
   //this is the scanner we will need 
   private Scanner scanner;
   //this is the file of the resume  
   private File resumeData;  
 
   public Resume(File file) throws FileNotFoundException {
      scanner = new Scanner(file);
      this.name = "";
      String firstName = scanner.next();
      String lastName = scanner.next();
      this.name = firstName + " " + lastName; 
      this.resumeData = file;
      //initalizing the Set of keywords in which we will be returning later 
      this.keyWords = new HashSet<String>();
      while(scanner.hasNext()) {
         String word = normalize(scanner.next());
         try {   
            Integer.parseInt(word);
            //in a try catch, the exception is an object  
         } catch (NumberFormatException e) {
            this.keyWords.add(word);
         }
      }
      this.experience = new TreeMap<String, Integer>();
      this.months = new ArrayList<String>();	      
      //first one is 11/30/2015, Nov 27, 2002 
      String [] month = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"}; 
      for (int i = 0; i < month.length; i++) {
         months.add(month[i]);
      }
   }
   
   // returns first line in resume file (assuming first line is candidate's full name)
   public String getName() {
      return this.name;
   }
   
   public HashSet<String> getKeyWords() {
      return (HashSet<String>) keyWords;
   }
   
   //this will allow you to get all of the individual keywords amount of experience
   //by giving you the total time in which has shown up with the word   
   public Map<String, Integer> getExperience() throws FileNotFoundException {
      scanner = new Scanner(this.resumeData);
      while(scanner.hasNextLine()) {
         int totalMonths = 0;
         String line = scanner.nextLine();
         Scanner newScan = new Scanner(line);
         //we only want to put the keyword once into the map
         //and then from that one line, give that one keyword a time 
         //this will allow me to only change the times on the keywords found on
         //the one line 
         Set<String> foundWords = new TreeSet<String>();  
         while (newScan.hasNext()) {  
            String word = normalize(newScan.next()); 
            if (months.contains(word)) {
               totalMonths = calculateMonths(word, newScan);
            } else {
               foundWords.add(word);
            }
        }
       
        for (String keyWord : foundWords) {
           try { 
              //if it is not an interger, it will throw an exeception, number format exception
              //parseInt, if you are able to parse it, you will not do anything with it 
              //so if you don't run into an error, you will do what is in the try block, else you do
              //whatever was in the catch block  
              //catch, what you want to have happen when the exceptions in the try block occur   
              Integer.parseInt(keyWord);
           //in a try catch, the exception is an object  
           } catch (NumberFormatException e) {
              if (!experience.containsKey(keyWord)) {
                 experience.put(keyWord, 0);
              }
              //for each of the words that were in that one line
              //we will add the additional time
              //but, we do not want to add the time to every word found,
              //unless if it was on the line  
              experience.put(keyWord, experience.get(keyWord) + totalMonths);
           }
        }
      }
      //this is to remove all the cases where the experience is just 0  
      Set<String> remove = new HashSet<String>();
      for (String keyWords : experience.keySet()) {
         int time = experience.get(keyWords);
         if (time == 0) {
            remove.add(keyWords);
         }
      }
      experience.keySet().removeAll(remove);
      return experience;
   }
   
   public String normalize(String word) {
      word = word.toLowerCase(); 
      word = word.trim();
      char end = word.charAt(word.length() - 1);
      if (!Character.isLetterOrDigit(end)) {
         word = word.substring(0, word.length() - 1);
      }
      return word;
   }
   
   public int calculateMonths(String word, Scanner newScan) {
      int totalMonths = 0;
      String startMonth = word;
      int startYear = newScan.nextInt();
      newScan.next(); //throws away the dash in the text 
      String endMonth = newScan.next();
      int endYear = 0;
      if (endMonth.equalsIgnoreCase("present")){
         Calendar calendar = new GregorianCalendar();
         endMonth = months.get(calendar.get(calendar.MONTH));
         endYear = calendar.get(calendar.YEAR);  
      } else {  
         endMonth = endMonth.substring(0, 3);
         endYear = newScan.nextInt();
      }
      totalMonths += (endYear - startYear) * 12;
      //approx 30 days for each month, we can figure this out later as well
      totalMonths += months.indexOf(endMonth) - months.indexOf(startMonth);
      return totalMonths;
   }

   public Map<String, Set<String>> containsWanted(Map<String, Set<String>> keyWord) {
      Map<String, Set<String>> foundKeyWords = new TreeMap<String, Set<String>>();
      for (String type : keyWord.keySet()) {
         Set<String> possibleKeyWords = keyWord.get(type);
         for (String possibleKeyWord : possibleKeyWords) {
            if (keyWords.contains(possibleKeyWord)) {
               if (!foundKeyWords.containsKey(type)) {
                  foundKeyWords.put(type, new HashSet<String>());
               }
               foundKeyWords.get(type).add(possibleKeyWord);
            }
         } 
      }
      return foundKeyWords;     
   }
}