package Settings;

import java.awt.*;

/***
 * Ustawienai okna głównego
 */
public class MainWindowSettings extends Settings {
    //  Work surface
    private int timePosX;
    private int timePosY;
    private int timeTextOffset;
    private int daysPosX;
    private int daysPosY;
    private int daysOffsetX;
    private int daysOffsetY;

    //  Workspace
    private int workspacePosX;
    private int workspacePosY;
    private int workspaceWidth;
    private int workspaceHeight;
    private int workspaceTextPosX;
    private int workspaceTextPosY;
    private Font workspaceFont;
    private Font daysFont;
    private Font timesFont;

    //  Icons surface
    private int iconSurfacePosX;
    private int iconSurfacePosY;
    private int iconSurfaceWidth;
    private int iconSurfaceHeight;

    //  Icons settings
    private int clearIconPosX;
    private int clearIconPosY;
    private int saveIconPosX;
    private int saveIconPosY;
    private int addIconPosX;
    private int addIconPosY;
    private int settingsIconPosX;
    private int settingsIconPosY;
    private int iconsWidth;
    private int iconsHeight;
    private int clearIconSize;

    /***
     * Konstruktor opcji głównego okna
     */
    public MainWindowSettings(){
//      Work surface
        timePosX = tileWidth;
        timePosY = tileHeight;
        timeTextOffset = tileWidth * 3;
        daysPosX = 0;
        daysPosY = tileHeight;
        daysOffsetX = tileWidth * 3;
        daysOffsetY = tileHeight * 2;

//      Workspace
        workspacePosX = 0;
        workspacePosY = windowHeight - tileHeight * 2;
        workspaceWidth = windowWidth;
        workspaceHeight = tileHeight * 2;
        workspaceTextPosX = 0;
        workspaceTextPosY = windowHeight;
        workspaceFont = new Font("Arial", Font.PLAIN, 20);
        daysFont  = new Font("Arial", Font.PLAIN, 50);
        timesFont = new Font("Arial", Font.PLAIN, 20);

//      Icons.Icon surface
        iconSurfacePosX = 0;
        iconSurfacePosY = 0;
        iconSurfaceWidth = tileWidth * 3;
        iconSurfaceHeight = tileHeight;

//      Icons settings
        clearIconPosX = windowWidth - tileWidth * 3;
        clearIconPosY = windowHeight - tileHeight * 2;
        saveIconPosX = 0;
        saveIconPosY = 5;
        addIconPosX = tileWidth;
        addIconPosY = 5;
        settingsIconPosX = tileWidth * 2;
        settingsIconPosY = 5;
        iconsWidth = tileWidth;
        iconsHeight = tileHeight;
        clearIconSize = 64;
    }

//  Getters
    public int getTimePosX() {
        return timePosX;
    }

    public int getTimePosY() {
        return timePosY;
    }

    public int getTimeTextOffset() {
        return timeTextOffset;
    }

    public int getDaysPosX() {
        return daysPosX;
    }

    public int getDaysPosY() {
        return daysPosY;
    }

    public int getDaysOffsetX() {
        return daysOffsetX;
    }

    public int getDaysOffsetY() {
        return daysOffsetY;
    }

    public int getWorkspacePosX() {
        return workspacePosX;
    }

    public int getWorkspacePosY() {
        return workspacePosY;
    }

    public int getWorkspaceWidth() {
        return workspaceWidth;
    }

    public int getWorkspaceHeight() {
        return workspaceHeight;
    }

    public int getWorkspaceTextPosX() {
        return workspaceTextPosX;
    }

    public int getWorkspaceTextPosY() {
        return workspaceTextPosY;
    }

    public Font getWorkspaceFont() {
        return workspaceFont;
    }

    public Font getDaysFont() {
        return daysFont;
    }

    public Font getTimesFont() {
        return timesFont;
    }

    public int getIconSurfacePosX() {
        return iconSurfacePosX;
    }

    public int getIconSurfacePosY() {
        return iconSurfacePosY;
    }

    public int getIconSurfaceWidth() {
        return iconSurfaceWidth;
    }

    public int getIconSurfaceHeight() {
        return iconSurfaceHeight;
    }

    public int getClearIconPosX() {
        return clearIconPosX;
    }

    public int getClearIconPosY() {
        return clearIconPosY;
    }

    public int getSaveIconPosX() {
        return saveIconPosX;
    }

    public int getSaveIconPosY() {
        return saveIconPosY;
    }

    public int getAddIconPosX() {
        return addIconPosX;
    }

    public int getAddIconPosY() {
        return addIconPosY;
    }

    public int getIconsWidth() {
        return iconsWidth;
    }

    public int getIconsHeight() {
        return iconsHeight;
    }

    public int getClearIconSize() {
        return clearIconSize;
    }

    public int getSettingsIconPosX() {
        return settingsIconPosX;
    }

    public int getSettingsIconPosY() {
        return settingsIconPosY;
    }
}
