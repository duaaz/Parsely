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
 
   //pre: this method should document all the nessecary information about the canidate, 
   //else a FileNotFoundException will be thrown
   //post: this will store the name of the person, which are the first two words of a resume
   //file in which will be passed in. this will also store the file of the resume to later 
   //reference to. this will also get all of the keywords that are non-integers, that are 
   //normalized with our normalized method:
   //if we had a case where we had java,
   //with the normalize method will turn it into java for example
   //we will also initalize the months arraylist and experience map, which will be storing 
   //all of the keywords and their associated experiences
   //Parameters:
   //File file - this is the resume file in which will contain all the informaton nessecary 
   //about a given canidate  
   public Resume(File file) throws FileNotFoundException {
      scanner = new Scanner(file);
      this.name = "";
      String firstName = scanner.next();
      String lastName = scanner.next();
      this.name = firstName + " " + lastName; 
      this.resumeData = file;
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
      String [] month = {"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"}; 
      for (int i = 0; i < month.length; i++) {
         months.add(month[i]);
      }
   }
   
   //first line in resume file is the name (assuming first line is candidate's full name)
   //returns the name of the canidate 
   public String getName() {
      return this.name;
   }
   
   //returns the Set (specifically HashSet) keywords of the resume
   public HashSet<String> getKeyWords() {
      return (HashSet<String>) keyWords;
   }
   
   //pre: will return all of the keywords in the resume, that are non intergers, and have a non-zero 
   //amount of time associated with it, else if the file does not exist, a FileNotFoundException will
   //be thrown
   //post: we will return a Map<String, Integer> which has all of the keywords within the map that are non-intergers
   //and have a non-zero time associated with it 
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
   
   //post: this will normalize a word that is passed in and will return
   //the normalized version of the word  
   /*
   for example if we had 
   experience,
   it would turn into 
   experience
   */
   //Parameters:
   //String word - this is the word in which we will be normalizing 
   public String normalize(String word) {
      word = word.toLowerCase(); 
      word = word.trim();
      char end = word.charAt(word.length() - 1);
      //we want to see if the ending character is either
      //a digit or non alphabetical character, if so, we 
      //will remove it  
      if (!Character.isLetterOrDigit(end)) {
         word = word.substring(0, word.length() - 1);
      }
      return word;
   }
   
   //first of all where is an example month and year that can be read in by the scanner:
   //    example ---> Nov 2002
   //post: this will calculate the months given a String which is the starting month
   //and a scanner that will start looking at the rest of the tokens in that line, which
   //will be tokens that will help us calculate the time, returns an integer representing
   //the total time associated with the one line 
   //Parameters:
   //String word - this is the String representing the first month 
   //Scanner newScan - this will allow us to read the rest of the line, which allows
   //us to calcualte the time  
   public int calculateMonths(String word, Scanner newScan) {
      int totalMonths = 0;
      String startMonth = word;
      int startYear = newScan.nextInt();
      newScan.next(); //throws away the dash in the text 
      String endMonth = newScan.next();
      int endYear = 0;
      //this will give you the present date in the case that the 
      //text said present  
      if (endMonth.equalsIgnoreCase("present")){
         Calendar calendar = new GregorianCalendar();
         endMonth = months.get(calendar.get(calendar.MONTH));
         endYear = calendar.get(calendar.YEAR);  
      } else {  
         endMonth = endMonth.substring(0, 3);
         endYear = newScan.nextInt();
      }
      //this is how we will be calculating the total amount of months associated with 
      //each of the lines that are passed in, by looking at the total years and the 
      //difference between the months  
      totalMonths += (endYear - startYear) * 12;
      //approx 30 days for each month, we can figure this out later as well
      totalMonths += months.indexOf(endMonth) - months.indexOf(startMonth);
      return totalMonths;
   }
   
   //post: this will return a Map<String, Set<String>> that represents all of the categories 
   //of words that the employer wanted in which you had. For example if the employeer wanted
   //desired words and required words, this will allow the employeer to find if you had words
   //in these categories. And the map will contain each cateogry along with the set of words
   //that they found for each category.
   //Parameters:
   //Map<String, Set<String>> - this is the map that has all the keys as String, which is the 
   //cateogry of the words, and Set<String> - this is the set of words associated with each 
   //of the category of words 
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