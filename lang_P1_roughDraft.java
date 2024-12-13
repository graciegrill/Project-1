import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class lang_P1_roughDraft{
    /*
     * This is a function which finds all the letters denoted with a 0 in the guess word.
     * Parameters: String inputWord is the word that is analyzed for the 0 letters.
     * Returns: ArrayList<Character> charsNotInWord is an arraylist of all the characters associated with a 0.
     */
    public static ArrayList<Character> analyzeNegative(String inputWord){
        ArrayList<Character> charsNotinWord = new ArrayList<Character>();
        for (int i = 0; i<5;i++){
            if (inputWord.charAt(i+5) == '0'){
                charsNotinWord.add(inputWord.charAt(i));
            }
            else{
                continue;
            }
        }
        return charsNotinWord;
    }
    /*
     * This is a function which analyzes a guess word and finds all those letters associated with a 1 and their respective indices.
     * Parameters: String inputWord is the word that is analyzed for the 1 letters.
     * Returns: ArrayList<Character> charsInWord is an arraylist of all the characters associated with a 1 followed by their indices (e.g. a,1,b,2).
     */
    public static ArrayList<Character> analyzePositive(String inputWord){
        ArrayList<Character> charsInWord = new ArrayList<Character>();
        for (int i = 0; i<5;i++){
            if (inputWord.charAt(i+5) == '1'){
                charsInWord.add(inputWord.charAt(i));
                charsInWord.add((char)(i));
            }
            else{
                continue;
            }
        }
        return charsInWord;
    }
    /*
     * This is a function which analyzes a guess word and finds all those letters associated with a 2 and their respective indices.
     * Parameters: String inputWord is the word that is analyzed for the 2 letters.
     * Returns: ArrayList<Character> charPos is an arraylist of all the characters associated with a 2 followed by their indices (e.g. a,1,b,2).
     */
    public static ArrayList<Character> analyzeCharactersandPositions(String inputWord){
        ArrayList<Character> charPos = new ArrayList<Character>();
        for (int i = 0; i<5;i++){
            if (inputWord.charAt(i+5) == '2'){
                charPos.add(inputWord.charAt(i));
                charPos.add((char)(i));
            }
            else{
                continue;
            }
        }
        return charPos;
    }
    /*
     * This is a function which analyzes the words in the list of possibilities and compares them against the 0 ArrayList, 1 ArrayList, and 2 ArrayList to see if the word is posssible. 
     * Parameters: String analysis word is the word to be checked. ArrayList<Character>charsInWord, ArrayList<Character>oneChars, ArrayList<Character>twoChars are the 0 related ArrayList, the 1 related ArrayList, and the 2 related ArrayList respectively.
     * Returns: Boolean isNotInWord is a boolean value which is returned as true if a particular character in the 0 list doesn't appear in the word or appears in the other two lists otherwise.
     * This accomodates a situation in which one letter is both green and gray in the same word. It is returned as false otherwise.
     */
    public static Boolean compareNegatives(String analysisWord, ArrayList<Character>charsInWord, ArrayList<Character>oneChars, ArrayList<Character>twoChars){
        Boolean isNotInWord = true;
        for (int i = 0; i<charsInWord.size();i++){
            int val = analysisWord.indexOf(charsInWord.get(i));
            if(val<0 || oneChars.contains(charsInWord.get(i)) || twoChars.contains(charsInWord.get(i))){
                continue;
            }
            else{
                isNotInWord = false;
                break;
            }
        }
        return isNotInWord;

    }
    /*
     * This is a function which analyzes the words in the list of possibilities and compares them against an ArrayList to see if the word is posssible. 
     * Parameters: String analysis word is the word to be checked. ArrayList<Character>charsInWord is the 1 related ArrayList.
     * Returns: Boolean isNotInWord is a boolean value which is returned as true if a particular character appears in the word and not at the value designated as incorrect. It is false otherwise.
     */
    public static Boolean comparePositives(String analysisWord, ArrayList<Character>charsInWord){
        Boolean isInWord = true;
        for (int i = 0; i<charsInWord.size(); i+=2){
            if(analysisWord.contains(charsInWord.get(i).toString()) && analysisWord.charAt((int)charsInWord.get(i+1)) != charsInWord.get(i)){
                continue;
            }
            else{
                isInWord = false;
                break;
            }
        }
        return isInWord;

    }
    /*
     * This is a function which analyzes the words in the list of possibilities and compares them against an ArrayList to see if the word is posssible. 
     * Parameters: String analysis word is the word to be checked. ArrayList<Character>charsInWord is the 2 related ArrayList.
     * Returns: Boolean doesFit is a boolean value which is returned as true if a particular character appears in the word and at the value designated as correct. It is false otherwise.
     */
    public static Boolean wordFit(String analysisWord, ArrayList<Character> charPos){
        Boolean doesFit = true;
        for (int j = 0; j<charPos.size();j+=2){
            if (analysisWord.charAt(charPos.get(j+1)) == charPos.get(j)){
                continue;
            }
            else{
                doesFit = false;
                break;
            }
        }
        return doesFit;
    }
    /*
     * This is a function which checks that the guess string starts with 5 letters and ends with 5 numbers (0,1, or 2).
     * Parameters: String inputWord is the word to be checked.
     * Returns: Boolean valid which is indicative of validity or lack thereof.
     */
    public static Boolean wordValid(String inputWord){
        Boolean valid = true;
        for (int i = 0; i<5;i++){
            if (Character.isLetter(inputWord.charAt(i)) && (((inputWord.charAt(i+5)) =='0') || ((inputWord.charAt(i+5)) =='1')|| ((inputWord.charAt(i+5)) =='2'))){
                continue;           
            }
            else{
                valid = false;
                break;
            }
        }
        return valid;
    }


    public static void main(String[] args){
        ArrayList <String> acceptableWords = new ArrayList<String>(); //ArrayList of the words that are returned by the analysis of each guess word.
        ArrayList <String> finalWords = new ArrayList<String>(); //Consensus list of the words that each guess word agreees on.
        Scanner in = new Scanner(System.in);

        //Input guess words as [five letter word][numeric sequence], [...]. Other forms of input will be considered incorrect.
        System.out.println("Enter your input: ");

        //Splits guesses according to acceptable format
        String[] inputWordList = in.nextLine().toLowerCase().split(", ");
            for (int i = 0; i<inputWordList.length;i++){
                String inputWord = inputWordList[i];

                //Checks if length fits and whether the word is a valid guess.
                if(inputWord.length() == 10 && wordValid(inputWord)){
                    //Creates the appropriate arrays for the guess.
                    ArrayList<Character> negativeChars = analyzeNegative(inputWord);
                    ArrayList<Character> positiveChars = analyzePositive(inputWord);
                    ArrayList<Character> positionChars = analyzeCharactersandPositions(inputWord);
                    
                    ArrayList<String> incorrectWords = new ArrayList<String>();

                        //This adds any guesses that are not the correct word to a list to ensure that they aren't listed as a possibility subsequently. 
                        if(positionChars.size()<5){
                            incorrectWords.add(inputWord);
                        }

                        in.close();

                            try{
                            //Opens the appropriate text file for possible words.    
                            File fiveWords = new File("fives.txt");
                            Scanner wordFile = new Scanner(fiveWords);

                            //Checks the words against the lists and adds to a list of acceptable words.
                            while(wordFile.hasNextLine()){
                                String testWord = wordFile.nextLine();
                                    if (!incorrectWords.contains(testWord) && compareNegatives(testWord, negativeChars, positiveChars, positionChars) && comparePositives(testWord,positiveChars) && wordFit(testWord, positionChars)&& i<1){
                                        acceptableWords.add(testWord);
                                    }
                                    else if (!incorrectWords.contains(testWord) && compareNegatives(testWord, negativeChars, positiveChars, positionChars) && comparePositives(testWord,positiveChars) && wordFit(testWord, positionChars) && acceptableWords.contains(testWord)){
                                        acceptableWords.add(testWord);
                                    }
                            }
                            
                            wordFile.close();
                            }
                            catch(FileNotFoundException x){
                                System.out.println("File not found");
                            }}
                    else{
                        //This is what is done if input is empty or invalid.
                        try{
                            File fiveWords = new File("fives.txt");
                            Scanner wordFile = new Scanner(fiveWords);
                            while(wordFile.hasNextLine()){
                                    System.out.println(wordFile.nextLine());
                                }
                            System.out.println("You either have invalid input or did not submit any guesses.");
                            wordFile.close();
                                    }

                                    catch(FileNotFoundException x){
                                        System.out.println("File not found");
                                    }
                            }
                        //Creates a hashmap of the acceptable words to track how often they occur. If they occur as many times as the number of guesses, it is a consensus guess.
                        HashMap<String, Integer> wordCounter = new HashMap<>();
                        for (String word : acceptableWords) {
                            if(wordCounter.containsKey(word)){
                                wordCounter.put(word, wordCounter.get(word)+1); 
                            }
                            else{
                                wordCounter.put(word, 1);
                            }
                            }
                        for (Map.Entry<String, Integer> entry : wordCounter.entrySet()) {
                            if (entry.getValue().equals(inputWordList.length)) {
                                finalWords.add(entry.getKey());
                            }
                            else{
                                continue;
                            }
                        //Prints the final list of acceptable, consensus words amongst all the guesses.
                        System.out.println(finalWords.get(finalWords.size()-1));
                        }
                    }}
                    }
