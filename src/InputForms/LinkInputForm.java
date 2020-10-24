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
    private JButton bDelete;
    private JLabel lLinkName;
    private JLabel lLink;

    private LinkCard link;

    public LinkInputForm(JDialog frame) {
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        setContentPane(pPanel);
        setUndecorated(false);
        bDelete.setVisible(false);
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
        setUndecorated(false);
        tfLinkName.setText(lc.getLinkName());
        tfLink.setText(lc.getLinkURI().toString());
        bCancle.setVisible(false);
        bAccept.setText("OK");
        bDelete.addActionListener(this);
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
            if(tfLinkName.getText().equals("") | tfLink.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Uzupełnij pole Nazwa oraz Adres URL");
            } else {
                link = new LinkCard(tfLinkName.getText(), URI.create(tfLink.getText()));
                setVisible(false);
            }
        }
        else if(e.getSource() == bCancle){
            setVisible(false);
        }
        else if(e.getSource() == bDelete){
            setVisible(false);
        }

    }

    public LinkCard getLink(){
        return this.link;
    }
}
