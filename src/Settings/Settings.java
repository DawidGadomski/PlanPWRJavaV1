package Settings;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;

import Object.Subject;
import Object.DataOfNote;
import Object.DataOfSubject;
import Object.Note;
import InputForms.NoteInputForm;
import InputForms.InputForm;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/***
 * Główne ustawienia
 * Wszystkie dane dotyczace pozycji elementów są narazie ustawione
 * Docelowo chciałbym by interfejs był skalowalny
 * Pozostałe klasy z ustawieniami dziedziczą po tej klasie
 */
public class Settings {
    private String path;

//  Main Settings.Settings
    protected Dimension screenDimension;
    protected int windowWidth;
    protected int windowHeight;
    protected int tileWidth;
    protected int tileHeight;
    private int smallWindowWidth;
    private int smallWindowHeight;
    private Color backgroundColor;
    private Color gridColor;
    private Color workSurfaceColor;
    private Stroke lineThickness5;
    private Stroke lineThickness3;
    private SimpleDateFormat sdf;
    private String[] days;
    private Color transparency;
    private AffineTransform defaultTransform;
    private Font defaultFont;
    private Stroke defaultThickness;

    private String backIconURL;
    private String clearIconURL;
    private String addIconURL;
    private String saveIconURL;

    private BufferedImage backIcon;
    private BufferedImage addIcon;
    private BufferedImage clearIcon;
    private BufferedImage saveIcon;

    /***
     * Ustawienia potrzebne w głównej klasie ustawień
     * Możliwe że do zmiany
     */
    protected int noteWindowPosX;
    protected int noteWindowPosY;
    protected int workSurfacePosX;
    protected int workSurfacePosY;
    protected int workSurfaceWidth;
    protected int workSurfaceHeight;

//  File Settings.Settings
    private JFileChooser fileChooser;
    private FileNameExtensionFilter fileNameFilter;
    private FileWriter fileWriter;

//  Data
    private ArrayList<Subject> subjects;
    private ArrayList<DataOfSubject> subjectsList;
    private ArrayList<DataOfNote> notesList;
    protected ArrayList<Note> notes;

