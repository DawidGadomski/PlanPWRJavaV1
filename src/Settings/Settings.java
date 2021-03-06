package Settings;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import com.google.gson.Gson;

import Object.Subject;
import Object.DataOfNote;
import Object.DataOfSubject;
import Object.Note;
import Object.TestCard;
import InputForms.NoteInputForm;
import InputForms.InputForm;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Property;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/***
 * Główne ustawienia
 */
public class Settings {
    private static final String APPLICATION_NAME = "PLAN PWR";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "resources/credentials.json";
    private static final String AUTHOR_NAME = "Dawid Gadomski";
    private static final String VERSION = "1.0";
    private static final String[] LANGUAGES = {"English", "Polski"};

//  Display metrics
    protected Dimension screenDimension;
    protected int windowWidth;
    protected int windowHeight;
    protected int numberOfTilesX;
    protected int numberOfTilesY;
    protected int tileWidth;
    protected int tileHeight;
    private int smallWindowWidth;
    private int smallWindowHeight;
    
//  Strokes
    private Stroke lineThickness5;
    private Stroke lineThickness3;
    private AffineTransform defaultTransform;
    private Font defaultFont;
    private Stroke defaultThickness;
    
//  Date
    private SimpleDateFormat sdf;
    private String[] days;
    
//  URLs
    private String backIconURL;
    private String clearIconURL;
    private String addIconURL;
    private String saveIconURL;
    
//  Icons files
    private File iBack;
    private File iClear;
    private File iAdd;
    private File iSave;
    
//  Icons size
    protected int bigIconSize;
    protected int smallIconSize;

//  Icons
    private BufferedImage backIcon;
    private BufferedImage addIcon;
    private BufferedImage clearIcon;
    private BufferedImage saveIcon;

    // Text
    private Font mediumTextFont;
    private Font bigTextFont;
    private Font smallTextFont;

    protected int mediumTextSize;
    private int bigTextSize;
    private int smallTextSize;

//  Colors
    private Color transparency;

    /***
     * Ustawienia potrzebne w głównej klasie ustawień
     */
    protected int noteWindowPosX;
    protected int noteWindowPosY;
    protected int workSurfacePosX;
    protected int workSurfacePosY;
    protected int workSurfaceWidth;
    protected int workSurfaceHeight;

//  File
    private JFileChooser fileChooser;
    private FileNameExtensionFilter fileNameFilter;
    private FileWriter fileWriter;

//  Data
    private ArrayList<Subject> subjects;
    private ArrayList<DataOfSubject> subjectsList;
    private ArrayList<DataOfNote> notesList;
    protected ArrayList<Note> notes;
    private net.fortuna.ical4j.model.Calendar calendar;

//  TEMP
    private Color subjectColor;
    private String tmpType;
    private String tmpDTStartHH;
    private String tmpDTEndHH;
    private String tmpDTStartMM;
    private String tmpDTEndMM;
    private ArrayList<String> subjectsCreatedFromCalendar;

