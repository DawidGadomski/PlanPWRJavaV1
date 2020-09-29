package Object;

import Settings.Settings;

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/***
 * Klasa odpowiadająca za tworzenie i rysyowanie obiektu typu przedmiot
 * Dane przedmiotu składowane są w obiekcie Object.DataOfSubject
 */
public class Subject {
    private int width;
    private int height;
    private int posX;
    private int posY;
    private String name;
    private String term;
    private int time;
    private String prof;
    private String room;
    private int type;
    private String week;
    private Color color;
    private Map<String, Object> dataMap;
    private boolean clickedFlag;
    private Settings settings;
    private ArrayList<Note> noteArrayList;
    private int absences;
    private int allAbsences;
    private ArrayList<TestCard> testList;
    private ArrayList<LinkCard> linksList;

    /***
     * Tworzenie nowego przedmiotu
     * @param x - miejsce X w którym stworzy się obiekt
     * @param y - współrzędna Y w któym stworzy się obiekt
     * @param map - mapa dostarczona przez formularz tworzenia przedmiotu (InputForm) zawierająca dane do stworzenia przedmiotu
     */
    public Subject(int x, int y, Map<String, Object> map){
//      Init
        settings = new Settings();
        this.noteArrayList = new ArrayList<Note>();
        this.testList = new ArrayList<TestCard>();
        this.linksList = new ArrayList<LinkCard>();
        this.width = settings.getTileWidth();
        this.height =settings.getTileHeight()*2;
        this.color = new Color(255,0,255);

//      Data of Object.Subject
        this.posX = x;
        this.posY = y;
        this.name = String.valueOf(map.get("name"));
        this.term = String.valueOf(map.get("term"));
        this.time = Integer.parseInt(String.valueOf(map.get("time")));
        this.prof = String.valueOf(map.get("prof"));
        this.room = String.valueOf(map.get("room"));
        this.type = Integer.parseInt(String.valueOf(map.get("type")));
        this.week = String.valueOf(map.get("week"));
        this.absences = 0;
        this.allAbsences = 1;

//      Update color and width (based on data of subject)
        selectColor();
        setWidth();
        this.dataMap = map;

    }

    /***
     * Tworzenie przedmiotu na podstawie obiektu zawierającego dane przedmiotu (klasa Object.DataOfSubject)
     * Konstrukto służy do stworzenia przedmiotu na podstawie wczytanej z pliku json listy przedmiotow
     * @param dataOfSubject - zawiera dane potrzebne do odtworzenia przedmiotu z zapisanej do pliku json listy przedmiotów
     */
    public Subject(DataOfSubject dataOfSubject){
//      Init
        settings = new Settings();
        noteArrayList = new ArrayList<Note>();
        this.testList = new ArrayList<TestCard>();
        this.linksList = new ArrayList<LinkCard>();
        this.width = settings.getTileWidth();
        this.height = settings.getTileHeight()*2;
        this.color = new Color(255,0,255);

//      Data of Object.Subject
        this.posX = dataOfSubject.getPosX();
        this.posY = dataOfSubject.getPosY();
        this.name = dataOfSubject.getName();
        this.term = dataOfSubject.getTerm();
        this.time = dataOfSubject.getTime();
        this.prof = dataOfSubject.getProf();
        this.room = dataOfSubject.getRoom();
        this.type = dataOfSubject.getType();
        this.week = dataOfSubject.getWeek();
        this.absences = dataOfSubject.getAbsences();
        this.allAbsences = dataOfSubject.getAllAbsences();
        this.testList = dataOfSubject.getTestList();

        for(DataOfNote dataOfNote: dataOfSubject.getNotes()){
            noteArrayList.add(new Note(dataOfNote));
        }

//      Update color and width (based on data of subject)
        selectColor();
        setWidth();
    }

