package Windows;

import Settings.AppProperties;
import Settings.SettingsSettings;
//import com.bric.colorpicker.ColorPicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SettingsWindow extends JDialog implements ActionListener {
    private JDialog dWindow;
    private JFrame frame;
    private SettingsSettings settings;
    private AppProperties appProperties;
    private JFileChooser fileChooser;
    private JPanel pCard;
    private JPanel buttonsPanel;
    private JPanel aboutPanel;
    private JPanel advancePanel;
    private GridBagConstraints constraints;

    private Image aboutIcon;
    private Image backIcon;
    private Image colorsIcon;
    private Image saveIcon;
    private Image advancedIcon;

    private JButton advancedPanelButton;
    private JButton authorPanelButton;
    private JButton colorsPanelButton;
    private JButton backButton;

    private JSeparator sLine;

//  About Panel
    private JPanel authorInfoPanel;
    private JLabel lAuthorInfo;
    private JLabel lAuthor;
    private JLabel lVersionInfo;
    private JLabel lVersion;
    private JLabel lAbout;

//    Color Panel
    private JPanel colorPanel;
    private JPanel colorsPanel;
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
    private JLabel lLectureColor;
    private JLabel lLectureColorInfo;
    private JLabel lSeminaryColor;
    private JLabel lSeminaryColorInfo;
    private JLabel lProjectColor;
    private JLabel lProjectColorInfo;
    private JLabel lLabColor;
    private JLabel lLabColorInfo;

