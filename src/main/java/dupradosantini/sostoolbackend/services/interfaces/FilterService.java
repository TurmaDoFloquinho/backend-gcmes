package dupradosantini.sostoolbackend.services.interfaces;

import dupradosantini.sostoolbackend.domain.Filter;

import java.util.Optional;

public interface FilterService {

    Filter createFilter(Filter filter);

    Optional<Filter> findFilterById(Long id);
}

