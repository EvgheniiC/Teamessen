
package eventim.teamessen.repos;

import eventim.teamessen.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository  extends JpaRepository<Team, Long>{
    
}
