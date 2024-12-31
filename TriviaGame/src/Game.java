import java.util.*;

// this class contains methods for running the trivia game
public class Game extends Question{
	
	// attributes
	private int gamePoints; // the points that the user accumulate during the trivia game
	private boolean isEndGame; // this boolean variable indicates if the trivia game ended 
	private String[] randomAnsOptions; // a random permutation of the order of answer options
	
	// constructor
	public Game() {
		gamePoints = 0;
		isEndGame = false;
		randomAnsOptions = new String[this.getAnsOptions().length];
		permuteRandAnsOp();
		
	}
	
	// getters
	public boolean getIsEndGame() {
		return isEndGame;
	}
	
	public int getGamePoints() {
		return gamePoints;
	}
	
	public String[] getRandomAnsOptions() {
		return randomAnsOptions;
	}
	
	// this method increments the user's points by 10
	public void incrementPoints() {
		gamePoints = gamePoints + 10;
	}
	
	// this method decreases the user's points by 5
	public void decreasePoints() {
		gamePoints = gamePoints - 5;
	}
	
	// this method generates a random permutation of the order of answer options
	private void permuteRandAnsOp() {
		
		Random random = new Random();
		
		ArrayList<Integer> indexOfAnsOp = new  ArrayList<Integer>(); // this list array represents the index value of the answers options from the class questions
		
		indexOfAnsOp.add(0);indexOfAnsOp.add(1);indexOfAnsOp.add(2);indexOfAnsOp.add(3); // assignment of the index value 
		
		String[] str = this.getAnsOptions();
		
		for(int i=0; i<randomAnsOptions.length; i++) {
			int randInd = random.nextInt(indexOfAnsOp.size()); // gets a random index in range of the list array indexOfAnsOp  
			randomAnsOptions[i] = str[indexOfAnsOp.get(randInd)]; // sets the random answer option at randomAnsOptions[i]
			indexOfAnsOp.remove(randInd); // removes the random index value from the list array indexOfAnsOp for not repeat the same answer option
		}
	}
	
	
	// if there is more questions in the QuestionsStorage class the method sets a new question, otherwise the method sets the isEndGame variable true   
	public void nextQuestion() {
		
		if(this.hasNextQuestion()) {
			this.setNewQuestion();
			permuteRandAnsOp();
		}
		
		else {
			isEndGame = true;
		}
	} 
	
}