    /***
     * Konstruktor ustawień
     */
    public Settings(){
//      Display metrics
        screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        windowWidth = screenDimension.width;
        windowHeight = screenDimension.height;
        
        numberOfTilesX = 58;
        numberOfTilesY = 13;
        
        tileWidth = windowWidth / numberOfTilesX;
        tileHeight = windowHeight / numberOfTilesY;
        smallWindowWidth = windowWidth/2;
        smallWindowHeight = windowHeight/2;

//      Strokes
        lineThickness5 = new BasicStroke(5);
        lineThickness3 = new BasicStroke(3);
        
//      Date
        sdf =  new SimpleDateFormat("HH.mm");
        days  = new String[]{"pn", "wt", "śr", "cz", "pt"};


//      Needed Settings
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
        
//      Icons files
        iBack = new File("Icons/back.png");
        iClear = new File("Icons/clear.png");
        iAdd = new File("Icons/add.png");
        iSave = new File("Icons/save.png");

//      URLs
        backIconURL = iBack.getAbsolutePath();
        clearIconURL = iClear.getAbsolutePath();
        addIconURL = iAdd.getAbsolutePath();
        saveIconURL = iSave.getAbsolutePath();

//      Text
        mediumTextSize = 26;
        bigTextSize = 32;
        smallTextSize = 20;

        mediumTextFont = new Font("Arial", Font.PLAIN, mediumTextSize);
        bigTextFont = new Font("Arial", Font.PLAIN, bigTextSize);
        smallTextFont = new Font("Arial", Font.PLAIN, smallTextSize);

//      Colors
        transparency  = new Color(0f,0f,0f,.5f);

//      Icons size
        bigIconSize = 64;
        smallIconSize = 20;

//      Icons
        try{
            backIcon = ImageIO.read(getClass().getResource("/resources/images/back.png"));
            addIcon = ImageIO.read(getClass().getResource("/resources/images/add.png"));
            clearIcon = ImageIO.read(getClass().getResource("/resources/images/clear.png"));
            saveIcon = ImageIO.read(getClass().getResource("/resources/images/save.png"));
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

    public int getStringHight(Graphics2D g2, String str,
                                      float x, float y)
    {
        FontRenderContext frc = g2.getFontRenderContext();
        GlyphVector gv = g2.getFont().createGlyphVector(frc, str);
        return gv.getPixelBounds(null, x, y).height;
    }
    /***
     * Edycja istniejącego przedmiotu
     * Stworzenie obiktu InputForm - formularz zwracający dane przedmiotu potrzebne do jego stworzenia
     * Na podstawie mapy danych z formularza stowrzenie przedmiotu i dodanie go do listy przedmiotów
     * @param frame - JFrame która będzie rodzicem dla okna formularza
     * @param d - dane przedmiotu dla którego wywołana jest edycja zaweira dane potrzebne do wypełnienia formularza
     */
    public Subject editSubject(JFrame frame, DataOfSubject d, AppProperties appProperties){
        InputForm f = new InputForm(frame, d);
//        subjects.add(new Object.Subject(d.getPosX(), d.getPosY(), f.getDataMap()));
        if (f.getKind().equals("1")) {
            subjectColor = appProperties.getLabColor();
        }
        if (f.getKind().equals("2")) {
            subjectColor = appProperties.getLectureColor();
        }
        if (f.getKind().equals("3")){
            subjectColor = appProperties.getProjectColor();
        }
        if (f.getKind().equals("4")){
            subjectColor = appProperties.getSeminaryColor();
        }
        return new Subject(d.getPosX(), d.getPosY(), f.getDataMap(), subjectColor);
    }

    /***
     * Tworzenie nowego przedmiotu
     * Stworzenie obiktu InputForm - formularz zwracający dane przedmiotu potrzebne do jego stworzenia
     * Na podstawie mapy danych z formularza stowrzenie przedmiotu i dodanie go do listy przedmiotów
     * @param frame - JFrame która będzie rodzicem dla okna formularza
     */
    public void createSubject(JFrame frame, AppProperties appProperties){
        InputForm f = new InputForm(frame);
//      Check if dataMap has been created and returned
        if (f.getDataMap() != null) {
            if (f.getKind().equals("1")) {
                subjectColor = appProperties.getLabColor();
            }
            if (f.getKind().equals("2")) {
                subjectColor = appProperties.getLectureColor();
            }
            if (f.getKind().equals("3")){
                subjectColor = appProperties.getProjectColor();
            }
            if (f.getKind().equals("4")){
                subjectColor = appProperties.getSeminaryColor();
            }
            subjects.add(new Subject(workSurfacePosX, workSurfacePosY, f.getDataMap(), subjectColor));
        }
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

    public void setIconColor(BufferedImage icon, Color color){
        for(int y = 0; y < icon.getHeight(); y++)
            for(int x = 0; x < icon.getWidth(); x++)
            {
                if(icon.getRGB(x, y) == Color.BLACK.getRGB()){
                    icon.setRGB(x, y, color.getRGB());
                }
            }
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

    public void notifications() throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
//        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("add.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        for(Subject subject : subjects){
            for(TestCard tc : subject.getTestList())
            trayIcon.displayMessage(tc.getTestName(), tc.getTestDate(), TrayIcon.MessageType.INFO);
        }
    }

    private Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void loadFromGoogleCalendar(AppProperties appProperties) throws IOException, GeneralSecurityException {
        Map<String, Object> dataMap = new TreeMap<>();
        ArrayList<String> subjectsCreatedFromCalendar = new ArrayList<String>();
        String tmpType = "";

        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Iterate over the events in the specified calendar
        String pageToken = null;
        DateTime dateTime = new DateTime(String.valueOf(LocalDateTime.now()));
        do {
            Events events = service.events().list("primary").setTimeMin(dateTime).setPageToken(pageToken).execute();
            List<com.google.api.services.calendar.model.Event> items = events.getItems();
            for (Event event : items) {

                if(subjectsCreatedFromCalendar.contains(event.getSummary())){
                    continue;
                }
                else{
                    subjectsCreatedFromCalendar.add(event.getSummary());
                    String name = event.getSummary().substring(2);
                    dataMap.put("name", name);
                    if (event.getSummary().charAt(0) == 'W') {
                        tmpType = "2";
                    }
                    if (event.getSummary().charAt(0) == 'L') {
                        tmpType = "1";
                    }
                    if (event.getSummary().charAt(0) == 'P') {
                        tmpType = "3";
                    }
                    if (event.getSummary().charAt(0) == 'S') {
                        tmpType = "4";
                    }

                    dataMap.put("type", tmpType);

                    if (tmpType.equals("1")) {
                        subjectColor = appProperties.getLabColor();
                    }
                    if (tmpType.equals("2")) {
                        subjectColor = appProperties.getLectureColor();
                    }
                    if (tmpType.equals("3")){
                        subjectColor = appProperties.getProjectColor();
                    }
                    if (tmpType.equals("4")){
                        subjectColor = appProperties.getSeminaryColor();
                    }

                    String prof = event.getDescription();
                    dataMap.put("prof", prof);

                    String room = event.getLocation();
                    dataMap.put("room", room);

                    String week = "week";
                    dataMap.put("week", week);
                    String tmpDTStartHH = event.getStart().getDateTime().toString().substring(11,13);
                    String tmpDTStartMM = event.getStart().getDateTime().toString().substring(14,16);
                    String tmpYY = event.getStart().getDateTime().toString().substring(0,4);
                    String tmpMM = event.getStart().getDateTime().toString().substring(5,7);
                    String tmpDD = event.getStart().getDateTime().toString().substring(8,10);

                    LocalDate localDate = LocalDate.of(Integer.parseInt(tmpYY),
                            Integer.parseInt(tmpMM), Integer.parseInt(tmpDD));

                    DayOfWeek day = DayOfWeek.from(localDate);

                    String term = day.toString() + ", " + tmpDTStartHH + ":" + tmpDTStartMM;
                    dataMap.put("term", term);


                    String tmpDTEndHH = event.getEnd().getDateTime().toString().substring(11,13);
                    String tmpDTEndMM = event.getEnd().getDateTime().toString().substring(14,16);

                    int time = (Integer.parseInt(tmpDTEndHH) - Integer.parseInt(tmpDTStartHH))*60 + (Integer.parseInt(tmpDTEndMM) - Integer.parseInt(tmpDTStartMM));

                    dataMap.put("time", Integer.toString(time));

                    Subject s = new Subject((workSurfacePosX + tileWidth*(subjectsCreatedFromCalendar.size() - 1)), (windowHeight - tileHeight), dataMap, subjectColor);
                    dataMap.clear();
                    subjects.add(s);
                }

                pageToken = events.getNextPageToken();
            }
        }
        while (pageToken != null) ;




    }

    public void loadICalendar(AppProperties appProperties) throws IOException, ParserException {

        Map<String, Object> dataMap = new TreeMap<>();
        subjectsCreatedFromCalendar = new ArrayList<String>();

        fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            FileInputStream fin = new FileInputStream(file);

            CalendarBuilder builder = new CalendarBuilder();
            calendar = builder.build(fin);

        }
        for (net.fortuna.ical4j.model.component.CalendarComponent calendarComponent : calendar.getComponents()) {
//            System.out.println("Component [" + calendarComponent.getName() + "]");
            if(subjectsCreatedFromCalendar.contains(calendarComponent.getProperties().getProperties("SUMMARY").toString())){
                continue;
            }
            else{
                subjectsCreatedFromCalendar.add(calendarComponent.getProperties().getProperties("SUMMARY").toString());
            }
            for (Property property : calendarComponent.getProperties()) {
//                System.out.println("Property [" + property.getName() + ", " + property.getValue() + "]");
                if(property.getName().equals("SUMMARY")){
                    String name = property.getValue().substring(2);
                    dataMap.put("name", name);
                    if(property.getValue().charAt(0) == 'W'){
                        tmpType = "2";
                    }
                    if(property.getValue().charAt(0) == 'L'){
                        tmpType = "1";
                    }
                    if(property.getValue().charAt(0) == 'P'){
                        tmpType = "3";
                    }
                    if(property.getValue().charAt(0) == 'S'){
                        tmpType = "4";
                    }

                    dataMap.put("type", tmpType);

                    if (tmpType.equals("1")) {
                        subjectColor = appProperties.getLabColor();
                    }
                    if (tmpType.equals("2")) {
                        subjectColor = appProperties.getLectureColor();
                    }
                    if (tmpType.equals("3")){
                        subjectColor = appProperties.getProjectColor();
                    }
                    if (tmpType.equals("4")){
                        subjectColor = appProperties.getSeminaryColor();
                    }
                }
                if(property.getName().equals("DESCRIPTION")){
                    String prof = property.getValue();
                    dataMap.put("prof", prof);
                }
                if(property.getName().equals("LOCATION")){
                    String room = property.getValue();
                    dataMap.put("room", room);
                }
                if(property.getName().equals("DTSTART")){
                    String week = "week";
                    dataMap.put("week", week);
                    tmpDTStartHH = property.getValue().substring(9,11);
                    tmpDTStartMM = property.getValue().substring(11,13);
                    String tmpYY = property.getValue().substring(0,4);
                    String tmpMM = property.getValue().substring(4,6);
                    String tmpDD = property.getValue().substring(6,8);

                    LocalDate localDate = LocalDate.of(Integer.parseInt(tmpYY),
                            Integer.parseInt(tmpMM), Integer.parseInt(tmpDD));

                    DayOfWeek day = DayOfWeek.from(localDate);

                    String term = day.toString() + ", " + tmpDTStartHH + ":" + tmpDTStartMM;
                    dataMap.put("term", term);
                }
                if(property.getName().equals("DTEND")){
                    tmpDTEndHH = property.getValue().substring(9,11);
                    tmpDTEndMM = property.getValue().substring(11,13);
                }
            }
            int time = (Integer.parseInt(tmpDTEndHH) - Integer.parseInt(tmpDTStartHH))*60 + (Integer.parseInt(tmpDTEndMM) - Integer.parseInt(tmpDTStartMM));
            dataMap.put("time", String.valueOf(time));

            Subject s = new Subject((workSurfacePosX + tileWidth*(subjectsCreatedFromCalendar.size() - 1)), (windowHeight - tileHeight), dataMap, subjectColor);
            dataMap.clear();
            subjects.add(s);
        }
    }

//  Getters and Setters

    public String getAuthorName(){return AUTHOR_NAME;}

    public String getVersion(){return VERSION;}

    public String[] getLanguages(){return LANGUAGES;}

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

    public int getBigIconSize() {
        return bigIconSize;
    }

    public int getSmallIconSize() {
        return smallIconSize;
    }

    public Font getMediumTextFont() {
        return mediumTextFont;
    }

    public Font getBigTextFont() {
        return bigTextFont;
    }

    public Font getSmallTextFont() {
        return smallTextFont;
    }

    public int getMediumTextSize() {
        return mediumTextSize;
    }

    public int getBigTextSize() {
        return bigTextSize;
    }

    public int getSmallTextSize() {
        return smallTextSize;
    }
}
