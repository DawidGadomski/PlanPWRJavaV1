import Settings.Settings;
import Windows.MainWindow;
import Windows.MenuWindow;
import Object.Subject;
import Windows.SettingsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;

/***
 * Klasa odpowiadająca za rysowanie oraz kursor
 * Na podstawie flagi wybierany jest rysowany jeden z trzech obiektów - Windows.MainWindow, Menu, Notes
 */
public class Screen extends JComponent implements MouseListener, MouseMotionListener {
//  Settings.Settings
    private JFrame frame;
    private Graphics2D g2d;

    private double mousePosX;
    private double mousePosY;
    private Settings settings;

//  Flags
    private boolean mainWindowFlag;
//    private boolean menuFlag;
//    private boolean notesFlag;
    private MainWindow mainWindow;
    private SettingsWindow settingsWindow;
    private MenuWindow menu;
    private Notes notes;

    public Screen(JFrame frame) {
//      Settings.Settings
        this.settings = new Settings();
        this.mainWindow = new MainWindow(g2d);
        this.frame = frame;

//      Action Listeners for mouse
        addMouseListener(this);
        addMouseMotionListener(this);

//      Load Data / Ask if u want to create new plan
        int reply = JOptionPane.showConfirmDialog(null, "Wybór planu", "PLAN PWR", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION){
            try {
                settings.loadData();
            } catch (IOException  e) {e.printStackTrace();}
        }

//      Flags
//        mainWindowFlag = true;
//        menuFlag = false;
    }

// JComponent Functions
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        settings.setDefaultThickness(g2d.getStroke());
        settings.setDefaultTransform(g2d.getTransform());
        settings.setDefaultFont(g2d.getFont());

        mousePosX = MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX();
        mousePosY = MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY();

//      Draw Main Window
        mainWindow.drawMainWindow(g2d, mousePosX, mousePosY, settings.getSubjects());
        settings.setDefaultDrawing(g2d);

////      Draw Menu Window
//        if (menuFlag) {
//        settings.drawShadow(g2d);
//        }

//      Draw Notes Window
//        if (notesFlag) {
//        settings.drawShadow(g2d);
//        notes.drawNoteWindow(g2d, mousePosX, mousePosY);
//        }
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(settings.getWindowWidth(), settings.getWindowHeight());
    }

//  Mouse Listener Functions
    public void mouseClicked(MouseEvent e) {
//          Menu for subject
            if (e.getButton() == 3) {
                for (Subject s : settings.getSubjects()) {
                    if (s.isOver(getMousePosition().getX(), getMousePosition().getY())) {
                        s.setClickedFlag(false);
                        mainWindow.getClearIcon().setVisableFlag(false);
                        menu = new MenuWindow(frame, s, settings.getSubjects());
                        break;
                    }
                }
            }

            byte mainWindowAction = mainWindow.mainWindowAction(e);
            switch (mainWindowAction) {
                case 1:
//                  Add Object.Subject
                    settings.createSubject(this.frame);
                    break;
                case 2:
//                  Save plan
                    settings.saveData();
                    break;
                case 3:
//                  Settings
                    settingsWindow = new SettingsWindow(frame);
                    break;
            }
            repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {
//      Pick up subject
        if (e.getButton() == 1){
            for (Subject s : settings.getSubjects()){
                if (s.isOver(getMousePosition().getX(), getMousePosition().getY())){
                    mainWindow.getClearIcon().setVisableFlag();
                    s.setClickedFlag();
                    break;
                }
            }
        }

//      Pick up note
//        if(notesFlag) {
//            if (e.getButton() == 1) {
//                for (Object.Note n : notes.getSubject().getNoteArrayList()) {
//                    if (n.isOver(getMousePosition().getX(), getMousePosition().getY())) {
//                        mainWindow.getClearIcon().setVisableFlag();
//                        n.setClickedFlag();
//                        break;
//                    }
//                }
//            }
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//      Put subject down
        if (e.getButton() == 1){
            for (Subject s : settings.getSubjects()){
                if(s.getClickedFlag()) {
                    s.setClickedFlag(false);
                    if (mainWindow.getClearIcon().isOver(getMousePosition().x, getMousePosition().y)) {
                        settings.getSubjects().remove(s);
                        mainWindow.getClearIcon().setVisableFlag(false);
                        repaint();
                        break;
                    }
                    mainWindow.getClearIcon().setVisableFlag(false);
                    repaint();
                }
            }
        }

//      Put note down
//        if(notesFlag) {
//            if (e.getButton() == 1) {
//                for (Object.Note n : notes.getSubject().getNoteArrayList()) {
//                    if (n.isOver(getMousePosition().getX(), getMousePosition().getY())) {
//                        mainWindow.getClearIcon().setVisableFlag();
//                        n.setClickedFlag();
//                    }
//                }
//            }
//            if (e.getButton() == 1) {
//                    if (mainWindow.getClearIcon().isOver(getMousePosition().getX(), getMousePosition().getY())) {
//                        for (Object.Note n : notes.getSubject().getNoteArrayList()) {
//                            if (n.getClickedFlag()){
//                                settings.getNotes().remove(n);
//                            }
//                    }
//                }
//            }
//        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        if (!menuFlag) {
            repaint();
//        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    }
}

