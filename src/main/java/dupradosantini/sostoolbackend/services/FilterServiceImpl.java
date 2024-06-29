package dupradosantini.sostoolbackend.services;

import dupradosantini.sostoolbackend.domain.*;
import dupradosantini.sostoolbackend.repositories.FilterRepository;
import dupradosantini.sostoolbackend.services.interfaces.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FilterServiceImpl implements FilterService {
    private final FilterRepository filterRepository;

    @Autowired
    public FilterServiceImpl(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    @Override
    public Filter createFilter(Filter filter) {
        return filterRepository.save(filter);
    }

    @Override
    public Optional<Filter> findFilterById(Long id) {
        return filterRepository.findById(id);
    }
}