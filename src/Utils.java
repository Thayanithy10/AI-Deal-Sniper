package src;

public class Utils {

    /**
     * Extracts values from a raw JSON block without relying on external dependencies.
     * Engineered to parse multi-line messaging payloads safely.
     */
    public static String extractJsonValue(String rawJson, String targetKey) {
        String wrappedKeySignature = "\"" + targetKey + "\"";
        int indexPointer = rawJson.indexOf(wrappedKeySignature);
        if (indexPointer == -1) return null;
        
        int executionIndex = rawJson.indexOf(":", indexPointer);
        if (executionIndex == -1) return null;
        executionIndex++; // Skip the colon delimiter character
        
        // Skip spaces or structural quotes to reach the actual value string
        while (executionIndex < rawJson.length() && 
              (rawJson.charAt(executionIndex) == ' ' || rawJson.charAt(executionIndex) == '"')) {
            executionIndex++;
        }
        
        int terminalIndex = executionIndex;
        if (rawJson.charAt(executionIndex - 1) == '"') {
            while (terminalIndex < rawJson.length() && rawJson.charAt(terminalIndex) != '"') {
                // Safely handle escaped quotation marks inside text blocks
                if (rawJson.charAt(terminalIndex) == '\\' && 
                    terminalIndex + 1 < rawJson.length() && 
                    rawJson.charAt(terminalIndex + 1) == '"') {
                    terminalIndex += 2;
                } else {
                    terminalIndex++;
                }
            }
        } else {
            // Stop at structural JSON boundaries if parsing raw unquoted primitives
            while (terminalIndex < rawJson.length() && 
                  (rawJson.charAt(terminalIndex) != ',' && 
                   rawJson.charAt(terminalIndex) != '}' && 
                   rawJson.charAt(terminalIndex) != ']')) {
                terminalIndex++;
            }
        }
        
        if (executionIndex >= rawJson.length() || terminalIndex > rawJson.length()) return null;
        
        // Clean up escaped sequences to restore native multi-line text blocks
        return rawJson.substring(executionIndex, terminalIndex)
                .replace("\\n", "\n")
                .replace("\\\"", "\"")
                .trim();
    }
}
