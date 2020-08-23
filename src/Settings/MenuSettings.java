package Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***
 * Ustawienai okna menu
 */
public class MenuSettings extends Settings {
// Settings.Settings
    private Color fontColor;
    private int menuTextSize;
    private Font menuTextFont;

//  URLs
    private String docIconURL;
    private String editIconURL;
    private String notesIconURL;
    private String editAbsencesIconURL;
    private String plusIconURL;
    private String minusIconURL;
    private String okIconURL;
    private String linksIconURL;

//  Icons
    private BufferedImage editAbsencesIcon;
    private BufferedImage noteIcon;
    private BufferedImage editIcon;
    private BufferedImage docIcon;
    private BufferedImage plusIcon;
    private BufferedImage minusIcon;
    private BufferedImage okIcon;
    private BufferedImage linksIcon;

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

//  Tests settings
    private Color testBGColor;
    private int testInfoPosX;
    private int testInfoPosY;
    private int testInfoWidth;
    private int testInfoHeight;
    private int testInfoTextPosX;
    private int testInfoTextPosY;

//  Absences settings
    private Color absencesBgColor;
    private int absencesInfoPosX;
    private int absencesInfoPosY;
    private int absencesInfoWidth;
    private int absencesInfoHeight;
    private int absencesInfoTextPosX;
    private int absencesInfoTextPosY;

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
    private int addTestIconPosX;
    private int addTestIconPosY;
    private int smallIconSize;

    /***
     * Konstruktor opcji menu
     */
    public MenuSettings(){
//      Settings.Settings
        fontColor = Color.WHITE;
        menuTextSize = 20;
        menuTextFont = new Font("Arial", Font.PLAIN, menuTextSize);

//  URLs
        File iDoc = new File("Icons/folder.png");
        File iEdit = new File("Icons/edit.png");
        File iNotes = new File("Icons/document.png");
        File iEditAbsences = new File("Icons/edit.png");
        File iPlus = new File("Icons/add.png");
        File iMinus = new File("Icons/minus.png");
        File iOk = new File("Icons/tick.png");
        File iLinks = new File("Icons/www.png");

        docIconURL = iDoc.getAbsolutePath();
        editIconURL = iEdit.getAbsolutePath();
        notesIconURL = iNotes.getAbsolutePath();
        editAbsencesIconURL = iEditAbsences.getAbsolutePath();
        plusIconURL = iPlus.getAbsolutePath();
        minusIconURL = iMinus.getAbsolutePath();
        okIconURL = iOk.getAbsolutePath();
        linksIconURL = iLinks.getAbsolutePath();

//      Icons
        try{
            noteIcon = ImageIO.read(iNotes);
            editIcon = ImageIO.read(iEdit);
            docIcon = ImageIO.read(iDoc);
            editAbsencesIcon = ImageIO.read(iEditAbsences);
            plusIcon = ImageIO.read(iPlus);
            minusIcon = ImageIO.read(iMinus);
            okIcon = ImageIO.read(iOk);
            linksIcon = ImageIO.read(iLinks);
        }catch (IOException e){
            e.printStackTrace();
        }

//      Menu window settings
        bgColor = new Color(150,150,150);
        menuWindowPosX = 300;
        menuWindowPosY = 150;
        menuWindowWidth = windowWidth/2;
        menuWindowHeightY = windowHeight/2;

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

//      Tests settings
        testBGColor = new Color(127, 127, 127);
        testInfoPosX = menuWindowPosX + menuWindowWidth / 2 + 25;
        testInfoPosY = menuWindowPosY + 25;
        testInfoWidth = menuWindowWidth / 2 - 30;
        testInfoHeight = menuWindowHeightY / 2 - 50;
        testInfoTextPosX = testInfoPosX;
        testInfoTextPosY = testInfoPosY - 5;

//      Absences settings
        absencesBgColor = new Color(127, 127,127);
        absencesInfoPosX = menuWindowPosX + menuWindowWidth / 2 + 25;
        absencesInfoPosY = menuWindowPosY + menuWindowHeightY / 2;
        absencesInfoWidth = menuWindowWidth / 2 - 30;
        absencesInfoHeight = 50;
        absencesInfoTextPosX = absencesInfoPosX;
        absencesInfoTextPosY = absencesInfoPosY - 5;

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
        addTestIconPosX = menuWindowPosX + menuWindowWidth / 2;
        addTestIconPosY = menuWindowPosY + 30;
        smallIconSize = 20;
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

    public String getDocIconURL() {
        return docIconURL;
    }

    public String getEditIconURL() {
        return editIconURL;
    }

    public String getNotesIconURL() {
        return notesIconURL;
    }


    public String getPlusIconURL() {
        return plusIconURL;
    }

    public String getMinusIconURL() {
        return minusIconURL;
    }

    public String getOkIconURL() {
        return okIconURL;
    }

    public String getEditAbsencesIconURL() {
        return editAbsencesIconURL;
    }

    public BufferedImage getEditAbsencesIcon() {
        return editAbsencesIcon;
    }

    public BufferedImage getNoteIcon() {
        return noteIcon;
    }

    public BufferedImage getEditIcon() {
        return editIcon;
    }

    public BufferedImage getDocIcon() {
        return docIcon;
    }

    public BufferedImage getPlusIcon() {
        return plusIcon;
    }

    public BufferedImage getMinusIcon() {
        return minusIcon;
    }

    public BufferedImage getOkIcon() {
        return okIcon;
    }

    public BufferedImage getLinksIcon() {
        return linksIcon;
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

    public Color getTestBGColor() {
        return testBGColor;
    }

    public int getTestInfoPosX() {
        return testInfoPosX;
    }

    public int getTestInfoPosY() {
        return testInfoPosY;
    }

    public int getTestInfoWidth() {
        return testInfoWidth;
    }

    public int getTestInfoHeight() {
        return testInfoHeight;
    }

    public int getAddTestIconPosX() {
        return addTestIconPosX;
    }

    public int getAddTestIconPosY() {
        return addTestIconPosY;
    }

    public int getSmallIconSize() {
        return smallIconSize;
    }

    public int getTestInfoTextPosX() {
        return testInfoTextPosX;
    }

    public int getTestInfoTextPosY() {
        return testInfoTextPosY;
    }

    public Color getAbsencesBgColor() {
        return absencesBgColor;
    }

    public int getAbsencesInfoPosX() {
        return absencesInfoPosX;
    }

    public int getAbsencesInfoPosY() {
        return absencesInfoPosY;
    }

    public int getAbsencesInfoWidth() {
        return absencesInfoWidth;
    }

    public int getAbsencesInfoHeight() {
        return absencesInfoHeight;
    }

    public int getAbsencesInfoTextPosX() {
        return absencesInfoTextPosX;
    }

    public int getAbsencesInfoTextPosY() {
        return absencesInfoTextPosY;
    }
}
