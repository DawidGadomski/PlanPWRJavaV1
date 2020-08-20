import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AddIcon extends Icon {
    public AddIcon(int x, int y, int size) {
        super(x, y);
        file = new File("Icons/add.png");
        System.out.println(file.getAbsolutePath());
        this.size = size;
        try{
            image = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
