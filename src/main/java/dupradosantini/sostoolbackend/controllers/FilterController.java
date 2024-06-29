package dupradosantini.sostoolbackend.controllers;

import dupradosantini.sostoolbackend.domain.AppUser;
import dupradosantini.sostoolbackend.domain.Filter;
import dupradosantini.sostoolbackend.domain.dtos.RoleHistoryDto;
import dupradosantini.sostoolbackend.services.interfaces.FilterService;
import dupradosantini.sostoolbackend.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<Filter> createFilter(@RequestBody Filter filter) {
        Filter createdFilter = filterService.createFilter(filter);
        return new ResponseEntity<>(createdFilter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filter> findFilterById(@PathVariable Long id) {
        Optional<Filter> filter = filterService.findFilterById(id);
        return filter.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}