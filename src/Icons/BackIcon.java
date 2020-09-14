package Icons;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BackIcon extends Icon{
    public BackIcon(int x, int y, int size) {
        super(x, y);
        file = new File("back.png");
        this.size = size;
        try{
            image = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}