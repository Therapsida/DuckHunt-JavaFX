import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.File;

public class Menu {

    /**
     * Title song of the menu.
     */
    public static MediaPlayer titlesong;


    /**
     * Creates menu pane with label, animation, image.
     */
    public static void createMenu() {
        StackPane menuPane = DuckHunt.menuPane;

        ImageView welcome = new ImageView(new Image("assets/welcome/1.png"));
        welcome.fitWidthProperty().bind(menuPane.widthProperty());
        welcome.fitHeightProperty().bind(menuPane.heightProperty());

        menuPane.getChildren().add(welcome);

        Label instructionLabel = new Label("PRESS ENTER TO PLAY\n   PRESS ESC TO EXIT");
        instructionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 40.0/3.0 * DuckHunt.SCALE ));
        instructionLabel.setStyle("-fx-text-fill: rgba(255,106,0,0.82)");
        StackPane.setAlignment(instructionLabel, Pos.CENTER);
        StackPane.setMargin(instructionLabel, new Insets(80 * DuckHunt.SCALE, 0, 0, 0));
        Timeline flashingAnimation = new Timeline(new KeyFrame(Duration.millis(700), event -> instructionLabel.setVisible(!instructionLabel.isVisible())));
        flashingAnimation.setCycleCount(Timeline.INDEFINITE);
        flashingAnimation.play();
        menuPane.getChildren().add(instructionLabel);

        Media title = new Media(new File("assets\\effects\\Title.mp3").toURI().toString());
        titlesong = new MediaPlayer(title);
        titlesong.setVolume(DuckHunt.VOLUME);

    }

    /**
     * starts menu after playing title song.
     */
    public static void startingMenu(){

        titlesong.play();

        titlesong.setOnEndOfMedia(() -> { // loop.
            titlesong.seek(Duration.ZERO);
            titlesong.play();
    });

        DuckHunt.stage.setScene(DuckHunt.menuScene);
    }


}
