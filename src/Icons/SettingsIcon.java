package Icons;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SettingsIcon extends Icon{
    public SettingsIcon(int x, int y, int size) {
        super(x, y);
        file = new File("Icons/settings.png");
        this.size = size;
        try{
            image = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
