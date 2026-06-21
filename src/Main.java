package src;

import java.io.IOException;

public class Main {
    // 🔴 CENTRALIZED CONFIGURATION MATRIX
    public static final String WHAPI_TOKEN = "YOUR_WHAPI_API_TOKEN_HERE";
    public static final String TARGET_GROUP_ID = "120363409188182876@g.us";
    public static final String MY_PERSONAL_PHONE_ID = "YOUR_PERSONAL_WHATSAPP_ID_HERE";
    public static final int SERVER_PORT = 8080;

    // Strict exact word-boundary matching keywords
    public static final String[] WISHLIST = {"AC", "conditioner", "laptop", "phone", "ssd", "monitor"};

    public static void main(String[] args) {
        try {
            System.out.println("=======================================================");
            System.out.println("🚀 INITIALIZING MODULAR AI DEAL SNIPER ENGINE V2.0");
            System.out.println("=======================================================");
            
            WebhookServer server = new WebhookServer(SERVER_PORT);
            server.start();
            
            System.out.println("🎯 Automated Radar Online. Monitoring Group ID: " + TARGET_GROUP_ID);
        } catch (IOException e) {
            System.err.println("❌ Critical Engine Boot Failure: " + e.getMessage());
        }
    }
}