    /***
     * Konstruktor ustawień
     */
    public Settings(){
    /***
     * Docelowo ustawienie scieżki dla ikon i folderów chciałbym zrobić w oknie ustawień w aplikacji
     * Stąd na chwilę obecną po prostu ścieżki podane są tutaj oraz w klasach ikon
     */
        switch (System.getProperty("os.name")) {
            case "Windows 10":
                path = "D:\\PWR";
                break;
            case "Linux":
                path = "home";
                break;
        }

//      Settings.Settings
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        windowWidth = screenDimension.width;
        windowHeight = screenDimension.height;
        tileWidth = windowWidth / 58;
        tileHeight = windowHeight / 13;
        smallWindowWidth = windowWidth/2;
        smallWindowHeight = windowHeight/2;
        backgroundColor = new Color(40, 40, 40);
        gridColor = new Color(155, 155, 155);
        workSurfaceColor = new Color(200, 200, 200);
        lineThickness5 = new BasicStroke(5);
        lineThickness3 = new BasicStroke(3);
        sdf =  new SimpleDateFormat("HH.mm");
        days  = new String[]{"pn", "wt", "sr", "cz", "pt"};
        transparency  = new Color(0f,0f,0f,.5f);

//      Needed Settings.Settings
        workSurfacePosX = tileWidth * 3;
        workSurfacePosY = tileHeight;
        workSurfaceWidth = windowWidth - workSurfacePosX;
        workSurfaceHeight = windowHeight - workSurfacePosY;
        noteWindowPosX = 140;
        noteWindowPosY = 50;

//      Data
        subjects = new ArrayList<Subject>();
        subjectsList = new ArrayList<DataOfSubject>();
        notesList = new ArrayList<DataOfNote>();
        notes = new ArrayList<Note>();


        File iBack = new File("Icons/back.png");
        File iClear = new File("Icons/clear.png");
        File iAdd = new File("Icons/add.png");
        File iSave = new File("Icons/save.png");

        backIconURL = iBack.getAbsolutePath();
        clearIconURL = iClear.getAbsolutePath();
        addIconURL = iAdd.getAbsolutePath();
        saveIconURL = iSave.getAbsolutePath();

        try{
            backIcon = ImageIO.read(iBack);
            addIcon = ImageIO.read(iAdd);
            clearIcon = ImageIO.read(iClear);
            saveIcon = ImageIO.read(iSave);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    /***
     * Draw shadow effect
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public void drawShadow(Graphics2D g){
        g.setColor(transparency);
        g.fillRect(0, 0, windowWidth, windowHeight);
    }

    /***
     * Edycja istniejącego przedmiotu
     * Stworzenie obiktu InputForm - formularz zwracający dane przedmiotu potrzebne do jego stworzenia
     * Na podstawie mapy danych z formularza stowrzenie przedmiotu i dodanie go do listy przedmiotów
     * @param frame - JFrame która będzie rodzicem dla okna formularza
     * @param d - dane przedmiotu dla którego wywołana jest edycja zaweira dane potrzebne do wypełnienia formularza
     */
    public Subject editSubject(JFrame frame, DataOfSubject d){
        InputForm f = new InputForm(frame, d);
//        subjects.add(new Object.Subject(d.getPosX(), d.getPosY(), f.getDataMap()));
        return new Subject(d.getPosX(), d.getPosY(), f.getDataMap());
    }

    /***
     * Tworzenie nowego przedmiotu
     * Stworzenie obiktu InputForm - formularz zwracający dane przedmiotu potrzebne do jego stworzenia
     * Na podstawie mapy danych z formularza stowrzenie przedmiotu i dodanie go do listy przedmiotów
     * @param frame - JFrame która będzie rodzicem dla okna formularza
     */
    public void createSubject(JFrame frame){
        InputForm f = new InputForm(frame);
//      Check if dataMap has been created and returned
        if (f.getDataMap() != null) {
            subjects.add(new Subject(workSurfacePosX, workSurfacePosY, f.getDataMap()));
        }
    }

    /***
     * Edycja istniejącego notatki
     * Stworzenie obiktu InputNoteForm - formularz zwracający dane notatki potrzebne do jej stworzenia
     * Na podstawie mapy danych z formularza stowrzenie notatki i dodanie jej do listy notatek
     * @param frame - JFrame która będzie rodzicem dla okna formularza
     * @param d - dane przedmiotu dla którego wywołana jest edycja zaweira dane potrzebne do wypełnienia formularza
     */
    public Note editNote(JDialog frame, DataOfNote d){
        NoteInputForm f = new NoteInputForm(frame, d);
        return new Note(d.getPosX(), d.getPosY(), f.getDataMap());
    }

    /***
     * Stworzenie nowej notatki
     * Stworzenie obiktu InputNoteForm - formularz zwracający dane notatki potrzebne do jej stworzenia
     * Na podstawie mapy danych z formularza stowrzenie notatki i dodanie jej do listy notatek
     * @param frame - JFrame która będzie rodzicem dla okna formularza
     */
    public void createNote(JDialog frame, Subject subject){
        NoteInputForm f = new NoteInputForm(frame);
//      Check if dataMap has been created and returned
        if (f.getDataMap() != null) {
            subject.getNoteArrayList().add(new Note(noteWindowPosX, noteWindowPosY, f.getDataMap()));
        }
    }

    /***
     * Funkcja wczytania danych z pliku JSON
     * Wyczyszczenie listy przedmiotów i inicializacja Gson oraz FileChooser
     * Wybór pliku do wczytania
     * Odtworzenie listy zawierającej Dane przedmiotów
     * Na podstawie listy odtworzenie wszystkich zapisanych w niej przedmiotów
     * @throws IOException - problem z plikiem
     */
    public void loadData() throws IOException {
        subjectsList.clear();
        Gson gson = new Gson();
        fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            FileReader fileReader = new FileReader(file);
            DataOfSubject[] dataOfSubjectsList = gson.fromJson(fileReader, DataOfSubject[].class);
            subjectsList.addAll(Arrays.asList(dataOfSubjectsList));
        }
        for(DataOfSubject dos: subjectsList){
            subjects.add(new Subject(dos));
        }
    }

    /***
     * Funkcja zapisująca dane do pliku JSON
     * Stworzenie listy zawierającej dane przedmiotów
     * Wybór miejsca zapisu pliku
     * zapisanie listy z danymi do pliku JSON
     */
    public void saveData() {
        Gson gson = new Gson();
//      List of subject data
        subjectsList.clear();
        for (Subject subject : subjects) {
            subjectsList.add(subject.convertSubjectToData());
        }

        String json = gson.toJson(subjectsList);
        fileChooser = new JFileChooser();
        fileNameFilter = new FileNameExtensionFilter("JSON FILES", "json", "text");
        fileChooser.setFileFilter(fileNameFilter);

        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();

//          Saving to json
            try{
                fileWriter = new FileWriter(file);
                gson.toJson(subjectsList, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /***
     * Funkcja rysujaca wycentrowany tekst
     * @param g - Graphics2d dostarczony z JComponentu
     * @param text - tekst do narysowania
     * @param rect - prostokąt w którym ma byc wycentrowany tekst
     * @param font - czcionka tekstu
     * @param rot - czy tekst ma być obrócony o 90 stopni
     */
    public void drawCenteredString(Graphics2D g, String text, Rectangle rect, Font font, boolean rot){
        FontMetrics metrics = g.getFontMetrics();
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();

        if (rot) {
            AffineTransform textTransform = new AffineTransform();
            x = rect.x + (rect.width + metrics.getHeight()) / 2 - metrics.getDescent();
            y = rect.y - (rect.height - metrics.stringWidth(text)) / 2;
            textTransform.setToRotation(Math.toRadians(-90), x, y);
            g.setTransform(textTransform);
        }
        g.drawString(text, x, y);
    }

    /***
     * Funkcja ustawiająca domyślne ustawienia rysowania
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public void setDefaultDrawing(Graphics2D g){
        g.setTransform(defaultTransform);
        g.setFont(defaultFont);
        g.setStroke(defaultThickness);
        g.setColor(Color.WHITE);
    }
//  Getters and Setters

    public String getPath() {
        return path;
    }

    public void setDefaultTransform(AffineTransform defaultTransform) {
        this.defaultTransform = defaultTransform;
    }

    public void setDefaultFont(Font defaultFont) {
        this.defaultFont = defaultFont;
    }

    public void setDefaultThickness(Stroke defaultThickness) {
        this.defaultThickness = defaultThickness;
    }

    public AffineTransform getDefaultTransform() {
        return defaultTransform;
    }

    public Font getDefaultFont() {
        return defaultFont;
    }

    public Stroke getDefaultThickness() {
        return defaultThickness;
    }

    public int getSmallWindowWidth() {
        return smallWindowWidth;
    }

    public int getSmallWindowHeight() {
        return smallWindowHeight;
    }

    public int getWorkSurfacePosX() {
        return workSurfacePosX;
    }

    public int getWorkSurfacePosY() {
        return workSurfacePosY;
    }

    public int getWorkSurfaceWidth() {
        return workSurfaceWidth;
    }

    public int getWorkSurfaceHeight() {
        return workSurfaceHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getGridColor() {
        return gridColor;
    }

    public Color getWorkSurfaceColor() {
        return workSurfaceColor;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public ArrayList<DataOfSubject> getSubjectsList() {
        return subjectsList;
    }

    public ArrayList<DataOfNote> getNotesList() {
        return notesList;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public Color getTransparency() {
        return transparency;
    }

    public Stroke getLineThickness5() {
        return lineThickness5;
    }

    public Stroke getLineThickness3() {
        return lineThickness3;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public String[] getDays() {
        return days;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public BufferedImage getBackIcon() {
        return backIcon;
    }

    public BufferedImage getAddIcon() {
        return addIcon;
    }

    public BufferedImage getClearIcon() {
        return clearIcon;
    }

    public BufferedImage getSaveIcon() {
        return saveIcon;
    }
}
