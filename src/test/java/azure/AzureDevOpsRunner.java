package azure;

import java.util.List;

import io.cucumber.java.Scenario;

public class AzureDevOpsRunner {
    public static void execute(Scenario scenario) {
        try {
            //Set<String> tags = (Set<String>) scenario.getSourceTagNames();
            List<String> tags = (List<String>) scenario.getSourceTagNames();

            Integer testCaseId = null;

            for (String tag : tags) {
                if (tag.startsWith("@TCID_")) {
                    testCaseId = Integer.parseInt(tag.replace("@TCID_", "").trim());
                    break;
                }
            }

            if (testCaseId != null) {
                AzureDevOpsService service = new AzureDevOpsService();
                var pointIds = service.getTestPointIds(testCaseId);
                int runId = service.createTestRun(pointIds);
                int resultId = service.getResultId(runId, testCaseId);
                String outcome = scenario.isFailed() ? "Failed" : "Passed";
                service.updateTestResult(runId, resultId, outcome);

                System.out.println("✅ Azure DevOps updated for TCID_" + testCaseId + ": " + outcome);
            } else {
                System.out.println("⚠️ No TCID tag found in scenario: " + scenario.getName());
            }
        } catch (Exception e) {
            System.err.println("❌ Azure DevOps update failed: " + e.getMessage());
        }
    }
}
