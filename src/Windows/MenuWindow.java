package Windows;

import Settings.AppProperties;
import Settings.MenuSettings;
import Object.Subject;
import Object.TestCard;
import InputForms.TestInputForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuWindow extends JDialog implements ActionListener, MouseListener {
    private Subject subject;
    private MenuSettings menuSettings;
    private NotesWindow notes;
    private LinksWindow linksWindow;
    private TestInputForm addTest;
    private ArrayList<Subject> subjects;
    private AppProperties appProperties;
    private ResourceBundle resourceBundle;
    private int allAbsences;
    private int absences;

//  JComponents
    private JFrame frame;
    private JPanel iconPanel;
    private JPanel textPanel;
    private JPanel advancePanel;
    private JPanel absencesPanel;
    private GridBagConstraints constraints;

//  Buttons
    private JButton backButton;
    private JButton docButton;
    private JButton editButton;
    private JButton noteButton;
    private JButton deleteButton;
    private JButton addTestButton;
    private JButton editAbsencesButton;
    private JButton absenceButton;
    private JButton plusButton;
    private JButton minusButton;
    private JButton okButton;
    private JButton linksButton;


    private JSeparator sLine;
    private JLabel lName;
    private JLabel lTextName;
    private JLabel lTerm;
    private JLabel lTextTerm;
    private JLabel lRoom;
    private JLabel lTextRoom;
    private JLabel lTextProf;
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
    private Image plusIcon;
    private Image minusIcon;
    private Image okIcon;
    private Image linksIcon;

//  Flags
    private boolean editAbsencesFlag;

    public MenuWindow(JFrame frame, AppProperties appProperties, ResourceBundle resourceBundle,
                      Subject subject, ArrayList<Subject> subjects){
        super(frame, ModalityType.APPLICATION_MODAL);

        this.frame = frame;
        this.subject = subject;
        this.subjects = subjects;
        this.appProperties = appProperties;
        this.resourceBundle = resourceBundle;

        menuSettings = new MenuSettings();
        allAbsences = subject.getAllAbsences();
        absences = subject.getAbsences();



//        dWindow = new JDialog();
        setLayout(new BorderLayout());
//        dWindow.setUndecorated(true);
        setSize(menuSettings.getMenuWindowWidth(), menuSettings.getMenuWindowHeightY());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setUndecorated(true);

        initIcons();

        initPanels();

        initLabels();

        initButtons();

        initDiffComponents();

        initTextPanel();

        initIconPanel();

        initAbsencesPanel();

        initAdvancePanel();

        add(advancePanel, BorderLayout.LINE_END);
        add(textPanel, BorderLayout.CENTER);
        add(iconPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    private void initIcons(){
        menuSettings.setIconColor(menuSettings.getNoteIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getClearIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getBackIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getDocIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getEditIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getEditAbsencesIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getPlusIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getMinusIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getOkIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getAddIcon(), appProperties.getSecondColor());
        menuSettings.setIconColor(menuSettings.getLinksIcon(), appProperties.getSecondColor());

        backIcon = menuSettings.getBackIcon().getScaledInstance(menuSettings.getBigIconSize(),
                menuSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        deleteIcon = menuSettings.getClearIcon().getScaledInstance(menuSettings.getBigIconSize(),
                menuSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        docIcon = menuSettings.getDocIcon().getScaledInstance(menuSettings.getBigIconSize(),
                menuSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        noteIcon = menuSettings.getNoteIcon().getScaledInstance(menuSettings.getBigIconSize(),
                menuSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        editIcon = menuSettings.getEditIcon().getScaledInstance(menuSettings.getBigIconSize(),
                menuSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        addTestIcon = menuSettings.getAddIcon().getScaledInstance(menuSettings.getSmallIconSize(),
                menuSettings.getSmallIconSize(), Image.SCALE_DEFAULT);
        editAbsencesIcon = menuSettings.getEditAbsencesIcon().getScaledInstance(menuSettings.getSmallIconSize(),
                menuSettings.getSmallIconSize(), Image.SCALE_DEFAULT);
        plusIcon = menuSettings.getAddIcon().getScaledInstance(menuSettings.getSmallIconSize(),
                menuSettings.getSmallIconSize(), Image.SCALE_DEFAULT);
        minusIcon = menuSettings.getMinusIcon().getScaledInstance(menuSettings.getSmallIconSize(),
                menuSettings.getSmallIconSize(), Image.SCALE_DEFAULT);
        okIcon = menuSettings.getOkIcon().getScaledInstance(menuSettings.getSmallIconSize(),
                menuSettings.getSmallIconSize(), Image.SCALE_DEFAULT);
        linksIcon = menuSettings.getLinksIcon().getScaledInstance(menuSettings.getBigIconSize(),
                menuSettings.getBigIconSize(), Image.SCALE_DEFAULT);
    }

    private void initPanels(){
        iconPanel = new JPanel();
        iconPanel.setBackground(appProperties.getFirstColor());
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.X_AXIS));

        textPanel = new JPanel();
        textPanel.setBackground(appProperties.getFirstColor());
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));

        advancePanel = new JPanel();
        advancePanel.setBackground(appProperties.getFirstColor());
        advancePanel.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        absencesPanel = new JPanel();
        absencesPanel.setBackground(appProperties.getSecondColor());
        absencesPanel.setLayout(new BoxLayout(absencesPanel, BoxLayout.LINE_AXIS));
        absencesPanel.setSize(new Dimension(menuSettings.getAbsencesInfoWidth(), menuSettings.getAbsencesInfoHeight()));
    }

    private void initLabels(){
        lTextName = new JLabel(resourceBundle.getString("name"));
        lTextName.setFont(menuSettings.getMediumTextFont());
        lTextName.setForeground(appProperties.getTextColor());

        lName = new JLabel(subject.getName());
        lName.setFont(menuSettings.getMediumTextFont());
        lName.setForeground(appProperties.getTextColor());
        lName.setBackground(appProperties.getSecondColor());

        lTextTerm = new JLabel(resourceBundle.getString("term"));
        lTextTerm.setFont(menuSettings.getMediumTextFont());
        lTextTerm.setForeground(appProperties.getTextColor());

        lTerm = new JLabel(subject.getTerm());
        lTerm.setFont(menuSettings.getMediumTextFont());
        lTerm.setForeground(appProperties.getTextColor());

        lTextRoom = new JLabel(resourceBundle.getString("room"));
        lTextRoom.setFont(menuSettings.getMediumTextFont());
        lTextRoom.setForeground(appProperties.getTextColor());

        lRoom = new JLabel(subject.getRoom());
        lRoom.setFont(menuSettings.getMediumTextFont());
        lRoom.setForeground(appProperties.getTextColor());

        lTextProf = new JLabel(resourceBundle.getString("lecturer"));
        lTextProf.setFont(menuSettings.getMediumTextFont());
        lTextProf.setForeground(appProperties.getTextColor());

        lProf = new JLabel(subject.getProf());
        lProf.setFont(menuSettings.getMediumTextFont());
        lProf.setForeground(appProperties.getTextColor());

        lTests = new JLabel(resourceBundle.getString("tests"));
        lTests.setFont(menuSettings.getMediumTextFont());
        lTests.setForeground(appProperties.getTextColor());
        lTests.setHorizontalAlignment(SwingConstants.RIGHT);

        lAbsences = new JLabel(resourceBundle.getString("absences"));
        lAbsences.setFont(menuSettings.getMediumTextFont());
        lAbsences.setForeground(appProperties.getTextColor());
        lAbsences.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    private void initButtons(){
        addTestButton = new JButton(new ImageIcon(addTestIcon));
        addTestButton.setMinimumSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        addTestButton.setPreferredSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        addTestButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getSmallIconSize()));
        addTestButton.setContentAreaFilled(false);
        addTestButton.addActionListener(this);

        editAbsencesButton = new JButton(new ImageIcon(editAbsencesIcon));
        editAbsencesButton.setMinimumSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        editAbsencesButton.setPreferredSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        editAbsencesButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getSmallIconSize()));
        editAbsencesButton.setContentAreaFilled(false);
        editAbsencesButton.addActionListener(this);

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        backButton.setPreferredSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getBigIconSize()));
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        docButton = new JButton(new ImageIcon(docIcon));
        docButton.setMinimumSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        docButton.setPreferredSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        docButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getBigIconSize()));
        docButton.setContentAreaFilled(false);
        docButton.addActionListener(this);

        editButton = new JButton(new ImageIcon(editIcon));
        editButton.setMinimumSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        editButton.setPreferredSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        editButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getBigIconSize()));
        editButton.setContentAreaFilled(false);
        editButton.addActionListener(this);

        noteButton = new JButton(new ImageIcon(noteIcon));
        noteButton.setMinimumSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        noteButton.setPreferredSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        noteButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getBigIconSize()));
        noteButton.setContentAreaFilled(false);
        noteButton.addActionListener(this);

        deleteButton = new JButton(new ImageIcon(deleteIcon));
        deleteButton.setMinimumSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        deleteButton.setPreferredSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        deleteButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getBigIconSize()));
        deleteButton.setContentAreaFilled(false);
        deleteButton.addActionListener(this);

        plusButton = new JButton(new ImageIcon(plusIcon));
        plusButton.setMinimumSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        plusButton.setPreferredSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        plusButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getSmallIconSize()));
        plusButton.setContentAreaFilled(false);
        plusButton.addActionListener(this);

        minusButton = new JButton(new ImageIcon(minusIcon));
        minusButton.setMinimumSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        minusButton.setPreferredSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        minusButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getSmallIconSize()));
        minusButton.setContentAreaFilled(false);
        minusButton.addActionListener(this);

        okButton = new JButton(new ImageIcon(okIcon));
        okButton.setMinimumSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        okButton.setPreferredSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
        okButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getSmallIconSize()));
        okButton.setContentAreaFilled(false);
        okButton.addActionListener(this);

        linksButton = new JButton(new ImageIcon(linksIcon));
        linksButton.setMinimumSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        linksButton.setPreferredSize(new Dimension(menuSettings.getBigIconSize(), menuSettings.getBigIconSize()));
        linksButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getBigIconSize()));
        linksButton.setContentAreaFilled(false);
        linksButton.addActionListener(this);
    }

    private void initDiffComponents(){
        listModel = new DefaultListModel<String>();
        for(TestCard test: subject.getTestList()){
            listModel.addElement(test.getTestName() + " - " + test.getTestDate().toString());
        }

        testList = new JList<String>(listModel); //data has type Object[]
        testList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        testList.setLayoutOrientation(JList.VERTICAL_WRAP);
        testList.setVisibleRowCount(-1);
        testList.setBackground(appProperties.getSecondColor());
        testList.setPreferredSize(new Dimension(menuSettings.getTestInfoWidth(), menuSettings.getTestInfoHeight()));
        testList.setFont(menuSettings.getMediumTextFont());
        testList.setForeground(Color.WHITE);
        testList.addMouseListener(this);

        listScroller = new JScrollPane(testList);
        listScroller.setPreferredSize(new Dimension(menuSettings.getTestInfoWidth(), menuSettings.getTestInfoHeight()));

        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(appProperties.getThirdColor());
    }

    private void initTextPanel(){
        textPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        textPanel.add(lTextName);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lName);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lTextTerm);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lTerm);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lTextRoom);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lRoom);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lTextProf);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(lProf);
        textPanel.add(Box.createVerticalGlue());
    }

    private void initIconPanel(){
        iconPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        iconPanel.add(Box.createHorizontalGlue());
        iconPanel.add(backButton);
        iconPanel.add(Box.createHorizontalGlue());
        iconPanel.add(sLine);

        iconPanel.add(docButton);
        iconPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(appProperties.getThirdColor());
        iconPanel.add(sLine);

        iconPanel.add(editButton);
        iconPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(appProperties.getThirdColor());
        iconPanel.add(sLine);

        iconPanel.add(linksButton);
        iconPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(appProperties.getThirdColor());
        iconPanel.add(sLine);

        iconPanel.add(noteButton);
        iconPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(appProperties.getThirdColor());
        iconPanel.add(sLine);

        iconPanel.add(deleteButton);
        iconPanel.add(Box.createHorizontalGlue());
    }

    private void initAbsencesPanel(){
        absencesPanel.removeAll();
        absencesPanel.updateUI();
        int count = absences;
        if (allAbsences < 0){
            allAbsences = 0;
        }
        for(int i = 1; i<= allAbsences; i++){
            JButton absenceButton = new JButton();
            absenceButton.setMinimumSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
            absenceButton.setPreferredSize(new Dimension(menuSettings.getSmallIconSize(), menuSettings.getSmallIconSize()));
            absenceButton.setMaximumSize(new Dimension(Short.MAX_VALUE, menuSettings.getSmallIconSize()));
            absenceButton.setBorder(BorderFactory.createEmptyBorder());
            absenceButton.setBackground(Color.GREEN);
            if (count > 0){
                absenceButton.setBackground(Color.RED);
                count -=1;
            }

            absenceButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (absenceButton.getBackground() == Color.GREEN){
                        absenceButton.setBackground(Color.RED);
                        absences +=1;
                        subject.setAbsences(absences);
                    }
                    else{
                        absenceButton.setBackground(Color.GREEN);
                        absences -=1;
                        subject.setAbsences(absences);
                    }
                }
            });
            absencesPanel.add(absenceButton);
        }
    }

    private void initAdvancePanel(){
        advancePanel.setBorder(new EmptyBorder(10, 0, 0, 10));

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        advancePanel.add(addTestButton, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 0;
        advancePanel.add(lTests, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 6;
        constraints.gridx = 0;
        constraints.gridy = 1;
        advancePanel.add(listScroller, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        advancePanel.add(editAbsencesButton, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 2;
        advancePanel.add(plusButton, constraints);
        plusButton.setVisible(false);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        advancePanel.add(minusButton, constraints);
        minusButton.setVisible(false);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        advancePanel.add(okButton, constraints);
        okButton.setVisible(false);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridwidth = 3;
        constraints.gridx = 3;
        constraints.gridy = 2;
        advancePanel.add(lAbsences, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 6;
        constraints.gridx = 0;
        constraints.gridy = 3;
        advancePanel.add(absencesPanel, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            setVisible(false);
        }
        else if(e.getSource() == docButton){
            File file = new File(appProperties.getFolderPath() + "\\" + this.subject.getName());
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
            this.subjects.add(menuSettings.editSubject(this.frame, this.subject.convertSubjectToData(), appProperties));
            this.subjects.remove(this.subject);
            setVisible(false);
        }
        else if(e.getSource() == noteButton){
            setVisible(false);
            notes = new NotesWindow(this, this.subject, appProperties, resourceBundle);
        }
        else if(e.getSource() == deleteButton){
            this.subjects.remove(this.subject);
            setVisible(false);
        }
        else if(e.getSource() == linksButton){
            linksWindow = new LinksWindow(this, this.subject, appProperties, resourceBundle);
        }
        else if(e.getSource() == addTestButton){
            addTest = new TestInputForm(this);
            if(addTest.getOutput() != null){
                listModel.addElement(addTest.getOutput().get(0) + " - " + addTest.getOutput().get(1));
                subject.getTestList().add(new TestCard(addTest.getOutput().get(0), addTest.getOutput().get(1)));
            }
        }
        else if(e.getSource() == editAbsencesButton){
            editAbsencesButton.setVisible(false);
            plusButton.setVisible(true);
            minusButton.setVisible(true);
            okButton.setVisible(true);
        }
        else if(e.getSource() == plusButton){
            allAbsences +=1;
            initAbsencesPanel();
        }

        else if(e.getSource() == minusButton){
            allAbsences -=1;
            initAbsencesPanel();
        }
        else if(e.getSource() == okButton){
            this.subject.setAllAbsences(allAbsences);
            editAbsencesButton.setVisible(true);
            plusButton.setVisible(false);
            minusButton.setVisible(false);
            okButton.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 3){
            TestCard testCard = subject.getTestList().get(testList.getSelectedIndex());
            addTest = new TestInputForm(this, testCard);
            if(addTest.getOutput().size() == 2){
                listModel.addElement(addTest.getOutput().get(0) + " - " + addTest.getOutput().get(1));
                subject.getTestList().add(new TestCard(addTest.getOutput().get(0), addTest.getOutput().get(1)));
            }
            listModel.remove(testList.getSelectedIndex());
            subject.getTestList().remove(testCard);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}
