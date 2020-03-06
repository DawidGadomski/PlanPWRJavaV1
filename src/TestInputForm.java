import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestInputForm extends JDialog implements ActionListener {
    private JTextField tfName;
    private JLabel lName;
    private JLabel lDate;
    private JTextField tfDate;
    private JButton bCancel;
    private JButton bAccept;
    private JPanel pPanel;
    private String output;

    public TestInputForm(JDialog frame){
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        setContentPane(pPanel);
        bCancel.addActionListener(this);
        bAccept.addActionListener(this);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    //  Buttons
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bAccept){
            setOutput(tfName.getText() + " - " + tfDate.getText());
            setVisible(false);
        }
        else if(e.getSource() == bCancel){
            setVisible(false);
        }
    }
}
