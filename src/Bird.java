import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class Bird {
    private static final int WIDTH =  256*DuckHunt.SCALE; //Width of screen
    private static final int HEIGHT = 240*DuckHunt.SCALE; // height of screen
    private int currentBird = 0; //index of image value
    public ImageView bird; // birds imageview
    private int xDirection = 1; // direction of move
    private int yDirection = -1; // direction of move

    private double speed = 5; // speed of bird.

    /**
     * Cross fly images of bird
     */
    private ArrayList<Image> crossFly = new ArrayList<>();
    /**
     * Horizontol fly images of bird
     */
    private ArrayList<Image> horizontalFly = new ArrayList<>();
    /**
     * Die animation images of bird.
     */
    private ArrayList<Image> dieAnimation = new ArrayList<>();

    private Timeline flyAnimation;


    /**
     * Constructor of bird.
     * It creates lists of images and sets values of the birds.
     * @param color color of the bird
     * @param Xvalue starting X value of bird
     * @param Yvalue starting Y value of bird
     * @param speed speed of bird.
     */
    public Bird(int xDirection,int yDirection, String color,int Xvalue, int Yvalue,double speed){
        this.xDirection = xDirection;
        this.yDirection = yDirection;

        for (int i = 1; i < 4 ; i++) {
            crossFly.add(new Image(String.format("assets/duck_%s/%d.png",color,i)));
        }
        for(int i = 4; i < 7; i++){
            horizontalFly.add(new Image(String.format("assets/duck_%s/%d.png",color,i)));
        }
        for(int i = 7; i <9; i++){
            dieAnimation.add(new Image(String.format("assets/duck_%s/%d.png",color,i)));
        }

        Image image;
        if(yDirection != 0) {
             image = new Image(String.format("assets/duck_%s/1.png", color));
        }
        else{
            image = new Image(String.format("assets/duck_%s/4.png", color));
        }

        ImageView bird = new ImageView(image);
        bird.setScaleX(DuckHunt.SCALE);
        bird.setScaleY(DuckHunt.SCALE);
        bird.setX(Xvalue);
        bird.setY(Yvalue);
        this.bird = bird;
        this.speed = speed;
    }

    /**
     * Plays animation of the bird. Every frame it moves bird and changes animation.
     * @return  image view of bird.
     */
    public ImageView Play(){
        Timeline flyAnimation = new Timeline(new KeyFrame(Duration.millis(220), e -> {moveBird(); changeAnimation();}));
        this.flyAnimation = flyAnimation;
        flyAnimation.setCycleCount(Timeline.INDEFINITE);
        flyAnimation.play();

       return bird;
    }

    /**
     * Flying animations of the bird. Every frame it changes the imageview of the bird.
     */
    private void changeAnimation() {

        currentBird = ((currentBird + 1) % 3); // index changes from 0,1,2.

        if (yDirection != 0) { //Flying horizontally and vertically.
            bird.setImage(crossFly.get(currentBird));
            // Rotating the image according to its direction.
            if (xDirection > 0 && yDirection < 0) {
                bird.setRotate(0);
            } else if (xDirection > 0 && yDirection > 0) {
                bird.setRotate(90);
            } else if (xDirection < 0 && yDirection > 0) {
                bird.setRotate(180);
            } else {
                bird.setRotate(270);
            }

        } else { //Flying horizontally only
            if(xDirection < 0){
                bird.setScaleX(-DuckHunt.SCALE); // Right
            }
            else {
                bird.setScaleX(DuckHunt.SCALE); // Left
            }

            bird.setImage(horizontalFly.get(currentBird));

        }
    }

    /**
     * bird moves with its speed.
     */
    private void moveBird() {


        bird.setX(bird.getX() + (speed*9 *xDirection));

        bird.setY(bird.getY() + (speed*9*yDirection));

        edgeCheck();

    }

    /**
     * Gets the bounds and  according to edge value bird reflects.
     */
    private void edgeCheck() {
        Bounds bounds = bird.getBoundsInParent();
        double rightBoundX = bounds.getMaxX();
        double leftBoundX = bounds.getMinX();

        double upperBoundY = bounds.getMaxY();
        double lowerBoundY = bounds.getMinY();



        if (rightBoundX  >= WIDTH ) {
            xDirection = -1;
        }
        else if(leftBoundX <= 0 ){
            xDirection = 1;
        }

        if (upperBoundY  >= HEIGHT ) {
            yDirection = -1;
        }
        else if(lowerBoundY  <= 0){
            yDirection = 1;
        }
    }

    /**
     * Die animation of the bird.
     * @param level pane which holds birds.
     */
    public void die(Pane level){

        Image die = dieAnimation.get(0); //die image.
        Image fall = dieAnimation.get(1); // fall image.
        flyAnimation.stop();
        bird.setRotate(0);
       if(xDirection < 0){
           bird.setScaleX(-DuckHunt.SCALE);  // It falls to the left direction if it used to fly left.
        }

        bird.setImage(die);
        Timeline diesAnimation = new Timeline(new KeyFrame(Duration.millis(500)));
        diesAnimation.play();

        diesAnimation.setOnFinished(event -> {
            bird.setImage(fall);
            TranslateTransition fallAnimation = new TranslateTransition(Duration.millis(150*(HEIGHT-bird.getY())/25.5),bird); // End of the edge.
            fallAnimation.setToY(HEIGHT);
            fallAnimation.play();
            fallAnimation.setOnFinished(event1 -> level.getChildren().remove(bird));
        });


}

    /**
     * Gets bounds of bird. and checks if X and Y value is on the bounds.
     * @param X value of mouse location
     * @param Y value of mouse location
     * @return if bird is shot.
     */
    public boolean isClicked(double X, double Y) {
        Bounds bounds = bird.getBoundsInParent();
        double rightBoundX = bounds.getMaxX();
        double leftBoundX = bounds.getMinX();
        double upperBoundY = bounds.getMaxY();
        double lowerBoundY = bounds.getMinY();

        if(leftBoundX < X && rightBoundX > X && upperBoundY > Y && lowerBoundY < Y){
            return true;
        }
        else{
            return false;
        }
    }
}

