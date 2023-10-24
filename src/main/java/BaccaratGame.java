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
	public double totalWinnings;
	public double currentMoney; // add this
	public String userChoice;
	public StartMenu initialScreen;


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	// this class creates option Bar
	public static class optionBar{
		static private MenuBar menuBar;
		static private Menu optionMenu;

		static private MenuItem exitButton;
		static private MenuItem restartButton;

		// use this to create an option Bar
		public static void createOptionBar(){
			menuBar = new MenuBar();
			optionMenu = new Menu("Options");
			exitButton = new MenuItem("Exit");
			restartButton = new MenuItem("Restart");
			optionMenu.getItems().addAll(exitButton, restartButton);
			menuBar.getMenus().add(optionMenu);
		}

		// use this to return the newly created optionBar
		public static MenuBar getOptionBar(){
			return menuBar;
		}

	}




	public static class StartMenu {

		//StackPane root;
		static Button startButton;
		static Label title;
		static Label author;
		static Label author2;
		static VBox words; // this is the root node



		static Scene startScene;
		// this function create a startMenu scene;
		public static void makeStartMenu() {


			startButton = new Button("Start");

			title = new Label("Baccarat Game");
			author = new Label("By: Manh Phan");
			author2 = new Label("Lei Chen");

			MenuBar optiontab = optionBar.getOptionBar();
			HBox menuBox = new HBox(optiontab);
			menuBox.setAlignment(Pos.BASELINE_RIGHT); // position the menu bar to the right

			VBox box2 = new VBox(title, author, author2);
			VBox box3 = new VBox(startButton);

			// set the font size
			title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
			author.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
			author2.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 20));
			startButton.setFont(Font.font("Robotic", FontWeight.BOLD, FontPosture.REGULAR, 40));
			startButton.setPrefSize(200,100);

			words = new VBox(menuBox,box2,box3); // put everything inside the root node

			box3.setAlignment(Pos.BOTTOM_CENTER);
			box2.setAlignment(Pos.CENTER);
			box2.setMinHeight(400);
			words.setMinSize(700,700);
			words.setAlignment(Pos.TOP_CENTER);


			startScene = new Scene(words, 700, 700);

		}

		public static Scene switchScene() {
			return startScene;
		}
	}


	public static class gameScreen{
		static BorderPane root;

		static Scene gameScene;
		static Label curWinning;
		static Label totalWinnings;
		static Label curMoney;
		static Button nextButton;
		static Button dealButton;

		static Label result;

		static HBox playerCards;
		static Label playerScore;
		static HBox bankerCards;
		static Label bankerScore;
		public static void makeGameScreen(){

			root = new BorderPane();
			root.setMinSize(700,700);

			curWinning = new Label("Round Result");
			//curWinning.setDisable(true);
			totalWinnings = new Label("Round: 0\nWin: 0");
			curMoney = new Label("$100");
			curWinning.setStyle("-fx-border-color: black;");
			curMoney.setStyle("-fx-border-color: black;");
			totalWinnings.setStyle("-fx-border-color: black;");

			curWinning.setMinSize(200,250);
			curMoney.setMinSize(200,200);
			totalWinnings.setMinSize(200,250);
			VBox leftBox = new VBox(curWinning, totalWinnings, curMoney);
			leftBox.setMinSize(200,700);
			leftBox.setAlignment(Pos.TOP_LEFT);

			MenuBar options = optionBar.getOptionBar();
			HBox topBox = new HBox(options);
			topBox.setAlignment(Pos.BASELINE_RIGHT);

			nextButton = new Button("Next");
			nextButton.setMinSize(150,100);
			dealButton = new Button("Deal");
			dealButton.setMinSize(150,100);
			HBox bottomBox = new HBox(dealButton, nextButton);
			//bottomBox.setStyle("-fx-border-color: black;");
			bottomBox.setAlignment(Pos.BOTTOM_CENTER);
			bottomBox.setMaxWidth(500);
			bottomBox.setSpacing(100);

			playerCards = new HBox();
			playerCards.setSpacing(20);
			bankerCards = new HBox();
			bankerCards.setSpacing(20);
			playerScore = new Label("Score: 0");
			bankerScore = new Label("Score: 0");
			VBox playerStat =  new VBox(playerScore, playerCards);
			playerStat.setMinWidth(200);
			VBox bankerStat =  new VBox(bankerScore,bankerCards);
			bankerStat.setMinWidth(200);
			result = new Label("Results");
			result.setMinSize(100,100);
			HBox resultBox = new HBox(result);
			resultBox.setAlignment(Pos.BOTTOM_CENTER);

			HBox gameStats = new HBox(playerStat, bankerStat);
			gameStats.setSpacing(100);
			gameStats.setMinHeight(400);
			gameStats.setAlignment(Pos.CENTER);
			VBox middleBox = new VBox(topBox,resultBox, gameStats,bottomBox);
			middleBox.setMinHeight(700);
			middleBox.setStyle("-fx-border-color: black;");
			result.setAlignment(Pos.CENTER);
			root.setLeft(leftBox);
			root.setCenter(middleBox);

			gameScene = new Scene(root,700,700);
		}

		public static Scene getGameScene() {
			return gameScene;
		}

		public static void addCard(){
			Label card = new Label("King 13");
			card.setMinWidth(13);
			Label card2 = new Label("King 13");
			Label card3 = new Label("King 13");
			Label card4 = new Label("King 13");
			Label card5 = new Label("King 13");
			Label card6 = new Label("King 13");

			playerCards.getChildren().addAll(card,card2,card3);
			bankerCards.getChildren().addAll(card4,card5,card6);
		}

	}
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Baccarat Game");

		optionBar.createOptionBar();
		StartMenu.makeStartMenu();
		gameScreen.makeGameScreen();

		primaryStage.setScene(gameScreen.getGameScene());
		gameScreen.addCard();

		primaryStage.show();
		
				
		
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
		if (userChoice == winner) {
			return currentBet * 2;
		}
		
		return 0;
	}

}
