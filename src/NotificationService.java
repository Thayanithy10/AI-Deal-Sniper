package src;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NotificationService {

    public static void dispatchAlert(String triggerKeyword, String itemTitle, String itemPrice, String accessUrl) {
        System.out.println("📤 Formulating outbound payload structures...");
        
        String cleanMessageBody = "🎯 *AI DEAL SNIPER ALARM* 🎯\\n\\n" +
                "An item from your wishlist (*" + triggerKeyword + "*) was spotted!\\n\\n" +
                "📦 *Product:* " + itemTitle + "\\n" +
                "💰 *Current Price:* " + itemPrice + "\\n\\n" +
                "🔗 *Buy Now:* " + accessUrl;

        broadcastPayload(Main.MY_PERSONAL_PHONE_ID, cleanMessageBody);
    }

    private static void broadcastPayload(String targetedUserChatId, String messageBodyText) {
        try {
            HttpClient networkClient = HttpClient.newHttpClient();
            String escapedMessage = messageBodyText.replace("\n", "\\n");
            String structuredJsonBody = "{\"to\":\"" + targetedUserChatId + "\",\"body\":\"" + escapedMessage + "\"}";

            HttpRequest outboundRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://gate.whapi.cloud/messages/text"))
                    .header("Authorization", "Bearer " + Main.WHAPI_TOKEN)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(structuredJsonBody))
                    .build();

            networkClient.send(outboundRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("✅ Security Alert Notification routed successfully to chat endpoint window!");
        } catch (Exception e) {
            System.err.println("❌ Network Notification Egress Failure: " + e.getMessage());
        }
    }
}
