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
    private int daysOffset;

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

    //  Icon surface
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
    private int iconSize;
    private int clearIconSize;

    /***
     * Konstruktor opcji głównego okna
     */
    public MainWindowSettings(){
//      Work surface
        timePosX = 20;
        timePosY = 50;
        timeTextOffset = 100;
        daysPosX = 0;
        daysPosY = 50;
        daysOffset = 100;

//      Workspace
        workspacePosX = 0;
        workspacePosY = windowHeight - 200;
        workspaceWidth = windowWidth;
        workspaceHeight = 200;
        workspaceTextPosX = 0;
        workspaceTextPosY = windowHeight;
        workspaceFont = new Font("Arial", Font.PLAIN, 36);
        daysFont  = new Font("Arial", Font.PLAIN, 50);
        timesFont = new Font("Arial", Font.PLAIN, 15);

//      Icon surface
        iconSurfacePosX = 0;
        iconSurfacePosY = 0;
        iconSurfaceWidth = 100;
        iconSurfaceHeight = 50;

//      Icons settings
        clearIconPosX = windowWidth - 100;
        clearIconPosY = windowHeight - 100;
        saveIconPosX = 5;
        saveIconPosY = 10;
        addIconPosX = 55;
        addIconPosY = 10;
        iconSize = 30;
        clearIconSize = 64;
    }

//  Setters and Getters
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

    public int getDaysOffset() {
        return daysOffset;
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

    public int getIconSize() {
        return iconSize;
    }

    public int getClearIconSize() {
        return clearIconSize;
    }
}
