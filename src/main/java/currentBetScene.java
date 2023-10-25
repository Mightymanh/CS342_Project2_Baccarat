import javafx.scene.Scene;

// UI components
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

// UI layouts
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
// Event handlers
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//
import javafx.geometry.Pos;

public class currentBetScene {
	
	// to keep track of game
	private BaccaratGame game;
	
	// UI components
	private Label lb1;
	private Label lb2;
	private TextField betAmountField;
	private Button b1;
	private Button b2;
	private Button b3;
	private Button confirmButton;
	private TextField userMoneyField;
	private TextField numRoundField;
	private TextField numWinField;
	private MenuBar mb;
	private Menu options;
	private MenuItem exit;
	private MenuItem freshStart;
	
	// UI layout
	private HBox betFieldBox;
	private HBox confirmBox;
	private HBox choiceRow;
	private VBox gameStats;
	private VBox mainPage;
	private HBox root;
	
	// Scene
	private Scene scene;
	
	
	// set up private variables for UI components and UI layout
	private void initPrivateVar() {
		// init Label
		lb1 = new Label("Bet amount");
		lb2 = new Label("Choose a side");
		
		// init Button
		b1 = new Button("Player");
		b2 = new Button("Draw");
		b3 = new Button("Banker");
		confirmButton = new Button("Confirm");
		
		// init TextField
		betAmountField = new TextField();
		betAmountField.setPromptText("Enter the amount you want to bet");
		userMoneyField = new TextField("Your money: $" + game.currentMoney);
		userMoneyField.setEditable(false);
		numRoundField = new TextField("Rounds played: " + game.numRounds);
		numRoundField.setEditable(false);
		numWinField = new TextField("Rounds won:" + game.numWins);
		numWinField.setEditable(false);
		
		// init menu options
		exit = new MenuItem("Exit");
		freshStart = new MenuItem("Fresh start");
		options = new Menu("Options");
		options.getItems().addAll(exit, freshStart);
		mb = new MenuBar(options);
		
		// init layout
		betFieldBox = new HBox(betAmountField);
		confirmBox = new HBox(confirmButton);
		choiceRow = new HBox(b1, b2, b3);
		gameStats = new VBox(userMoneyField, numRoundField, numWinField);
		mainPage = new VBox(lb1, betFieldBox, lb2, choiceRow, confirmButton);
		root = new HBox(gameStats, mainPage, mb);
	}
	
	private void initActionEvent() {
		// if user choose an option, set game.userChoice = that option, highlight that button
		b1.setOnAction((ActionEvent e) -> {
				// unhighlight all three buttons
				b1.setStyle("-fx-base: #5B71D9;");
				b2.setStyle("-fx-base: #5B71D9;");
				b3.setStyle("-fx-base: #5B71D9;");
				
				// highlight this button
				b1.setStyle("-fx-base: #FEE715;");
				
				// set userChoice
				game.userChoice = "Player";
		});
		b2.setOnAction((ActionEvent e) -> {
				// unhighlight all three buttons
				b1.setStyle("-fx-base: #5B71D9;");
				b2.setStyle("-fx-base: #5B71D9;");
				b3.setStyle("-fx-base: #5B71D9;");
				
				// highlight this button
				b2.setStyle("-fx-base: #FEE715;");
				
				// set userChoice
				game.userChoice = "Draw";
		});
		b3.setOnAction((ActionEvent e) -> {
				// unhighlight all three buttons
				b1.setStyle("-fx-base: #5B71D9;");
				b2.setStyle("-fx-base: #5B71D9;");
				b3.setStyle("-fx-base: #5B71D9;");
				
				// highlight this button
				b3.setStyle("-fx-base: #FEE715;");
				
				// set userChoice
				game.userChoice = "Draw";
		});
		
		// if user press confirm, set the current bet money, set the option, then go to the next scene
		confirmButton.setOnAction((ActionEvent e) -> {
				game.currentBet = Integer.parseInt(betAmountField.getText());
				game.currentMoney -= game.currentBet;
				
				userMoneyField.setText("Your money: $" + game.currentMoney);
				
				// go to the next scene TODO
		});
		
		// if user click exit, exit application
		exit.setOnAction((ActionEvent e) -> {
			System.exit(0);
		});
		
		// if user click fresh start, reset the game, go to main scene
		freshStart.setOnAction((ActionEvent e) -> {
			game.reset();
			
			// go to the initial scene TODO
		});
		
	}
	
	private void decorateLayoutUI() {
		// set the width of the child of root so they add up to root's width
		gameStats.setPrefWidth(150);
		mainPage.setPrefWidth(750);
		mb.setPrefWidth(100);
		
		// set childs of mainPage to center
		mainPage.setAlignment(Pos.CENTER);
		
		// set choiceRow width to fit with the mainPage
		choiceRow.setPrefWidth(700);
		
		// set all buttons to center and add space to it
		choiceRow.setAlignment(Pos.TOP_CENTER);
		choiceRow.setSpacing(70);

		// add spacing between the childs of mainPage, make sure that the last child is near the bottom of root
		lb1.setPrefHeight(100);
		lb1.setAlignment(Pos.CENTER);
		betFieldBox.setPrefHeight(100);
		lb2.setPrefHeight(100);
		choiceRow.setPrefHeight(120);
		//confirmBox.setPrefHeight(120);
		
		
		// set betAmountField to be smaller & in the center, 
		// set the text in betAmountField to be center
		betFieldBox.setAlignment(Pos.CENTER);
		betAmountField.setPrefSize(300, 100);
		betAmountField.setAlignment(Pos.CENTER);
		
		// make the lb1 larger and at the center (like a title)
		lb1.setTextAlignment(TextAlignment.CENTER);
		
		// set the text in betAmountField to be larger
		
		// change the font of lb1 to be something nicer
		
		
		
	}
	
	// constructor
	public currentBetScene(BaccaratGame game) {
		this.game = game;
		
		// init UI components && layouts
		initPrivateVar();
		
		// define all action event
		initActionEvent();
		
		// decorate UI
		decorateLayoutUI();
		
		// init scene
		scene = new Scene(root, 1000, 600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
