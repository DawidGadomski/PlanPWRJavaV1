import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuWindow extends JDialog implements ActionListener {
    private Subject subject;
    private MenuSettings menuSettings;
    private NotesWindow notes;
    private TestInputForm addTest;
    private EditAbsencesForm editAbsences;
    private ArrayList<Subject> subjects;

//  JComponents
    private JFrame frame;
    private JDialog dWindow;
    private JPanel iconPanel;
    private JPanel textPanel;
    private JPanel advancePanel;
    private JPanel absencesPanel;
    private GridBagConstraints constraints;
    private JButton backButton;
    private JButton docButton;
    private JButton editButton;
    private JButton noteButton;
    private JButton deleteButton;
    private JButton addTestButton;
    private JButton editAbsencesButton;
    private JButton absenceButton;
    private JSeparator sLine;
    private JLabel lName;
    private JLabel lTerm;
    private JLabel lRoom;
    private JLabel lProf;
    private JLabel lTests;
    private JScrollPane listScroller;
    private DefaultListModel<String> listModel;
    private JList testList;
    private JLabel lAbsences;


//  Icons
    private Image backIcon;
    private Image editAbsencesIcon;
    private Image addTestIcon;
    private Image deleteIcon;
    private Image noteIcon;
    private Image editIcon;
    private Image docIcon;

//  Flags
    private boolean editAbsencesFlag;

    public MenuWindow(JFrame frame, Subject subject, ArrayList<Subject> subjects){
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        this.frame = frame;
        this.subject = subject;
        this.subjects = subjects;
        menuSettings = new MenuSettings();

        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);
//        dWindow.setLocationRelativeTo(frame);

        dWindow.setSize(menuSettings.getMenuWindowWidth(), menuSettings.getMenuWindowHeightY());

        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        backIcon = menuSettings.getBackIcon().getScaledInstance(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize(), Image.SCALE_DEFAULT);
        deleteIcon = menuSettings.getDeleteIcon().getScaledInstance(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize(), Image.SCALE_DEFAULT);
        docIcon = menuSettings.getDocIcon().getScaledInstance(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize(), Image.SCALE_DEFAULT);
        noteIcon = menuSettings.getNoteIcon().getScaledInstance(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize(), Image.SCALE_DEFAULT);
        editIcon = menuSettings.getEditIcon().getScaledInstance(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize(), Image.SCALE_DEFAULT);
        addTestIcon = menuSettings.getAddTestIcon().getScaledInstance(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize(), Image.SCALE_DEFAULT);
        editAbsencesIcon = menuSettings.getEditAbsencesIcon().getScaledInstance(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize(), Image.SCALE_DEFAULT);

        iconPanel = new JPanel();
        iconPanel.setBackground(menuSettings.getBgColor());
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.X_AXIS));

        textPanel = new JPanel();
        textPanel.setBackground(menuSettings.getBgColor());
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));

        advancePanel = new JPanel();
        advancePanel.setBackground(menuSettings.getBgColor());
        advancePanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        absencesPanel = new JPanel();
        absencesPanel.setBackground(menuSettings.getAbsencesBgColor());
        absencesPanel.setLayout(new BoxLayout(absencesPanel, BoxLayout.LINE_AXIS));
        absencesPanel.setSize(new Dimension(menuSettings.getAbsencesInfoWidth(), menuSettings.getAbsencesInfoHeight()));

        lName = new JLabel("Nazwa: " + subject.getName());
        lName.setFont(menuSettings.getMenuTextFont());
        lName.setForeground(Color.WHITE);
        lTerm = new JLabel("Termin: " + subject.getTerm());
        lTerm.setFont(menuSettings.getMenuTextFont());
        lTerm.setForeground(Color.WHITE);
        lRoom = new JLabel("Sala: " + subject.getRoom());
        lRoom.setFont(menuSettings.getMenuTextFont());
        lRoom.setForeground(Color.WHITE);
        lProf = new JLabel("Prowadzący: " + subject.getProf());
        lProf.setFont(menuSettings.getMenuTextFont());
        lProf.setForeground(Color.WHITE);

        addTestButton = new JButton(new ImageIcon(addTestIcon));
        addTestButton.setMinimumSize(new Dimension(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize()));
        addTestButton.setPreferredSize(new Dimension(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize()));
        addTestButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getAddTestIconSize()));
//        addTestButton.setBorder(BorderFactory.createEmptyBorder());
        addTestButton.setContentAreaFilled(false);
        addTestButton.addActionListener(this);

        lTests = new JLabel("Kolokwia");
        lTests.setFont(menuSettings.getMenuTextFont());
        lTests.setForeground(Color.WHITE);

        editAbsencesButton = new JButton(new ImageIcon(editAbsencesIcon));
        editAbsencesButton.setMinimumSize(new Dimension(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize()));
        editAbsencesButton.setPreferredSize(new Dimension(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize()));
        editAbsencesButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getAddTestIconSize()));
//        addTestButton.setBorder(BorderFactory.createEmptyBorder());
        editAbsencesButton.setContentAreaFilled(false);
        editAbsencesButton.addActionListener(this);

        absenceButton = new JButton(new ImageIcon(addTestIcon));
        absenceButton.setMinimumSize(new Dimension(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize()));
        absenceButton.setPreferredSize(new Dimension(menuSettings.getAddTestIconSize(), menuSettings.getAddTestIconSize()));
        absenceButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getAddTestIconSize()));
