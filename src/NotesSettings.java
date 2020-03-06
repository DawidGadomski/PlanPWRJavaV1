import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***
 * Ustawienai okna notatek
 */
public class NotesSettings extends Settings {
    // Settings
    private Color notesColor;

    //  URLs
    private String backIconURL;
    private String addIconURL;
    private String saveIconURL;

//  Icons
    private BufferedImage backIcon;
    private BufferedImage addIcon;
    private BufferedImage saveIcon;

    // Note settings
    private int noteWidth;
    private int noteHeight;
    private int noteFontSize;
    private Color noteColor;

    // Note window settings

    private int noteWindowWidth;
    private int noteWindowHeight;

    // Separate line settings
    private Color noteLineColor;
    private int noteLineOffsetX;
    private int noteLineOffsetY;
    private int noteLineStartX;
    private int noteLineStartY;
    private int noteLineEndX;
    private int noteLineEndY;

    // Icons settings
    private int noteIconsSize;
    private int noteIconsOffsetX;
    private int noteIconsOffsetY;
    private int noteIconPosX;
    private int noteBackIconPosY;
    private int noteAddIconPosY;
    private int noteSaveIconPosY;
    private int noteClearIconPosY;

    // Note text settings
    private Font noteFont;
    private int noteTextOffsetX;
    private int noteTextOffsetY;

    /***
     * Konstruktor opcji notatek
     */
    public NotesSettings(){
//      Notes Settings
        notesColor = new Color(155, 155, 155);

//  URLs
        backIconURL = "C:\\Users\\dawik\\IdeaProjects\\Plan PWR\\Icons\\back.png";
        saveIconURL = "C:\\Users\\dawik\\IdeaProjects\\Plan PWR\\Icons\\save.png";
        addIconURL = "C:\\Users\\dawik\\IdeaProjects\\Plan PWR\\Icons\\add.png";


//      Icons
        try{
            backIcon = ImageIO.read(new File(getBackIconURL()));
            addIcon = ImageIO.read(new File(getAddIconURL()));
            saveIcon = ImageIO.read(new File(getSaveIconURL()));
        }catch (IOException e){
            e.printStackTrace();
        }

//      Note settings
        noteWidth = 385;
        noteHeight = 200;
        noteFontSize = 20;
        noteColor = new Color(200, 200, 200);

//      Note window settings
        noteWindowWidth = 1020;
        noteWindowHeight = 600;

//      Separate line settings
        noteLineColor = new Color(127,127,127);
        noteLineOffsetX = 100;
        noteLineOffsetY = 20;
        noteLineStartX = noteWindowPosX + noteWindowWidth - noteLineOffsetX;
        noteLineStartY = noteWindowPosY + noteLineOffsetY;
        noteLineEndX = noteWindowPosX + noteWindowWidth - noteLineOffsetX;
        noteLineEndY = noteWindowPosY + noteWindowHeight - noteLineOffsetY;

//      Icons settings
        noteIconsSize = 64;
        noteIconsOffsetX = 20;
        noteIconsOffsetY = 20;
        noteIconPosX = noteWindowPosX + noteWindowWidth - noteIconsSize - noteIconsOffsetX;
        noteBackIconPosY = noteWindowPosY + noteIconsOffsetY;
        noteAddIconPosY = noteWindowPosY + 2 * noteIconsOffsetY + noteIconsSize;
        noteSaveIconPosY = noteWindowPosY + 3 * noteIconsOffsetY + 2 * noteIconsSize;
        noteClearIconPosY = noteWindowPosY + 7 * noteIconsOffsetY + 5 * noteIconsSize;

//       Note text settings
        noteFont = new Font("Arial", Font.PLAIN, noteFontSize);
        noteTextOffsetX = 5;
        noteTextOffsetY = noteFontSize + 5;
    }

//  Getters and Setters
    public Color getNotesColor() {
        return notesColor;
    }

    public String getBackIconURL() {
        return backIconURL;
    }

    public String getAddIconURL() {
        return addIconURL;
    }

    public String getSaveIconURL() {
        return saveIconURL;
    }

    public BufferedImage getBackIcon() {
        return backIcon;
    }

    public BufferedImage getAddIcon() {
        return addIcon;
    }

    public BufferedImage getSaveIcon() {
        return saveIcon;
    }

    public int getNoteWidth() {
        return noteWidth;
    }

    public int getNoteHeight() {
        return noteHeight;
    }

    public int getNoteFontSize() {
        return noteFontSize;
    }

    public Color getNoteColor() {
        return noteColor;
    }

    public int getNoteWindowPosX() {
        return noteWindowPosX;
    }

    public int getNoteWindowPosY() {
        return noteWindowPosY;
    }

    public int getNoteWindowWidth() {
        return noteWindowWidth;
    }

    public int getNoteWindowHeight() {
        return noteWindowHeight;
    }

    public Color getNoteLineColor() {
        return noteLineColor;
    }

    public int getNoteLineOffsetX() {
        return noteLineOffsetX;
    }

    public int getNoteLineOffsetY() {
        return noteLineOffsetY;
    }

    public int getNoteLineStartX() {
        return noteLineStartX;
    }

    public int getNoteLineStartY() {
        return noteLineStartY;
    }

    public int getNoteLineEndX() {
        return noteLineEndX;
    }

    public int getNoteLineEndY() {
        return noteLineEndY;
    }

    public int getNoteIconsSize() {
        return noteIconsSize;
    }

    public int getNoteIconsOffsetX() {
        return noteIconsOffsetX;
    }

    public int getNoteIconsOffsetY() {
        return noteIconsOffsetY;
    }

    public int getNoteIconPosX() {
        return noteIconPosX;
    }

    public int getNoteBackIconPosY() {
        return noteBackIconPosY;
    }

    public int getNoteAddIconPosY() {
        return noteAddIconPosY;
    }

    public int getNoteSaveIconPosY() {
        return noteSaveIconPosY;
    }

    public int getNoteClearIconPosY() {
        return noteClearIconPosY;
    }

    public Font getNoteFont() {
        return noteFont;
    }

    public int getNoteTextOffsetX() {
        return noteTextOffsetX;
    }

    public int getNoteTextOffsetY() {
        return noteTextOffsetY;
    }

}
