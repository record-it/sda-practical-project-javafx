package pl.sda.api.lib;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    Optional<T> findById(int id) throws IOException, InterruptedException;
    List<T> findAll() throws IOException, InterruptedException;
    Optional<T> findByUrl(String url) throws IOException, InterruptedException;
}
