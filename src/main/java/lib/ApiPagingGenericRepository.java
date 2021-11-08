package lib;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

public class ApiPagingGenericRepository<T> extends ApiRepository<T> implements GenericPagingRepository<T> {
    private final URI baseUri;
    private final String resultField;
    private final String nextField;
    private final String previousField;

    public ApiPagingGenericRepository(Class<T> modelClazz, URI baseUri, String resultField, String nextField, String previousField) {
        super(modelClazz);
        this.baseUri = baseUri;
        this.resultField = resultField;
        this.nextField = nextField;
        this.previousField = previousField;
    }
    public ApiPagingGenericRepository(Class<T> modelClazz) {
        super(modelClazz);
        this.baseUri = null;
        this.resultField = null;
        this.nextField = null;
        this.previousField = null;
    }


    @Override
    public Optional<T> findById(int id) throws IOException, InterruptedException {
        return get(baseUri, id);
    }

    @Override
    public List<T> findAll() throws IOException, InterruptedException {
        return getPages(baseUri, resultField, previousField, nextField);
    }

    @Override
    public Optional<T> findByUrl(String url) throws IOException, InterruptedException {
        return get(URI.create(url));
    }
}
