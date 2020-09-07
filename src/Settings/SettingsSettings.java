package Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SettingsSettings extends Settings{
    private Color bgColor;
    private Color textColor;
    private Color lineColor;

    private int bigTextSize;
    private int smallTextSize;

    private Font bigTextFont;
    private Font smallTextFont;

    private File backIcon;
    private File colorsIcon;
    private File advancedIcon;
    private File aboutIcon;

    private BufferedImage backImage;
    private BufferedImage colorsImage;
    private BufferedImage advancedImage;
    private BufferedImage aboutImage;

    private int iconSize;

    public SettingsSettings(){
        bgColor = new Color(127,127,127);
        lineColor = new Color(155, 155, 155);
        textColor = new Color(255,255,255);

        bigTextSize = 32;
        smallTextSize = 24;

        bigTextFont = new Font("Arial", Font.PLAIN, bigTextSize);
        smallTextFont = new Font("Arial", Font.PLAIN, smallTextSize);

        backIcon = new File("Icons/back.png");
        colorsIcon = new File("Icons/paint.png");
        advancedIcon = new File("Icons/maintenance.png");
        aboutIcon = new File("Icons/info.png");

        try{
            backImage = ImageIO.read(backIcon);
            colorsImage = ImageIO.read(colorsIcon);
            advancedImage = ImageIO.read(advancedIcon);
            aboutImage = ImageIO.read(aboutIcon);
        }catch (IOException e){
            e.printStackTrace();
        }

        iconSize = 64;
    }

    public int getIconSize() {
        return iconSize;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public BufferedImage getBackImage() {
        return backImage;
    }

    public BufferedImage getColorsImage() {
        return colorsImage;
    }

    public BufferedImage getAdvancedImage() {
        return advancedImage;
    }

    public BufferedImage getAboutImage() {
        return aboutImage;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public int getBigTextSize() {
        return bigTextSize;
    }

    public int getSmallTextSize() {
        return smallTextSize;
    }

    public Font getBigTextFont() {
        return bigTextFont;
    }

    public Font getSmallTextFont() {
        return smallTextFont;
    }
}
