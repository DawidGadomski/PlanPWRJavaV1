package InputForms;

;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import Object.DataOfSubject;

/***
 * Formularz do tworzenia przedmiotów
 */
public class InputForm extends JDialog implements ActionListener{
    private JDialog dialog;
    private JPanel pValue;
    private JTextField tfName;
    private JTextField tfTerm;
    private JTextField tfProf;
    private JTextField tfRoom;
    private JTextField tfTime;
    private JLabel lName;
    private JLabel lTerm;
    private JLabel lProf;
    private JLabel lRoom;
    private JLabel lTime;
    private JRadioButton rLab;
    private JRadioButton rLect;
    private JRadioButton rWeek;
    private JRadioButton rOddWeek;
    private JPanel pPanel;
    private JButton bAccept;
    private JButton bCancel;
    private JRadioButton rEvenWeek;
    private JRadioButton rbPro;
    private JRadioButton rbSem;
    private String name, term, time, prof, room, kind, week;

    private Map<String, Object> dataMap;

    /***
     * Konstruktor służący do stworzenia nowego przedmiotu
     * @param frame
     */
    public InputForm(JFrame frame) {
        super(frame, ModalityType.APPLICATION_MODAL);

        setContentPane(pPanel);
        bAccept.addActionListener(this);
        bCancel.addActionListener(this);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);

    }

    /***
     * Konstruktor do edycji istniejącego przedmiotu
     * @param frame - parent frame
     * @param d - dane edytowanego przedmiotu potrzebne do uzupełnienia formularza
     */
    public InputForm(JFrame frame, DataOfSubject d){
        super(frame, ModalityType.APPLICATION_MODAL);

        tfName.setText(d.getName());
        tfTerm.setText(d.getTerm());
        tfTime.setText(String.valueOf(d.getTime()));
        tfProf.setText(d.getProf());
        tfRoom.setText(d.getRoom());

        if (d.getType() == 1) {
            rLab.setSelected(true);
        }
        if (d.getType() == 2) {
            rLect.setSelected(true);
        }
        if (d.getType() == 3){
            rbPro.setSelected(true);
        }
        if (d.getType() == 4){
            rbSem.setSelected(true);
        }
        if (d.getWeek().equals("week")) {
            rWeek.setSelected(true);
        }
        if (d.getWeek().equals("TN")) {
            rOddWeek.setSelected(true);
        }
        if (d.getWeek().equals("TP")) {
            rEvenWeek.setSelected(true);
        }

        createDataOfSubject();

        setContentPane(pPanel);
        bAccept.addActionListener(this);
        bCancel.addActionListener(this);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

//  Getters and Setters
    public Map<String, Object> getDataMap(){
        return this.dataMap;
    }

    public void setName(String n) {
        this.name = n;
    }

    public void setTerm(String t) {
        this.term = t;
    }

    public void setTime(String t) {
        this.time = t;
    }

    public void setProf(String p) {
        this.prof = p;
    }

    public void setRoom(String r) {
        this.room = r;
    }

    public void setKind(String t) {
        this.kind = t;
    }

    public void setWeek(String w) {
        this.week = w;
    }

    public String getName() {
        return this.name;
    }

    public String getTerm() {
        return this.term;
    }

    public String getTime() {
        return this.time;
    }

    public String getProf() {
        return this.prof;
    }

    public String getRoom() {
        return this.room;
    }

    public String getKind() {
        return this.kind;
    }

    public String getWeek() {
        return this.week;
    }

    /***
     * Zebranie danych z text area i dodanie ich do mapy która będzie zwracana przez formularz
     */
    public void createDataOfSubject(){
        setName(tfName.getText());
        setTerm(tfTerm.getText());
        setTime(tfTime.getText());
        setProf(tfProf.getText());
        setRoom(tfRoom.getText());
        if (rLab.isSelected()) {
            setKind("1");
        }
        if (rLect.isSelected()) {
            setKind("2");
        }
        if(rbPro.isSelected()){
            setKind("3");
        }
        if(rbSem.isSelected()){
            setKind("4");
        }
        if (rWeek.isSelected()) {
            setWeek("week");
        }
        if (rOddWeek.isSelected()) {
            setWeek("TN");
        }
        if (rEvenWeek.isSelected()) {
            setWeek("TP");
        }
        dataMap = new TreeMap<>();
        dataMap.put("name", getName());
        dataMap.put("term", getTerm());
        dataMap.put("time", getTime());
        dataMap.put("prof", getProf());
        dataMap.put("room", getRoom());
        dataMap.put("type", getKind());
        dataMap.put("week", getWeek());
    }

//  Buttons
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bAccept){
            if(tfName.getText().equals("") | tfTime.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Uzupełnij pole Nazwa oraz Czas");
            } else {
                try {
                    Integer.parseInt(tfTime.getText());
                    createDataOfSubject();
                    setVisible(false);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Podaj czas w minutach !");
                }
            }
        }
        else if(e.getSource() == bCancel){
            setVisible(false);
        }
    }
}
