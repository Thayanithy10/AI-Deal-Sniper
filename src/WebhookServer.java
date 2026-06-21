package src;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class WebhookServer {
    private final HttpServer server;

    public WebhookServer(int port) throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        this.server.createContext("/", new WebhookHandler());
        this.server.setExecutor(null); // Simple default executor thread pool
    }

    public void start() {
        this.server.start();
    }

    static class WebhookHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStream is = exchange.getRequestBody();
                String rawJsonPayload = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                
                // Fail-fast network handshake confirmation back to Whapi Cloud Router
                String jsonResponse = "{\"status\":\"success\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, jsonResponse.length());
                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes());
                os.close();

                // Forward data execution loop downstream asynchronously
                processPayload(rawJsonPayload);
            } else {
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        }

        private static void processPayload(String rawBody) {
            // Verify payload integrity and ignore our own outbound messages
            if (rawBody.contains("\"messages\"") && !rawBody.contains("\"from_me\":true")) {
                String chatId = Utils.extractJsonValue(rawBody, "chat_id");
                String textBody = Utils.extractJsonValue(rawBody, "body");

                if (chatId != null && textBody != null && chatId.equals(Main.TARGET_GROUP_ID)) {
                    System.out.println("\n⚡ [NETWORK INGRESS] New stream intercepted from target group chat.");
                    
                    // Route to the regex engine to search for hidden links
                    String isolatedUrl = RegexFilter.extractLink(textBody);
                    if (isolatedUrl != null) {
                        System.out.println("🔗 Isolated URL Signature: " + isolatedUrl);
                        DealParser.inspectProductLayout(isolatedUrl);
                    } else {
                        System.out.println("ℹ️ Message evaluated as structural text only. No tracking link found.");
                    }
                }
            }
        }
    }
}
