package sd.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.project.Model.Publisher;
import sd.project.Model.Series;

import java.util.List;

@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {
    List<Series> findByTitleStartingWith(String title);
    Series findById(Integer id);
    List<Series> findAllByPublisher(Publisher p);
}
