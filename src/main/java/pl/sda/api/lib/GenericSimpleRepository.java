package pl.sda.api.lib;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public interface GenericSimpleRepository<T> {
    Optional<T> findByURI(URI uri) throws IOException, InterruptedException;
}
