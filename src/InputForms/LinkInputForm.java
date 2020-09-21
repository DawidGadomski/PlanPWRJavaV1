package InputForms;

import Object.LinkCard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Map;

public class LinkInputForm extends JDialog implements ActionListener {
    private JPanel pPanel;
    private JTextField tfLinkName;
    private JTextField tfLink;
    private JButton bAccept;
    private JButton bCancle;
    private JLabel lLinkName;
    private JLabel lLink;

    private LinkCard link;

    public LinkInputForm(JDialog frame) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        setContentPane(pPanel);
        bCancle.addActionListener(this);
        bAccept.addActionListener(this);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

    public LinkInputForm(JDialog frame, LinkCard lc) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        setContentPane(pPanel);
        tfLinkName.setText(lc.getLinkName());
        tfLink.setText(lc.getLinkURI().toString());
        bCancle.addActionListener(this);
        bAccept.addActionListener(this);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bAccept){
            link = new LinkCard(tfLinkName.getText(), URI.create(tfLink.getText()));
            setVisible(false);
        }
        else if(e.getSource() == bCancle){
            setVisible(false);
        }

    }

    public LinkCard getLink(){
        return this.link;
    }
}
