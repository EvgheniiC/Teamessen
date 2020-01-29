
package eventim.teamessen.repos;

import eventim.teamessen.entity.Team;
import eventim.teamessen.entity.Users;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsersRepository extends JpaRepository<Users, String>{
    @Query("select u from Users u where u.team=?1")
    List<Users> getUsersByTeam(Team team);

    @Query("select u from Users u where u.email=?1")
    List<Users> getUsersByName(String name);
}
