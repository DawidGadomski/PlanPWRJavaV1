package Icons;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SaveIcon extends Icon {
    public SaveIcon(int x, int y, int size, Color color) {
        super(x, y);
        this.size = size;
        try{
            image = ImageIO.read(getClass().getResource("/resources/images/save.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        setIconColor(color);
    }
}
