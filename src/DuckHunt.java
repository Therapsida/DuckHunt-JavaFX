import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class DuckHunt extends Application {

    // SCALE FACTOR OF  THE GAME
    public static  int SCALE = 3 ;

    // VOLUME OF THE GAME
    public static double VOLUME = 0.025;


    /**
     * THESE PANES ARE USED DURİNG THE GAME
     */
    public static StackPane settingsPane = new StackPane();
    public static StackPane menuPane = new StackPane();
    public static StackPane gamePane = new StackPane();


    /**
     * THESE SCENES ARE USED DURING THE GAME
     */
    public static Scene menuScene = new Scene(menuPane,256*SCALE,240*SCALE);
    public static Scene gameScene = new Scene(gamePane,256*SCALE,240*SCALE);

    public static Scene settingsScene = new Scene(settingsPane,256*SCALE,240*SCALE);

    //MAIN STAGE OF THE GAME
    public static Stage stage;




    public void start(Stage primaryStage) {

             stage = primaryStage;

             SoundEffects.createSounds(VOLUME);  // Creates sounds of the game.

             Backgrounds.createBackgrounds(); // Reads backgrounds of the game

             Crosshairs.createCrosshairs(); //  Reads crosshairs of the game

             Menu.createMenu(); // Creates menu pane and scene.

             Menu.startingMenu();  // Starts the music and sets scene.

             Settings.createSettings(); // Creates settings pane and scene.

        /*
        Key Bindings of Menu scene :
            ENTER: set settings scene.
            ESCAPE: closes the game.
         */

        menuScene.setOnKeyPressed(event -> {if(event.getCode().toString().equals("ENTER")){
            primaryStage.setScene(settingsScene);


        } else if (event.getCode().toString().equals("ESCAPE")) {
            primaryStage.close();
        }
        });


          /*
        Key Bindings of Menu scene :
            ENTER: After the start sound game,game starts. !(While sound playing, you cannot press escape.)
            Arrow Keys: Sets background and crosshair.
            ESCAPE: Returns to the menu.
         */
            settingsScene.setOnKeyPressed(event -> {

                String key = event.getCode().toString();
                if(MediaPlayer.Status.PLAYING != Settings.startSound.getStatus())
             {
                Backgrounds.changeBackground(key);
                Crosshairs.changeCrosshair(key);
            }
                if (key.equals("ESCAPE")) {
                    if(MediaPlayer.Status.PLAYING != Settings.startSound.getStatus()) {
                        flushAll();
                        Menu.startingMenu();
                    }
                }

                else if(key.equals("ENTER")){
                    Menu.titlesong.stop();
                    Settings.startSound.play();
                    Settings.startSound.setOnEndOfMedia(Game::scene);
                }
            });



        primaryStage.getIcons().add(new Image("assets/favicon/1.png")); // Setting favicon.
        primaryStage.setTitle("HUBBM Duck Hunt"); // Setting title
        primaryStage.show();
        }

    /**
     * Flushes all the information about settings. Resets the game simply.
     */
    public static void flushAll() {
        settingsPane.getChildren().clear();
        gamePane.getChildren().clear();
        Settings.createSettings();
        Crosshairs.setDefault();
        Backgrounds.setDefault();
    }


}
