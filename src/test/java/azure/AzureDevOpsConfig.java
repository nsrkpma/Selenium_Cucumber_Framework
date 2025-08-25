package azure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AzureDevOpsConfig {
    public static final String ORGANIZATION;
    public static final String PROJECT;
    public static final String PAT;
    public static final int PLAN_ID;
    public static final int SUITE_ID;

    static {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/global.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load global.properties", e);
        }

        ORGANIZATION = props.getProperty("organization");
        PROJECT = props.getProperty("project");
        PLAN_ID = Integer.parseInt(props.getProperty("plan.id"));
        SUITE_ID = Integer.parseInt(props.getProperty("suite.id"));

        PAT = System.getenv("AZURE_PAT"); // Load from environment
        if (PAT == null || PAT.isEmpty()) {
            throw new RuntimeException("AZURE_PAT environment variable is not set");
        }
    }
}
