
// this class contains the question, the right answer, and the other 3 wrong answer options
public class Question extends QuestionsStorage{
	
	// attributes
	private String[] qustionAndAnswers; // this string array variable contains the question, right answer, and 3 other wrong answers option

	
	//constructor
	Question(){
		qustionAndAnswers = new String[5];	
		qustionAndAnswers = this.getRandomQuestion();										  
	}
	
	// this method returns the question
	public String getQuestion() {
		return qustionAndAnswers[0];
	}
	
	public String getRightAnswer() {
		return qustionAndAnswers[1];
	}
	
	// this method returns the answers options of the question as String[] array
	public String[] getAnsOptions() {
		int numOfAnsOptions = 4; // the number of answers options
		String[] str = new String[numOfAnsOptions];
		for (int i=0; i<numOfAnsOptions; i++) {
			str[i] = qustionAndAnswers[i+1];
		}
		return str;
	}
	
	// this method returns true if the input answer is the right answer of the question otherwise returns false 
	public boolean isRightAnswer(String inputAns) {
		//return inputAns == qustionAndAnswers[1]; // the right answer is qustionAndAnswers[1]
		return qustionAndAnswers[1].equals(inputAns); // the right answer is qustionAndAnswers[1]
	}
	
	//  this method returns false if the question storage is empty returns true
	public boolean hasNextQuestion() {
		if(this.isQuestionsStorageEmpty()) {
			return false;
		}
		
		else {
			return true;
		}
	}
	
	// this method sets a new question from the QuestionsStorage class for the trivia game
	public void setNewQuestion() {
		qustionAndAnswers = this.getRandomQuestion();
	}

}
