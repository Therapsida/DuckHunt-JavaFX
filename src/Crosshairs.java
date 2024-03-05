import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Crosshairs {

    /**
     * cursor list.
     */
    public static ArrayList<Image> crosshairs = new ArrayList<>();


    /**
     * cursor index of crosshairs list.
     */
    public static int currentCrosshair = 0;


    /**
     * current crosshair image.
     */
    public static ImageView crosshair = new ImageView();


    /**
     * sets everything default.
     */
    public static void setDefault(){
        currentCrosshair = 0;
        crosshair.setImage(crosshairs.get(currentCrosshair));
    }

    /**
     * create crosshairs list. sets current crosshair to first index.
     */
    public static void createCrosshairs(){
            for (int i = 1; i < 8 ; i++) {
                crosshairs.add(new Image(String.format("assets/crosshair/%d.png",i)));
            }

            crosshair.setImage(crosshairs.get(currentCrosshair));

        }

    /**
     * If left decrease currentcrosshair, if right increase currentcrosshair.
     * @param key of the event of settingsScene.
     */
        public static void changeCrosshair(String key) {
            if(key.equals("LEFT")){
                changeCurrentCrosshair(false);
                crosshair.setImage(crosshairs.get(currentCrosshair));
            }
            else if(key.equals("RIGHT")){
                changeCurrentCrosshair(true);
                crosshair.setImage(crosshairs.get(currentCrosshair));

            }



        }

    /**
     * change current crosshair according to value.
     * @param isIncrease if true right arrow key is pressed.
     */
    private static void changeCurrentCrosshair(boolean isIncrease){
            if(isIncrease){
                currentCrosshair = currentCrosshair == 5 ? 0 : currentCrosshair+1;
            }
            else{
                currentCrosshair = currentCrosshair == 0 ? 5 : currentCrosshair-1;
            }
        }
    }

