import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundEffects {
    /**
     * Most of the sounds of the game.
     */
    public static MediaPlayer youwinSound ;

    public static MediaPlayer levelSound ;

    public static  MediaPlayer gameOverSound ;

    public static Media gunshot ;

    public static Media duckFalls  ;


    /**
     * creates all the sounds for the game and sets volume.
     * @param volume volume of the game
     */
    public static void createSounds(double volume){
         youwinSound = new MediaPlayer(new Media(new File("assets\\effects\\GameCompleted.mp3").toURI().toString()));

        levelSound = new MediaPlayer(new Media(new File("assets\\effects\\LevelCompleted.mp3").toURI().toString()));

         gameOverSound = new MediaPlayer(new Media(new File("assets\\effects\\GameOver.mp3").toURI().toString()));

         gunshot = new Media(new File("assets\\effects\\Gunshot.mp3").toURI().toString());

         duckFalls = new Media(new File("assets\\effects\\DuckFalls.mp3").toURI().toString());


        youwinSound.setVolume(volume);
        levelSound.setVolume(volume);
        gameOverSound.setVolume(volume);


    }
}
