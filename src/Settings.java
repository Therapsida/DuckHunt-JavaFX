import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;


public class Settings {


    /**
     * Start sound of the menu.
     */
    public static MediaPlayer startSound;


    /**
     * Create settings pane. (Crosshair,Background, information label)
     */
    public static void createSettings(){
        StackPane settingsPane = DuckHunt.settingsPane;
        int SCALE = DuckHunt.SCALE;


        settingsPane.getChildren().add(Backgrounds.addBackGround());
        settingsPane.getChildren().add(Crosshairs.crosshair);
        Label navigate = new Label("USE ARROW KEY TO NAVIGATE\n     PRESS ENTER TO START");
        navigate.setStyle("-fx-text-fill: rgba(255,106,0,0.82)");
        navigate.setFont(Font.font("Arial", FontWeight.BOLD, 8*SCALE));
        StackPane.setAlignment(navigate, Pos.TOP_CENTER);
        StackPane.setMargin(navigate, new Insets(10*SCALE, 0, 0, 0));
        settingsPane.getChildren().add(navigate);


        Crosshairs.crosshair.setFitHeight(11*SCALE);
        Crosshairs.crosshair.setFitWidth(11*SCALE);

        Media startSoundMedia = new Media(new File("assets\\effects\\Intro.mp3").toURI().toString());
        startSound = new MediaPlayer(startSoundMedia);
        startSound.setVolume(DuckHunt.VOLUME);



    }
}
