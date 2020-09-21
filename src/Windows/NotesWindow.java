package Windows;

import Icons.ClearIcon;
import Settings.NotesSettings;
import Object.Subject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesWindow extends JDialog implements ActionListener {
    private JDialog frame;
    private JDialog dWindow;
    private Subject subject;
    private NotesSettings notesSettings;

    private JPanel iconPanel;
    private JPanel notesPanel;
    private JButton addButton;
    private JButton saveButton;
    private JButton backButton;
    private ClearIcon noteClearIcon;
    private JSeparator sLine;

//  Icons
    private Image addIcon;
    private Image saveIcon;
    private Image backIcon;

    public NotesWindow(JDialog frame, Subject subject){
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        this.frame = frame;
        this.subject = subject;
        notesSettings = new NotesSettings();

        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);
//        dWindow.setLocationRelativeTo(null);

        dWindow.setSize(notesSettings.getNoteWindowWidth(), notesSettings.getNoteWindowHeight());
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        backIcon = notesSettings.getBackIcon().getScaledInstance(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize(), Image.SCALE_DEFAULT);
        addIcon = notesSettings.getAddIcon().getScaledInstance(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize(), Image.SCALE_DEFAULT);
        saveIcon = notesSettings.getSaveIcon().getScaledInstance(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize(), Image.SCALE_DEFAULT);

        iconPanel = new JPanel();
        iconPanel.setBackground(notesSettings.getNotesColor());
        iconPanel.setLayout(new BoxLayout(iconPanel, BoxLayout.PAGE_AXIS));

        notesPanel = new JPanel();
        notesPanel.setBackground(notesSettings.getNotesColor());

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize()));
        backButton.setPreferredSize(new Dimension(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize()));
        backButton.setMaximumSize(new Dimension(notesSettings.getNoteIconsSize(), Short.MAX_VALUE));
//        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        saveButton = new JButton(new ImageIcon(saveIcon));
        saveButton.setMinimumSize(new Dimension(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize()));
        saveButton.setPreferredSize(new Dimension(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize()));
        saveButton.setMaximumSize(new Dimension(notesSettings.getNoteIconsSize(), Short.MAX_VALUE));
//        saveButton.setBorder(BorderFactory.createEmptyBorder());
        saveButton.setContentAreaFilled(false);
        saveButton.addActionListener(this);

        addButton = new JButton(new ImageIcon(addIcon));
        addButton.setMinimumSize(new Dimension(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize()));
        addButton.setPreferredSize(new Dimension(notesSettings.getNoteIconsSize(), notesSettings.getNoteIconsSize()));
        addButton.setMaximumSize(new Dimension(notesSettings.getNoteIconsSize(), Short.MAX_VALUE));
//        addButton.setBorder(BorderFactory.createEmptyBorder());
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(this);

        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(notesSettings.getNoteLineColor());

        iconPanel.add(sLine, BorderLayout.LINE_END);

        iconPanel.setBorder(new EmptyBorder(10, 0, 10, 10));

        iconPanel.add(addButton);
        iconPanel.add(Box.createHorizontalGlue());
        iconPanel.add(saveButton);
        iconPanel.add(Box.createHorizontalGlue());
        iconPanel.add(backButton);
        iconPanel.add(Box.createHorizontalGlue());

        dWindow.add(notesPanel, BorderLayout.CENTER);
        dWindow.add(iconPanel, BorderLayout.LINE_END);

        dWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addButton){
            notesSettings.createNote(this, this.subject);
        }
        else if(e.getSource() == saveButton){
//            notesSettings.saveNotes();
//            notes.getSubject().setNoteArrayList(settings.getNotesList());
        }
        else if(e.getSource() == backButton){
            dWindow.setVisible(false);
        }

    }
}
