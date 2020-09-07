package Windows;

import Settings.SettingsSettings;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JDialog implements ActionListener {
    private JDialog dWindow;
    private SettingsSettings settings;
    private JPanel pCard;
    private JPanel buttonsPanel;
    private JPanel aboutPanel;
    private JPanel colorPanel;
    private JPanel advancePanel;

    private Image aboutIcon;
    private Image backIcon;
    private Image colorsIcon;
    private Image advancedIcon;

    private JButton advancedPanelButton;
    private JButton authorPanelButton;
    private JButton colorsPanelButton;
    private JButton backButton;

    private JSeparator sLine;

    private JLabel lAuthor;
    private JLabel lVersion;
    private JLabel lAbout;
    private JLabel lAdvanced;
    private JLabel lColors;
    private JLabel lBackgroundColors;
    private JLabel lTextColor;
    private JLabel lGridColor;

    public SettingsWindow(JFrame frame){
        super(frame, ModalityType.APPLICATION_MODAL);

        settings = new SettingsSettings();
        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);

        dWindow.setSize(settings.getSmallWindowWidth(), settings.getSmallWindowHeight());
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dWindow.setLocationRelativeTo(frame);
        dWindow.setBackground(settings.getBackgroundColor());

        pCard = new JPanel();
        pCard.setLayout(new CardLayout());

        initButtonsPanel();
        initAdvancedPanel();
        initColorsPanel();
        initAboutPanel();

        dWindow.add(buttonsPanel, BorderLayout.LINE_START);
        dWindow.add(pCard, BorderLayout.CENTER);
        pCard.add(aboutPanel, "ABOUT");
        pCard.add(advancePanel, "ADVANCED");
        pCard.add(colorPanel, "COLORS");
        dWindow.setVisible(true);
    }

    public void initButtonsPanel(){
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(settings.getBgColor());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        backIcon = settings.getBackImage().getScaledInstance(settings.getIconSize(), settings.getIconSize(), Image.SCALE_DEFAULT);
        colorsIcon = settings.getColorsImage().getScaledInstance(settings.getIconSize(), settings.getIconSize(), Image.SCALE_DEFAULT);
        aboutIcon = settings.getAboutImage().getScaledInstance(settings.getIconSize(), settings.getIconSize(), Image.SCALE_DEFAULT);
        advancedIcon = settings.getAdvancedImage().getScaledInstance(settings.getIconSize(), settings.getIconSize(), Image.SCALE_DEFAULT);

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        backButton.setPreferredSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getIconSize()));
//        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        colorsPanelButton = new JButton(new ImageIcon(colorsIcon));
        colorsPanelButton.setMinimumSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        colorsPanelButton.setPreferredSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        colorsPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getIconSize()));
//        colorsPanelButton.setContentAreaFilled(false);
        colorsPanelButton.addActionListener(this);

        advancedPanelButton = new JButton(new ImageIcon(advancedIcon));
        advancedPanelButton.setMinimumSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        advancedPanelButton.setPreferredSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        advancedPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getIconSize()));
//        advancedPanelButton.setContentAreaFilled(false);
        advancedPanelButton.addActionListener(this);

        authorPanelButton = new JButton(new ImageIcon(aboutIcon));
        authorPanelButton.setMinimumSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        authorPanelButton.setPreferredSize(new Dimension(settings.getIconSize(), settings.getIconSize()));
        authorPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getIconSize()));
//        authorPanelButton.setContentAreaFilled(false);
        authorPanelButton.addActionListener(this);

        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(settings.getLineColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(colorsPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(settings.getLineColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(authorPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(settings.getLineColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(advancedPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
    }

    public void initAboutPanel(){
        aboutPanel = new JPanel();
        aboutPanel.setBorder(new EmptyBorder(10,10,10,10));
        aboutPanel.setBackground(settings.getBgColor());
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
//        aboutPanel.setVisible(true);

        lAuthor = new JLabel("Author: ");
        lVersion = new JLabel("Version: ");
        lAbout = new JLabel("ABOUT");

        lAbout.setHorizontalAlignment(SwingConstants.CENTER);

        lAuthor.setFont(settings.getSmallTextFont());
        lVersion.setFont(settings.getSmallTextFont());
        lAbout.setFont(settings.getBigTextFont());

        lAuthor.setForeground(settings.getTextColor());
        lVersion.setForeground(settings.getTextColor());
        lAbout.setForeground(settings.getTextColor());

        aboutPanel.add(lAbout);
        aboutPanel.add(lAuthor);
        aboutPanel.add(lVersion);
    }

    public void initAdvancedPanel(){
        advancePanel = new JPanel();
        advancePanel.setBorder(new EmptyBorder(10,10,10,10));
        advancePanel.setBackground(settings.getBgColor());
        advancePanel.setLayout(new BoxLayout(advancePanel, BoxLayout.X_AXIS));
//        advancePanel.setVisible(false);

        lAdvanced = new JLabel("ADVANCED");
        lAdvanced.setForeground(settings.getTextColor());
        lAdvanced.setHorizontalAlignment(SwingConstants.CENTER);
        lAdvanced.setFont(settings.getBigTextFont());
    }

    public void initColorsPanel(){
        colorPanel = new JPanel();
        colorPanel.setBorder(new EmptyBorder(10,10,10,10));
        colorPanel.setBackground(settings.getBgColor());
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));
//        colorPanel.setVisible(false);

        lColors = new JLabel("COLORS");
        lColors.setFont(settings.getBigTextFont());
        lColors.setHorizontalAlignment(SwingConstants.CENTER);
        lColors.setForeground(settings.getTextColor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == colorsPanelButton){
            aboutPanel.setVisible(false);
            advancePanel.setVisible(false);
            colorPanel.setVisible(true);
        }
        else if (e.getSource() == authorPanelButton){
            aboutPanel.setVisible(true);
            advancePanel.setVisible(false);
            colorPanel.setVisible(false);
        }
        else if (e.getSource() == advancedPanelButton){
            aboutPanel.setVisible(false);
            advancePanel.setVisible(true);
            colorPanel.setVisible(false);
        }
        else if (e.getSource() == backButton){
            dWindow.setVisible(false);
        }
    }
}
