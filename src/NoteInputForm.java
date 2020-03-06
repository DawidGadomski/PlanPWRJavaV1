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

    //  Data
    private String text;
    private Map<String, Object> dataMap;

    /***
     * Konstruktor służący do stworzenia nowej notatki
     * @param frame
     */
    public NoteInputForm(JDialog frame) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        setContentPane(pNotes);
        bCancel.addActionListener(this);
        bAccept.addActionListener(this);
        pack();
//        setUndecorated(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

    /***
     * Konstruktor do edycji istniejącej notatki
     * @param frame - parent frame
     * @param dN - dane edytowanej notatki do uzupełnienia formularza
     */
    public NoteInputForm(JDialog frame, DataOfNote dN) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);

        System.out.println(dN.getText());
        taNote.setText(dN.getText());

        createDataOfNote();

        setContentPane(pNotes);
        bCancel.addActionListener(this);
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
    }
}
