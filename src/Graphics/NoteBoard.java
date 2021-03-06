package Graphics;

import InputForms.NoteInputForm;
import Settings.NotesSettings;
import Settings.AppProperties;
import Object.Note;
import Object.Subject;
import Windows.MenuWindow;
import com.sun.jdi.JDIPermission;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class NoteBoard extends JComponent implements MouseListener, MouseMotionListener {
    private JDialog frame;
    private Graphics2D g2d;
    private Subject subject;

    private double mousePosX;
    private double mousePosY;
    private NotesSettings notesSettings;
    private AppProperties appProperties;

    public NoteBoard(JDialog frame, AppProperties appProperties, Subject subject){
        this.frame = frame;
        this.appProperties = appProperties;
        this.subject = subject;

//      Action Listeners for mouse
        addMouseListener(this);
        addMouseMotionListener(this);

        notesSettings = new NotesSettings();
    }

    // JComponent Functions
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;

        mousePosX = MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX();
        mousePosY = MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY();

//      Draw Main Window
        drawNoteWindow(g2d, mousePosX, mousePosY);
    }

    public void drawNoteWindow(Graphics2D g, double mousePosX, double mousePosY) {

//      Draw notes
        for (Note n : this.subject.getNoteArrayList()) {
            n.drawNote(g);
            if (n.isOver(mousePosX, mousePosY)) {
                n.drawOutline(g);
                if (n.getClickedFlag()) {
                    n.move(mousePosX, mousePosY, getWidth(), getHeight());
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 3) {
            for (Note note : subject.getNoteArrayList()) {
                if (note.isOver(getMousePosition().getX(), getMousePosition().getY())) {
                    note.setClickedFlag(false);
                    NoteInputForm f = new NoteInputForm(frame, note);
                    if(!f.getToDelete()){
                        subject.getNoteArrayList().add(new Note(note.getDataMap().getPosX(),
                                note.getDataMap().getPosY(), f.getDataMap()));
                    }
                    subject.getNoteArrayList().remove(note);
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//      Pick up subject
        if (e.getButton() == 1){
            for (Note n : subject.getNoteArrayList()){
                if (n.isOver(getMousePosition().getX(), getMousePosition().getY())){
                    n.setClickedFlag();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//      Put subject down
        if (e.getButton() == 1){
            for (Note n : subject.getNoteArrayList()){
                if(n.getClickedFlag()) {
                    n.setClickedFlag(false);
                    repaint();
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    }
}
