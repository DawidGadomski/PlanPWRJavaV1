import com.sun.jdi.JDIPermission;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
        this.subject = subject;

        linksSettings = new LinksSettings();

        dWindow = new JDialog();
        dWindow.setLayout(new BorderLayout());
        dWindow.setUndecorated(true);

        dWindow.setSize(400, 300);
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        initIcons();
        initPanels();
        initLabels();
        initButtons();
        initLinkList();
        initIconPanel();
    }

    public void initIcons(){
        backIcon = linksSettings.getBackIcon().getScaledInstance(linksSettings.getIconSize(), linksSettings.getIconSize(), Image.SCALE_DEFAULT);
        addIcon = linksSettings.getAddIcon().getScaledInstance(linksSettings.getIconSize(), linksSettings.getIconSize(), Image.SCALE_DEFAULT);
    }

    public void initPanels(){
        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(linksSettings.getBgColor());
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
    }

    public void initLabels(){
        lLinks = new JLabel("Links");
        lLinks.setFont(linksSettings.getFont());
        lLinks.setForeground(Color.WHITE);

    }

    public void initButtons(){
        addButton = new JButton(new ImageIcon(addIcon));
        addButton.setMinimumSize(new Dimension(linksSettings.getIconSize(), linksSettings.getIconSize()));
        addButton.setPreferredSize(new Dimension(linksSettings.getIconSize(), linksSettings.getIconSize()));
        addButton.setMaximumSize(new Dimension(Short.MAX_VALUE, linksSettings.getIconSize()));
        addButton.setContentAreaFilled(false);
        addButton.addActionListener(this);

        backButton = new JButton(new ImageIcon(backIcon));
        backButton.setMinimumSize(new Dimension(linksSettings.getIconSize(), linksSettings.getIconSize()));
        backButton.setPreferredSize(new Dimension(linksSettings.getIconSize(), linksSettings.getIconSize()));
        backButton.setMaximumSize(new Dimension(Short.MAX_VALUE, linksSettings.getIconSize()));
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(this);

    }

    public void initLinkList(){
        listModel = new DefaultListModel<String>();
        for(LinkCard lc : subject.getLinksList()){
            listModel.addElement(lc.getLinkName());
        }

        linkList = new JList<String>(listModel);
        linkList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        linkList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        linkList.setVisibleRowCount(-1);
        linkList.setBackground(linksSettings.getLinksBGColor());
        linkList.setPreferredSize(new Dimension(linksSettings.getWindowWidth(), linksSettings.getWindowHeight()));
        linkList.setFont(linksSettings.getFont());
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
            listModel.addElement(addLink.getLinkCard().getLinkName());
            subject.getLinksList().add(addLink.getLinkCard());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 3){
            addLink = new LinkInputForm(this, listModel.get(linkList.getSelectedIndex()));
            if(addLink.getLinkCard() != null){
                listModel.addElement(addLink.getLinkCard().getLinkName());
            }
            listModel.remove(linkList.getSelectedIndex());
            subject.getLinksList().add(addLink.getLinkCard());
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
