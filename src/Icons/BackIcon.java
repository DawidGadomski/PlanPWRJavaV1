package Icons;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackIcon extends Icon{
    public BackIcon(int x, int y, int size, Color color) {
        super(x, y);
        this.size = size;
        try{
            image = ImageIO.read(getClass().getResource("/resources/images/back.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        setIconColor(color);
    }
}
