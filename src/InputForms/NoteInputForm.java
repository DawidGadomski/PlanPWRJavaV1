package InputForms;

import Object.Note;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class NoteInputForm extends JDialog implements ActionListener{
//  Swing
    private JFrame noteInputFrame;
    private JPanel pNotes;
    private JTextArea taNote;
    private JButton bAccept;
    private JButton bCancel;
    private JLabel lText;
    private JPanel pButtons;
    private JButton bDelete;

    //  Data
    private String text;
    private Map<String, Object> dataMap;

    // Flags
    private boolean toDelete;

    /***
     * Konstruktor służący do stworzenia nowej notatki
     * @param frame
     */
    public NoteInputForm(JDialog frame) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        setContentPane(pNotes);
        setUndecorated(true);
        bDelete.setVisible(false);
        bCancel.addActionListener(this);
        bAccept.addActionListener(this);
        pack();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

    /***
     * Konstruktor do edycji istniejącej notatki
     * @param frame - parent frame
     * @param note - notatka do edycji
     */
    public NoteInputForm(JDialog frame, Note note) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);

        taNote.setText(note.getText());

        createDataOfNote();

        toDelete = false;
        setContentPane(pNotes);
        bCancel.setVisible(false);
        bAccept.setText("OK");
        bDelete.addActionListener(this);
        bAccept.addActionListener(this);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

//  Getters and Setters
    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Map<String, Object> getDataMap(){
        return this.dataMap;
    }

    public boolean getToDelete() {return this.toDelete;}

    /***
     * Zebranie danych z text area i dodanie ich do mapy która będzie zwracana przez formularz
     */
    public void createDataOfNote(){
        setText(taNote.getText());
        dataMap = new TreeMap<>();
        dataMap.put("text", getText());
    }

//  Buttons
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bAccept){
            createDataOfNote();
            setVisible(false);
        }
        else if(e.getSource() == bCancel){
            setVisible(false);
        }
        else if(e.getSource() == bDelete){
            toDelete = true;
            setVisible(false);
        }
    }
}
