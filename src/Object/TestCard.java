package Object;

import java.util.Date;

public class TestCard {
    private String testName;
    private String testDate;

    public TestCard(String testName, String testDate) {
        this.testName = testName;
        this.testDate = testDate;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
