import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

/***
 * Klasa tworzaca okno główne
 */
public class MainWindow {
//  Icons
    private AddIcon addIcon;
    private SaveIcon saveIcon;
    private ClearIcon clearIcon;

//  Default drawing settings
    private AffineTransform defaultTransform;
    private Font defaultFont;
    private Stroke defaultThickness;

    private MainWindowSettings mainWindowSettings;

    /***
     * Konstruktor głównego okna
     * Inicializacja ustawien oraz ikon
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public MainWindow(Graphics2D g){
//      Init
        mainWindowSettings = new MainWindowSettings();

        // Icons
        addIcon = new AddIcon(mainWindowSettings.getAddIconPosX(), mainWindowSettings.getAddIconPosY(), mainWindowSettings.getIconSize());
        saveIcon = new SaveIcon(mainWindowSettings.getSaveIconPosX(), mainWindowSettings.getSaveIconPosY(), mainWindowSettings.getIconSize());
        clearIcon = new ClearIcon(mainWindowSettings.getClearIconPosX(), mainWindowSettings.getClearIconPosY(), mainWindowSettings.getClearIconSize());
    }

//  Getters and Setters
    public ClearIcon getClearIcon() {
        return clearIcon;
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

    /***
     * Rysowanie Tła
     * @param g - Graphics2d dostarczony z JComponentu
     */
    private void drawBackground(Graphics2D g){
//      Background
        g.setColor(mainWindowSettings.getBackgroundColor());
        g.fillRect(0,0,mainWindowSettings.getWindowWidth(),mainWindowSettings.getWindowHeight());

//      Grid
        g.setColor(mainWindowSettings.getGridColor());
        for(int x=mainWindowSettings.getWorkSurfacePosX(); x <= mainWindowSettings.getWindowWidth(); x+=mainWindowSettings.getTileWidth()){
            g.drawLine(x, 0, x, (mainWindowSettings.getWindowHeight() - mainWindowSettings.getWorkspaceHeight()));
        }
        for(int y=mainWindowSettings.getWorkSurfacePosY(); y <= (mainWindowSettings.getWindowHeight()-mainWindowSettings.getWorkspaceHeight()); y+=mainWindowSettings.getTileHeight()){
            g.drawLine(0, y, mainWindowSettings.getWindowWidth(), y);
        }

//      Workspace
        g.setColor(mainWindowSettings.getWorkSurfaceColor());
        g.fillRect(mainWindowSettings.getWorkspacePosX(), mainWindowSettings.getWorkspacePosY(), mainWindowSettings.getWorkspaceWidth(), mainWindowSettings.getWorkspaceHeight());

//      Grid line
        g.setColor(Color.WHITE);
        g.setStroke(mainWindowSettings.getLineThickness5());
        g.drawLine(mainWindowSettings.getWorkspacePosX(), mainWindowSettings.getWorkspacePosY(), mainWindowSettings.getWorkspaceWidth(), mainWindowSettings.getWorkspacePosY());

//      Note line
        g.drawLine(mainWindowSettings.getWorkSurfacePosX(), mainWindowSettings.getWorkspacePosY(), mainWindowSettings.getWorkSurfacePosX(), mainWindowSettings.getWindowHeight());

//      Note text
        g.setFont(mainWindowSettings.getWorkspaceFont());
        mainWindowSettings.drawCenteredString(g, "Workspace",
                new Rectangle(mainWindowSettings.getWorkspaceTextPosX(), mainWindowSettings.getWorkspaceTextPosY(), 100, 200), mainWindowSettings.getWorkspaceFont(), true);
    }

    /***
     * Rysowanie napisów czasu i dni
     * @param g - Graphics2d dostarczony z JComponentu
     */
    private void drawTimeOnGrid(Graphics2D g){
//      Draw days on grid
        g.setFont(mainWindowSettings.getDaysFont());
        for(int index = 0; index<mainWindowSettings.getDays().length; index++){
            mainWindowSettings.drawCenteredString(g, mainWindowSettings.getDays()[index],
                    new Rectangle(mainWindowSettings.getDaysPosX(), (mainWindowSettings.getDaysPosY() + mainWindowSettings.getDaysOffset() * index),
                            mainWindowSettings.getDaysOffset(), mainWindowSettings.getDaysOffset()), mainWindowSettings.getDaysFont(), false);
        }

//      Draw time on grid
        Timestamp timestamp = new Timestamp(2020,0,1,7,30,0,0);
        String timeStamp = mainWindowSettings.getSdf().format(timestamp);
        g.setFont(mainWindowSettings.getTimesFont());
        for(int index = 0; index < 55; index++){
            mainWindowSettings.drawCenteredString(g, timeStamp, new Rectangle((mainWindowSettings.getTimeTextOffset() + mainWindowSettings.getTimePosX() * index),
                    mainWindowSettings.getTimePosY(), mainWindowSettings.getTileWidth(), mainWindowSettings.getTimePosY()), mainWindowSettings.getTimesFont(), true);
            timestamp.setTime(timestamp.getTime() + TimeUnit.MINUTES.toMillis(15));
            timeStamp = mainWindowSettings.getSdf().format(timestamp);
        }

    }

    /***
     * Rysowanie ikon
     * @param g - Graphics2d dostarczony z JComponentu
     */
    private void drawIcons(Graphics2D g){
//      Icons
        addIcon.drawIcon(g);
        saveIcon.drawIcon(g);
        if(clearIcon.getVisableFlag()){
            clearIcon.drawIcon(g);
        }
    }

    /***
     * Rysowanie głównego okna
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public void drawMainWindow(Graphics2D g, double mousePosX, double mousePosY){
//      Default drawing settings
        defaultThickness = g.getStroke();
        defaultTransform = g.getTransform();
        defaultFont = g.getFont();

//      Drawing
        drawBackground(g);
        setDefaultDrawing(g);
        drawIcons(g);
        setDefaultDrawing(g);
        drawTimeOnGrid(g);
        setDefaultDrawing(g);

        //      Draw subjects
        for (Subject s : mainWindowSettings.getSubjects()) {
            s.drawSubject(g);
            if (s.isOver(mousePosX, mousePosY)) {
                s.drawOutline(g);
                if (s.getClickedFlag()) {
                    s.move(mousePosX, mousePosY);
                }
            }
        }
    }

    /***
     * Funkcja zwracająca która ikona została kliknięta
     * @param e - MouseListener Event (kliknięcie LPM)
     * @return - liczba symbolizująca która ikona została kliknięta
     */
    public byte mainWindowAction (MouseEvent e){
        if (e.getButton() == 1 && addIcon.isOver(e.getX(), e.getY())) {
            return 1;
        }
        else if (e.getButton() == 1 && saveIcon.isOver(e.getX(), e.getY())) {
            return 2;
        }
        else {
            return 0;
        }
    }

}
