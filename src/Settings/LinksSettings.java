package Settings;

public class LinksSettings extends Settings {
    private int linkWindowPosX;
    private int linkWindowPosY;
    private int linkWindowWidth;
    private int linkWindowHeight;

    private int linksWidth;
    private int linksHeight;

    /***
    * Ustawienia okna z zakładkami
    */
    
    public LinksSettings() {
        linkWindowPosX = 300;
        linkWindowPosY = 150;
        linkWindowWidth = windowWidth / 2;
        linkWindowHeight = windowHeight / 2;

        linksWidth = linkWindowWidth / 2 - 30;
        linksHeight = linkWindowHeight / 2 - 50;
    }

    //  Getters

    public int getLinksWidth() {
        return linksWidth;
    }

    public int getLinksHeight() {
        return linksHeight;
    }

    public int getLinkWindowPosX() {
        return linkWindowPosX;
    }

    public int getLinkWindowPosY() {
        return linkWindowPosY;
    }

    public int getLinkWindowWidth() {
        return linkWindowWidth;
    }

    public int getLinkWindowHeight() {
        return linkWindowHeight;
    }
}


