package dupradosantini.sostoolbackend.repositories;

import dupradosantini.sostoolbackend.domain.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long> {
}
