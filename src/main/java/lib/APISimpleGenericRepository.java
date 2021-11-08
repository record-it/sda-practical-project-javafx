package lib;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class APISimpleGenericRepository<T> extends ApiRepository<T> implements GenericSimpleRepository<T>{

    public APISimpleGenericRepository(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public Optional<T> findByURI(URI uri) throws IOException, InterruptedException {
        return get(uri);
    }
}
