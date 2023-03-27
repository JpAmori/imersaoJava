import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Criando uma conexão HTTP
        String Url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI address = URI.create(Url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Criando o JSON a partir da resposta da API (Com a classe JsonParser)
        JsonParser jsonParser =  new JsonParser();
        List<Map<String, String>> moviesList = jsonParser.parse(body);

        // Exibindo os Dados
        for (Map<String, String> movie : moviesList){
            System.out.println("\u001B[1mTítulo: \u001B[m" + movie.get("title"));
            System.out.println("\u001B[1mPoster: \u001B[m: " + movie.get("image"));
            System.out.println("\u001B[1mNotas: \u001B[m " + movie.get(("imDbRating")));
            float classification = Float.parseFloat(movie.get("imDbRating"));
            int numStars = (int) classification;
            for (int n = 1; n <= numStars; n++){
                System.out.print("\u001B[1m\u2B50");
            }
            System.out.println("\n--------------------------------------------------------");
        }
    }
}