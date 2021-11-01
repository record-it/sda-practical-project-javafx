package pl.sda.api.lib;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

public class ApiRepository<T> {
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Map<Integer, T> cache = new HashMap<>();
    private final Class<T> modelClazz;

    public ApiRepository(Class<T> modelClazz) {
        this.modelClazz = modelClazz;
    }

    public Optional<T> get(URI uri, int id) throws IOException, InterruptedException {
        return get(uri.resolve(uri.getPath() +"/" + id).normalize());
    }

    private String getBody(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        final HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() == 200){
            return res.body();
        } else {
            return "";
        }
    }

    public List<T> getPage(URI uri, String resultField, String previousField, String nextField, BiConsumer<String, String> linksConsumer) throws IOException, InterruptedException {
        final String body = getBody(uri);
        if (!body.isEmpty()) {
            final JsonNode root = mapper.readTree(body);
            linksConsumer.accept(root.get(previousField).asText(), root.get(nextField).asText());
            final JsonNode jsonNode = root.get(resultField);
            JavaType type = mapper.getTypeFactory().
                    constructCollectionType(List.class, modelClazz);
            return mapper.convertValue(jsonNode, type);
        } else {
            return Collections.emptyList();
        }
    }

    public List<T> getPages(URI start, String resultField, String previousField, String nextField) throws IOException, InterruptedException {
        List<T> results = new ArrayList<>();
        AtomicReference<String> nextPageUrl = new AtomicReference<>(start.toASCIIString());
        do{
            results.addAll(getPage(URI.create(nextPageUrl.get()), resultField, previousField, nextField, (prev, next) -> {
                nextPageUrl.set(next);
            }));
        }while (!Objects.equals(nextPageUrl.get(), "null"));
        return results;
    }

    public Optional<T> get(URI uri) throws IOException, InterruptedException {
        String body = getBody(uri);
        if (!body.isEmpty()) {
            return Optional.of(mapper.readValue(body, modelClazz));
        } else {
            return Optional.empty();
        }
    }

    static public int extractId(String url) {
        int lastSlash = url.lastIndexOf("/");
        int previousSlash = url.lastIndexOf("/", lastSlash - 1);
        if (lastSlash <= 0 || previousSlash < 0) {
            return -1;
        }
        String strId = url.substring(previousSlash + 1, lastSlash);
        try {
            return Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
