package dupradosantini.sostoolbackend.services;

import dupradosantini.sostoolbackend.domain.Filter;
import dupradosantini.sostoolbackend.domain.dtos.FilterDto;
import dupradosantini.sostoolbackend.repositories.FilterRepository;
import dupradosantini.sostoolbackend.services.FilterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FilterServiceImplTest {

    @InjectMocks
    private FilterServiceImpl filterService;

    @Mock
    private FilterRepository filterRepository;

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

        Filter filter = new Filter();
        filter.setName("Test Filter");
        filter.setActive(true);
        filter.setType("Type1");

        when(filterRepository.save(any(Filter.class))).thenReturn(filter);

        FilterDto createdFilterDto = filterService.createFilter(filterDto);

        assertEquals("Test Filter", createdFilterDto.getName());
    }

    @Test
    public void testAtivarFiltroSucesso() {
        Long id = 1l;

        Filter filterOriginal = new Filter(
                1,
                "atividade = concluída",
                false,
                "atividades"
        );

        Filter result = new Filter(
                1,
                "atividade = concluída",
                true,
                "atividades"
        );

        when(filterRepository.findById(id)).thenReturn(Optional.of(filterOriginal));
        when(filterRepository.save(any(Filter.class))).thenReturn(result);

       boolean success = filterService.ativarDesativarFiltro(id);

        assertEquals(true, success);
    }

    @Test
    public void testAtivarFiltroFalha() {
        Long id = 2l;

        when(filterRepository.findById(id)).thenReturn(Optional.empty());

        boolean success = filterService.ativarDesativarFiltro(id);

        assertEquals(false, success);
    }
}
