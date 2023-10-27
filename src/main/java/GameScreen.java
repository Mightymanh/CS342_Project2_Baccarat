import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        Button nextButton;
        Button dealButton;
        MenuBar mb;
        
        Label result;

        HBox playerCards;
        Label playerScore;
        HBox bankerCards;
        Label bankerScore;
        
        // UI layout
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
            	if (game.currentMoney == 0) {
            		if (result.getText() == "GameOver") {
            			StartMenu newStart = new StartMenu(game);
            			game.reset();
            			game.getPrimaryStage().setScene(newStart.getStartScene());
            		}
            		else result.setText("GameOver");
        			
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

            gameScene = new Scene(root,1000,600);
        }

        public  Scene getGameScene() {
            return gameScene;
        }

        // add cards to the screen
        public  void addCard(int side, Card curCard) throws FileNotFoundException {
        	ImageView imageView = new ImageView(curCard.image);
        	imageView.setFitHeight(120);
        	imageView.setPreserveRatio(true);
        	
            // 0 means player, 1 means banker
            if (side == 0 ) {
                Label curCardLabel  = new Label(curCard.suite + " "+curCard.name);
                playerCards.getChildren().add(imageView);
            } else {
                Label curCardLabel  = new Label(curCard.suite + " "+curCard.name);
                bankerCards.getChildren().add(imageView);
            }
        }

        public void updateScore() {
            playerScore.setText("Score: " + game.gameLogic.handTotal(game.playerHand));
            bankerScore.setText("Score: " + game.gameLogic.handTotal(game.bankerHand));
        }

        // initialze all the UI to the left of the screen
        private void initLeftBox() {
            // create the fields
            numWinField = new TextField("Rounds won: "+ this.game.numWins);
            numRoundField = new TextField("Rounds played: "+this.game.numRounds);
            curMoneyField = new TextField("Your money: $"+this.game.currentMoney);
            userSideField = new TextField("Picked side: "+this.game.userChoice);

            // make them uneditable
            numRoundField.setEditable(false);
            curMoneyField.setEditable(false);
            numWinField.setEditable(false);
            userSideField.setEditable(false);

            //adjust the size
            leftBox = new VBox(curMoneyField, numRoundField, numWinField,userSideField);
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
            playerCards = new HBox();
            bankerCards = new HBox();
            playerScore = new Label("Score: "+ game.gameLogic.handTotal(game.playerHand));
            bankerScore = new Label("Score: "+ game.gameLogic.handTotal(game.bankerHand));
            VBox playerStat =  new VBox(playerScore, playerCards);
            playerStat.setAlignment(Pos.CENTER);
            playerStat.setMinWidth(200);
            VBox bankerStat =  new VBox(bankerScore,bankerCards);
            bankerStat.setAlignment(Pos.CENTER);
            bankerStat.setMinWidth(200);
            HBox gameStats = new HBox(playerStat, bankerStat);
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
    		
    		// make the title Result bigger and center it
    		result.setFont(new Font(30));
            result.setAlignment(Pos.CENTER);
               
        }
        
    }

