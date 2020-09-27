package Windows;

//import com.bric.colorpicker.ColorPicker;
//import com.bric.colorpicker.listeners.ColorListener;
//import com.bric.colorpicker.models.ColorModel;


import javax.swing.*;
import java.awt.*;

public class ColorPickerWindow extends JDialog {
    private JDialog dWindow;
    private Color newColor;

    ColorPickerWindow(JFrame frame, Color color){
        super(frame, ModalityType.APPLICATION_MODAL);
        dWindow = new JDialog();
        color = Color.WHITE;
        dWindow.setSize(600, 400);
        dWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        dWindow.setUndecorated(true);
        dWindow.setLocationRelativeTo(frame);

//        ColorPicker colorPicker = new ColorPicker(true, true);
//        colorPicker.setColor(color);
//        colorPicker.addColorListener(new ColorListener() {
//            @Override
//            public void colorChanged(ColorModel colorModel) {
//                newColor = colorModel.getColor();
//            }
//        });
//        dWindow.add(colorPicker);

        dWindow.setVisible(true);
    }

    public Color getNewColor(){
        return newColor;
    }

}
