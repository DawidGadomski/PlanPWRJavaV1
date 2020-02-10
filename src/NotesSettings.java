import java.awt.*;

/***
 * Ustawienai okna notatek
 */
public class NotesSettings extends Settings {
    // Settings
    private Color notesColor;

    // Note settings
    private int noteWidth;
    private int noteHeight;
    private int noteFontSize;
    private Color noteColor;

    // Note window settings

    private int noteWindowSizeX;
    private int noteWindowSizeY;

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

//      Note settings
        noteWidth = 385;
        noteHeight = 200;
        noteFontSize = 20;
        noteColor = new Color(200, 200, 200);

//      Note window settings
        noteWindowSizeX = 1020;
        noteWindowSizeY = 600;

//      Separate line settings
        noteLineColor = new Color(127,127,127);
        noteLineOffsetX = 100;
        noteLineOffsetY = 20;
        noteLineStartX = noteWindowPosX + noteWindowSizeX - noteLineOffsetX;
        noteLineStartY = noteWindowPosY + noteLineOffsetY;
        noteLineEndX = noteWindowPosX + noteWindowSizeX - noteLineOffsetX;
        noteLineEndY = noteWindowPosY + noteWindowSizeY - noteLineOffsetY;

//      Icons settings
        noteIconsSize = 64;
        noteIconsOffsetX = 20;
        noteIconsOffsetY = 20;
        noteIconPosX = noteWindowPosX + noteWindowSizeX - noteIconsSize - noteIconsOffsetX;
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

    public int getNoteWindowSizeX() {
        return noteWindowSizeX;
    }

    public int getNoteWindowSizeY() {
        return noteWindowSizeY;
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
