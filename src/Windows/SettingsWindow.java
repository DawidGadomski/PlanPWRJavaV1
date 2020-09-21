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

//    Color Panel
    private JPanel colorPanel;
    private JPanel colorsPanel;
    private JPanel colorsInfo;
    private JPanel colorsButtons;
    private JLabel lColors;
    private JLabel lMainColor;
    private JLabel lMainColorInfo;
    private JLabel lSecondColor;
    private JLabel lSecondColorInfo;
    private JLabel lThirdColor;
    private JLabel lThirdColorInfo;
    private JLabel lFourthColor;
    private JLabel lFourthColorInfo;
    private JLabel lGridColor;
    private JLabel lGridColorInfo;
    private JLabel lTextColor;
    private JLabel lTextColorInfo;

    private JButton bMainColor;
    private JButton bSecondColor;
    private JButton bThirdColor;
    private JButton bFourthColor;
    private JButton bGridColor;
    private JButton bTextColor;


    public SettingsWindow(JFrame frame){
        super(frame, ModalityType.APPLICATION_MODAL);

        settings = new SettingsSettings();
        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);

        dWindow.setSize(settings.getSmallWindowWidth(), settings.getSmallWindowHeight());
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dWindow.setLocationRelativeTo(frame);
        dWindow.setBackground(settings.getGridBackgroundColor());

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
        buttonsPanel.setBackground(settings.getFirstColor());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        backIcon = settings.getBackIcon().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);
        colorsIcon = settings.getColorsImage().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);
        aboutIcon = settings.getAboutImage().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);
        advancedIcon = settings.getAdvancedImage().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        backButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
//        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        colorsPanelButton = new JButton(new ImageIcon(colorsIcon));
        colorsPanelButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        colorsPanelButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        colorsPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
//        colorsPanelButton.setContentAreaFilled(false);
        colorsPanelButton.addActionListener(this);

        advancedPanelButton = new JButton(new ImageIcon(advancedIcon));
        advancedPanelButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        advancedPanelButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        advancedPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
//        advancedPanelButton.setContentAreaFilled(false);
        advancedPanelButton.addActionListener(this);

        authorPanelButton = new JButton(new ImageIcon(aboutIcon));
        authorPanelButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        authorPanelButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        authorPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
