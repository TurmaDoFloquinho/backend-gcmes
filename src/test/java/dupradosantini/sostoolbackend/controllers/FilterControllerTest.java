package dupradosantini.sostoolbackend.controllers;

import dupradosantini.sostoolbackend.domain.dtos.FilterDto;
import dupradosantini.sostoolbackend.services.FilterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FilterControllerTest {

    @InjectMocks
    private FilterController filterController;

    @Mock
    private FilterServiceImpl filterService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFilter() {
        FilterDto filterDto = new FilterDto();
        filterDto.setName("Test Filter");
        filterDto.setActive(true);
        filterDto.setType("Type1");

        when(filterService.createFilter(any(FilterDto.class))).thenReturn(filterDto);

        ResponseEntity<FilterDto> response = filterController.createFilter(filterDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Test Filter", response.getBody().getName());
    }

    @Test
    public void testFindFilterById_Found() {
        FilterDto filterDto = new FilterDto();
        filterDto.setId(1);
        filterDto.setName("Test Filter");
        filterDto.setActive(true);
        filterDto.setType("Type1");

        when(filterService.findFilterById(1L)).thenReturn(Optional.of(filterDto));

        ResponseEntity<FilterDto> response = filterController.findFilterById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Filter", response.getBody().getName());
    }

    @Test
    public void testFindFilterById_NotFound() {
        when(filterService.findFilterById(1L)).thenReturn(Optional.empty());

        ResponseEntity<FilterDto> response = filterController.findFilterById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
  
    public void testAtivarFiltroSucesso() {

        when(filterService.ativarDesativarFiltro(1l)).thenReturn(true);

        ResponseEntity response = filterController.ativarDesativarFiltro(1l);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testAtivarFiltroFalha() {

        when(filterService.ativarDesativarFiltro(1l)).thenReturn(false);

        ResponseEntity response = filterController.ativarDesativarFiltro(1l);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
