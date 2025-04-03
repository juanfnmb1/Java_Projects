package Joke_Generator_Full_Stack;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JokeServer {

    public static void main(String[] args) throws IOException {
        // Creates HTTP server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Sets up context for serving static files (HTML, CSS, JS)
        server.createContext("/", new StaticFileHandler());

        // Sets up context for joke fetching API
        server.createContext("/get-joke", new JokeHandler());

        // Starts server
        server.start();
        System.out.println("Server is running on: http://localhost:8000/");
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

    // Handles joke fetching
    static class JokeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = fetchJoke();
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); // CORS header
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        // Fetches a random joke from the local list
        public String fetchJoke() {
            List<String> jokes = new ArrayList<>();
            jokes.add("Why don’t skeletons fight each other? They don’t have the guts.");
            jokes.add("What do you call fake spaghetti? An impasta.");
            jokes.add("I told my wife she was drawing her eyebrows too high. She looked surprised.");
            jokes.add("I told my computer I needed a break, and now it won’t stop sending me Kit-Kats.");
            jokes.add("Why don’t programmers like nature? It has too many bugs.");
            jokes.add("Why did the scarecrow win an award? Because he was outstanding in his field.");
            jokes.add("I would tell you a chemistry joke, but I know I wouldn’t get a reaction.");
            jokes.add("What’s orange and sounds like a parrot? A carrot.");
            jokes.add("I used to play piano by ear, but now I use my hands.");
            jokes.add("Why don’t oysters donate to charity? Because they are shellfish.");
            // Select a random joke
            Random random = new Random();
            String randomJoke = jokes.get(random.nextInt(jokes.size()));

            return "{\"joke\": \"" + randomJoke + "\"}"; // Return as a JSON string
        }
    }
}
