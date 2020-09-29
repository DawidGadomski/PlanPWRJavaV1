package InputForms;

import Object.TestCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestInputForm extends JDialog implements ActionListener {
    private JTextField tfName;
    private JLabel lName;
    private JLabel lDate;
    private JTextField tfDate;
    private JButton bCancel;
    private JButton bAccept;
    private JPanel pPanel;
    private ArrayList<String> output;

    public TestInputForm(JDialog frame){
        super(frame, Dialog.ModalityType.APPLICATION_MODAL);
        setContentPane(pPanel);
        bCancel.addActionListener(this);
        bAccept.addActionListener(this);
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);

        output = new ArrayList<String>();

        setVisible(true);
    }

    public TestInputForm(JDialog frame, TestCard tc){
        super(frame, ModalityType.APPLICATION_MODAL);
        tfName.setText(tc.getTestName());
        tfDate.setText(tc.getTestDate());

        setContentPane(pPanel);
        bCancel.addActionListener(this);
        bAccept.addActionListener(this);

        bAccept.setText("OK");
        bCancel.setText("Usuń");

        output = new ArrayList<String>();

        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(frame);
        setResizable(false);
        setVisible(true);
    }

    public ArrayList<String> getOutput(){
        return output;
    }

    //  Buttons
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == bAccept){
            output.add(tfName.getText());
            output.add(tfDate.getText());
            setVisible(false);
        }
        else if(e.getSource() == bCancel){
            setVisible(false);
        }
    }
}
