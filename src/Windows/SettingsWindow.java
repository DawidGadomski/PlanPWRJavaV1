package Windows;

import Settings.SettingsSettings;
//import com.bric.colorpicker.ColorPicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JDialog implements ActionListener {
    private JDialog dWindow;
    private JFrame frame;
    private SettingsSettings settings;
    private JPanel pCard;
    private JPanel buttonsPanel;
    private JPanel aboutPanel;
    private JPanel advancePanel;
//    private ColorPicker colorPicker;
    private GridBagConstraints constraints;

    private Image aboutIcon;
    private Image backIcon;
    private Image colorsIcon;
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
    private JFileChooser fileChooser;

    private JButton bMainColor;
    private JButton bSecondColor;
    private JButton bThirdColor;
    private JButton bFourthColor;
    private JButton bGridColor;
    private JButton bTextColor;


    public SettingsWindow(JFrame frame){
        super(frame, ModalityType.APPLICATION_MODAL);

        this.frame = frame;
        settings = new SettingsSettings();
        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);

        dWindow.setSize(settings.getSmallWindowWidth(), settings.getSmallWindowHeight());
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dWindow.setLocationRelativeTo(frame);
        dWindow.setBackground(settings.getFirstColor());

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
        aboutPanel.setBackground(settings.getFirstColor());
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));

        lAbout = new JLabel("ABOUT");
        lAbout.setHorizontalAlignment(SwingConstants.CENTER);
        lAbout.setForeground(settings.getTextColor());
        lAbout.setFont(settings.getBigTextFont());

        authorInfoPanel = new JPanel();
        authorInfoPanel.setBackground(settings.getSecondColor());
        authorInfoPanel.setLayout(new GridBagLayout());

        lAuthorInfo = new JLabel("Author: ");
        lAuthorInfo.setFont(settings.getMediumTextFont());
        lAuthorInfo.setForeground(settings.getTextColor());

        lAuthor = new JLabel(settings.getAuthorName());
        lAuthor.setFont(settings.getSmallTextFont());
        lAuthor.setForeground(settings.getTextColor());

        lVersionInfo = new JLabel("Version: ");
        lVersionInfo.setFont(settings.getMediumTextFont());
        lVersionInfo.setForeground(settings.getTextColor());

        lVersion = new JLabel(settings.getVersion());
        lVersion.setFont(settings.getSmallTextFont());
        lVersion.setForeground(settings.getTextColor());

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
        advancePanel.setBackground(settings.getFirstColor());
        advancePanel.setLayout(new BoxLayout(advancePanel, BoxLayout.Y_AXIS));
