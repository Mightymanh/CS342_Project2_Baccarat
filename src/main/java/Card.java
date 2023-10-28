import javafx.scene.image.Image;

public class Card {

    public String suite;
    public int value;
    public String name;
    public String imagePath;
    Image image;
    Card(String theSuite, int theValue,String theName)  {
        this.suite = theSuite;
        this.value = theValue;
        this.name = theName;
        this.imagePath = "pokerCards/" + name + "_of_" + suite +"s.png";

        //System.out.println(this.imagePath);

        try {
            this.image = new Image(getClass().getClassLoader().getResource(imagePath).toExternalForm());
        } catch(Exception e){

        }



    }
}


