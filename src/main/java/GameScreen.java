import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameScreen {
        BorderPane root;

        Scene gameScene;
        Label curWinning;
        Label totalWinnings;
        Label curMoney;
        Button nextButton;
        Button dealButton;

        Label result;

        HBox playerCards;
        Label playerScore;
        HBox bankerCards;
        Label bankerScore;

        BaccaratGame game;
        public  GameScreen(BaccaratGame game){

            this.game =  game;
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

            OptionBar newOptionBar = new OptionBar(this.game);
            MenuBar options = newOptionBar.getOptionBar();
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

        public  Scene getGameScene() {
            return gameScene;
        }

        public  void addCard() {
            Label card = new Label("King 13");
            card.setMinWidth(13);
            Label card2 = new Label("King 13");
            Label card3 = new Label("King 13");
            Label card4 = new Label("King 13");
            Label card5 = new Label("King 13");
            Label card6 = new Label("King 13");

            playerCards.getChildren().addAll(card,card2,card3);
            bankerCards.getChildren().addAll(card4,card5,card6);
            System.out.println("Title: " + ((Stage)gameScene.getWindow()).getTitle());
        }

    }

