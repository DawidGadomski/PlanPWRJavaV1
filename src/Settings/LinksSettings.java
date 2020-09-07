package Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class LinksSettings extends Settings{
    private Color linksBGColor;
    private int textSize;
    private Color linksColor;

    private Color bgColor;
    private int linkWindowPosX;
    private int linkWindowPosY;
    private int linkWindowWidth;
    private int linkWindowHeight;

    private int iconSize;
    private Font font;

    private int linksWidth;
    private int linksHeight;

    public LinksSettings(){
        bgColor = new Color(150,150,150);
        linksBGColor = new Color(127, 127, 127);

        linkWindowPosX = 300;
        linkWindowPosY = 150;
        linkWindowWidth = windowWidth/2;
        linkWindowHeight = windowHeight/2;

        iconSize = 64;
        textSize = 20;
        font = new Font("Arial", Font.PLAIN, textSize);

//        linksPosX = linkWindowPosX + linkWindowWidth / 2 + 25;
//        linksPosY = linkWindowPosY + 25;
        linksWidth = linkWindowWidth / 2 - 30;
        linksHeight = linkWindowHeight / 2 - 50;


    }

    public int getLinksWidth() {
        return linksWidth;
    }

    public int getLinksHeight() {
        return linksHeight;
    }

    public Color getLinksBGColor() {
        return linksBGColor;
    }

    public void setLinksBGColor(Color linksBGColor) {
        this.linksBGColor = linksBGColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getLinksColor() {
        return linksColor;
    }

    public void setLinksColor(Color linksColor) {
        this.linksColor = linksColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public int getLinkWindowPosX() {
        return linkWindowPosX;
    }

    public void setLinkWindowPosX(int linkWindowPosX) {
        this.linkWindowPosX = linkWindowPosX;
    }

    public int getLinkWindowPosY() {
        return linkWindowPosY;
    }

    public void setLinkWindowPosY(int linkWindowPosY) {
        this.linkWindowPosY = linkWindowPosY;
    }

    public int getLinkWindowWidth() {
        return linkWindowWidth;
    }

    public void setLinkWindowWidth(int linkWindowWidth) {
        this.linkWindowWidth = linkWindowWidth;
    }

    public int getLinkWindowHeight() {
        return linkWindowHeight;
    }

    public void setLinkWindowHeight(int linkWindowHeight) {
        this.linkWindowHeight = linkWindowHeight;
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }
}
