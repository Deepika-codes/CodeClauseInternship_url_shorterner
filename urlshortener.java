import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;

public class urlshortener {
    private HashMap<String, String> urlMap;

    public urlshortener() {
        urlMap = new HashMap<>();
    }

    public String shortenURL(String url) {
        String shortCode = generateShortCode(url);
        urlMap.put(shortCode, url);
        return shortCode;
    }

    public String expandURL(String shortCode) {
        return urlMap.get(shortCode);
    }

    private String generateShortCode(String url) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(url.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString().substring(0, 8); // Using first 8 characters as the short code
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        urlshortener
     shortener = new urlshortener ();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the original URL: ");
        String url = scanner.nextLine();

        String shortCode = shortener.shortenURL(url);

        System.out.println("Original URL: " + url);
        System.out.println("Shortened URL: " + shortCode);

        String expandedURL = shortener.expandURL(shortCode);
        System.out.println("Expanded URL: " + expandedURL);
    }
}
