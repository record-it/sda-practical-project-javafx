package pl.sda.api.swapi.repository;

import lombok.extern.slf4j.Slf4j;
import pl.sda.api.lib.ApiGenericRepository;
import pl.sda.api.swapi.config.PageConfiguration;
import pl.sda.api.swapi.config.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SWAPIGenericRepository<T> implements SWAPIRepository<T> {
    private final ApiGenericRepository<T> respository;

    public SWAPIGenericRepository(Resource resource) {
        this.respository = new ApiGenericRepository<T>(resource.getClazz(), resource.toURI(), PageConfiguration.RESULTS_FIELD, PageConfiguration.PREVIOUS_FIELD, PageConfiguration.NEXT_FIELD);
    }

    @Override
    public Optional<T> findById(int id) throws IOException, InterruptedException {
        return respository.findById(id);
    }

    @Override
    public List<T> findAll() throws IOException, InterruptedException {
        return respository.findAll();
    }

    @Override
    public Optional<T> findByUrl(String url) throws IOException, InterruptedException {
        return respository.findByUrl(url);
    }
}
