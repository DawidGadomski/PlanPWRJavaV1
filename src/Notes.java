import java.awt.*;
import java.awt.event.MouseEvent;

/***
 * Klasa tworząca okno Notatek w którym rysowane są notatki
 */
public class Notes {
    private Subject subject;
    private BackIcon noteBackIcon;
    private SaveIcon noteSaveIcon;
    private AddIcon noteAddIcon;
    private ClearIcon noteClearIcon;
    private NotesSettings notesSettings;

    /***
     *Konstruktor okna notatek
     * Inicializacja ustawień oraz ikon
     * Narysowanie efektu shadow
     * @param g - Graphics2d dostarczony z JComponentu
     * @param s - przedmiot dla którego otwarte zostało okno notatek
     */
    public Notes(Graphics2D g, Subject s){
//      Init
        notesSettings = new NotesSettings();
        this.subject = s;

//      Icons
        noteBackIcon = new BackIcon(notesSettings.getNoteIconPosX(), notesSettings.getNoteBackIconPosY(), notesSettings.getNoteIconsSize());
        noteAddIcon = new AddIcon(notesSettings.getNoteIconPosX(), notesSettings.getNoteAddIconPosY(), notesSettings.getNoteIconsSize());
        noteSaveIcon = new SaveIcon(notesSettings.getNoteIconPosX(), notesSettings.getNoteSaveIconPosY(), notesSettings.getNoteIconsSize());
        noteClearIcon = new ClearIcon(notesSettings.getNoteIconPosX(), notesSettings.getNoteClearIconPosY(), notesSettings.getNoteIconsSize());
        noteClearIcon.setVisableFlag(true);

//      Shadow effect in background
        notesSettings.drawShadow(g);
    }

//  Getters and Setters
    public Subject getSubject() {
        return this.subject;
    }

    /***
     * Rysowanie okienka notatek oraz rysowanie notatek
     * @param g - Graphics2d dostarczony z JComponentu
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     */
    public void drawNoteWindow(Graphics2D g, double mousePosX, double mousePosY) {
//      Background
        g.setColor(notesSettings.getNotesColor());
        g.fillRect(notesSettings.getNoteWindowPosX(), notesSettings.getNoteWindowPosY(), notesSettings.getNoteWindowWidth(), notesSettings.getNoteWindowHeight());

//      Line
        g.setColor(notesSettings.getNoteLineColor());
        g.setStroke(notesSettings.getLineThickness3());
        g.drawLine(notesSettings.getNoteLineStartX(), notesSettings.getNoteLineStartY(), notesSettings.getNoteLineEndX(), notesSettings.getNoteLineEndY());

//      Draw Icons
        noteBackIcon.drawIcon(g);
        noteAddIcon.drawIcon(g);
        noteSaveIcon.drawIcon(g);
        noteClearIcon.drawIcon(g);

//      Draw notes
        for (Note n : this.subject.getNoteArrayList()) {
            n.drawNote(g);
            if (n.isOver(mousePosX, mousePosY)) {
                n.drawOutline(g);
                if (n.getClickedFlag()) {
                    n.move(mousePosX, mousePosY);
                }
            }
        }
    }

    /***
     * Funkcja zwracająca która ikona została kliknięta
     * @param e - MouseListener Event (kliknięcie LPM)
     * @return - liczba symbolizująca która ikona została kliknięta
     */
    public byte notesAction (MouseEvent e){
        if (e.getButton() == 1 && noteBackIcon.isOver(e.getX(), e.getY())) {
            return 1;
        }
        else if (e.getButton() == 1 && noteAddIcon.isOver(e.getX(), e.getY())) {
            return 2;
        }
        else if (e.getButton() == 1 && noteSaveIcon.isOver(e.getX(), e.getY())) {
            return 3;
        }
        else {
            return 0;
        }
    }
}
