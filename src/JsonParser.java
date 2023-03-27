import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;
public class JsonParser {

    private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public List<Map<String, String>> parse(String json){

        Matcher matcher = REGEX_ITEMS.matcher(json);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> data = new ArrayList<>();

        for (String item : items) {

            Map<String, String> itemAttributes = new HashMap<>();

            Matcher matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item);
            while (matcherAtributosJson.find()) {
                String attribute = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                itemAttributes.put(attribute, valor);
            }

            data.add(itemAttributes);
        }

        return data;
    }
}