//        absenceButtonButton.setBorder(BorderFactory.createEmptyBorder());
        absenceButton.setContentAreaFilled(false);
        absenceButton.addActionListener(this);

        lAbsences = new JLabel("Nieobecności");
        lAbsences.setFont(menuSettings.getMenuTextFont());
        lAbsences.setForeground(Color.WHITE);

        listModel = new DefaultListModel<String>();

        testList = new JList<String>(listModel); //data has type Object[]
        testList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        testList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        testList.setVisibleRowCount(-1);
        testList.setBackground(menuSettings.getTestBGColor());
        testList.setPreferredSize(new Dimension(menuSettings.getTestInfoWidth(), menuSettings.getTestInfoHeight()));

        listScroller = new JScrollPane(testList);
        listScroller.setPreferredSize(new Dimension(menuSettings.getTestInfoWidth(), menuSettings.getTestInfoHeight()));


        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        backButton.setPreferredSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getMenuIconSize()));
//        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        docButton = new JButton(new ImageIcon(docIcon));
        docButton.setMinimumSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        docButton.setPreferredSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        docButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getMenuIconSize()));
//        docButton.setBorder(BorderFactory.createEmptyBorder());
        docButton.setContentAreaFilled(false);
        docButton.addActionListener(this);

        editButton = new JButton(new ImageIcon(editIcon));
        editButton.setMinimumSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        editButton.setPreferredSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        editButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getMenuIconSize()));
//        editButton.setBorder(BorderFactory.createEmptyBorder());
        editButton.setContentAreaFilled(false);
        editButton.addActionListener(this);

        noteButton = new JButton(new ImageIcon(noteIcon));
        noteButton.setMinimumSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        noteButton.setPreferredSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        noteButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getMenuIconSize()));
//        noteButton.setBorder(BorderFactory.createEmptyBorder());
        noteButton.setContentAreaFilled(false);
        noteButton.addActionListener(this);

        deleteButton = new JButton(new ImageIcon(deleteIcon));
        deleteButton.setMinimumSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        deleteButton.setPreferredSize(new Dimension(menuSettings.getMenuIconSize(), menuSettings.getMenuIconSize()));
        deleteButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getMenuIconSize()));
//        deleteButton.setBorder(BorderFactory.createEmptyBorder());
        deleteButton.setContentAreaFilled(false);
        deleteButton.addActionListener(this);

        textPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        textPanel.add(lName);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lTerm);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lRoom);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lProf);
        textPanel.add(Box.createVerticalGlue());

        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(menuSettings.getLineColor());

        iconPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        iconPanel.add(Box.createHorizontalGlue());
        iconPanel.add(backButton);
        iconPanel.add(Box.createHorizontalGlue());
        iconPanel.add(sLine);

        iconPanel.add(docButton);
        iconPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(menuSettings.getLineColor());
        iconPanel.add(sLine);

        iconPanel.add(editButton);
        iconPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(menuSettings.getLineColor());
        iconPanel.add(sLine);

        iconPanel.add(noteButton);
        iconPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(menuSettings.getLineColor());
        iconPanel.add(sLine);

        iconPanel.add(deleteButton);
        iconPanel.add(Box.createHorizontalGlue());

        advancePanel.setBorder(new EmptyBorder(10, 0, 0, 10));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        advancePanel.add(addTestButton, constraints);
        constraints.fill = GridBagConstraints.LINE_END;
        constraints.weightx = 0.0;
        constraints.gridx = 1;
        constraints.gridy = 0;
        advancePanel.add(lTests, constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 1;
        advancePanel.add(listScroller, constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        advancePanel.add(editAbsencesButton, constraints);
        constraints.fill = GridBagConstraints.LINE_END;
        constraints.weightx = 0.0;
        constraints.gridx = 1;
        constraints.gridy = 2;
        advancePanel.add(lAbsences, constraints);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 3;


        absencesPanel.add(absenceButton);
        advancePanel.add(absencesPanel, constraints);

        dWindow.add(advancePanel, BorderLayout.LINE_END);
        dWindow.add(textPanel, BorderLayout.CENTER);
        dWindow.add(iconPanel, BorderLayout.PAGE_END);

        dWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            dWindow.setVisible(false);
        }
        else if(e.getSource() == docButton){
            File file = new File(menuSettings.getPath() + "\\" + this.subject.getName());
            if (!file.exists()) {
                file.mkdir();
            }
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(e.getSource() == editButton){
            this.subjects.add(menuSettings.editSubject(this.frame, this.subject.convertSubjectToData()));
            this.subjects.remove(this.subject);
            dWindow.setVisible(false);
        }
        else if(e.getSource() == noteButton){
            dWindow.setVisible(false);
            notes = new NotesWindow(this, this.subject);
        }
        else if(e.getSource() == deleteButton){
            this.subjects.remove(this.subject);
            dWindow.setVisible(false);
        }
        else if(e.getSource() == addTestButton){
            addTest = new TestInputForm(this);
            listModel.addElement(addTest.getOutput());
        }
        else if(e.getSource() == editAbsencesButton){
            editAbsencesFlag = true;
        }
    }
}