//        advancePanel.setVisible(false);

        lAdvanced = new JLabel("ADVANCED");
        lAdvanced.setForeground(settings.getTextColor());
        lAdvanced.setHorizontalAlignment(SwingConstants.CENTER);
        lAdvanced.setFont(settings.getBigTextFont());

        advancedSettingsPanel = new JPanel();
        advancedSettingsPanel.setBackground(settings.getSecondColor());
        advancedSettingsPanel.setLayout(new GridBagLayout());

        lLanguage = new JLabel("Language: ");
        lLanguage.setFont(settings.getMediumTextFont());
        lLanguage.setForeground(settings.getTextColor());

        comboLanguages = new JComboBox(settings.getLanguages());

        lNotifications = new JLabel("Notifications");
        lNotifications.setFont(settings.getMediumTextFont());
        lNotifications.setHorizontalAlignment(SwingConstants.LEFT);
        lNotifications.setForeground(settings.getTextColor());

        lNotificationsInfo = new JLabel("On/Off Notifications");
        lNotificationsInfo.setFont(settings.getSmallTextFont());
        lNotificationsInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lNotificationsInfo.setForeground(settings.getDarkTextColor());

        lRoute = new JLabel("Documents route");
        lRoute.setFont(settings.getMediumTextFont());
        lRoute.setHorizontalAlignment(SwingConstants.LEFT);
        lRoute.setForeground(settings.getTextColor());

        lRouteInfo = new JLabel("Where do you want to store subject's documents");
        lRouteInfo.setFont(settings.getSmallTextFont());
        lRouteInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lRouteInfo.setForeground(settings.getDarkTextColor());

        lFullscreen = new JLabel("Fullscreen");
        lFullscreen.setFont(settings.getMediumTextFont());
        lFullscreen.setHorizontalAlignment(SwingConstants.LEFT);
        lFullscreen.setForeground(settings.getTextColor());

        lFullscreenInfo = new JLabel("On/Off Fullscreen");
        lFullscreenInfo.setFont(settings.getSmallTextFont());
        lFullscreenInfo.setHorizontalAlignment(SwingConstants.LEFT);
        lFullscreenInfo.setForeground(settings.getDarkTextColor());

        cbFullscreen = new JCheckBox();

        cbNotifications = new JCheckBox();

        fileChooser = new JFileChooser();

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        advancedSettingsPanel.add(lLanguage, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 3;
        constraints.gridy = 0;
        advancedSettingsPanel.add(comboLanguages, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        advancedSettingsPanel.add(lNotifications, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 2;
        advancedSettingsPanel.add(cbNotifications, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 3;
        advancedSettingsPanel.add(lNotificationsInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 4;
        advancedSettingsPanel.add(lFullscreen, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 4;
        advancedSettingsPanel.add(cbFullscreen, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 5;
        advancedSettingsPanel.add(lFullscreenInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 6;
        advancedSettingsPanel.add(lRoute, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 7;
        advancedSettingsPanel.add(lRouteInfo, constraints);

        advancePanel.add(lAdvanced);
        advancePanel.add(advancedSettingsPanel);
    }

    public void initColorsPanel(){
        colorPanel = new JPanel();
        colorPanel.setBorder(new EmptyBorder(10,10,10,10));
        colorPanel.setBackground(settings.getFirstColor());
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
//        colorPanel.setVisible(false);

        lColors = new JLabel("COLORS");
        lColors.setFont(settings.getBigTextFont());
        lColors.setHorizontalAlignment(SwingConstants.CENTER);
        lColors.setForeground(settings.getTextColor());

        colorsPanel = new JPanel();
        colorsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        colorsPanel.setBackground(settings.getSecondColor());
        colorsPanel.setLayout(new GridBagLayout());

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

        bMainColor = new JButton();
        bMainColor.setBackground(settings.getFirstColor());
        bMainColor.setOpaque(true);
        bMainColor.setContentAreaFilled(false);
        bMainColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bMainColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bMainColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bMainColor.setBorder(new LineBorder(Color.BLACK));
        bMainColor.addActionListener(this);

        bSecondColor = new JButton();
        bSecondColor.setBackground(settings.getSecondColor());
        bSecondColor.setOpaque(true);
        bSecondColor.setContentAreaFilled(false);
        bSecondColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bSecondColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bSecondColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bSecondColor.setBorder(new LineBorder(Color.BLACK));
        bSecondColor.addActionListener(this);

        bThirdColor = new JButton();
        bThirdColor.setBackground(settings.getThirdColor());
        bThirdColor.setOpaque(true);
        bThirdColor.setContentAreaFilled(false);
        bThirdColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bThirdColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bThirdColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bThirdColor.setBorder(new LineBorder(Color.BLACK));
        bThirdColor.addActionListener(this);

        bFourthColor = new JButton();
        bFourthColor.setBackground(settings.getFourthColor());
        bFourthColor.setContentAreaFilled(false);
        bFourthColor.setOpaque(true);
        bFourthColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bFourthColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bFourthColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bFourthColor.setBorder(new LineBorder(Color.BLACK));
        bFourthColor.addActionListener(this);

        bGridColor = new JButton();
        bGridColor.setBackground(settings.getGridBackgroundColor());
        bGridColor.setContentAreaFilled(false);
        bGridColor.setOpaque(true);
        bGridColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bGridColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bGridColor.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        bGridColor.setBorder(new LineBorder(Color.BLACK));
        bGridColor.addActionListener(this);

        bTextColor = new JButton();
        bTextColor.setBackground(settings.getTextColor());
        bTextColor.setContentAreaFilled(false);
        bTextColor.setOpaque(true);
        bTextColor.setMinimumSize(new Dimension(settings.getSmallIconSize(), settings.getSmallIconSize()));
        bTextColor.setPreferredSize(new Dimension(settings.getColorButtonsSize(), settings.getColorButtonsSize()));
        bTextColor.setMaximumSize(new Dimension(Short.MAX_VALUE, settings.getColorButtonsSize()));
        bTextColor.setBorder(new LineBorder(Color.BLACK));
        bTextColor.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 0;
        colorsPanel.add(lMainColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 0;
        colorsPanel.add(bMainColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 1;
        colorsPanel.add(lMainColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 2;
        colorsPanel.add(lSecondColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 2;
        colorsPanel.add(bSecondColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 3;
        colorsPanel.add(lSecondColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 4;
        colorsPanel.add(lThirdColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 4;
        colorsPanel.add(bThirdColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 5;
        colorsPanel.add(lThirdColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 6;
        colorsPanel.add(lFourthColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 6;
        colorsPanel.add(bFourthColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 7;
        colorsPanel.add(lFourthColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 8;
        colorsPanel.add(lGridColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 8;
        colorsPanel.add(bGridColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 9;
        colorsPanel.add(lGridColorInfo, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 10;
        colorsPanel.add(lTextColor, constraints);

        constraints.fill = GridBagConstraints.LINE_END;
        constraints.gridx = 3;
        constraints.gridy = 10;
        colorsPanel.add(bTextColor, constraints);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 1;
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
            ColorPickerWindow colorPickerWindow = new ColorPickerWindow(frame, settings.getFirstColor());
            settings.setFirstColor(colorPickerWindow.getNewColor());
            System.out.println(settings.getFirstColor());
        }
        else if (e.getSource() == bSecondColor){
            ColorPickerWindow colorPickerWindow = new ColorPickerWindow(frame, settings.getSecondColor());
            settings.setSecondColor(colorPickerWindow.getNewColor());
        }
        else if (e.getSource() == bThirdColor){
            ColorPickerWindow colorPickerWindow = new ColorPickerWindow(frame, settings.getThirdColor());
            settings.setThirdColor(colorPickerWindow.getNewColor());
        }
        else if (e.getSource() == bFourthColor){
            ColorPickerWindow colorPickerWindow = new ColorPickerWindow(frame, settings.getFourthColor());
            settings.setFourthColor(colorPickerWindow.getNewColor());
        }
        else if (e.getSource() == bGridColor){
            ColorPickerWindow colorPickerWindow = new ColorPickerWindow(frame, settings.getGridBackgroundColor());
            settings.setGridBackgroundColor(colorPickerWindow.getNewColor());
        }
        else if (e.getSource() == bTextColor){
            ColorPickerWindow colorPickerWindow = new ColorPickerWindow(frame, settings.getTextColor());
            settings.setTextColor(colorPickerWindow.getNewColor());
        }
    }
}
