package src;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DealParser {

    public static void inspectProductLayout(String targetUrl) {
        System.out.println("🔍 Running DOM Extraction Thread on Vendor Server...");
        try {
            Document htmlDocument = Jsoup.connect(targetUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .timeout(15000)
                    .followRedirects(true)
                    .get();
            
            // Fail-Fast Idempotent Inventory Check
            String parsedTextSpace = htmlDocument.text();
            if (parsedTextSpace.contains("Currently unavailable") || 
                parsedTextSpace.contains("Out of Stock") || 
                htmlDocument.selectFirst("#outOfStock") != null) {
                System.out.println("🛑 Inventory Status: SOLD OUT. Halting calculation thread.");
                return;
            }

            String fullPageTitle = htmlDocument.title();
            System.out.println("📦 Web Product Title: " + fullPageTitle);
            
            // Route processing downstream to match against Wishlist arrays
            String matchedTerm = RegexFilter.matchWishlist(fullPageTitle);
            
            if (matchedTerm != null) {
                System.out.println("🎯 [WISHLIST MATCH] Hit target keyword token: " + matchedTerm);
                
                // Extract pricing elements cleanly from the HTML layout hierarchy
                Element priceTag = htmlDocument.selectFirst(".a-price-whole");
                String dynamicPrice = (priceTag != null) ? "₹" + priceTag.text().trim() : "Review listing for live pricing metrics";
                
                System.out.println("💰 Current Web Pricing: " + dynamicPrice);
                
                // Dispatch alerts to the notification layer
                NotificationService.dispatchAlert(matchedTerm, fullPageTitle, dynamicPrice, targetUrl);
            } else {
                System.out.println("❌ Listing title failed target evaluation criteria. Dropping sequence.");
            }

        } catch (Exception e) {
            System.err.println("❌ Jsoup Extraction Processing Failed: " + e.getMessage());
        }
    }
}
