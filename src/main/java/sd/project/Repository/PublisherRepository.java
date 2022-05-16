package sd.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sd.project.Model.Publisher;
import sd.project.Model.User;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Publisher findByUser(User user);
    Publisher findById(Integer id);
}

