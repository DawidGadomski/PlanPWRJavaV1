package Icons;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class AddIcon extends Icon {
    public AddIcon(int x, int y, int size) {
        super(x, y);
        this.size = size;
        try{
            image = ImageIO.read(getClass().getResource("/resources/images/add.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
