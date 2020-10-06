package Icons;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ClearIcon extends Icon {
    private boolean visableFlag;

    public ClearIcon(int x, int y, int size, Color color) {
        super(x, y);
        this.size = size;
        try{
            image = ImageIO.read(getClass().getResource("/resources/images/clear.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        setIconColor(color);
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
