package dupradosantini.sostoolbackend.controllers;

import dupradosantini.sostoolbackend.domain.Filter;
import dupradosantini.sostoolbackend.domain.dtos.FilterDto;
import dupradosantini.sostoolbackend.services.interfaces.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@Controller
@RequestMapping("/filters")
public class FilterController {
    private final FilterService filterService;

    @Autowired
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping
    public ResponseEntity<FilterDto> createFilter(@RequestBody FilterDto filterDto) {
        Filter filter = new Filter();
        filter.setName(filterDto.getName());
        filter.setActive(filterDto.isActive());
        filter.setType(filterDto.getType());

        Filter createdFilter = filterService.createFilter(filter);

        FilterDto createdFilterDto = new FilterDto();
        createdFilterDto.setId(createdFilter.getId());
        createdFilterDto.setName(createdFilter.getName());
        createdFilterDto.setActive(createdFilter.isActive());
        createdFilterDto.setType(createdFilter.getType());

        return new ResponseEntity<>(createdFilterDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilterDto> findFilterById(@PathVariable Long id) {
        Optional<Filter> filter = filterService.findFilterById(id);

        if (filter.isPresent()) {
            FilterDto filterDto = new FilterDto();
            filterDto.setId(filter.get().getId());
            filterDto.setName(filter.get().getName());
            filterDto.setActive(filter.get().isActive());
            filterDto.setType(filter.get().getType());
            return new ResponseEntity<>(filterDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
