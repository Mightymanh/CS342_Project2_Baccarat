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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import java.io.FileNotFoundException;
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

	public String Winner;
	public Card playerThirdCard;
	public int curPhase = 0;

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
		reset();



		StartMenu newStart = new StartMenu(this);
	

		primaryStage.setScene(newStart.getStartScene());
		primaryStage.show();
			
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	// reset the game
	public void reset() {
		playerHand = new ArrayList<>();
		bankerHand = new ArrayList<>();
		theDealer = new BaccaratDealer();
		theDealer.generateDeck();
		theDealer.shuffleDeck();
		gameLogic = new BaccaratGameLogic();
		currentBet = 0;
		numWins = 0;
		numRounds = 0;
		currentMoney = 1000;
		curPhase = 0;
		playerThirdCard = null;
		Winner = null;
		userChoice = "N/A";
	}

	// when user press next button on the game screen, this function allows a small reset to happen
	public void again(){
		playerHand.clear();
		bankerHand.clear();
		playerThirdCard = null;
		Winner = null;
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
		Winner = gameLogic.whoWon(playerHand, bankerHand);
		
		// decide the money user win or lose
		if (userChoice.equals(Winner)) {
			return currentBet * 2;
		}
		
		return 0;
	}

	// show the winner and end the game
	public void endGame(GameScreen screen) {
		this.currentMoney+= this.evaluateWinnings();

		screen.dealButton.setDisable(true);

		if (this.Winner.equals(userChoice)){
			numWins++;
			screen.result.setText("Winner: "+this.Winner+ "\n You won the bet!!");
		} else {
			screen.result.setText("Winner: "+this.Winner+ "\n You Lose the bet!!");
		}
		numRounds++;
		screen.numWinField.setText("Rounds won: " + this.numWins);
		//curWinning.setDisable(true);
		screen.numRoundField.setText("Rounds played: " + this.numRounds);
		screen.curMoneyField.setText("Your money $"+this.currentMoney);
		screen.nextButton.setDisable(false);
		this.curPhase = 0;
	}

	/*
	 * Deal button split into 3 phase
	 * 1st phase(curDeal == 0): each gets 2 cards. If natural, then we dont proceed furthur
	 * 2nd phase: check if player can draw 3rd card
	 * 3rd Phase: check if bank can draw 3rd card. Then evavulate winner
	 * */
	public void gamePhase(GameScreen screen) throws FileNotFoundException {
		// 1st phase
		if (this.curPhase==0) {
			// deal the first 2 cards
			ArrayList<Card> curdeal =  this.theDealer.dealHand();
			this.playerHand.add(curdeal.get(0));
			this.playerHand.add(curdeal.get(1));
			screen.addCard(0, curdeal.get(0));
			screen.addCard(0, curdeal.get(1));
			curdeal = this.theDealer.dealHand();
			this.bankerHand.add(curdeal.get(0));
			this.bankerHand.add(curdeal.get(1));
			screen.addCard(1, curdeal.get(0));
			screen.addCard(1, curdeal.get(1));

			// update the score for player and banker
			screen.updateScore();
			this.curPhase++;
			
			// check whether the current hands are natural, if so then end the game
			if(this.checkNatural()) {
				endGame(screen);
			}
		} else if(this.curPhase == 1){ // second phase
			if(this.gameLogic.evaluatePlayerDraw(this.playerHand)) {
				this.playerThirdCard = this.theDealer.drawOne();
				this.playerHand.add(this.playerThirdCard);
				screen.addCard(0,this.playerThirdCard);

			} else if (!this.gameLogic.evaluateBankerDraw(this.bankerHand,playerThirdCard)) {
				endGame(screen);
			} else {
				screen.result.setText("Player cannot draw a card\n Press the Deal Button again");
			}
			screen.updateScore();
			this.curPhase++;
		} else if (this.curPhase == 2) { // third phase
			if (this.gameLogic.evaluateBankerDraw(this.bankerHand,this.playerThirdCard)) {
				this.bankerHand.add(this.theDealer.drawOne());
				screen.addCard(1,this.bankerHand.get(2));

			}
			screen.updateScore();
			endGame(screen);
		}
	}

}
