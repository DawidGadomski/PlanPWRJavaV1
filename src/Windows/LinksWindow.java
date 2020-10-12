package Windows;

import Settings.AppProperties;
import Settings.LinksSettings;
import Object.Subject;
import Object.LinkCard;
import InputForms.LinkInputForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.ResourceBundle;

public class LinksWindow extends JDialog implements ActionListener, MouseListener {
    private LinksSettings linksSettings;
    private AppProperties appProperties;
    private ResourceBundle resourceBundle;
    private Subject subject;

    private JPanel buttonsPanel;
    private JPanel linksPanel;
    private JButton backButton;
    private JButton addButton;

    private Image addIcon;
    private Image backIcon;
    private JLabel lLinks;
    private DefaultListModel<String> listModel;
    private JList<String> linkList;
    private JScrollPane listScroller;
    private JSeparator sLine;
    private LinkInputForm addLink;


    public LinksWindow(JDialog frame, Subject subject, AppProperties appProperties, ResourceBundle resourceBundle) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        this.subject = subject;

        linksSettings = new LinksSettings();
        this.appProperties = appProperties;
        this.resourceBundle = resourceBundle;

        setLayout(new BorderLayout());
        setUndecorated(true);

        setSize(linksSettings.getSmallWindowWidth(), linksSettings.getSmallWindowHeight());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setBackground(appProperties.getFirstColor());

        initIcons();
        initPanels();
        initLabels();
        initButtons();
        initLinkList();
        initIconPanel();

        linksPanel.add(lLinks);
        linksPanel.add(listScroller);

        add(linksPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.PAGE_END);

        setVisible(true);
    }

    public void initIcons(){
        linksSettings.setIconColor(linksSettings.getBackIcon(), appProperties.getSecondColor());
        linksSettings.setIconColor(linksSettings.getAddIcon(), appProperties.getSecondColor());

        backIcon = linksSettings.getBackIcon().getScaledInstance(linksSettings.getSmallIconSize(),
                linksSettings.getSmallIconSize(), Image.SCALE_DEFAULT);
        addIcon = linksSettings.getAddIcon().getScaledInstance(linksSettings.getSmallIconSize(),
                linksSettings.getSmallIconSize(), Image.SCALE_DEFAULT);
    }

    public void initPanels(){
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(appProperties.getFirstColor());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.setBorder(new EmptyBorder(10,10,0,10));

        linksPanel = new JPanel();
        linksPanel.setBackground(appProperties.getFirstColor());
        linksPanel.setLayout(new BoxLayout(linksPanel, BoxLayout.Y_AXIS));
        linksPanel.setBorder(new EmptyBorder(10,10,10,10));
    }

    public void initLabels(){
        lLinks = new JLabel(resourceBundle.getString("links"));
        lLinks.setFont(linksSettings.getBigTextFont());
        lLinks.setForeground(appProperties.getTextColor());
        lLinks.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void initButtons(){
        addButton = new JButton(new ImageIcon(addIcon));
        addButton.setMinimumSize(new Dimension(linksSettings.getSmallIconSize(), linksSettings.getSmallIconSize()));
        addButton.setPreferredSize(new Dimension(linksSettings.getSmallIconSize(), linksSettings.getSmallIconSize()));
        addButton.setMaximumSize(new Dimension(Short.MAX_VALUE, linksSettings.getSmallIconSize()));
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(this);

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(linksSettings.getSmallIconSize(), linksSettings.getSmallIconSize()));
        backButton.setPreferredSize(new Dimension(linksSettings.getSmallIconSize(), linksSettings.getSmallIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, linksSettings.getSmallIconSize()));
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

    }

    public void initLinkList(){
        listModel = new DefaultListModel<String>();
        for(LinkCard link : subject.getLinksList()){
            listModel.addElement(link.getLinkName());
        }

        linkList = new JList<String>(listModel);
        linkList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        linkList.setLayoutOrientation(JList.VERTICAL_WRAP);
        linkList.setVisibleRowCount(-1);
        linkList.setBackground(appProperties.getSecondColor());
        linkList.setPreferredSize(new Dimension(linksSettings.getWindowWidth(), linksSettings.getWindowHeight()));
        linkList.setFont(linksSettings.getBigTextFont());
        linkList.setForeground(Color.WHITE);
        linkList.addMouseListener(this);

        listScroller = new JScrollPane(linkList);
        listScroller.setPreferredSize(new Dimension(linksSettings.getWindowWidth(), linksSettings.getWindowHeight()));
    }

    public void initIconPanel(){
        buttonsPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        sLine = new JSeparator(SwingConstants.VERTICAL);
        buttonsPanel.add(sLine);

        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createHorizontalGlue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            setVisible(false);
        }
        else if(e.getSource() == addButton){
            addLink = new LinkInputForm(this);
            listModel.addElement(addLink.getLink().getLinkName());
            subject.getLinksList().add(addLink.getLink());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 1){
            LinkCard lc = this.subject.getLinksList().get(linkList.getSelectedIndex());
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(lc.getLinkURI());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(e.getButton() == 3){
            LinkCard lc = this.subject.getLinksList().get(linkList.getSelectedIndex());
            addLink = new LinkInputForm(this, lc);
            if(addLink.getLink() != null){
                listModel.addElement(addLink.getLink().getLinkName());
                listModel.remove(linkList.getSelectedIndex());
                subject.getLinksList().add(addLink.getLink());
            }
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
