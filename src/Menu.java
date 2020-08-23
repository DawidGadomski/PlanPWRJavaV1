import Icons.*;
import Settings.MenuSettings;
import Object.Subject;
import java.awt.*;
import java.awt.event.MouseEvent;

/***
 * Klasa tworząca okno menu
 */
public class Menu {
    private Subject subject;

//  Icons
    private BackIcon backIcon;
    private DocIcon docIcon;
    private FolderIcon folderIcon;
    private EditIcon editIcon;
    private ClearIcon clearIcon;
    private MenuSettings menuSettings;
    private AddIcon addTestIcon;

    /***
     * Konstruktor okna menu
     * Inicializacja ustawień i ikon
     * Narysowanie efektu Shadow
     * @param g - Graphics2d dostarczony z JComponentu
     * @param s - przedmiot dla którego otwarte zostało okno notatek
     */
    public Menu(Graphics2D g, Subject s){
//      Init
        this.subject = s;
        menuSettings = new MenuSettings();

//      Icons
        backIcon = new BackIcon(menuSettings.getMenuBackIconPosX(), menuSettings.getMenuBackIconPosY(), menuSettings.getMenuIconSize());
        docIcon = new DocIcon(menuSettings.getMenuDocIconPosX(), menuSettings.getMenuDocIconPosY(), menuSettings.getMenuIconSize());
        folderIcon = new FolderIcon(menuSettings.getMenuFolderIconPosX(), menuSettings.getMenuFolderIconPosY(), menuSettings.getMenuIconSize());
        editIcon = new EditIcon(menuSettings.getMenuEditIconPosX(), menuSettings.getMenuEditIconPosY(), menuSettings.getMenuIconSize());
        clearIcon = new ClearIcon(menuSettings.getMenuClearIconPosX(), menuSettings.getMenuClearIconPosY(), menuSettings.getMenuIconSize());
        clearIcon.setVisableFlag(true);
        addTestIcon = new AddIcon(menuSettings.getAddTestIconPosX(), menuSettings.getAddTestIconPosY(), menuSettings.getSmallIconSize());

//      Draw
        menuSettings.drawShadow(g);
    }

//  Getters and Setters
    public Subject getSubject(){
        return this.subject;
    }

    private void drawTestInfo(Graphics2D g){
        g.setColor(Color.WHITE);
        g.drawString("Kolokwia", menuSettings.getTestInfoTextPosX(), menuSettings.getTestInfoTextPosY());

        g.setColor(menuSettings.getTestBGColor());
        g.fillRect(menuSettings.getTestInfoPosX(), menuSettings.getTestInfoPosY(), menuSettings.getTestInfoWidth(), menuSettings.getTestInfoHeight());

        addTestIcon.drawIcon(g);
    }

    private void drawAbsencesInfo(Graphics2D g){
        g.setColor(Color.WHITE);
        g.drawString("Nieobecności", menuSettings.getAbsencesInfoTextPosX(), menuSettings.getAbsencesInfoTextPosY());

        g.setColor(menuSettings.getAbsencesBgColor());
        g.fillRect(menuSettings.getAbsencesInfoPosX(), menuSettings.getAbsencesInfoPosY(), menuSettings.getAbsencesInfoWidth(), menuSettings.getAbsencesInfoHeight());
    }
    /***
     * Rysowanie okna menu
     * @param g - Graphics2d dostarczony z JComponentu
     */
    public void drawMenuWindow(Graphics2D g){
//      Draw background
        g.setColor(menuSettings.getBgColor());
        g.fillRect(menuSettings.getMenuWindowPosX(), menuSettings.getMenuWindowPosY(), menuSettings.getMenuWindowWidth(), menuSettings.getMenuWindowHeightY());

//      Draw info about subject
        g.setColor(menuSettings.getFontColor());
        g.setFont(menuSettings.getMenuTextFont());
        g.drawString(("Nazwa: " + this.subject.getName()), menuSettings.getMenuTextPosX(), menuSettings.getMenuFirstTextPosY());
        g.drawString(("Termin: " + this.subject.getTerm()), menuSettings.getMenuTextPosX(), menuSettings.getMenuSecondTextPosY());
        g.drawString(("Prowadzacy: " + this.subject.getProf()), menuSettings.getMenuTextPosX(), menuSettings.getMenuThirdTextPosY());
        g.drawString(("Sala: " + this.subject.getRoom()), menuSettings.getMenuTextPosX(), menuSettings.getMenuForthTextPosY());

//      Draw Icons
        backIcon.drawIcon(g);
        docIcon.drawIcon(g);
        folderIcon.drawIcon(g);
        editIcon.drawIcon(g);
        clearIcon.drawIcon(g);

//      Draw separate line
        g.setColor(menuSettings.getLineColor());
        g.setStroke(menuSettings.getLineThickness3());
        g.drawLine(menuSettings.getLineStartX(), menuSettings.getLineStartY(), menuSettings.getLineEndX(), menuSettings.getLineEndY());


//      Draw Test Info
        drawTestInfo(g);

//      Draw Absence Info
        drawAbsencesInfo(g);
    }


    /***
     * Funkcja zwracająca która ikona została kliknięta
     * @param e - MouseListener Event (kliknięcie LPM)
     * @return - liczba symbolizująca która ikona została kliknięta
     */
    public byte menuAction (MouseEvent e){
        if (e.getButton() == 1 && backIcon.isOver(e.getX(), e.getY())) {
            return 1;
        }
        else if (e.getButton() == 1 && clearIcon.isOver(e.getX(), e.getY())) {
            return 2;
        }
        else if (e.getButton() == 1 && folderIcon.isOver(e.getX(), e.getY())) {
            return 3;
        }
        else if (e.getButton() == 1 && editIcon.isOver(e.getX(), e.getY())) {
            return 4;
        }
        else if (e.getButton() == 1 && docIcon.isOver(e.getX(), e.getY())) {
            return 5;
        }
        else {
            return 0;
        }
    }
}
