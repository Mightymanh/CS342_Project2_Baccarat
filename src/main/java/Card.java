import javafx.scene.image.Image;

public class Card {

    public String suite;
    public int value;
    public String name;
    public String imagePath = "pokerCards/" + value + name + ".png";
    Image image;
    Card(String theSuite, int theValue,String theName)  {
        this.suite = theSuite;
        this.value = theValue;
        this.name = theName;
        this.imagePath = "pokerCards/" + name + "_of_" + suite +"s.png";
        this.image = new Image(getClass().getClassLoader().getResource(imagePath).toExternalForm());
    }
}


