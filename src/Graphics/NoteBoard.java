package Graphics;

import Settings.NotesSettings;
import Settings.AppProperties;
import Object.Note;
import Object.Subject;
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

        System.out.println("X: " + mousePosX );
        System.out.println("Y: " + mousePosY );

//      Draw Main Window
        drawNoteWindow(g2d, mousePosX, mousePosY);
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(notesSettings.getSmallWindowWidth(), notesSettings.getSmallWindowHeight());
    }


    public void drawNoteWindow(Graphics2D g, double mousePosX, double mousePosY) {
        g.setColor(Color.CYAN);
        g.fillRect(0,0,notesSettings.getSmallWindowWidth(), notesSettings.getSmallWindowHeight());

//      Draw notes
        for (Note n : this.subject.getNoteArrayList()) {
            n.drawNote(g);
            if (n.isOver(mousePosX, mousePosY)) {
                System.out.println("tak");
                n.drawOutline(g);
                if (n.getClickedFlag()) {
                    n.move(mousePosX, mousePosY);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("tak");
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
