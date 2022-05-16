package sd.project.Repository;

import sd.project.Model.Series;
import sd.project.Model.Utils.Rating;
import sd.project.Model.Issue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    List<Issue> findAllBySeries(Series series);
}
