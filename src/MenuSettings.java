import java.awt.*;
/***
 * Ustawienai okna menu
 */
public class MenuSettings extends Settings {
// Settings
    private Color fontColor;
    private int menuTextSize;
    private Font menuTextFont;

//  Menu window settings
    private Color bgColor;
    private int menuWindowPosX;
    private int menuWindowPosY;
    private int menuWindowWidth;
    private int menuWindowHeightY;

//  Separate line settings
    private Color lineColor;
    private int lineOffsetX;
    private int lineOffsetY;
    private int lineStartX;
    private int lineStartY;
    private int lineEndX;
    private int lineEndY;

// Text settings
    private int menuTextOffsetX;
    private int menuTextOffsetY;
    private int menuTextPosX;
    private int menuFirstTextPosY;
    private int menuSecondTextPosY;
    private int menuThirdTextPosY;
    private int menuForthTextPosY;

//  Icons settings
    private int menuIconPosX;
    private int menuIconPosY;
    private int menuIconOffset;
    private int menuIconSize;
    private int menuBackIconPosX;
    private int menuBackIconPosY;
    private int menuDocIconPosX;
    private int menuDocIconPosY;
    private int menuFolderIconPosX;
    private int menuFolderIconPosY;
    private int menuEditIconPosX;
    private int menuEditIconPosY;
    private int menuClearIconPosX;
    private int menuClearIconPosY;

    /***
     * Konstruktor opcji menu
     */
    public MenuSettings(){
//      Settings
        fontColor = Color.WHITE;
        menuTextSize = 20;
        menuTextFont = new Font("Arial", Font.PLAIN, menuTextSize);

//      Menu window settings
        bgColor = new Color(150,150,150);
        menuWindowPosX = 300;
        menuWindowPosY = 150;
        menuWindowWidth = 520;
        menuWindowHeightY = 300;

//      Separate line settings
        lineColor = new Color(127,127,127);
        lineOffsetX = 20;
        lineOffsetY = 80;
        lineStartX = menuWindowPosX + lineOffsetX;
        lineStartY = menuWindowPosY + menuWindowHeightY - lineOffsetY;
        lineEndX = menuWindowPosX + menuWindowWidth - lineOffsetX;
        lineEndY = menuWindowPosY + menuWindowHeightY - lineOffsetY;

//      Text settings
        menuTextOffsetX = 20;
        menuTextOffsetY = 20;
        menuTextPosX = menuWindowPosX + menuTextOffsetX;
        menuFirstTextPosY = menuWindowPosY + menuTextOffsetY;
        menuSecondTextPosY = menuWindowPosY + 2 * menuTextOffsetY + menuTextSize;
        menuThirdTextPosY = menuWindowPosY + 4 * menuTextOffsetY + menuTextSize;
        menuForthTextPosY = menuWindowPosY + 6 * menuTextOffsetY + menuTextSize;

//      Icons settings
        menuIconPosX = menuWindowPosX;
        menuIconPosY = 380;
        menuIconOffset = 33;
        menuIconSize = 64;
        menuBackIconPosX = menuIconPosX + menuIconOffset;
        menuBackIconPosY = menuIconPosY;
        menuDocIconPosX = menuIconPosX + menuIconSize + 2 * menuIconOffset;
        menuDocIconPosY = menuIconPosY;
        menuFolderIconPosX = menuIconPosX + 2 * menuIconSize + 3 * menuIconOffset;
        menuFolderIconPosY = menuIconPosY;
        menuEditIconPosX = menuIconPosX + 3 * menuIconSize + 4 * menuIconOffset;
        menuEditIconPosY = menuIconPosY;
        menuClearIconPosX = menuIconPosX + 4 * menuIconSize + 5 * menuIconOffset;
        menuClearIconPosY = menuIconPosY;
    }

//  Setters and Getters
    public Color getFontColor() {
        return fontColor;
    }

    public int getMenuTextSize() {
        return menuTextSize;
    }

    public Font getMenuTextFont() {
        return menuTextFont;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public int getMenuWindowPosX() {
        return menuWindowPosX;
    }

    public int getMenuWindowPosY() {
        return menuWindowPosY;
    }

    public int getMenuWindowWidth() {
        return menuWindowWidth;
    }

    public int getMenuWindowHeightY() {
        return menuWindowHeightY;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public int getLineOffsetX() {
        return lineOffsetX;
    }

    public int getLineOffsetY() {
        return lineOffsetY;
    }

    public int getLineStartX() {
        return lineStartX;
    }

    public int getLineStartY() {
        return lineStartY;
    }

    public int getLineEndX() {
        return lineEndX;
    }

    public int getLineEndY() {
        return lineEndY;
    }

    public int getMenuTextOffsetX() {
        return menuTextOffsetX;
    }

    public int getMenuTextOffsetY() {
        return menuTextOffsetY;
    }

    public int getMenuTextPosX() {
        return menuTextPosX;
    }

    public int getMenuFirstTextPosY() {
        return menuFirstTextPosY;
    }

    public int getMenuSecondTextPosY() {
        return menuSecondTextPosY;
    }

    public int getMenuThirdTextPosY() {
        return menuThirdTextPosY;
    }

    public int getMenuForthTextPosY() {
        return menuForthTextPosY;
    }

    public int getMenuIconPosX() {
        return menuIconPosX;
    }

    public int getMenuIconPosY() {
        return menuIconPosY;
    }

    public int getMenuIconOffset() {
        return menuIconOffset;
    }

    public int getMenuIconSize() {
        return menuIconSize;
    }

    public int getMenuBackIconPosX() {
        return menuBackIconPosX;
    }

    public int getMenuBackIconPosY() {
        return menuBackIconPosY;
    }

    public int getMenuDocIconPosX() {
        return menuDocIconPosX;
    }

    public int getMenuDocIconPosY() {
        return menuDocIconPosY;
    }

    public int getMenuFolderIconPosX() {
        return menuFolderIconPosX;
    }

    public int getMenuFolderIconPosY() {
        return menuFolderIconPosY;
    }

    public int getMenuEditIconPosX() {
        return menuEditIconPosX;
    }

    public int getMenuEditIconPosY() {
        return menuEditIconPosY;
    }

    public int getMenuClearIconPosX() {
        return menuClearIconPosX;
    }

    public int getMenuClearIconPosY() {
        return menuClearIconPosY;
    }
}
