import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public  class StartMenu {

    //StackPane root;
    Button startButton;
    Label title;
    Label author;
    Label author2;
    VBox words; // this is the root node

    BaccaratGame game;



    Scene startScene;
    // this function create a startMenu scene;

    public void addEvents() {
        startButton.setOnAction((e) -> {
            //currentBetScene newBet = new currentBetScene();
            currentBetScene newBet = new currentBetScene(this.game);
            this.game.getPrimaryStage().setScene(newBet.getScene());
        });
    }
    public  StartMenu(BaccaratGame game) {
        this.game = game;

        startButton = new Button("Start");

        title = new Label("Baccarat Game");
        author = new Label("By: Manh Phan");
        author2 = new Label("Lei Chen");


        OptionBar newOption = new OptionBar(this.game);
        MenuBar optionBar = newOption.getOptionBar();
        HBox menuBox = new HBox(optionBar);

        menuBox.setAlignment(Pos.BASELINE_RIGHT); // position the menu bar to the right
        VBox box2 = new VBox(title, author, author2);
        VBox box3 = new VBox(startButton);

        // set the font size

        title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 80));
        author.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, FontPosture.REGULAR, 20));
        author2.setFont(Font.font("Comic Sans MS", FontWeight.LIGHT, FontPosture.REGULAR, 20));
        startButton.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.REGULAR, 40));

        Stop[] titleStop = new Stop[] {new  Stop(0,Color.YELLOWGREEN), new Stop(.5, Color.GREEN), new Stop(1, Color.WHITESMOKE)};
        LinearGradient titleLG = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, titleStop);
        title.setTextFill(titleLG);
        author.setTextFill(Color.WHITESMOKE);
        author2.setTextFill(Color.WHITESMOKE);

        Stop[] startStop = new Stop[] {new  Stop(0,Color.WHITESMOKE), new Stop(.5, Color.YELLOWGREEN), new Stop(1, Color.WHITESMOKE)};
        LinearGradient StartLG = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, startStop);
        startButton.setTextFill(StartLG);
        startButton.setStyle("-fx-base: #027833;");
        startButton.setPrefSize(200,100);

        words = new VBox(menuBox,box2,box3); // put everything inside the root node

        box3.setAlignment(Pos.BOTTOM_CENTER);
        box2.setAlignment(Pos.CENTER);
        box2.setMinHeight(400);
        words.setMinSize(700,700);
        words.setAlignment(Pos.TOP_CENTER);

        addEvents();
        
        
        // add background image
        Image img = new Image(getClass().getClassLoader().getResource("background.jpeg").toExternalForm());
        BackgroundImage backgroundImg = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(530, 400, true, true, true, true));
        Background background = new Background(backgroundImg);
        words.setBackground(background);
        
        startScene = new Scene(words, 700, 700);

    }

    public  Scene getStartScene() {
        return startScene;
    }
}