import java.awt.*;
import java.util.Map;

/***
 * Klasa odpowiadająca za tworzenie i rysyowanie obiektu typu notatka
 * Dane notatki składowane są w obiekcie DataOfNote
 */
public class Note {
//  Data
    private String text;
    private int posX;
    private int posY;
    private NotesSettings notesSettings;

//  Flags
    private boolean clickedFlag;

    /***
     * Tworzenie nowej notatki
     * @param x - miejsce X w którym stworzy się obiekt
     * @param y - współrzędna Y w któym stworzy się obiekt
     * @param dataMapOfNote - mapa dostarczona przez formularz tworzenia notatki (InputNoteForm) zawierająca dane do stworzenia notatki
     */
    public Note(int x, int y, Map<String, Object> dataMapOfNote){
//      Init
        notesSettings = new NotesSettings();

//      Data
        this.posX = x;
        this.posY = y;
        this.text = (String) dataMapOfNote.get("text");
    }

    /***
     * Tworzenie notatki na podstawie obiektu zawierającego dane notatki (klasa DataOfNote)
     * Konstrukto służy do stworzenia notatki na podstawie wczytanej z pliku json listy notatek danego przedmiotu
     * @param dataOfNote - zawiera dane potrzebne do odtworzenia notatek z zapisanej do pliku json listy przedmiotów
     */
    public Note(DataOfNote dataOfNote){
        notesSettings = new NotesSettings();
//      Data
        this.posX = dataOfNote.getPosX();
        this.posY = dataOfNote.getPosY();
        this.text = dataOfNote.getText();
    }

//  Setters and Getters
    public void setClickedFlag(){
        this.clickedFlag = !this.clickedFlag;
    }

    public void setClickedFlag(boolean flag){
        this.clickedFlag = flag;
    }

    public boolean getClickedFlag(){
        return this.clickedFlag;
    }

    /***
     * Funkcja tworząca i zwracająca obiekt zawierający dane notatki potrzebne do jej odtworzenia po wczytaniu pliku json
     * @return - obiekt DataOfNote
     */
    public DataOfNote getDataMap(){
        DataOfNote d = new DataOfNote(this.text, this.posX, this.posY);
        return d;
    }

    /***
     * Rysowanie obramowania w momęcie najechania na notatke kursorem
     * @param g - Graphics2D dostarczony z JComponentu
     */
    public void drawOutline(Graphics2D g){
        g.drawRect(this.posX, this.posY, notesSettings.getNoteWidth(), notesSettings.getNoteHeight());
    }

    /***
     * Funkcja sprawdzająca czy kursor jest nad obiektem
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     * @return True jeśli kursor jest nad obiektem else False
     */
    public boolean isOver(double mousePosX, double mousePosY) {
        if (this.posX < mousePosX && mousePosX < (this.posX + notesSettings.getNoteWidth())) {
            if (this.posY < mousePosY && mousePosY < (this.posY + notesSettings.getNoteHeight())) {
                return true;
            }
        }
        return false;
    }

    /***
     * Rysowanie notatki
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public void drawNote(Graphics g) {
        g.setColor(notesSettings.getNoteColor());
        g.fillRect(this.posX, this.posY, notesSettings.getNoteWidth(), notesSettings.getNoteHeight());
        g.setColor(Color.WHITE);
        g.setFont(notesSettings.getNoteFont());
        g.drawString(this.text, (this.posX + notesSettings.getNoteTextOffsetX()), (this.posY + notesSettings.getNoteTextOffsetY()));
    }

    /***
     * Poruszanie notatką na podstawie położenia kursora jeśli flaga jest ustawiona na true (LPM jest wcisnięty)
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     */
    public void move(double mousePosX, double mousePosY){
        if (this.clickedFlag){
            this.posX = (int) mousePosX - notesSettings.getNoteWidth() / 2;
            this.posY = (int) mousePosY - notesSettings.getNoteHeight() / 2;
            //Right
            if ((this.posX + notesSettings.getNoteWidth()) > (notesSettings.getNoteLineStartX() - 1)){
                this.posX = notesSettings.getNoteLineStartX() - notesSettings.getNoteWidth() - 1;
            }
            //Left
            if (this.posX < notesSettings.getNoteWindowPosX() + 1){
                this.posX = notesSettings.getNoteWindowPosX() + 1;
            }
            //Down
            if (this.posY + notesSettings.getNoteHeight() > notesSettings.getNoteWindowPosY() + notesSettings.getNoteWindowSizeY() - 1){
                this.posY = notesSettings.getNoteWindowPosY() + notesSettings.getNoteWindowSizeY() - notesSettings.getNoteHeight() - 1;
            }
            //UP
            if (this.posY < notesSettings.getNoteWindowPosY() + 1){
                this.posY = notesSettings.getNoteWindowPosY() + 1;
            }
        }
    }

}
