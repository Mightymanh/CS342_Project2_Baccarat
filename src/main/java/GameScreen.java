import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GameScreen {
        BaccaratGame game;

        // UI components
        VBox leftBox;
        VBox middleBox;
        TextField numWinField;
        TextField numRoundField;
        TextField curMoneyField;
        TextField userSideField;
        TextField cardLeft;
        Button nextButton;
        Button dealButton;
        MenuBar mb;
        
        Label result;
        Label playerLabel;
        Label bankerLabel;

        HBox playerCards;
        Label playerScore;
        HBox bankerCards;
        Label bankerScore;
        HBox gameStats;
        VBox lineBox;
        
        Line line;

        
        // UI layout
        VBox playerStat;
        VBox bankerStat;
        HBox root;
        
        Scene gameScene;
        

        private void addEvents() {

            dealButton.setOnAction((e) -> {
                try {
					this.game.gamePhase(this);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            });

            nextButton.setOnAction((e)->{
            	// Game Over when user runs out of money or we do not have enough card to continue the game
            	if (game.currentMoney == 0 || this.game.theDealer.deckSize() < 6) {
            		if (result.getText() == "Game Over") {
            			StartMenu newStart = new StartMenu(game);
            			game.reset();
            			game.getPrimaryStage().setScene(newStart.getStartScene());
            		}
            		else result.setText("Game Over");
        			
                    return;
        		}
            	
                currentBetScene newBet = new currentBetScene(this.game);
                this.game.again();
                this.game.getPrimaryStage().setScene(newBet.getScene());
            });
        }
        
        private void initPrivateVar() {

        	// init main components for root
            initLeftBox();
            initMiddleBox();
            initRightBox();
            
            // styling
            decorateLayoutUI();

            root = new HBox(leftBox, middleBox, mb);
        }
        
        public  GameScreen(BaccaratGame game){


            this.game =  game;
            
            initPrivateVar();
        
            addEvents();
            
            Image img = new Image(getClass().getClassLoader().getResource("background.jpeg").toExternalForm());
            BackgroundImage backgroundImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(530, 400, true, true, true, true));
            Background background = new Background(backgroundImg);
            root.setBackground(background);

            gameScene = new Scene(root,1000,600);
        }

        public  Scene getGameScene() {
            return gameScene;
        }

        // add cards to the screen
        public  void addCard(int side, Card curCard) throws FileNotFoundException {
        	ImageView imageView = new ImageView(curCard.image);
        	imageView.setFitHeight(110);
        	imageView.setPreserveRatio(true);
        	
            // 0 means player, 1 means banker
            if (side == 0 ) {
                playerCards.getChildren().add(imageView);
            } else {
                bankerCards.getChildren().add(imageView);
            }
        }

        public void updateScore() {
            playerScore.setText("Score: " + game.gameLogic.handTotal(game.playerHand));
            bankerScore.setText("Score: " + game.gameLogic.handTotal(game.bankerHand));
            cardLeft.setText("Cards left: "+ this.game.theDealer.deckSize());
        }

        // initialze all the UI to the left of the screen
        private void initLeftBox() {
            // create the fields
            numWinField = new TextField("Rounds won: "+ this.game.numWins);
            numRoundField = new TextField("Rounds played: "+this.game.numRounds);
            curMoneyField = new TextField("Your money: $"+this.game.currentMoney);
            userSideField = new TextField("Picked side: "+this.game.userChoice);
            cardLeft = new TextField("Cards left: "+ this.game.theDealer.deckSize());
            // make them uneditable
            numRoundField.setEditable(false);
            curMoneyField.setEditable(false);
            numWinField.setEditable(false);
            userSideField.setEditable(false);
            cardLeft.setEditable(false);

            //adjust the size
            leftBox = new VBox(curMoneyField, numRoundField, numWinField,userSideField, cardLeft);
            //leftBox.setAlignment(Pos.TOP_LEFT);
        }

        private void initMiddleBox() {

            // create next and deal buttons
            nextButton = new Button("Next");
            dealButton = new Button("Deal");
            HBox bottomBox = new HBox(dealButton, nextButton);
            bottomBox.setAlignment(Pos.CENTER);
            bottomBox.setSpacing(100);

            // create player cards holder and banker card holder
            Line line = new Line();
            line.setStartX(0);
            line.setEndX(0);
            line.setStartY(20);
            line.setEndY(250);
            line.setStrokeWidth(4);
            lineBox = new VBox(line);
            
            
            playerCards = new HBox();
            bankerCards = new HBox();      
            playerScore = new Label("Score: "+ game.gameLogic.handTotal(game.playerHand));
            bankerScore = new Label("Score: "+ game.gameLogic.handTotal(game.bankerHand));
            playerLabel = new Label("Player");
            bankerLabel = new Label("Banker");
            playerStat =  new VBox(playerLabel, playerScore, playerCards);
            bankerStat =  new VBox(bankerLabel, bankerScore,bankerCards);
            gameStats = new HBox(playerStat, lineBox, bankerStat);
            gameStats.setSpacing(100);
            gameStats.setMinHeight(300);
            gameStats.setAlignment(Pos.CENTER);

            // create the result display
            result = new Label("Result");
            middleBox = new VBox(result, gameStats,bottomBox);
        }
        
        private void initRightBox() {
        	// init menu options
    		OptionBar newOptionBar = new OptionBar(this.game);
    		mb = newOptionBar.getOptionBar();
 
        }

        private void decorateLayoutUI() {
        	// set the size for children in the root
            leftBox.setPrefWidth(160);
        	middleBox.setPrefWidth(750);
        	mb.setPrefWidth(90);
        	playerStat.setPrefWidth(370);
        	bankerStat.setPrefWidth(370);
        	lineBox.setPrefWidth(10);
            playerStat.setAlignment(Pos.CENTER);
            bankerStat.setAlignment(Pos.CENTER);
            lineBox.setAlignment(Pos.CENTER);
        	
        	// center all children inside middleBox
        	middleBox.setAlignment(Pos.CENTER);
        	
        	// set size for buttons deal and next
        	nextButton.setMinSize(150,100);
            nextButton.setDisable(true);
            dealButton.setMinSize(150,100);
            
            // set spacing between cards
            playerCards.setSpacing(20);
            bankerCards.setSpacing(20);
            
            // make the textField in leftBox bigger
            numRoundField.setPrefHeight(40);
            curMoneyField.setPrefHeight(40);
            numWinField.setPrefHeight(40);
            userSideField.setPrefHeight(40);
            curMoneyField.setFont(new Font(15));
    		numRoundField.setFont(new Font(15));
    		numWinField.setFont(new Font(15));
    		userSideField.setFont(new Font(15));
            cardLeft.setFont(new Font(15));
            cardLeft.setPrefHeight(40);
    		
    		// make the title Result bigger and center it
    		result.setFont(new Font(35));
            result.setAlignment(Pos.CENTER);
            
            // make the label player and banker bigger
            playerLabel.setFont(new Font(35));
            bankerLabel.setFont(new Font(35));
            playerStat.setMargin(playerScore, new Insets(0, 0, 20, 0));
            bankerStat.setMargin(bankerScore, new Insets(0, 0, 20, 0));   
            
            // make the line always in the center of the gameStats
            
            // make the text Deal and Next button bigger
            dealButton.setFont(new Font(20));
            nextButton.setFont(new Font(20));
               
        }
        
//        void colorUI() {
//        	
//        }
//        
//        void fontUI() {
//        	        	
//        }
        
    }

