import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

import javax.swing.*;
import java.util.ArrayList;



public class BaccaratGame extends Application {

	public ArrayList<Card> playerHand;
	public ArrayList<Card> bankerHand;
	public BaccaratDealer theDealer;
	public BaccaratGameLogic gameLogic;
	public double currentBet;
	public int numWins;
	public int numRounds;
	public double currentMoney;
	public String userChoice;
	public StartMenu initialScreen;

	public static Stage primaryStage;

	public currentBetScene betScene;
	public OptionBar optionObject;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}





	//feel free to remove the starter code from this method
	@Override
	public void start(Stage curPrimaryStage) throws Exception {
		// TODO Auto-generated method stub


		primaryStage = curPrimaryStage;
		primaryStage.setTitle("Baccarat Game");

		this.currentMoney = 1000;
		this.numWins = 0;
		this.numRounds = 0;


		StartMenu newStart = new StartMenu(this);
	

		primaryStage.setScene(newStart.getStartScene());
		primaryStage.show();
			
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	// reset the game
	public void reset() {
		playerHand = null;
		bankerHand = null;
		theDealer = null;
		gameLogic = null;
		currentBet = 0;
		numWins = 0;
		numRounds = 0;
		currentMoney = 1000;
		userChoice = "N/A";
	}
	
	// check if the hands are in natural case
	public boolean checkNatural() {
		int playerScore = gameLogic.handTotal(playerHand);
		int bankerScore = gameLogic.handTotal(bankerHand);
		if (playerScore == 9 || playerScore == 8 || bankerScore == 9 || bankerScore == 8) {
			return true;
		}
		return false;
	}
	
	// determine if the user win or lost their bet and return the amount won or lost based on the value in currentBet
	public double evaluateWinnings() {
		String winner = gameLogic.whoWon(playerHand, bankerHand);
		
		// decide the money user win or lose
		if (userChoice.equals(winner)) {
			return currentBet * 2;
		}
		
		return 0;
	}

}
