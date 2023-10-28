import javafx.scene.Scene;

// UI components
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

// UI layouts
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.text.*;
// Event handlers
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

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
	private Label userMoneyField;
	private Label numRoundField;
	private Label numWinField;
	private Label cardLeft;
	private MenuBar mb;
	
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
		//buttonFont = new Font("sans-serif", FontWeight.MEDIUM, FontPosture.REGULAR,20.0);
		
		// init TextField
		betAmountField = new TextField();
		betAmountField.setPromptText("Enter the amount you want to bet as integer");
		userMoneyField = new Label("Your money: $" + game.currentMoney);
		//userMoneyField.setEditable(false);
		numRoundField = new Label("Rounds played: " + game.numRounds);
		//numRoundField.setEditable(false);
		numWinField = new Label("Rounds won: " + game.numWins);
		//numWinField.setEditable(false);
		cardLeft = new Label("Cards left: " + game.theDealer.deck.size());
		//cardLeft.setEditable(false);
		
		// init menu options
		OptionBar newOptionBar = new OptionBar(this.game);

		mb = newOptionBar.getOptionBar();

		// init layout
		betFieldBox = new HBox(betAmountField);
		confirmBox = new HBox(confirmButton);
		choiceRow = new HBox(b1, b2, b3);
		gameStats = new VBox(userMoneyField, numRoundField, numWinField, cardLeft);
		mainPage = new VBox(lb1, betFieldBox, lb2, choiceRow, confirmButton);
		root = new HBox(gameStats, mainPage, mb);
	}
	
	private void initActionEvent() {
		// if user choose an option, set game.userChoice = that option, highlight that button
		b1.setOnAction((ActionEvent e) -> {
				b2.setOpacity(.5);
				b3.setOpacity(.5);
				b1.setOpacity(1);
				
				// set userChoice
				game.userChoice = "Player";
				lb2.setText("You picked Player");
		});
		b2.setOnAction((ActionEvent e) -> {
				// unhighlight all three buttons
				b1.setOpacity(.5);
				b3.setOpacity(.5);
				b2.setOpacity(1);
				// highlight this button
				//b2.setStyle("-fx-base: #FEE715;");
				
				// set userChoice
				game.userChoice = "Draw";
				lb2.setText("You picked Draw");
		});
		b3.setOnAction((ActionEvent e) -> {
				// unhighlight all three buttons

				b2.setOpacity(.5);
				b1.setOpacity(.5);
				b3.setOpacity(1);
				// highlight this button
				//b3.setStyle("-fx-base: #FEE715;");
				
				// set userChoice
				game.userChoice = "Banker";
				lb2.setText("You picked Banker");
		});
		
		// if user press confirm, set the current bet money, set the option, then go to the next scene
		confirmButton.setOnAction((ActionEvent e) -> {
				// if user type non digit characters then decline the input
				try {
					game.currentBet = Integer.parseInt(betAmountField.getText());
				} catch (Exception error) {
					lb1.setText("Must be valid Integer");
					return;
				}
				
				// if user bet more than what they have then decline the input
				if (game.currentBet > game.currentMoney) {
					lb1.setText("You bet more than you Have!");
					return;
				}
				
				// if the user bet too few then decline the input
				if (game.currentBet < 1) {
					lb1.setText("You bet too few!");
					return;
				}
				
				// if the user has not chosen a side then decline the confirm
				if (game.userChoice == "N/A") {
					lb2.setText("You must pick a Side!");
					return;
				}
				
				game.currentMoney -= game.currentBet;
				
				userMoneyField.setText("Your money: $" + game.currentMoney);

				GameScreen newGame = new GameScreen(this.game);

				this.game.getPrimaryStage().setScene(newGame.getGameScene());

		});

		
	}
	
	private void decorateLayoutUI() {
		
		// set the width of the child of root so they add up to root's width
		gameStats.setPrefWidth(180);
		mainPage.setPrefWidth(750);
		mb.setPrefWidth(90);
		
		// set childs of mainPage to center
		mainPage.setAlignment(Pos.CENTER);
		
		// set choiceRow width to fit with the mainPage
		choiceRow.setPrefWidth(700);
		
		// set all buttons to center and add space between them
		choiceRow.setAlignment(Pos.TOP_CENTER);
		choiceRow.setSpacing(100);

		// add space between the childs of mainPage, make sure that the last child is near the bottom of root
		lb1.setPrefHeight(100);
		lb1.setAlignment(Pos.CENTER);
		betFieldBox.setPrefHeight(100);
		lb2.setPrefHeight(100);
		choiceRow.setPrefHeight(150);
		mainPage.setMargin(choiceRow, new Insets(0, 0, 10, 0));
		mainPage.setMargin(lb2, new Insets(10, 0, 10, 0));
		confirmBox.setPrefHeight(120);
		
		
		// set betAmountField to be smaller & in the center, 
		// set the text in betAmountField to be center
		betFieldBox.setAlignment(Pos.CENTER);
		betAmountField.setPrefSize(300, 100);
		betAmountField.setAlignment(Pos.CENTER);
		
		// make the lb1 and lb2 larger and at the center (like a title)
		lb1.setTextAlignment(TextAlignment.CENTER);

		
		// set the text in betAmountField to be larger
		betAmountField.setFont(new Font(15));
		
		// make the buttons bigger and nicer
		b1.setPrefSize(150, 100);
		b2.setPrefSize(150, 100);
		b3.setPrefSize(150, 100);

		
		// make the confirm button longer
		confirmButton.setPrefSize(200, 50);
		confirmButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,20.0));

		// make Labels in stats bigger
		userMoneyField.setPrefHeight(40);
		numRoundField.setPrefHeight(40);
		numWinField.setPrefHeight(40);
		cardLeft.setPrefHeight(40);

		//adjust padding of the Labels
		Insets padding = new Insets(0,0,5,5);
		userMoneyField.setPadding(padding);
		numRoundField.setPadding(padding);
		numWinField.setPadding(padding);
		cardLeft.setPadding(padding);
		
	}
	
	void colorUI() {
		lb1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,40));
		lb2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,40.0));

		b1.setStyle("-fx-base: #027833;");
		b2.setStyle("-fx-base: #027833;");
		b3.setStyle("-fx-base: #027833;");

		
		// set the background color to blue
		
		// set color of confirm button to yellow
		confirmButton.setStyle("-fx-base: #d40b33");

		//DropShadow shadow = new DropShadow();
		lb1.setTextFill(Color.WHITE);
		lb2.setTextFill(Color.WHITE);

		//mb.setStyle("-fx-background-color: transparent; -fx-text-fill: #FFFFFF;");


		userMoneyField.setTextFill(Color.YELLOW);
		numRoundField.setTextFill(Color.WHITE);
		cardLeft.setTextFill(Color.WHITE);
		numWinField.setTextFill(Color.WHITE);
		
	}
	
	void fontUI() {
		// change the font of lb1 to be something nicer
		
		// make the font of buttons bigger
		b1.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR,20.0));
		b2.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR,20.0));
		b3.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR,20.0));


		// set the text field to be larger in game stat
		userMoneyField.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,15));
		numRoundField.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,15));
		numWinField.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,15));
		cardLeft.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR,15));





		
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
		
		// color UI
		colorUI();
		
		// font UI
		fontUI();
		
		// add background image
		Image img = new Image(getClass().getClassLoader().getResource("background.jpeg").toExternalForm());
        BackgroundImage backgroundImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(530, 400, true, true, true, true));
        Background background = new Background(backgroundImg);
        root.setBackground(background);
		
		// init scene
		scene = new Scene(root, 1000, 600);
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
}
