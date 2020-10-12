package Windows;

import Icons.ClearIcon;
import Settings.AppProperties;
import Settings.NotesSettings;
import Object.Subject;
import Graphics.NoteBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class NotesWindow extends JDialog implements ActionListener {
    private JDialog frame;
    private Subject subject;
    private NotesSettings notesSettings;
    private AppProperties appProperties;
    private ResourceBundle resourceBundle;

    private NoteBoard noteBoard;

    private JPanel buttonsPanel;
    private JPanel notesPanel;
    private JButton addButton;
    private JButton saveButton;
    private JButton backButton;
    private ClearIcon noteClearIcon;
    private JLabel lNotes;
    private JSeparator sLine;

//  Icons
    private Image addIcon;
    private Image saveIcon;
    private Image backIcon;

    public NotesWindow(JDialog frame, Subject subject, AppProperties appProperties, ResourceBundle resourceBundle){
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        this.frame = frame;
        this.subject = subject;
        this.appProperties = appProperties;
        this.resourceBundle = resourceBundle;
        notesSettings = new NotesSettings();

        setLayout(new BorderLayout());
        setUndecorated(true);
        setSize(notesSettings.getSmallWindowWidth(), notesSettings.getSmallWindowHeight());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setBackground(this.appProperties.getFirstColor());

        lNotes = new JLabel(resourceBundle.getString("notes"));
        lNotes.setHorizontalAlignment(SwingConstants.CENTER);
        lNotes.setForeground(appProperties.getTextColor());
        lNotes.setFont(notesSettings.getBigTextFont());

        notesPanel = new JPanel();
        notesPanel.setBackground(appProperties.getSecondColor());
        noteBoard = new NoteBoard(this.frame, appProperties, subject);
        notesPanel.add(lNotes);
        notesPanel.add(noteBoard);
        notesPanel.setBorder(new EmptyBorder(10,10,10,0));

        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(appProperties.getThirdColor());

        initButtonsPanel();

        add(notesPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.LINE_END);

        setVisible(true);
    }

    public void initButtonsPanel(){

        notesSettings.setIconColor(notesSettings.getBackImage(), appProperties.getSecondColor());
        notesSettings.setIconColor(notesSettings.getAddImage(), appProperties.getSecondColor());
        notesSettings.setIconColor(notesSettings.getSaveImage(), appProperties.getSecondColor());

        backIcon = notesSettings.getBackImage().getScaledInstance(notesSettings.getBigIconSize(),
                notesSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        addIcon = notesSettings.getAddImage().getScaledInstance(notesSettings.getBigIconSize(),
                notesSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        saveIcon = notesSettings.getSaveImage().getScaledInstance(notesSettings.getBigIconSize(),
                notesSettings.getBigIconSize(), Image.SCALE_DEFAULT);

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(appProperties.getFirstColor());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));

        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(notesSettings.getBigIconSize(), notesSettings.getBigIconSize()));
        backButton.setPreferredSize(new Dimension(notesSettings.getBigIconSize(), notesSettings.getBigIconSize()));
        backButton.setMaximumSize(new Dimension(notesSettings.getBigIconSize(), Short.MAX_VALUE));
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        saveButton = new JButton(new ImageIcon(saveIcon));
        saveButton.setMinimumSize(new Dimension(notesSettings.getBigIconSize(), notesSettings.getBigIconSize()));
        saveButton.setPreferredSize(new Dimension(notesSettings.getBigIconSize(), notesSettings.getBigIconSize()));
        saveButton.setMaximumSize(new Dimension(notesSettings.getBigIconSize(), Short.MAX_VALUE));
        saveButton.setContentAreaFilled(false);
        saveButton.addActionListener(this);

        addButton = new JButton(new ImageIcon(addIcon));
        addButton.setMinimumSize(new Dimension(notesSettings.getBigIconSize(), notesSettings.getBigIconSize()));
        addButton.setPreferredSize(new Dimension(notesSettings.getBigIconSize(), notesSettings.getBigIconSize()));
        addButton.setMaximumSize(new Dimension(notesSettings.getBigIconSize(), Short.MAX_VALUE));
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(this);

        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.HORIZONTAL);
        sLine.setBackground(appProperties.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.HORIZONTAL);
        sLine.setBackground(appProperties.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(Box.createVerticalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
            notesSettings.createNote(this, this.subject);
            noteBoard.repaint();
        }
        else if(e.getSource() == saveButton){
//            notesSettings.saveNotes();
//            notes.getSubject().setNoteArrayList(settings.getNotesList());
        }
        else if(e.getSource() == backButton){
            setVisible(false);
        }

    }
}
