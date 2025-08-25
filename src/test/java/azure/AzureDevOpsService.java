package azure;

import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

public class AzureDevOpsService {
    private final String baseUrl;
    private final String authHeader;

    public AzureDevOpsService() {
        this.baseUrl = "https://dev.azure.com/" + AzureDevOpsConfig.ORGANIZATION + "/" + AzureDevOpsConfig.PROJECT;
        this.authHeader = "Basic " + Base64.getEncoder().encodeToString((":" + AzureDevOpsConfig.PAT).getBytes());
    }

    private HttpURLConnection setupConnection(String endpoint, String method) throws Exception {
        URL url = new URL(baseUrl + endpoint);
        System.out.println("🔗 Request URL: " + url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Authorization", authHeader);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        return conn;
    }

    public List<Integer> getTestPointIds(int testCaseId) throws Exception {
        String endpoint = "/_apis/test/Plans/" + AzureDevOpsConfig.PLAN_ID + "/Suites/" + AzureDevOpsConfig.SUITE_ID + "/Points?api-version=7.1";
        HttpURLConnection conn = setupConnection(endpoint, "GET");

        String response = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().reduce("", String::concat);
        System.out.println("📥 Response (Test Points): " + response);

        JSONArray points = new JSONObject(response).getJSONArray("value");
        List<Integer> pointIds = new ArrayList<>();

        for (int i = 0; i < points.length(); i++) {
            JSONObject point = points.getJSONObject(i);
            if (point.getJSONObject("testCase").getInt("id") == testCaseId) {
                pointIds.add(point.getInt("id"));
            }
        }

        System.out.println("✅ Found Test Point IDs: " + pointIds);
        return pointIds;
    }

    public int createTestRun(List<Integer> pointIds) throws Exception {
        String endpoint = "/_apis/test/runs?api-version=7.1";
        HttpURLConnection conn = setupConnection(endpoint, "POST");

        JSONObject body = new JSONObject();
        body.put("name", "Automation Test Run - " + UUID.randomUUID());
        body.put("plan", new JSONObject().put("id", AzureDevOpsConfig.PLAN_ID));
        body.put("pointIds", pointIds);

        System.out.println("📤 Request Body (Create Run): " + body.toString());

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.toString().getBytes());
        }

        int responseCode = conn.getResponseCode();
        System.out.println("📡 Response Code (Create Run): " + responseCode);

        String response = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().reduce("", String::concat);
        System.out.println("📥 Response (Create Run): " + response);

        return new JSONObject(response).getInt("id");
    }

    public int getResultId(int runId, int testCaseId) throws Exception {
        String endpoint = "/_apis/test/Runs/" + runId + "/Results?api-version=7.1";
        HttpURLConnection conn = setupConnection(endpoint, "GET");

        String response = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().reduce("", String::concat);
        System.out.println("📥 Response (Get Results): " + response);

        JSONArray results = new JSONObject(response).getJSONArray("value");

        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);
            if (result.getJSONObject("testCase").getInt("id") == testCaseId) {
                int resultId = result.getInt("id");
                System.out.println("✅ Found Result ID: " + resultId);
                return resultId;
            }
        }

        throw new RuntimeException("❌ Result ID not found for test case " + testCaseId);
    }

    public void updateTestResult(int runId, int resultId, String outcome) throws Exception {
        String endpoint = "/_apis/test/Runs/" + runId + "/Results?api-version=7.1";
        HttpURLConnection conn = setupConnection(endpoint, "POST");
        conn.setRequestProperty("X-HTTP-Method-Override", "PATCH");

        JSONArray body = new JSONArray();
        JSONObject result = new JSONObject();
        result.put("id", resultId);
        result.put("outcome", outcome);
        result.put("state", "Completed");
        body.put(result);

        System.out.println("📤 Request Body (Update Result): " + body.toString());

        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.toString().getBytes());
        }

        int responseCode = conn.getResponseCode();
        System.out.println("📡 Response Code (Update Result): " + responseCode);

        String response = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().reduce("", String::concat);
        System.out.println("📥 Response (Update Result): " + response);
    }

}
