import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // Criando uma conexão HTTP
        String Url = "https://raw.githubusercontent.com/lukadev08/lukadev08.github.io/main/apidata/imdbmostpopularmovies.json";
        URI address = URI.create(Url);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response =  httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // Criando o JSON a partir da resposta da API (Com a classe JsonParser)
        JsonParser jsonParser =  new JsonParser();
        List<Map<String, String>> moviesList = jsonParser.parse(body);

        var creator = new createStickers();
        // Exibindo os Dados
        for (Map<String, String> movie : moviesList){

            String urlImage = movie.get("image");
            String title = movie.get("title");
            String nameFile = "output/" + title + ".png";

            InputStream inputStream = new URL(urlImage).openStream();

            creator.createSticker(inputStream, nameFile);

            System.out.println("\u001B[1mTítulo: \u001B[m" + movie.get("title"));
            System.out.println("--------------------------------------------------------");
        }
    }
}