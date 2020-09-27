package Settings;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***
 * Ustawienai okna z ustawieniami
 */

public class SettingsSettings extends Settings{   
//  Icons
    private File colorsIcon;
    private File advancedIcon;
    private File aboutIcon;

    private BufferedImage colorsImage;
    private BufferedImage advancedImage;
    private BufferedImage aboutImage;

    private int colorButtonsSize;
    
//  Konstruktor
    public SettingsSettings(){
//      Icons
        colorsIcon = new File("Icons/paint.png");
        advancedIcon = new File("Icons/maintenance.png");
        aboutIcon = new File("Icons/info.png");

        colorButtonsSize = windowWidth / 2 /20;

        try{
            colorsImage = ImageIO.read(getClass().getResource("/resources/images/paint.png"));
            advancedImage = ImageIO.read(getClass().getResource("/resources/images/maintenance.png"));
            aboutImage = ImageIO.read(getClass().getResource("/resources/images/info.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //  Getters

    public BufferedImage getColorsImage() {
        return colorsImage;
    }

    public BufferedImage getAdvancedImage() {
        return advancedImage;
    }

    public BufferedImage getAboutImage() {
        return aboutImage;
    }

    public int getColorButtonsSize() {
        return colorButtonsSize;
    }
}
