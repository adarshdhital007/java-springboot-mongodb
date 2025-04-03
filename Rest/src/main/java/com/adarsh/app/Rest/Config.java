import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    public static void main(String[] args) {
        // Load the environment variables from the .env file
        Dotenv dotenv = Dotenv.load();

        // Retrieve specific environment variables from the .env file
        String mongoUrl = dotenv.get("MONGO_URL");

        // Use these values in your application
        System.out.println("MongoDB URL: " + mongoUrl);

        // You can use the values wherever needed in your application
        // For example, use the mongoUrl to connect to MongoDB, etc.
    }
}
