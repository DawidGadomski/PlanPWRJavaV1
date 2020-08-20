import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class LinksSettings extends Settings{
    private Color linksBGColor;
    private int textSize;
    private Color linksColor;

    private String backIconURL;
    private String addIconURL;

    private BufferedImage backIcon;
    private BufferedImage addIcon;

    private Color bgColor;
    private int linkWindowPosX;
    private int linkWindowPosY;
    private int linkWindowWidth;
    private int linkWindowHeight;

    private int iconSize;
    private Font font;

    private int linksPosX;
    private int linksPosY;
    private int linksWidth;
    private int linksHeight;

    public LinksSettings(){
        File iBack = new File("Icons/back.png");
        File iAddLink = new File("Icons/add.png");

        backIconURL = iBack.getAbsolutePath();
        addIconURL = iAddLink.getAbsolutePath();

        bgColor = new Color(150,150,150);
        linksBGColor = new Color(127, 127, 127);

        linkWindowPosX = 300;
        linkWindowPosY = 150;
        linkWindowWidth = 520;
        linkWindowHeight = 360;

        iconSize = 64;
        textSize = 20;
        font = new Font("Arial", Font.PLAIN, textSize);

//        linksPosX = linkWindowPosX + linkWindowWidth / 2 + 25;
//        linksPosY = linkWindowPosY + 25;
        linksWidth = linkWindowWidth / 2 - 30;
        linksHeight = linkWindowHeight / 2 - 50;
    }

    public int getLinksPosX() {
        return linksPosX;
    }

    public int getLinksPosY() {
        return linksPosY;
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

    public String getBackIconURL() {
        return backIconURL;
    }

    public void setBackIconURL(String backIconURL) {
        this.backIconURL = backIconURL;
    }

    public String getAddIconURL() {
        return addIconURL;
    }

    public void setAddIconURL(String addIconURL) {
        this.addIconURL = addIconURL;
    }

    public BufferedImage getBackIcon() {
        return backIcon;
    }

    public void setBackIcon(BufferedImage backIcon) {
        this.backIcon = backIcon;
    }

    public BufferedImage getAddIcon() {
        return addIcon;
    }

    public void setAddIcon(BufferedImage addIcon) {
        this.addIcon = addIcon;
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
