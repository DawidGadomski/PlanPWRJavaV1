import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class EditIcon extends Icon{
    public EditIcon(int x, int y, int size) {
        super(x, y);
        file = new File("Icons/edit.png");
        this.size = size;
        try{
            image = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
