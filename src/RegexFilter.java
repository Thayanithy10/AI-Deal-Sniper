package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFilter {
    
    // RegEx compiling sequence to pull HTTP links safely out of unstructured text layouts
    private static final Pattern LINK_PATTERN = Pattern.compile("(https?://[^\\s\"'\\}\\],]+)");

    public static String extractLink(String plainText) {
        Matcher matcher = LINK_PATTERN.matcher(plainText);
        if (matcher.find()) {
            String url = matcher.group(1).trim();
            if (url.endsWith("]")) {
                url = url.substring(0, url.length() - 1);
            }
            return url;
        }
        return null;
    }

    public static String matchWishlist(String productTitle) {
        String lowerTitle = productTitle.toLowerCase();
        
        for (String keyword : Main.WISHLIST) {
            // \\b enforces exact word matching bounds to eliminate sub-string overlap glitches
            Pattern boundaryPattern = Pattern.compile("\\b" + Pattern.quote(keyword.toLowerCase()) + "\\b");
            Matcher matcher = boundaryPattern.matcher(lowerTitle);
            
            if (matcher.find()) {
                return keyword; // Returns the exact word that tripped the filter matrix
            }
        }
        return null;
    }
}
