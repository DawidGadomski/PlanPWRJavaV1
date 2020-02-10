import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FolderIcon extends Icon{
    public FolderIcon(int x, int y, int size) {
        super(x, y);
        imgURL = "C:\\Users\\dawik\\IdeaProjects\\Plan PWR\\Icons\\folder.png";
        this.size = size;
        try{
            image = ImageIO.read(new File(imgURL));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
