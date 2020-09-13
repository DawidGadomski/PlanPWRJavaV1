package Windows;

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
import java.net.URI;
import java.util.Map;

public class LinksWindow extends JDialog implements ActionListener, MouseListener {
    private JDialog dWindow;
    private LinksSettings linksSettings;
    private Subject subject;

    private JPanel buttonsPanel;
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


    public LinksWindow(JDialog frame, Subject subject) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        this.subject = subject;

        linksSettings = new LinksSettings();

        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);

        dWindow.setSize(linksSettings.getLinksWidth(), linksSettings.getLinksHeight());
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dWindow.setLocationRelativeTo(frame);
        dWindow.setBackground(linksSettings.getFirstColor());

        initIcons();
        initPanels();
        initLabels();
        initButtons();
        initLinkList();
        initIconPanel();

        dWindow.add(lLinks, BorderLayout.PAGE_START);
        dWindow.add(listScroller, BorderLayout.CENTER);
        dWindow.add(buttonsPanel, BorderLayout.PAGE_END);

        dWindow.setVisible(true);
    }

    public void initIcons(){
        backIcon = linksSettings.getBackIcon().getScaledInstance(linksSettings.getBigIconSize(), linksSettings.getBigIconSize(), Image.SCALE_DEFAULT);
        addIcon = linksSettings.getAddIcon().getScaledInstance(linksSettings.getBigIconSize(), linksSettings.getBigIconSize(), Image.SCALE_DEFAULT);
    }

    public void initPanels(){
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(linksSettings.getFirstColor());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
    }

    public void initLabels(){
        lLinks = new JLabel("Links");
        lLinks.setFont(linksSettings.getBigTextFont());
        lLinks.setForeground(Color.WHITE);
        lLinks.setBackground(linksSettings.getFirstColor());
        lLinks.setHorizontalAlignment(SwingConstants.CENTER);

    }

    public void initButtons(){
        addButton = new JButton(new ImageIcon(addIcon));
        addButton.setMinimumSize(new Dimension(linksSettings.getBigIconSize(), linksSettings.getBigIconSize()));
        addButton.setPreferredSize(new Dimension(linksSettings.getBigIconSize(), linksSettings.getBigIconSize()));
        addButton.setMaximumSize(new Dimension(Short.MAX_VALUE, linksSettings.getBigIconSize()));
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(this);

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(linksSettings.getBigIconSize(), linksSettings.getBigIconSize()));
        backButton.setPreferredSize(new Dimension(linksSettings.getBigIconSize(), linksSettings.getBigIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, linksSettings.getBigIconSize()));
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
        linkList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        linkList.setVisibleRowCount(-1);
        linkList.setBackground(linksSettings.getSecondColor());
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
            dWindow.setVisible(false);
        }
        else if(e.getSource() == addButton){
            addLink = new LinkInputForm(this);
            listModel.addElement(addLink.getLink().getLinkName());
            subject.getLinksList().add(addLink.getLink());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 3){
            LinkCard lc = this.subject.getLinksList().get(linkList.getSelectedIndex());
            addLink = new LinkInputForm(this, lc);
            if(addLink.getLink() != null){
                listModel.addElement(addLink.getLink().getLinkName());
            }
            listModel.remove(linkList.getSelectedIndex());
            subject.getLinksList().add(addLink.getLink());
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
