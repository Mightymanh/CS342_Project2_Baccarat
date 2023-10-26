import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.util.ArrayList;

public class GameScreen {
        BorderPane root;

        VBox leftBox;
        VBox middleBox;
        Scene gameScene;
        TextField curWinning;
        TextField totalWinnings;
        TextField curMoney;
        TextField userSide;
        Button nextButton;
        Button dealButton;

        Label result;

        HBox playerCards;
        Label playerScore;
        HBox bankerCards;
        Label bankerScore;

        BaccaratGame game;

        void addEvents() {

            dealButton.setOnAction((e) -> {
                this.game.gamePhase(this);
            });

            nextButton.setOnAction((e)->{

                currentBetScene newBet = new currentBetScene(this.game);
                this.game.again();
                this.game.getPrimaryStage().setScene(newBet.getScene());
            });
        }
        public  GameScreen(BaccaratGame game){


            this.game =  game;
            root = new BorderPane();
            root.setMinSize(700,700);

            initLeftBox();
            initMiddleBox();


            root.setLeft(leftBox);
            root.setCenter(middleBox);
            addEvents();

            gameScene = new Scene(root,1000,600);
        }

        public  Scene getGameScene() {
            return gameScene;
        }

        // add cards to the screen
        public  void addCard(int side, Card curCard) {
            // 0 means player, 1 means banker
            if (side == 0 ) {
                Label curCardLabel  = new Label(curCard.suite + " "+curCard.name);
                playerCards.getChildren().add(curCardLabel);
            } else {
                Label curCardLabel  = new Label(curCard.suite + " "+curCard.name);
                bankerCards.getChildren().add(curCardLabel);
            }
        }

        public void updateScore() {
            playerScore.setText("Score: "+game.gameLogic.handTotal(game.playerHand));
            bankerScore.setText("Score: "+game.gameLogic.handTotal(game.bankerHand));
        }

        // initialze all the UI to the left of the screen
        private void initLeftBox() {
            // create the fields
            curWinning = new TextField("Win: "+ this.game.numWins);
            totalWinnings = new TextField("Round: "+this.game.numRounds);
            curMoney = new TextField("$"+this.game.currentMoney);
            userSide = new TextField("Picked side: "+this.game.userChoice);

            // make them uneditable
            totalWinnings.setEditable(false);
            curMoney.setEditable(false);
            curWinning.setEditable(false);
            userSide.setEditable(false);

            //adjust the size
            curWinning.setMinSize(150,10);
            curMoney.setMinSize(150,10);
            totalWinnings.setMinSize(150,10);
            userSide.setMinSize(150,10);
            leftBox = new VBox(curMoney, totalWinnings, curWinning,userSide);
            leftBox.setMinSize(200,700);
            leftBox.setAlignment(Pos.TOP_LEFT);
        }

        private void initMiddleBox() {
            // create the option bar
            OptionBar newOptionBar = new OptionBar(this.game);
            MenuBar options = newOptionBar.getOptionBar();
            HBox topBox = new HBox(options);
            topBox.setAlignment(Pos.BASELINE_RIGHT);

            // create next and deal buttons
            nextButton = new Button("Next");
            nextButton.setMinSize(150,100);
            nextButton.setDisable(true);
            dealButton = new Button("Deal");
            dealButton.setMinSize(150,100);
            HBox bottomBox = new HBox(dealButton, nextButton);
            bottomBox.setAlignment(Pos.BOTTOM_CENTER);
            bottomBox.setMaxWidth(500);
            bottomBox.setSpacing(100);

            // create player cards holder and banker card holder
            playerCards = new HBox();
            playerCards.setSpacing(20);
            bankerCards = new HBox();
            bankerCards.setSpacing(20);
            playerScore = new Label("Score: "+game.gameLogic.handTotal(game.playerHand));
            bankerScore = new Label("Score: "+game.gameLogic.handTotal(game.bankerHand));
            VBox playerStat =  new VBox(playerScore, playerCards);
            playerStat.setMinWidth(200);
            VBox bankerStat =  new VBox(bankerScore,bankerCards);
            bankerStat.setMinWidth(200);
            HBox gameStats = new HBox(playerStat, bankerStat);
            gameStats.setSpacing(100);
            gameStats.setMinHeight(400);
            gameStats.setAlignment(Pos.CENTER);

            // create the result display
            result = new Label("Result");
            result.setMinSize(100,100);
            result.setAlignment(Pos.CENTER);
            HBox resultBox = new HBox(result);
            resultBox.setAlignment(Pos.BOTTOM_CENTER);
            middleBox = new VBox(topBox,resultBox, gameStats,bottomBox);
            middleBox.setMinHeight(700);
            middleBox.setStyle("-fx-border-color: black;");
        }

    }

