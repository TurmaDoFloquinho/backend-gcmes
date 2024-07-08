package dupradosantini.sostoolbackend.controllers;

import dupradosantini.sostoolbackend.domain.dtos.FilterDto;
import dupradosantini.sostoolbackend.services.FilterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "https://sitesegurodofloquinho.com")
@Controller
@RequestMapping("/filters")
public class FilterController {
    private final FilterServiceImpl filterService;

    @Autowired
    public FilterController(FilterServiceImpl filterService) {
        this.filterService = filterService;
    }

    @PostMapping
    public ResponseEntity<FilterDto> createFilter(@RequestBody FilterDto filterDto) {
        FilterDto createdFilterDto = filterService.createFilter(filterDto);
        return new ResponseEntity<>(createdFilterDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilterDto> findFilterById(@PathVariable Long id) {
        Optional<FilterDto> filterDto = filterService.findFilterById(id);

        return filterDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
