package Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/***
 * Ustawienai okna notatek
 */
public class NotesSettings extends Settings {
    //  Icons
    private File backIcon;
    private File addIcon;
    private File saveIcon;

    private BufferedImage backImage;
    private BufferedImage saveImage;
    private BufferedImage addImage;

    private int buttonSize;

    private int noteWidth;
    private int noteHeight;
    private Color noteColor;
    /***
     * Konstruktor opcji notatek
     */
    public NotesSettings(){
//  URLs
        backIcon = new File("Icons/back.png");
        addIcon = new File("Icons/save.png");
        saveIcon = new File("Icons/add.png");

//      Icons
        try{
            backImage = ImageIO.read(getClass().getResource("/resources/images/back.png"));
            addImage = ImageIO.read(getClass().getResource("/resources/images/add.png"));
            saveImage = ImageIO.read(getClass().getResource("/resources/images/save.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        buttonSize = windowWidth / 2 /20;

        noteWidth = windowWidth/5;
        noteHeight = windowHeight/5;
        noteColor = new Color(200, 200, 200);

    }

//  Getters and Setters
    public void setBackIcon(File backIcon) {
        this.backIcon = backIcon;
    }


    public void setAddIcon(File addIcon) {
        this.addIcon = addIcon;
    }
    public void setSaveIcon(File saveIcon) {
        this.saveIcon = saveIcon;
    }

    public BufferedImage getBackImage() {
        return backImage;
    }

    public void setBackImage(BufferedImage backImage) {
        this.backImage = backImage;
    }

    public BufferedImage getSaveImage() {
        return saveImage;
    }

    public void setSaveImage(BufferedImage saveImage) {
        this.saveImage = saveImage;
    }

    public BufferedImage getAddImage() {
        return addImage;
    }

    public void setAddImage(BufferedImage addImage) {
        this.addImage = addImage;
    }

    public int getButtonSize() {
        return buttonSize;
    }

    public void setButtonSize(int buttonSize) {
        this.buttonSize = buttonSize;
    }

    public int getNoteWidth() {
        return noteWidth;
    }

    public void setNoteWidth(int noteWidth) {
        this.noteWidth = noteWidth;
    }

    public int getNoteHeight() {
        return noteHeight;
    }

    public void setNoteHeight(int noteHeight) {
        this.noteHeight = noteHeight;
    }

    public Color getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(Color noteColor) {
        this.noteColor = noteColor;
    }
}