    public void addAbsence(){
        this.absences +=1;
    }
    public void removeAbsence(){
        this.absences -=1;
    }

//  Getters and Setters
    public void setNoteArrayList(ArrayList<Note> noteArrayList) {
        this.noteArrayList = noteArrayList;
    }

    public ArrayList<Note> getNoteArrayList() {
        return noteArrayList;
    }
    public String getName(){
        return this.name;
    }
    public String getTerm(){
        return this.term;
    }
    public String getProf(){
        return this.prof;
    }
    public String getRoom(){
        return this.room;
    }
    public String getWeek(){
        return this.week;
    }
    public int getTime(){
        return this.time;
    }
    public int getType(){
        return this.type;
    }

    public ArrayList<TestCard> getTestList() {
        return testList;
    }

    public ArrayList<LinkCard> getLinksList(){
        return this.linksList;
    }

    public void setTestList(ArrayList<TestCard> testList) {
        this.testList = testList;
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public int getAllAbsences() {
        return allAbsences;
    }

    public void setAllAbsences(int allAbsences) {
        this.allAbsences = allAbsences;
    }

    public void setClickedFlag(){
        this.clickedFlag = !this.clickedFlag;
    }

    public void setClickedFlag(boolean flag){
        this.clickedFlag = flag;
    }

    public boolean getClickedFlag(){
        return this.clickedFlag;
    }

    public void setColor(Color color) {
        this.color = color;
    }

//  Functions of Object.Subject

    /***
     * Funkcja tworząca i zwracająca obiekt zawierający dane przedmiotu potrzebne do odtworzenia
     * W ramach zapisu Notatek przedmiotu tworzona jest lista zawierająca dane notatek (Object.DataOfNote)
     * @return - obiekt Object.DataOfSubject
     */
    public DataOfSubject convertSubjectToData(){
//      List of Object.DataOfNote
        ArrayList<DataOfNote> dataOfNoteArrayList = new ArrayList<DataOfNote>();
        for(Note note: this.noteArrayList){
            dataOfNoteArrayList.add(note.getDataMap());
        }
//      Return Data Of Object.Subject
        return new DataOfSubject(this.posX, this.posY, this.name, this.term, this.time, this.prof,
                this.room, this.type, this.week, dataOfNoteArrayList, this.absences, this.allAbsences, this.testList);
    }

    /***
     * Funkcja wyznaczająca kolor przedmiotu na podstawie typu
     * Lab - czerwony
     * Wykład - niebieski
     */
    public void selectColor() {
        if (this.type == 1){
            setColor(new Color(255,0,0));
        }
        else if (this.type == 2){
            setColor(new Color(0,0,255));
        }
    }

    /***
     * Funkcja wyznaczająca szerokość przedmiotu na podstawie czasu
     */
    public void setWidth() {
        this.width = this.time / 15 * settings.getTileWidth();
    }

    /***
     * Rysowanie obramowania w momęcie najechania na przedmiot kursorem
     * @param g - Graphics2D dostarczony z JComponentu
     */
    public void drawOutline(Graphics2D g){
        if (this.week.equals("TN")){
            g.drawRect(this.posX, this.posY, this.width, (this.height / 2));
        }
        else if (this.week.equals("TP")){
            g.drawRect(this.posX, (this.posY + this.height / 2), this.width, (this.height / 2));
        }
        else{
            g.drawRect(this.posX, this.posY, this.width, this.height);
        }
    }

    /***
     * Funkcja sprawdzająca czy kursor jest nad obiektem
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     * @return True jeśli kursor jest nad obiektem else False
     */
    public boolean isOver(double mousePosX, double mousePosY){
        if (this.week.equals("TN")) {
            if (this.posX < mousePosX && mousePosX < (this.posX + width)) {
                if (this.posY < mousePosY && mousePosY < (this.posY + this.height / 2)) {
                    return true;
                }
            }
        }
        else if (this.week.equals("TP")) {
            if (this.posX < mousePosX && mousePosX < (this.posX + width)) {
                if (this.posY + this.height / 2 < mousePosY && mousePosY < (this.posY + height)) {
                    return true;
                }
            }
        }
        else{
            if (this.posX < mousePosX && mousePosX < (this.posX + width)) {
                if (this.posY < mousePosY && mousePosY < (this.posY + height)) {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Rysowanie przedmiotu
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public void drawSubject(Graphics g) {
        if (this.week.equals("TN")){
            g.setColor(this.color);
            g.fillRect(this.posX, this.posY, this.width, (this.height / 2));
            g.setColor(Color.WHITE);
            g.drawString(this.name, (this.posX + this.width / 2), (this.posY + this.height / 4));

        }
        else if (this.week.equals("TP")){
            g.setColor(this.color);
            g.fillRect(this.posX, (this.posY + this.height / 2), this.width, (this.height / 2));
            g.setColor(Color.WHITE);
            g.drawString(this.name, (this.posX + this.width / 2), (this.posY + this.height * 3 / 4));
        }
        else {
            g.setColor(this.color);
            g.fillRect(this.posX, this.posY, this.width, this.height);
            g.setColor(Color.WHITE);
            g.drawString(this.name, (this.posX + this.width / 2), (this.posY + this.height / 2));
        }

    }

    /***
     * Poruszanie przedmiotem na podstawie położenia kursora jeśli flaga jest ustawiona na true (LPM jest wcisnięty)
     * @param mousePosX - współrzędna X kursora
     * @param mousePosY - współrzędna Y kursora
     */
    public void move(double mousePosX, double mousePosY){
        if (this.clickedFlag){
            if (this.week.equals("TN")){
                this.posX = (int) mousePosX - this.width / 2;
                this.posY = (int) mousePosY - this.height / 4;
                //Right
                if ((this.posX + this.width) > (settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - 1)){
                    this.posX = settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - this.width - 1;
                }
                //Left
                if (this.posX < settings.getWorkSurfacePosX() + 1){
                    this.posX = settings.getWorkSurfacePosX() + 1;
                }
                //Down
                if (this.posY + this.height > settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - 1){
                    this.posY = settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - this.height - 1;
                }
                //UP
                if (this.posY < settings.getWorkSurfacePosY() + 1){
                    this.posY = settings.getWorkSurfacePosY() + 1;
                }

            }
            else if (this.week.equals("TP")){
                this.posX = (int) mousePosX - this.width / 2;
                this.posY = (int) mousePosY - this.height * 3 / 4;
                //Right
                if ((this.posX + this.width) > (settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - 1)){
                    this.posX = settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - this.width - 1;
                }
                //Left
                if (this.posX < settings.getWorkSurfacePosX() + 1){
                    this.posX = settings.getWorkSurfacePosX() + 1;
                }
                //Down
                if (this.posY + this.height > settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - 1){
                    this.posY = settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - this.height - 1;
                }
                //UP
                if (this.posY < settings.getWorkSurfacePosY() + 1){
                    this.posY = settings.getWorkSurfacePosY() + 1;
                }

            }
            else{
                this.posX = (int) mousePosX - this.width / 2;
                this.posY = (int) mousePosY - this.height / 2;
                //Right
                if ((this.posX + this.width) > (settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - 1)){
                    this.posX = settings.getWorkSurfaceWidth() + settings.getWorkSurfacePosX() - this.width - 1;
                }
                //Left
                if (this.posX < settings.getWorkSurfacePosX() + 1){
                    this.posX = settings.getWorkSurfacePosX() + 1;
                }
                //Down
                if (this.posY + this.height > settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - 1){
                    this.posY = settings.getWorkSurfaceHeight() + settings.getWorkSurfacePosY() - this.height - 1;
                }
                //UP
                if (this.posY < settings.getWorkSurfacePosY() + 1){
                    this.posY = settings.getWorkSurfacePosY() + 1;
                }

            }
        }
    }
}
