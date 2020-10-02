package Icons;

import Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/***
 * Obiekt Ikony
 * Pozostałe ikony dziedziczą po tym obiekcie
 */
public class Icon extends JComponent {
    protected Settings settings;
    protected int size;
    protected int posX, posY;
    protected BufferedImage image;

    /***
     * Konstruktor ikony
     * @param x - współrzędna X miejsca w którym narysowana zostanie ikona
     * @param y - współrzędna Y miejsca w którym narysowana zostanie ikona
     */
    public Icon(int x, int y){
        this.posX = x;
        this.posY = y;
        this.settings = new Settings();
    }

//  Setters and Getters
    public void setPosX(int x){
        this.posX = x;
    }
    public void setPosY(int y){
        this.posY = y;
    }
    public void setSize(int s){
        this.size = s;
    }
    public void setIconImage(BufferedImage i){
        this.image = i;
    }
    public int getX(){
        return posX;
    }
    public int getY(){
        return posY;
    }
    public int getIconSize(){
        return size;
    }
    public BufferedImage getIconImage(){
        return image;
    }

    /***
     * Rysowanie ikony
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public void drawIcon(Graphics g) {
        g.drawImage(image, posX, posY, size, size, this);
    }


    /***
     * Funkcja zwracająca która ikona została kliknięta
     * @param mousePosX - pozycja kursora X
     * @param mousePosY - pozycja kursora Y
     * @return - liczba symbolizująca która ikona została kliknięta
     */
    public boolean isOver(double mousePosX, double mousePosY){
        if(posX < mousePosX  && mousePosX < (posX + getIconSize())){
            if (posY < mousePosY && mousePosY < (posY + getIconSize())){
                return true;
            }
        }
        return false;
    }

    public void setIconColor(){
        for(int y = 0; y < image.getHeight(); y++)
            for(int x = 0; x < image.getWidth(); x++)
            {
                if(image.getRGB(x, y) == Color.BLACK.getRGB()){
                    image.setRGB(x, y, settings.getSecondColor().getRGB());
                }
            }
    }
}
