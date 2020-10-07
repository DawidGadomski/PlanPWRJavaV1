package Settings;

import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.Properties;

public class AppProperties {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Properties prop;
    private String propFileName;

    private String folderPath;

    // Colors
    private Color firstColor;
    private Color secondColor;
    private Color textColor;
    private Color darkTextColor;
    private Color gridBackgroundColor;
    private Color thirdColor;
    private Color fourthColor;

    private Color lectureColor;
    private Color labColor;
    private Color projectColor;
    private Color seminaryColor;

    public AppProperties() throws IOException {

        try {
             prop = new Properties();
             propFileName = "config.properties";

            inputStream = new FileInputStream(propFileName);

            prop.load(inputStream);

            folderPath = prop.getProperty("folderPath");

            textColor = new Color(Integer.parseInt(prop.getProperty("textColor")));
            darkTextColor = new Color(Integer.parseInt(prop.getProperty("darkTextColor")));
            firstColor = new Color(Integer.parseInt(prop.getProperty("firstColor")));  // main color of background
            secondColor = new Color(Integer.parseInt(prop.getProperty("secondColor")));   // second color of background
            thirdColor = new Color(Integer.parseInt(prop.getProperty("thirdColor")));// lines color
            fourthColor = new Color(Integer.parseInt(prop.getProperty("fourthColor"))); // work surface color
            gridBackgroundColor = new Color(Integer.parseInt(prop.getProperty("gridBackgroundColor")));

            lectureColor = new Color(Integer.parseInt(prop.getProperty("lectureColor")));
            projectColor = new Color(Integer.parseInt(prop.getProperty("projectColor")));
            labColor = new Color(Integer.parseInt(prop.getProperty("labColor")));
            seminaryColor = new Color(Integer.parseInt(prop.getProperty("seminaryColor")));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
    }

    public Color getDarkTextColor() {
        return darkTextColor;
    }

    public Color getGridBackgroundColor() {
        return gridBackgroundColor;
    }

    public Color getThirdColor() {
        return thirdColor;
    }

    public Color getFourthColor() {
        return fourthColor;
    }

    public Color getLectureColor() {
        return lectureColor;
    }

    public void setLectureColor(Color lectureColor) {
        this.lectureColor = lectureColor;
        saveColorProperties("lectureColor", this.lectureColor);
    }

    public Color getLabColor() {
        return labColor;
    }

    public void setLabColor(Color labColor) {
        this.labColor = labColor;
        saveColorProperties("labColor", this.labColor);
    }

    public Color getProjectColor() {
        return projectColor;
    }

    public void setProjectColor(Color projectColor) {
        this.projectColor = projectColor;
        saveColorProperties("projectColor", this.projectColor);
    }

    public Color getSeminaryColor() {
        return seminaryColor;
    }

    public void setSeminaryColor(Color seminaryColor) {
        this.seminaryColor = seminaryColor;
        saveColorProperties("seminaryColor", this.seminaryColor);
    }

    public void setFirstColor(Color firstColor) {
        this.firstColor = firstColor;
        saveColorProperties("firstColor", this.firstColor);
    }

    public void setSecondColor(Color secondColor) {
        this.secondColor = secondColor;
        saveColorProperties("secondColor", this.secondColor);
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        saveColorProperties("textColor", this.textColor);
    }

    public void setDarkTextColor(Color darkTextColor) {
        this.darkTextColor = darkTextColor;
        saveColorProperties("darkTextColor", this.darkTextColor);
    }

    public void setGridBackgroundColor(Color gridBackgroundColor) {
        this.gridBackgroundColor = gridBackgroundColor;
        saveColorProperties("gridBackgroundColor", this.gridBackgroundColor);
    }

    public void setThirdColor(Color thirdColor) {
        this.thirdColor = thirdColor;
        saveColorProperties("thirdColor", this.thirdColor);
    }

    public void setFourthColor(Color fourthColor) {
        this.fourthColor = fourthColor;
        saveColorProperties("fourthColor", this.fourthColor);
    }

    public Color getFirstColor() {
        return firstColor;
    }

    public Color getSecondColor() {
        return secondColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    private void saveColorProperties(String propName, Color color){
        try{
            inputStream = new FileInputStream(propFileName);
            prop.load(inputStream);
            inputStream.close();

            outputStream = new FileOutputStream(propFileName);
            prop.setProperty(propName, Integer.toString(color.getRGB()));
            prop.store(outputStream, null);
            outputStream.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