//  Advanced Panel
    private JLabel lAdvanced;
    private JPanel advancedSettingsPanel;
    private JLabel lLanguage;
    private JComboBox comboLanguages;
    private JLabel lNotifications;
    private JLabel lNotificationsInfo;
    private JLabel lRoute;
    private JLabel lRouteInfo;
    private JLabel lFullscreen;
    private JLabel lFullscreenInfo;
    private JCheckBox cbFullscreen;
    private JCheckBox cbNotifications;
    private JButton bChooseFilePath;
    private JLabel lFilePath;


    private JButton bMainColor;
    private JButton bSecondColor;
    private JButton bThirdColor;
    private JButton bFourthColor;
    private JButton bGridColor;
    private JButton bTextColor;
    private JButton bLectureColor;
    private JButton bSeminaryColor;
    private JButton bProjectColor;
    private JButton bLabColor;


    public SettingsWindow(JFrame frame, AppProperties appProperties){
        super(frame, ModalityType.APPLICATION_MODAL);

        this.frame = frame;
        settings = new SettingsSettings();
        this.appProperties = appProperties;

        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);
        dWindow.setSize(settings.getSmallWindowWidth(), settings.getSmallWindowHeight());
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dWindow.setLocationRelativeTo(frame);
        dWindow.setBackground(this.appProperties.getFirstColor());

        pCard = new JPanel();
        pCard.setLayout(new CardLayout());

        constraints = new GridBagConstraints();

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

        settings.setIconColor(settings.getBackIcon(), appProperties.getSecondColor());
        settings.setIconColor(settings.getColorsImage(), appProperties.getSecondColor());
        settings.setIconColor(settings.getAboutImage(), appProperties.getSecondColor());
        settings.setIconColor(settings.getAdvancedImage(), appProperties.getSecondColor());

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(appProperties.getFirstColor());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(new EmptyBorder(10,10,10,0));

        backIcon = settings.getBackIcon().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);
        colorsIcon = settings.getColorsImage().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);
        aboutIcon = settings.getAboutImage().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);
        advancedIcon = settings.getAdvancedImage().getScaledInstance(settings.getBigIconSize(), settings.getBigIconSize(), Image.SCALE_DEFAULT);

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        backButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

        colorsPanelButton = new JButton(new ImageIcon(colorsIcon));
        colorsPanelButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        colorsPanelButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        colorsPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
        colorsPanelButton.setContentAreaFilled(false);
        colorsPanelButton.addActionListener(this);

        advancedPanelButton = new JButton(new ImageIcon(advancedIcon));
        advancedPanelButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        advancedPanelButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        advancedPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
        advancedPanelButton.setContentAreaFilled(false);
        advancedPanelButton.addActionListener(this);

        authorPanelButton = new JButton(new ImageIcon(aboutIcon));
        authorPanelButton.setMinimumSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        authorPanelButton.setPreferredSize(new Dimension(settings.getBigIconSize(), settings.getBigIconSize()));
        authorPanelButton.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getBigIconSize()));
        authorPanelButton.setContentAreaFilled(false);
        authorPanelButton.addActionListener(this);

        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.HORIZONTAL);
        sLine.setBackground(appProperties.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(colorsPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.HORIZONTAL);
        sLine.setBackground(appProperties.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(authorPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
        sLine = new JSeparator(SwingConstants.HORIZONTAL);
        sLine.setBackground(appProperties.getThirdColor());
        buttonsPanel.add(sLine);
        buttonsPanel.add(advancedPanelButton);
        buttonsPanel.add(Box.createVerticalGlue());
    }

    public void initAboutPanel(){
        aboutPanel = new JPanel();
        aboutPanel.setBorder(new EmptyBorder(10,10,10,10));
        aboutPanel.setBackground(appProperties.getFirstColor());
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));

        lAbout = new JLabel("ABOUT");
        lAbout.setHorizontalAlignment(SwingConstants.CENTER);
        lAbout.setForeground(appProperties.getTextColor());
        lAbout.setFont(settings.getBigTextFont());

        authorInfoPanel = new JPanel();
        authorInfoPanel.setBackground(appProperties.getSecondColor());
        authorInfoPanel.setLayout(new GridBagLayout());

        lAuthorInfo = new JLabel("Author: ");
        lAuthorInfo.setFont(settings.getMediumTextFont());
        lAuthorInfo.setForeground(appProperties.getTextColor());

        lAuthor = new JLabel(settings.getAuthorName());
        lAuthor.setFont(settings.getSmallTextFont());
        lAuthor.setForeground(appProperties.getTextColor());

        lVersionInfo = new JLabel("Version: ");
        lVersionInfo.setFont(settings.getMediumTextFont());
        lVersionInfo.setForeground(appProperties.getTextColor());

        lVersion = new JLabel(settings.getVersion());
        lVersion.setFont(settings.getSmallTextFont());
        lVersion.setForeground(appProperties.getTextColor());

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        authorInfoPanel.add(lAuthorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 3;
        constraints.gridy = 0;
        authorInfoPanel.add(lAuthor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        authorInfoPanel.add(lVersionInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 3;
        constraints.gridy = 2;
        authorInfoPanel.add(lVersion, constraints);

        aboutPanel.add(lAbout);
        aboutPanel.add(authorInfoPanel);
    }

    public void initAdvancedPanel(){
        advancePanel = new JPanel();
        advancePanel.setBorder(new EmptyBorder(10,10,10,10));
        advancePanel.setBackground(appProperties.getFirstColor());
        advancePanel.setLayout(new BoxLayout(advancePanel, BoxLayout.Y_AXIS));
//        advancePanel.setVisible(false);

        lAdvanced = new JLabel("ADVANCED");
        lAdvanced.setForeground(appProperties.getTextColor());
        lAdvanced.setHorizontalAlignment(SwingConstants.CENTER);
        lAdvanced.setFont(settings.getBigTextFont());

        advancedSettingsPanel = new JPanel();
        advancedSettingsPanel.setBackground(appProperties.getSecondColor());
        advancedSettingsPanel.setLayout(new GridBagLayout());

        lLanguage = new JLabel("Language: ");
        lLanguage.setFont(settings.getMediumTextFont());
        lLanguage.setForeground(appProperties.getTextColor());

        comboLanguages = new JComboBox(settings.getLanguages());

        lNotifications = new JLabel("Notifications");
        lNotifications.setFont(settings.getMediumTextFont());
        lNotifications.setHorizontalAlignment(SwingConstants.LEFT);
        lNotifications.setForeground(appProperties.getTextColor());

        lNotificationsInfo = new JLabel("On/Off Notifications");
        lNotificationsInfo.setFont(settings.getSmallTextFont());
        lNotificationsInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lNotificationsInfo.setForeground(appProperties.getDarkTextColor());

        lRoute = new JLabel("Documents route");
        lRoute.setFont(settings.getMediumTextFont());
        lRoute.setHorizontalAlignment(SwingConstants.LEFT);
        lRoute.setForeground(appProperties.getTextColor());

        lRouteInfo = new JLabel("Where do you want to store subject's documents");
        lRouteInfo.setFont(settings.getSmallTextFont());
        lRouteInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lRouteInfo.setForeground(appProperties.getDarkTextColor());

        lFullscreen = new JLabel("Fullscreen");
        lFullscreen.setFont(settings.getMediumTextFont());
        lFullscreen.setHorizontalAlignment(SwingConstants.LEFT);
        lFullscreen.setForeground(appProperties.getTextColor());

        lFullscreenInfo = new JLabel("On/Off Fullscreen");
        lFullscreenInfo.setFont(settings.getSmallTextFont());
        lFullscreenInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lFullscreenInfo.setForeground(appProperties.getDarkTextColor());

        cbFullscreen = new JCheckBox();

        cbNotifications = new JCheckBox();

        lFilePath = new JLabel(appProperties.getFolderPath());
        lFilePath.setFont(settings.getSmallTextFont());
        lFilePath.setHorizontalAlignment(SwingConstants.LEFT);
        lFilePath.setForeground(appProperties.getTextColor());
        lFilePath.setBackground(appProperties.getSecondColor());

        saveIcon = settings.getAdvancedImage().getScaledInstance(settings.getSmallIconSize(), settings.getSmallIconSize(), Image.SCALE_DEFAULT);
        bChooseFilePath = new JButton(new ImageIcon(saveIcon));
        bChooseFilePath.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bChooseFilePath.setPreferredSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bChooseFilePath.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getSmallIconSize()));
        bChooseFilePath.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        advancedSettingsPanel.add(lLanguage, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 0;
        advancedSettingsPanel.add(comboLanguages, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        advancedSettingsPanel.add(lNotifications, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 1;
        advancedSettingsPanel.add(cbNotifications, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        advancedSettingsPanel.add(lNotificationsInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        advancedSettingsPanel.add(lFullscreen, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 3;
        advancedSettingsPanel.add(cbFullscreen, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        advancedSettingsPanel.add(lFullscreenInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        advancedSettingsPanel.add(lRoute, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 6;
        advancedSettingsPanel.add(lRouteInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 7;
        advancedSettingsPanel.add(lFilePath, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 6;
        advancedSettingsPanel.add(bChooseFilePath, constraints);

        advancePanel.add(lAdvanced);
        advancePanel.add(advancedSettingsPanel);
    }

    public void initColorsPanel(){
        colorPanel = new JPanel();
        colorPanel.setBorder(new EmptyBorder(10,10,10,10));
        colorPanel.setBackground(appProperties.getFirstColor());
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
//        colorPanel.setVisible(false);

        lColors = new JLabel("COLORS");
        lColors.setFont(settings.getBigTextFont());
        lColors.setHorizontalAlignment(SwingConstants.CENTER);
        lColors.setForeground(appProperties.getTextColor());

        colorsPanel = new JPanel();
        colorsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        colorsPanel.setBackground(appProperties.getSecondColor());
        colorsPanel.setLayout(new GridBagLayout());

        lMainColor = new JLabel("Main Color");
        lMainColor.setFont(settings.getMediumTextFont());
        lMainColor.setHorizontalAlignment(SwingConstants.LEFT);
        lMainColor.setForeground(appProperties.getTextColor());

        lMainColorInfo = new JLabel("Main color of app");
        lMainColorInfo.setFont(settings.getSmallTextFont());
        lMainColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lMainColorInfo.setForeground(appProperties.getDarkTextColor());

        lSecondColor = new JLabel("Second Color");
        lSecondColor.setFont(settings.getMediumTextFont());
        lSecondColor.setHorizontalAlignment(SwingConstants.LEFT);
        lSecondColor.setForeground(appProperties.getTextColor());

        lSecondColorInfo = new JLabel("Highlight color");
        lSecondColorInfo.setFont(settings.getSmallTextFont());
        lSecondColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lSecondColorInfo.setForeground(appProperties.getDarkTextColor());

        lThirdColor = new JLabel("Third Color");
        lThirdColor.setFont(settings.getMediumTextFont());
        lThirdColor.setHorizontalAlignment(SwingConstants.LEFT);
        lThirdColor.setForeground(appProperties.getTextColor());

        lThirdColorInfo = new JLabel("Lines color");
        lThirdColorInfo.setFont(settings.getSmallTextFont());
        lThirdColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lThirdColorInfo.setForeground(appProperties.getDarkTextColor());

        lFourthColor = new JLabel("Fourth Color");
        lFourthColor.setFont(settings.getMediumTextFont());
        lFourthColor.setHorizontalAlignment(SwingConstants.LEFT);
        lFourthColor.setForeground(appProperties.getTextColor());

        lFourthColorInfo = new JLabel("Color of the workspace");
        lFourthColorInfo.setFont(settings.getSmallTextFont());
        lFourthColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lFourthColorInfo.setForeground(appProperties.getDarkTextColor());

        lGridColor = new JLabel("Grid Color");
        lGridColor.setFont(settings.getMediumTextFont());
        lGridColor.setHorizontalAlignment(SwingConstants.LEFT);
        lGridColor.setForeground(appProperties.getTextColor());

        lGridColorInfo = new JLabel("Color of the grid");
        lGridColorInfo.setFont(settings.getSmallTextFont());
        lGridColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lGridColorInfo.setForeground(appProperties.getDarkTextColor());

        lTextColor = new JLabel("Text Color");
        lTextColor.setFont(settings.getMediumTextFont());
        lTextColor.setHorizontalAlignment(SwingConstants.LEFT);
        lTextColor.setForeground(appProperties.getTextColor());

        lTextColorInfo = new JLabel("Color of text in app");
        lTextColorInfo.setFont(settings.getSmallTextFont());
        lTextColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lTextColorInfo.setForeground(appProperties.getDarkTextColor());

        lLectureColor = new JLabel("Lecture Color");
        lLectureColor.setFont(settings.getMediumTextFont());
        lLectureColor.setHorizontalAlignment(SwingConstants.LEFT);
        lLectureColor.setForeground(appProperties.getTextColor());

        lLectureColorInfo = new JLabel("Color of lecture in grid");
        lLectureColorInfo.setFont(settings.getSmallTextFont());
        lLectureColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lLectureColorInfo.setForeground(appProperties.getDarkTextColor());

        lSeminaryColor = new JLabel("Seminary Color");
        lSeminaryColor.setFont(settings.getMediumTextFont());
        lSeminaryColor.setHorizontalAlignment(SwingConstants.LEFT);
        lSeminaryColor.setForeground(appProperties.getTextColor());

        lSeminaryColorInfo = new JLabel("Color of seminary in app");
        lSeminaryColorInfo.setFont(settings.getSmallTextFont());
        lSeminaryColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lSeminaryColorInfo.setForeground(appProperties.getDarkTextColor());

        lProjectColor = new JLabel("Project Color");
        lProjectColor.setFont(settings.getMediumTextFont());
        lProjectColor.setHorizontalAlignment(SwingConstants.LEFT);
        lProjectColor.setForeground(appProperties.getTextColor());

        lProjectColorInfo = new JLabel("Color of project in app");
        lProjectColorInfo.setFont(settings.getSmallTextFont());
        lProjectColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lProjectColorInfo.setForeground(appProperties.getDarkTextColor());

        lLabColor = new JLabel("Lab Color");
        lLabColor.setFont(settings.getMediumTextFont());
        lLabColor.setHorizontalAlignment(SwingConstants.LEFT);
        lLabColor.setForeground(appProperties.getTextColor());

        lLabColorInfo = new JLabel("Color of lab in app");
        lLabColorInfo.setFont(settings.getSmallTextFont());
        lLabColorInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lLabColorInfo.setForeground(appProperties.getDarkTextColor());

        bMainColor = new JButton();
        bMainColor.setBackground(appProperties.getFirstColor());
        bMainColor.setContentAreaFilled(false);
        bMainColor.setOpaque(true);
        bMainColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bMainColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bMainColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bMainColor.setBorder(new LineBorder(Color.BLACK));
        bMainColor.addActionListener(this);

        bSecondColor = new JButton();
        bSecondColor.setBackground(appProperties.getSecondColor());
        bSecondColor.setContentAreaFilled(false);
        bSecondColor.setOpaque(true);
        bSecondColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bSecondColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bSecondColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bSecondColor.setBorder(new LineBorder(Color.BLACK));
        bSecondColor.addActionListener(this);

        bThirdColor = new JButton();
        bThirdColor.setBackground(appProperties.getThirdColor());
        bThirdColor.setContentAreaFilled(false);
        bThirdColor.setOpaque(true);
        bThirdColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bThirdColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bThirdColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bThirdColor.setBorder(new LineBorder(Color.BLACK));
        bThirdColor.addActionListener(this);

        bFourthColor = new JButton();
        bFourthColor.setBackground(appProperties.getFourthColor());
        bFourthColor.setContentAreaFilled(false);
        bFourthColor.setOpaque(true);
        bFourthColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bFourthColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bFourthColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bFourthColor.setBorder(new LineBorder(Color.BLACK));
        bFourthColor.addActionListener(this);

        bGridColor = new JButton();
        bGridColor.setBackground(appProperties.getGridBackgroundColor());
        bGridColor.setContentAreaFilled(false);
        bGridColor.setOpaque(true);
        bGridColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bGridColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bGridColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bGridColor.setBorder(new LineBorder(Color.BLACK));
        bGridColor.addActionListener(this);

        bTextColor = new JButton();
        bTextColor.setBackground(appProperties.getTextColor());
        bTextColor.setContentAreaFilled(false);
        bTextColor.setOpaque(true);
        bTextColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bTextColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bTextColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bTextColor.setBorder(new LineBorder(Color.BLACK));
        bTextColor.addActionListener(this);

        bLectureColor = new JButton();
        bLectureColor.setBackground(appProperties.getLectureColor());
        bLectureColor.setContentAreaFilled(false);
        bLectureColor.setOpaque(true);
        bLectureColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bLectureColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bLectureColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bLectureColor.setBorder(new LineBorder(Color.BLACK));
        bLectureColor.addActionListener(this);

        bProjectColor = new JButton();
        bProjectColor.setBackground(appProperties.getProjectColor());
        bProjectColor.setContentAreaFilled(false);
        bProjectColor.setOpaque(true);
        bProjectColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bProjectColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bProjectColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bProjectColor.setBorder(new LineBorder(Color.BLACK));
        bProjectColor.addActionListener(this);

        bSeminaryColor = new JButton();
        bSeminaryColor.setBackground(appProperties.getSeminaryColor());
        bSeminaryColor.setContentAreaFilled(false);
        bSeminaryColor.setOpaque(true);
        bSeminaryColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bSeminaryColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bSeminaryColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bSeminaryColor.setBorder(new LineBorder(Color.BLACK));
        bSeminaryColor.addActionListener(this);

        bLabColor = new JButton();
        bLabColor.setBackground(appProperties.getLabColor());
        bLabColor.setContentAreaFilled(false);
        bLabColor.setOpaque(true);
        bLabColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bLabColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bLabColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bLabColor.setBorder(new LineBorder(Color.BLACK));
        bLabColor.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        colorsPanel.add(lMainColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 0;
        colorsPanel.add(bMainColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        colorsPanel.add(lMainColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 0;
        colorsPanel.add(lLectureColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 0;
        colorsPanel.add(bLectureColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 1;
        colorsPanel.add(lLectureColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        colorsPanel.add(lSecondColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 2;
        colorsPanel.add(bSecondColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        colorsPanel.add(lSecondColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 2;
        colorsPanel.add(lLabColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 2;
        colorsPanel.add(bLabColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 3;
        colorsPanel.add(lLabColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        colorsPanel.add(lThirdColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 4;
        colorsPanel.add(bThirdColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        colorsPanel.add(lThirdColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 4;
        colorsPanel.add(lSeminaryColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 4;
        colorsPanel.add(bSeminaryColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 5;
        colorsPanel.add(lSeminaryColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 6;
        colorsPanel.add(lFourthColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 6;
        colorsPanel.add(bFourthColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 7;
        colorsPanel.add(lFourthColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 6;
        colorsPanel.add(lProjectColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 6;
        colorsPanel.add(bProjectColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 7;
        colorsPanel.add(lProjectColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 8;
        colorsPanel.add(lGridColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 8;
        colorsPanel.add(bGridColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 9;
        colorsPanel.add(lGridColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 10;
        colorsPanel.add(lTextColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 1;
        constraints.gridy = 10;
        colorsPanel.add(bTextColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 11;
        colorsPanel.add(lTextColorInfo, constraints);

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
        else if (e.getSource() == bMainColor){
            appProperties.setFirstColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getFirstColor()));
            bMainColor.setBackground(appProperties.getFirstColor());
            dWindow.setBackground(appProperties.getFirstColor());
            colorPanel.setBackground(appProperties.getFirstColor());
            dWindow.repaint();
        }
        else if (e.getSource() == bSecondColor){
            appProperties.setSecondColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getSecondColor()));
            bSecondColor.setBackground(appProperties.getSecondColor());
            colorsPanel.setBackground(appProperties.getSecondColor());
            dWindow.repaint();
        }
        else if (e.getSource() == bThirdColor){
            appProperties.setThirdColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getThirdColor()));
            bThirdColor.setBackground(appProperties.getThirdColor());
            dWindow.repaint();
        }
        else if (e.getSource() == bFourthColor){
            appProperties.setFourthColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getFourthColor()));
        }
        else if (e.getSource() == bGridColor){
            appProperties.setGridBackgroundColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getGridBackgroundColor()));
        }
        else if (e.getSource() == bTextColor){
            appProperties.setTextColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getTextColor()));
        }
        else if (e.getSource() == bLectureColor){
            appProperties.setLectureColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getLectureColor()));
            bLectureColor.revalidate();
        }
        else if (e.getSource() == bSeminaryColor){
            appProperties.setSeminaryColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getSeminaryColor()));
            bSeminaryColor.revalidate();
        }
        else if (e.getSource() == bProjectColor){
            appProperties.setProjectColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getProjectColor()));
            bProjectColor.revalidate();
        }
        else if (e.getSource() == bLabColor){
            appProperties.setLabColor(JColorChooser.showDialog(null, "Pick Color", appProperties.getLabColor()));
            bLabColor.revalidate();
        }
        else if (e.getSource() == bChooseFilePath){
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(appProperties.getFolderPath()));
            fileChooser.setDialogTitle("Choose file path");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            //
            // disable the "All files" option.
            //
            fileChooser.setAcceptAllFileFilterUsed(false);
            //
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                appProperties.setFolderPath(fileChooser.getSelectedFile().getAbsolutePath());
            }
            else {
                System.out.println("No Selection ");
            }
            lFilePath.setText(appProperties.getFolderPath());
        }
    }

}
