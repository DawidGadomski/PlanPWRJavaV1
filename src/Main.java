import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    private JFrame mainFrame;
    private JPanel panel1;
    private Screen screen1;

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
                Main app = new Main();
                app.initGui();
                app.mainFrame.setVisible(true);
            }
        });
    }

    private void initGui(){
        mainFrame = new JFrame("PLAN PWR");
        mainFrame.setContentPane(panel1);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
    }

    private void createUIComponents() {
        screen1 = new Screen(mainFrame);
    }
}
