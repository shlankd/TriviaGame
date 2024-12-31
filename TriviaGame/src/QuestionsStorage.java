import java.util.*;
import java.util.Random; 
import java.io.File;
import java.io.FileNotFoundException;


// this class contains questions (that are from text file) for trivia game
public class QuestionsStorage {
	
	// attributes
	private ArrayList<String> questionsStorage; // every line from the text file "trivia" will be an element at the string array list questionsStorage
	
	//constructor
	QuestionsStorage(){
		questionsStorage = new ArrayList<String>();
		setQuestionsStorageFromTxtFile();
	}
	
	public boolean isQuestionsStorageEmpty(){
		return questionsStorage.isEmpty();
	}
	
	// this method returns the question with the answer options as String[] array and removes these elements from questionsStorage for not return the same question
	public String[] getRandomQuestion(){
		
		int amountOfLines = 5; // every question has 4 answer options, so the amount of lines/element to draw from the string array list questionsStorage is 5
		String[] randQuestion = new String[5]; // the question is at the first element, the right answer option is second and the rest answers options are third, fourth, and fifth element 
		
		Random random = new Random();
		int lastQuestionIndex = questionsStorage.size() - 5; // according to the text file construct the last 4 lines are answer options and the fifth line from the end is the last question
		int amountOfQuestions = lastQuestionIndex/5 + 1; // including the question at index 0
		int randomQuestionIndex = 5*random.nextInt(amountOfQuestions); // gets a random question index as we know that the question indices are 0,5,10...
		
		for(int i = 0; i < amountOfLines; i++) {
			randQuestion[i] = questionsStorage.get(i + randomQuestionIndex); // adds the random questions with the answer options
		}
		
		removeUsedQustionAtInd(randomQuestionIndex); // removes the same questions with the answer options for not repeat the same questions at the trivia game
		
		return randQuestion; 		
	}
	
	// this method removes question with the answer options that had been used for the trivia game
	private void removeUsedQustionAtInd(int qustionIndex) {// gets the index of the question in the questionsStorage  
		int amountOfLines = 5; // the amount of lines to remove from questionsStorage when every element is a line from the text file
		for(int i = 0; i < amountOfLines; i++) {
			questionsStorage.remove(qustionIndex); // the next line will be at the same index as the removed line from the previous iteration 
		}
	}
	
	// this method sets the questions from text file to the string array list questionsStorage for the trivia game
	private void setQuestionsStorageFromTxtFile() {
		File textFile = null;
		Scanner input = null;
		String filePath = "C:\\Users\\Dor Shlank\\eclipse-workspace\\TriviaGame\\trivia.txt"; // the address path of the text file
		
		try {
			
			textFile = new File(filePath); // every 5 lines in the text file "trivia" contains: 
										   // question at the first line, 
										   // the right answer option at the second line,
										   // wrong answer option at the third line,
										   // another wrong answer option at the fourth line,
			                               // the last wrong answer option at the fifth line
		
		}catch(NullPointerException e){System.out.println("the filePath argument is null");}
		
		try {
			
			input = new Scanner(textFile);
			
		}catch(FileNotFoundException e) {System.out.println("could not find the source of the file");}
		
		try {
			
			while(input.hasNextLine()) { // when there is empty line from the text file it indicates the end of the text file
				String st = input.nextLine(); // store the current line from the text file to st
				questionsStorage.add(st); // add the line from the text file to the string array listquestionsStorage
			}
			
			input.close(); // close the input stream
			
		}catch(IllegalStateException state) {System.out.println("the scanner has been closed due to an IllegalStateException");}
		 catch(NoSuchElementException e) {System.out.println("no text to read is available");}
	}
}
