package Icons;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FolderIcon extends Icon{
    public FolderIcon(int x, int y, int size) {
        super(x, y);
        this.size = size;
        try{
            image = ImageIO.read(getClass().getResource("/resources/images/folder.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
