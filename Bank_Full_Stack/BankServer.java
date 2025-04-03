package Bank_Full_Stack;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.Paths;

public class BankServer {

    public static void main(String[] args) throws IOException {
        // Creates HTTP server on port 3000
        HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);

        // Serves static files (HTML, CSS, JS)
        server.createContext("/", new StaticFileHandler());

        // Serves /get-users API route
        server.createContext("/get-users", new UsersHandler());

        // Starts server
        server.start();
        System.out.println("Server is running on: http://localhost:3000/");
    }

    // Serves static files (HTML, CSS, JS)
    static class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String filePath = "index.html"; // Update this to your actual file path
            byte[] response = Files.readAllBytes(Paths.get(filePath));
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        }
    }

    // Handles /get-users API
    static class UsersHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = getUsersJson();
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); // CORS header
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        // Generate users' data as a JSON response
        public String getUsersJson() {
            List<User> users = new ArrayList<>();
            users.add(new User("John", "Doe", 1000.00, "123456789", "987654321"));
            users.add(new User("Jane", "Smith", 1500.50, "987654321", "123456789"));
            users.add(new User("Michael", "Johnson", 2000.75, "112233445", "998877665"));
            users.add(new User("Emily", "Davis", 500.30, "223344556", "776655443"));
            users.add(new User("David", "Brown", 1200.40, "334455667", "665544332"));
            users.add(new User("Sophia", "Wilson", 1800.25, "445566778", "554433221"));

            StringBuilder jsonResponse = new StringBuilder("[");
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                jsonResponse.append("{")
                        .append("\"firstName\": \"").append(user.firstName).append("\",")
                        .append("\"lastName\": \"").append(user.lastName).append("\",")
                        .append("\"balance\": ").append(user.balance).append(",")
                        .append("\"routingNumber\": \"").append(user.routingNumber).append("\",")
                        .append("\"accountNumber\": \"").append(user.accountNumber).append("\"")
                        .append("}");
                if (i < users.size() - 1) {
                    jsonResponse.append(",");
                }
            }
            jsonResponse.append("]");
            return jsonResponse.toString();
        }
    }

    // A simple User class to hold user data
    static class User {
        String firstName;
        String lastName;
        double balance;
        String routingNumber;
        String accountNumber;

        public User(String firstName, String lastName, double balance, String routingNumber, String accountNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.balance = balance;
            this.routingNumber = routingNumber;
            this.accountNumber = accountNumber;
        }
    }
}
