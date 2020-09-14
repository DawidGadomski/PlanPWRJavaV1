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
    
//  Konstruktor
    public SettingsSettings(){
//      Icons
        colorsIcon = new File("Icons/paint.png");
        advancedIcon = new File("Icons/maintenance.png");
        aboutIcon = new File("Icons/info.png");

        try{
            colorsImage = ImageIO.read(colorsIcon);
            advancedImage = ImageIO.read(advancedIcon);
            aboutImage = ImageIO.read(aboutIcon);
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
}