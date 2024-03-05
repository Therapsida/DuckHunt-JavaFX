import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import java.util.ArrayList;

public class Game {

    private static int totalAmmo = 3 ;

    private static boolean isLevelFinished = false;  // true if birds lists size is 0.

    private static boolean isGameOver = false; // true if total ammo is 0 while birds lists size is not 0.

    public static int currentLevel = 1;

    public static Label  ammoText = new Label(String.format("Ammo Left: %d", totalAmmo));

    public static Label levelText = new Label();

    public static  VBox informationVBox; // Information about the game. (GAME OVER!, YOU WİN , etc.)
    public static ArrayList<Bird> birds = new ArrayList<>(); // It holds birds information. It is used in level complete algorithm.

    public static Pane level = new Pane(); // Level pane. Ducks are flying in this pane.

    public static Label statusLabel; // informationVBox's label.

    public static Label flashingLabel; // informationVBox's label.

    private static Timeline flashingAnimation; // animation of flashingLabel

    /**
     * scene method is used when creating game pane from first level.
     */
    public static void scene(){

        currentLevel = 1;

        DuckHunt.gamePane.getChildren().add(Backgrounds.addBackGround());

        Image crosshair =Crosshairs.crosshairs.get(Crosshairs.currentCrosshair);
        DuckHunt.gamePane.setCursor(new ImageCursor(crosshair, crosshair.getWidth() / 2, crosshair.getHeight() / 2));



        /*
        Setting ammoText Label
         */
        ammoText.setFont(Font.font("Arial", FontWeight.BOLD, 8*DuckHunt.SCALE));
        ammoText.setStyle("-fx-text-fill: rgba(255,106,0,0.82)");
        StackPane.setAlignment(ammoText,Pos.TOP_RIGHT);
        DuckHunt.gamePane.getChildren().add(ammoText);

        /*
        Setting levelText Label
         */
        levelText.setFont(Font.font("Arial", FontWeight.BOLD, 8*DuckHunt.SCALE));
        levelText.setStyle("-fx-text-fill: rgba(255,106,0,0.82);");
        StackPane.setAlignment(levelText,Pos.TOP_CENTER);
        DuckHunt.gamePane.getChildren().add(levelText);



        setLevel();

        DuckHunt.gamePane.getChildren().add(level);


        /*
        Setting informationVBox.
         */
        informationVBox = new VBox();

        statusLabel = new Label("Text");
        statusLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15 * DuckHunt.SCALE));
        statusLabel.setStyle("-fx-text-fill: rgba(255,106,0,0.82)");
        informationVBox.getChildren().add(statusLabel);


        flashingLabel = new Label("Text");
        flashingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15 * DuckHunt.SCALE));
        flashingLabel.setStyle("-fx-text-fill: rgba(255,106,0,0.82)");



        informationVBox.getChildren().add(flashingLabel);
        informationVBox.setAlignment(Pos.CENTER);
        informationVBox.setVisible(false);


        DuckHunt.gamePane.getChildren().add(Backgrounds.addForeGround()); // Adds foreground.

        DuckHunt.gamePane.getChildren().add(informationVBox);


        StackPane.setAlignment(level,Pos.CENTER);

        DuckHunt.stage.setScene(DuckHunt.gameScene);










    }


    public static void setLevel(){
        /*
        resets previous levels info
         */
        level.getChildren().clear();
        birds.clear();
        isGameOver = false;
        isLevelFinished = false;

        //Flash animation.

        flashingAnimation = new Timeline(new KeyFrame(Duration.millis(900), event -> flashingLabel.setVisible(!flashingLabel.isVisible())));
        flashingAnimation.setCycleCount(Timeline.INDEFINITE);

        level.setPrefWidth(256*DuckHunt.SCALE);
        level.setPrefHeight(240*DuckHunt.SCALE);


        /*
        Levels of game.
        Adds bird to the pane only.
         */
        switch (currentLevel){
            case 1 :  Bird bird = new Bird(1,0,"red",DuckHunt.SCALE*70,DuckHunt.SCALE*70,DuckHunt.SCALE);
                level.getChildren().add(bird.Play());
                birds.add(bird);
                break;
            case 2 :

                bird = new Bird(1,0,"red",DuckHunt.SCALE*70,DuckHunt.SCALE*70,DuckHunt.SCALE*2);
                Bird bird1 = new Bird(1 ,-1,"blue",200*DuckHunt.SCALE,30*DuckHunt.SCALE,DuckHunt.SCALE);
                level.getChildren().add(bird.Play());
                level.getChildren().add(bird1.Play());
                birds.add(bird);
                birds.add(bird1);
                break;

            case 3 :
                bird = new Bird(1,1,"black",DuckHunt.SCALE*70,DuckHunt.SCALE*70,DuckHunt.SCALE*1.20);
                bird1 = new Bird(-1 ,-1,"blue",DuckHunt.SCALE*70,DuckHunt.SCALE*70,DuckHunt.SCALE*1.20);
                level.getChildren().add(bird.Play());
                level.getChildren().add(bird1.Play());
                birds.add(bird);
                birds.add(bird1);
                break;

            case 4 :
                bird = new Bird(1,0,"red",DuckHunt.SCALE*140,DuckHunt.SCALE*70,DuckHunt.SCALE*1.5);
                bird1 = new Bird(1 ,-1,"blue",DuckHunt.SCALE*200,DuckHunt.SCALE*150,DuckHunt.SCALE*1.20);
                Bird bird2 = new Bird(1,1,"red",DuckHunt.SCALE*50,DuckHunt.SCALE*90,DuckHunt.SCALE*1.20);
                level.getChildren().add(bird.Play());
                level.getChildren().add(bird1.Play());
                level.getChildren().add(bird2.Play());
                birds.add(bird);
                birds.add(bird1);
                birds.add(bird2);
                break;

            case 5 :
                bird = new Bird(-1,-1,"blue",DuckHunt.SCALE*150,DuckHunt.SCALE*200,DuckHunt.SCALE*1.20);
                bird1 = new Bird(1 ,-1,"black",DuckHunt.SCALE*200,DuckHunt.SCALE*160,DuckHunt.SCALE*1.20);
                bird2 = new Bird(1,1,"red",DuckHunt.SCALE*50,DuckHunt.SCALE*90,DuckHunt.SCALE*1.20);
                level.getChildren().add(bird.Play());
                level.getChildren().add(bird1.Play());
                level.getChildren().add(bird2.Play());
                birds.add(bird);
                birds.add(bird1);
                birds.add(bird2);
                break;
            case 6 :
                bird = new Bird(-1,-1,"blue",DuckHunt.SCALE*150,DuckHunt.SCALE*200,DuckHunt.SCALE*1.20);
                bird1 = new Bird(1 ,-1,"black",DuckHunt.SCALE*200,DuckHunt.SCALE*160,DuckHunt.SCALE*1.20);
                bird2 = new Bird(1,0,"blue",DuckHunt.SCALE*50,DuckHunt.SCALE*50,DuckHunt.SCALE*2);
                Bird bird3 = new Bird(1,0,"blue",DuckHunt.SCALE*40, DuckHunt.SCALE*140,DuckHunt.SCALE*1.20);
                level.getChildren().add(bird.Play());
                level.getChildren().add(bird1.Play());
                level.getChildren().add(bird2.Play());
                birds.add(bird);
                birds.add(bird1);
                birds.add(bird2);

        }


        /*
        Sets ammo and text info.
         */
        levelText.setText(String.format("Level %d/6",currentLevel));
        totalAmmo = birds.size()*3;
        ammoText.setText(String.format("Ammo Left: %d",totalAmmo));


        birdShoot();

    }



    private static void checkGame() {
        /*
        Checks game if level finished or not
         */
        if (birds.size() == 0) {
            isLevelFinished = true;
        } else {
            isLevelFinished = false;
        }

        if (isLevelFinished) {
            if (currentLevel == 6) { // Last level.

                statusLabel.setText("You have completed the game!");
                flashingLabel.setText("Press ENTER to play again\n       Press ESC to exit");
                SoundEffects.youwinSound.play();
                flashingAnimation.play();
                informationVBox.setVisible(true);


                /*
                Key Bindings :
                    ENTER : it resets the game and starts from the first level.
                    ESCAPE: sets cursor to default and starts menu.
                 */
                DuckHunt.gameScene.setOnKeyPressed(event -> {
                    if (isLevelFinished) {
                        if (event.getCode().toString().equals("ENTER")) {
                            flashingAnimation.stop();
                            SoundEffects.youwinSound.stop();
                            DuckHunt.gamePane.getChildren().clear();
                            scene();

                        } else if (event.getCode().toString().equals("ESCAPE")) {
                            flashingAnimation.stop();
                            SoundEffects.youwinSound.stop();
                            DuckHunt.gamePane.setCursor(Cursor.DEFAULT);
                            Timeline cursorAnimation = new Timeline(new KeyFrame(Duration.millis(100)));
                            cursorAnimation.play();
                            cursorAnimation.setOnFinished(event1 -> {
                                DuckHunt.flushAll();
                                Menu.startingMenu();
                            });

                        }
                    }
                });



            } else {  // From 1-5 levels.

                SoundEffects.levelSound.play();

                flashingLabel.setText("Press ENTER to play next level");
                statusLabel.setText("YOU WIN!");
                flashingAnimation.play();
                informationVBox.setVisible(true);
                /*
                Key Bindings :
                       ENTER : sets to the next level.
                 */
                DuckHunt.gameScene.setOnKeyPressed(event -> {
                    if (isLevelFinished) {
                        if (event.getCode().toString().equals("ENTER")) {
                            flashingAnimation.stop();
                            SoundEffects.levelSound.stop();
                            currentLevel++;
                            informationVBox.setVisible(false);
                            setLevel();
                        }
                    }
                });
            }

        }
        else{
            if (totalAmmo == 0) {  //Level is not completed and total ammo is 0.
                isGameOver = true;  // It means that game over.

                statusLabel.setText("GAME OVER!");
                flashingLabel.setText("Press ENTER to play again\n       Press ESC to exit");
                informationVBox.setVisible(true);
                flashingAnimation.play();
                SoundEffects.gameOverSound.play();


                /*
                Key Bindings:
                    ENTER : resets and play from the first level.
                    ESCAPE: returns to menu.
                 */
                DuckHunt.gameScene.setOnKeyPressed(event -> {
                    if (isGameOver) {
                        if (event.getCode().toString().equals("ENTER")) {
                            flashingAnimation.stop();
                            DuckHunt.gamePane.getChildren().clear();
                            SoundEffects.gameOverSound.stop();
                            scene();

                        } else if (event.getCode().toString().equals("ESCAPE")) {
                            flashingAnimation.stop();
                            DuckHunt.gamePane.setCursor(Cursor.DEFAULT);
                            SoundEffects.gameOverSound.stop();
                            Timeline cursorAnimation = new Timeline(new KeyFrame(Duration.millis(100)));
                            cursorAnimation.play();
                            cursorAnimation.setOnFinished(event1 -> {
                                DuckHunt.flushAll();
                                Menu.startingMenu();
                            });

                        }
                    }
                });
            }
        }

    }



    /**
     * Shooting method.
     */
    public static void birdShoot() {


        /*
        While game is not finished, click animation shoot the bird.
         */
        DuckHunt.gamePane.setOnMouseClicked(event -> {
            if(!isLevelFinished && !isGameOver) {

                MediaPlayer gunshot = new MediaPlayer(SoundEffects.gunshot);
                gunshot.setVolume(DuckHunt.VOLUME);
                gunshot.play();
                totalAmmo--;
                ammoText.setText(String.format("Ammo Left: %d", totalAmmo));
                checkGame(); // checks for if total ammo equals 0;
            }
            double mouseX = event.getX();
            double mouseY = event.getY();



            Bird[] birdsArray = birds.toArray(new Bird[0]);

            for (Bird bird : birdsArray) {
                if(bird.isClicked(mouseX,mouseY) && !isGameOver){  // Mouse is on bird.
                    {
                        MediaPlayer duckFalls = new MediaPlayer(SoundEffects.duckFalls);
                        duckFalls.setVolume(DuckHunt.VOLUME);
                        duckFalls.play();


                        birds.remove(bird);  // Remove the bird
                        checkGame(); // Check if it is finished.
                        bird.die(level); // Bird's die animation.

                    }
                }

            }
        });
        }


}
