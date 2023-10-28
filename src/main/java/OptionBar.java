import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

// this class creates option Bar
public class OptionBar {
    private MenuBar menuBar;
    private Menu optionMenu;

    private MenuItem exitButton;
    private MenuItem restartButton;
    private BaccaratGame game;

    // use this to create an option Bar

    private void addEvents() {
        exitButton.setOnAction((e) -> {
            System.exit(0);
        });

        restartButton.setOnAction((e)->{
            StartMenu newStart = new StartMenu(game);
            this.game.reset();
            this.game.getPrimaryStage().setScene(newStart.getStartScene());

        });
    }
    public OptionBar(BaccaratGame game) {
        this.game = game;
        menuBar = new MenuBar();
        optionMenu = new Menu("Options");
        exitButton = new MenuItem("Exit");
        restartButton = new MenuItem("Restart");
        optionMenu.getItems().addAll(exitButton, restartButton);

        addEvents();


        menuBar.getMenus().add(optionMenu);
    }

    // use this to return the newly created optionBar
    public  MenuBar getOptionBar(){
        return menuBar;
    }

}