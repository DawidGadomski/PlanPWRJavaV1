package Settings;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class AppProperties {
    InputStream inputStream;
    private Properties prop;
    private String propFileName;

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

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

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
        prop.setProperty("lectureColor", Integer.toString(lectureColor.getRGB()));
    }

    public Color getLabColor() {
        return labColor;
    }

    public void setLabColor(Color labColor) {
        this.labColor = labColor;
        prop.setProperty("labColor", Integer.toString(labColor.getRGB()));
    }

    public Color getProjectColor() {
        return projectColor;
    }

    public void setProjectColor(Color projectColor) {
        this.projectColor = projectColor;
        prop.setProperty("projectColor", Integer.toString(projectColor.getRGB()));
    }

    public Color getSeminaryColor() {
        return seminaryColor;
    }

    public void setSeminaryColor(Color seminaryColor) {
        this.seminaryColor = seminaryColor;
        prop.setProperty("seminaryColor", Integer.toString(seminaryColor.getRGB()));
    }

    public void setFirstColor(Color firstColor) {
        this.firstColor = firstColor;
        prop.setProperty("firstColor", Integer.toString(firstColor.getRGB()));
    }

    public void setSecondColor(Color secondColor) {
        this.secondColor = secondColor;
        prop.setProperty("secondColor", Integer.toString(secondColor.getRGB()));
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
        prop.setProperty("textColor", Integer.toString(textColor.getRGB()));
    }

    public void setDarkTextColor(Color darkTextColor) {
        this.darkTextColor = darkTextColor;
        prop.setProperty("darkTextColor", Integer.toString(darkTextColor.getRGB()));
    }

    public void setGridBackgroundColor(Color gridBackgroundColor) {
        this.gridBackgroundColor = gridBackgroundColor;
        prop.setProperty("gridBackgroundColor", Integer.toString(gridBackgroundColor.getRGB()));
    }

    public void setThirdColor(Color thirdColor) {
        this.thirdColor = thirdColor;
        prop.setProperty("thirdColor", Integer.toString(thirdColor.getRGB()));
    }

    public void setFourthColor(Color fourthColor) {
        this.fourthColor = fourthColor;
        prop.setProperty("fourthColor", Integer.toString(fourthColor.getRGB()));
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
}