//        authorPanelButton.setContentAreaFilled(false);
        authorPanelButton.addActionListener(this);

        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(settings.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(colorsPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(settings.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(authorPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        sLine.setBackground(settings.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(advancedPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
    }

    public void initAboutPanel(){
        aboutPanel = new JPanel();
        aboutPanel.setBorder(new EmptyBorder(10,10,10,10));
        aboutPanel.setBackground(settings.getSecondColor());
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
        advancePanel.setBackground(settings.getSecondColor());
        advancePanel.setLayout(new BoxLayout(advancePanel, BoxLayout.X_AXIS));
//        advancePanel.setVisible(false);

        lAdvanced = new JLabel("ADVANCED");
        lAdvanced.setForeground(settings.getTextColor());
        lAdvanced.setHorizontalAlignment(SwingConstants.CENTER);
        lAdvanced.setFont(settings.getBigTextFont());

        advancePanel.add(lAdvanced);
    }

    public void initColorsPanel(){
        colorPanel = new JPanel();
        colorPanel.setBorder(new EmptyBorder(10,10,10,10));
        colorPanel.setBackground(settings.getFirstColor());
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.X_AXIS));
//        colorPanel.setVisible(false);

        lColors = new JLabel("COLORS");
        lColors.setFont(settings.getBigTextFont());
        lColors.setHorizontalAlignment(SwingConstants.CENTER);
        lColors.setForeground(settings.getTextColor());

        colorsPanel = new JPanel();
        colorsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        colorsPanel.setBackground(settings.getSecondColor());
        colorsPanel.setLayout(new BoxLayout(colorsInfo, BoxLayout.Y_AXIS));

        colorsInfo = new JPanel();
        colorsInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
        colorsInfo.setBackground(settings.getSecondColor());
        colorsInfo.setLayout(new BoxLayout(colorsInfo, BoxLayout.X_AXIS));

        colorsButtons = new JPanel();
        colorsButtons.setBorder(new EmptyBorder(10, 10, 10, 10));
        colorsButtons.setBackground(settings.getSecondColor());
        colorsButtons.setLayout(new BoxLayout(colorsInfo, BoxLayout.X_AXIS));

        lMainColor = new JLabel("Main Color");
        lMainColor.setFont(settings.getMediumTextFont());
        lMainColor.setHorizontalAlignment(SwingConstants.LEFT);
        lMainColor.setForeground(settings.getTextColor());

        lMainColorInfo = new JLabel("Main color of app");
        lMainColorInfo.setFont(settings.getSmallTextFont());
        lMainColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lMainColorInfo.setForeground(settings.getDarkTextColor());

        lSecondColor = new JLabel("Second Color");
        lSecondColor.setFont(settings.getMediumTextFont());
        lSecondColor.setHorizontalAlignment(SwingConstants.LEFT);
        lSecondColor.setForeground(settings.getTextColor());

        lSecondColorInfo = new JLabel("Highlight color");
        lSecondColorInfo.setFont(settings.getSmallTextFont());
        lSecondColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lSecondColorInfo.setForeground(settings.getDarkTextColor());

        lThirdColor = new JLabel("Third Color");
        lThirdColor.setFont(settings.getMediumTextFont());
        lThirdColor.setHorizontalAlignment(SwingConstants.LEFT);
        lThirdColor.setForeground(settings.getTextColor());

        lThirdColorInfo = new JLabel("Lines color");
        lThirdColorInfo.setFont(settings.getSmallTextFont());
        lThirdColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lThirdColorInfo.setForeground(settings.getDarkTextColor());

        lFourthColor = new JLabel("Fourth Color");
        lFourthColor.setFont(settings.getMediumTextFont());
        lFourthColor.setHorizontalAlignment(SwingConstants.LEFT);
        lFourthColor.setForeground(settings.getTextColor());

        lFourthColorInfo = new JLabel("Color of the workspace");
        lFourthColorInfo.setFont(settings.getSmallTextFont());
        lFourthColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lFourthColorInfo.setForeground(settings.getDarkTextColor());

        lGridColor = new JLabel("Grid Color");
        lGridColor.setFont(settings.getMediumTextFont());
        lGridColor.setHorizontalAlignment(SwingConstants.LEFT);
        lGridColor.setForeground(settings.getTextColor());

        lGridColorInfo = new JLabel("Color of the grid");
        lGridColorInfo.setFont(settings.getSmallTextFont());
        lGridColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lGridColorInfo.setForeground(settings.getDarkTextColor());

        lTextColor = new JLabel("Text Color");
        lTextColor.setFont(settings.getMediumTextFont());
        lTextColor.setHorizontalAlignment(SwingConstants.LEFT);
        lTextColor.setForeground(settings.getTextColor());

        lTextColorInfo = new JLabel("Color of text in app");
        lTextColorInfo.setFont(settings.getSmallTextFont());
        lTextColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lTextColorInfo.setForeground(settings.getDarkTextColor());

        colorsInfo.add(lMainColor);
        colorsInfo.add(lMainColorInfo);
        colorsInfo.add(lSecondColor);
        colorsInfo.add(lSecondColorInfo);
        colorsInfo.add(lThirdColor);
        colorsInfo.add(lThirdColorInfo);
        colorsInfo.add(lFourthColor);
        colorsInfo.add(lFourthColorInfo);
        colorsInfo.add(lGridColor);
        colorsInfo.add(lGridColorInfo);
        colorsInfo.add(lTextColor);
        colorsInfo.add(lTextColorInfo);

        bMainColor = new JButton();
        bMainColor.setBackground(settings.getFirstColor());

        bSecondColor = new JButton();
        bSecondColor.setBackground(settings.getSecondColor());

        bThirdColor = new JButton();
        bThirdColor.setBackground(settings.getThirdColor());

        bFourthColor = new JButton();
        bFourthColor.setBackground(settings.getFourthColor());

        bGridColor = new JButton();
        bGridColor.setBackground(settings.getGridBackgroundColor());

        bTextColor = new JButton();
        bTextColor.setBackground(settings.getTextColor());

        colorsButtons.add(bMainColor);
        colorsButtons.add(bSecondColor);
        colorsButtons.add(bThirdColor);
        colorsButtons.add(bFourthColor);
        colorsButtons.add(bGridColor);
        colorsButtons.add(bTextColor);

        colorsPanel.add(colorsInfo);
        colorsPanel.add(colorsButtons);

        colorPanel.add(lColors);
        colorPanel.add(colorsPanel);
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
