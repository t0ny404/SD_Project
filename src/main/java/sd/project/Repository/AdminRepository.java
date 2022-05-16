package sd.project.Repository;

import sd.project.Model.Admin;
import sd.project.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUser(User user);
    Admin findById(Integer id);
}
