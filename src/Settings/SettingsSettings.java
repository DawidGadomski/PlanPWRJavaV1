package Settings;

import java.awt.*;

public class SettingsSettings extends Settings{
    private Color bgColor;
    private Color textColor;
    private Color lineColor;

    private int bigTextSize;
    private int smallTextSize;

    private Font bigTextFont;
    private Font smallTextFont;

    public SettingsSettings(){
        bgColor = new Color(127,127,127);
        lineColor = new Color(155, 155, 155);
        textColor = new Color(255,255,255);

        bigTextSize = 32;
        smallTextSize = 24;

        bigTextFont = new Font("Arial", Font.PLAIN, bigTextSize);
        smallTextFont = new Font("Arial", Font.PLAIN, smallTextSize);

    }

    public Color getLineColor() {
        return lineColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public int getBigTextSize() {
        return bigTextSize;
    }

    public int getSmallTextSize() {
        return smallTextSize;
    }

    public Font getBigTextFont() {
        return bigTextFont;
    }

    public Font getSmallTextFont() {
        return smallTextFont;
    }
}
