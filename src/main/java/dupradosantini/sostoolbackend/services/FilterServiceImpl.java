package dupradosantini.sostoolbackend.services;

import dupradosantini.sostoolbackend.domain.Filter;
import dupradosantini.sostoolbackend.domain.dtos.FilterDto;
import dupradosantini.sostoolbackend.repositories.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilterServiceImpl {
    private final FilterRepository filterRepository;

    @Autowired
    public FilterServiceImpl(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    public FilterDto createFilter(FilterDto filterDto) {
        Filter filter = new Filter();
        filter.setName(filterDto.getName());
        filter.setActive(filterDto.isActive());
        filter.setType(filterDto.getType());

        Filter createdFilter = filterRepository.save(filter);

        FilterDto createdFilterDto = new FilterDto();
        createdFilterDto.setId(createdFilter.getId());
        createdFilterDto.setName(createdFilter.getName());
        createdFilterDto.setActive(createdFilter.isActive());
        createdFilterDto.setType(createdFilter.getType());

        return createdFilterDto;
    }

    public Optional<FilterDto> findFilterById(Long id) {
        Optional<Filter> filter = filterRepository.findById(id);

        if (filter.isPresent()) {
            FilterDto filterDto = new FilterDto();
            filterDto.setId(filter.get().getId());
            filterDto.setName(filter.get().getName());
            filterDto.setActive(filter.get().isActive());
            filterDto.setType(filter.get().getType());
            return Optional.of(filterDto);
        } else {
            return Optional.empty();
        }
    }
}