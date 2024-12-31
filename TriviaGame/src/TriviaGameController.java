import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

// this class running the trivia game
public class TriviaGameController {
	
	private Stage stage;
	private Game game; // the game instance
	
    @FXML
    private AnchorPane scenePane;

    @FXML
    private Button aButton; // the aButton is the answer option of getRandomAnsOptions()[0]

    @FXML
    private Button bButton; // the bButton is the answer option of getRandomAnsOptions()[1]

    @FXML
    private Button cButton; // the cButton is the answer option of getRandomAnsOptions()[2]

    @FXML
    private Button dButton; // the dButton is the answer option of getRandomAnsOptions()[3]

    @FXML
    private Text textOne;

    @FXML
    private Text textTwo;
    
    @FXML
    private Button nextQuestion;
    
    // at every button pressed event the user input an answer and gets points
    
    @FXML
    void aPressed(ActionEvent event) {
    	
    	ansButtonEvent(aButton);
    }

    @FXML
    void bPressed(ActionEvent event) {
    	
    	ansButtonEvent(bButton);
    }

    @FXML
    void cPressed(ActionEvent event) {
    	
    	ansButtonEvent(cButton);
    }

    @FXML
    void dPressed(ActionEvent event) {
    	
    	ansButtonEvent(dButton);
    }
    
    void ansButtonEvent(Button ansButton) {
    	
    	String inputAns = ansButton.getText().substring(3); // the original answer string
    	
    	if(game.isRightAnswer(inputAns)){ // condition if the user input the correct answer
    		textTwo.setText("Correct. Press the next question button to continue");
    		ansButton.setStyle("-fx-background-color: Green"); // sets the button color in green 
    		game.incrementPoints(); // increments the user's points
    	}
    	else { // condition if the user input the wrong answer 
    		textTwo.setText("Wrong. Press the next question button to continue");
    		ansButton.setStyle("-fx-background-color: Red");// sets the button color in red 
    		game.decreasePoints(); // decrease the user's points
    	}
    	
    	setDisableAnsButtons(true); // all the answer buttons will Disable until the user continue to another question
    	nextQuestion.setDisable(false);
    	 	
   
    }
    
    @FXML
    void nextQuestionPressed(ActionEvent event) {
    	
    	game.nextQuestion(); // Perform the next question method
    	
    	if(game.getIsEndGame()) { // if after the next question method the variable isEndGame == true
    		endGame(); // perform the end game method
    	}
    	
    	else {
    		setDisplayQuestion();
    		setDisableAnsButtons(false); // enable all the answer buttons
    	}
    	
    }
    
    @FXML
    void quitPressed(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION); // creates an extra window of Alert type of confirmation
    	alert.setTitle("Quit");
    	alert.setHeaderText("You are about to quite the game");
    	alert.setContentText("Your points in the game so far is: " + game.getGamePoints() + "\n You can cancel and continue the game or press OK button to quit"); // message for the user
    	
    	if(alert.showAndWait().get() == ButtonType.OK) {
        	stage = (Stage) scenePane.getScene().getWindow(); // gets the window of trivia game
        	stage.close(); // close the window of trivia game
    	}
    }

    @FXML
    void restartPressed(ActionEvent event) {
    	game = new Game();
    	setDisplayQuestion();
    	setDisableAnsButtons(false); // enable the answer buttons when it is restart
    	nextQuestion.setDisable(false); // enable the next question button when it is restart
    }
    
    // this method sets the display of the question an the answers buttons
    public void setDisplayQuestion() {
    	
    	nextQuestion.setDisable(true);
    	textTwo.setText("Trivia Game Of World Cup");
    	textOne.setText(game.getQuestion());
    	
    	aButton.setStyle("-fx-background-color: paleturquoise  ");
    	bButton.setStyle("-fx-background-color: paleturquoise  ");
    	cButton.setStyle("-fx-background-color: paleturquoise  ");
    	dButton.setStyle("-fx-background-color: paleturquoise  ");
    	
    	aButton.setText("A. "+ game.getRandomAnsOptions()[0]);
    	bButton.setText("B. "+ game.getRandomAnsOptions()[1]);
    	cButton.setText("C. "+ game.getRandomAnsOptions()[2]);
    	dButton.setText("D. "+ game.getRandomAnsOptions()[3]);
    }
    
    void setDisableAnsButtons(boolean setDisable) {
    	aButton.setDisable(setDisable);bButton.setDisable(setDisable);cButton.setDisable(setDisable);dButton.setDisable(setDisable); // disable or enable the answers depend the setDisable value
    }
    
    void endGame() {
    	textTwo.setText("You Complete The Trivia Game And Youre Points Status is: " + game.getGamePoints());
    	textOne.setText("press restart/quit button if you want to restart/quit");
    	nextQuestion.setDisable(true); // disable the next question button when is the end of the game
    }
    
    public void initialize(){
    	Alert alert = new Alert(AlertType.INFORMATION); // creates an extra window of Alert type of information
    	alert.setTitle("Trivia Game");
    	alert.setHeaderText("Welcome to the trivia game about the World Cup tournament");
    	alert.setContentText("In every turn, there is a question and 4 possible answers to choose.\n"+"If your answer is right you get +10 points, otherwise you get -5 points." +"\n You can restart/quit the game by press the restart/quit button");
    	if(alert.showAndWait().get() == ButtonType.OK) {// displays the message in the alert window 
    		alert.close();
    	}
    	
    	game = new Game();
    	setDisplayQuestion();
    }

}
