
package eventim.teamessen.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventim.teamessen.entity.Event;
import eventim.teamessen.entity.Team;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("Select e from Event e where e.team=?1")
    List<Event> getEventByTeam(Team team);

    List<Event> getEventsByBeginDateEquals(Date date);
}
