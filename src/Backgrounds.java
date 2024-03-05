import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
class Backgrounds {
    /**
     * Backgrounds list.
     */
    private static ArrayList<Image> backGrounds = new ArrayList<>();
    /**
     * Foregrounds list.
     */
    private static ArrayList<Image> foreGrounds = new ArrayList<>();


    /**
     * index of list.
     */
    private static int currentBackground = 0;

    /**
     * current back and fore grounds.
     */
    public static ImageView backGround = new ImageView();
    public static ImageView foreGround = new ImageView();

    /**
     * Creates background and foregrounds lists.
     */
    public static void createBackgrounds(){
        for (int i = 1; i < 7 ; i++) {
            backGrounds.add(new Image(String.format("assets/background/%d.png",i)));
        }
        for (int i = 1; i < 7 ; i++) {
            foreGrounds.add(new Image(String.format("assets/foreground/%d.png",i)));
        }
        backGround.setImage(backGrounds.get(currentBackground));
        foreGround.setImage(foreGrounds.get(currentBackground));
    }

    /**
     * sets first index of background and foreground.
     */
    public static void setDefault(){
        currentBackground = 0;
        backGround.setImage(backGrounds.get(currentBackground));
        foreGround.setImage(foreGrounds.get(currentBackground));
    }

    /**
     * changes background and background on settingMenu.
     * @param key if down or up
     */
    public static void changeBackground(String key) {
        if(key.equals("DOWN")){
            changeCurrentBackGround(false);
            backGround.setImage(backGrounds.get(currentBackground));
            foreGround.setImage(foreGrounds.get(currentBackground));
        }
        else if(key.equals("UP")){
            changeCurrentBackGround(true);
            backGround.setImage(backGrounds.get(currentBackground));
            foreGround.setImage(foreGrounds.get(currentBackground));
        }



    }

    /**
     * adds foreground after scaling it.
     * @return imageview  of foreground.
     */
    public static ImageView addForeGround(){
        foreGround.setScaleX(DuckHunt.SCALE);
        foreGround.setScaleY(DuckHunt.SCALE);
        return foreGround;
    }
    /**
     * adds background after scaling it.
     * @return imageview  of background.
     */
    public static ImageView addBackGround(){
        backGround.setScaleX(DuckHunt.SCALE);
        backGround.setScaleY(DuckHunt.SCALE);
        return backGround;
    }


    /**
     * Changes index value according to key.
     * @param isUp true if key equals up.
     */
    private static void changeCurrentBackGround(boolean isUp){
        if(isUp){
             currentBackground = currentBackground == 5 ? 0 : currentBackground+1;
        }
        else{
            currentBackground = currentBackground == 0 ? 5 : currentBackground-1;
        }
    }



}
