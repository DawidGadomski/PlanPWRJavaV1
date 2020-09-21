package Icons;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ClearIcon extends Icon {
    private boolean visableFlag;

    public ClearIcon(int x, int y, int size) {
        super(x, y);
        file = new File("Icons/clear.png");
        this.size = size;
        try{
            image = ImageIO.read(file);
        } catch (IOException e){
            e.printStackTrace();
        }
        this.visableFlag = false;
    }

    public boolean getVisableFlag(){
        return visableFlag;
    }

    public void setVisableFlag(){
        this.visableFlag = !this.visableFlag;
    }

    public void setVisableFlag(boolean flag) {
        this.visableFlag = flag;
    }
}
