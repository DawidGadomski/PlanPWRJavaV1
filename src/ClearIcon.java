import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ClearIcon extends Icon {
    private boolean visableFlag;

    public ClearIcon(int x, int y, int size) {
        super(x, y);
        imgURL = "C:\\Users\\dawik\\IdeaProjects\\Plan PWR\\Icons\\clear.png";
        this.size = size;
        try{
            image = ImageIO.read(new File(imgURL));
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
