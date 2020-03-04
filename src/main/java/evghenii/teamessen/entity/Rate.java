
package eventim.teamessen.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="rates",uniqueConstraints = @UniqueConstraint(columnNames = {"event_id","user_id"}))
public class Rate  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    Long id;  
    @ManyToOne
    @JoinColumn(name = "event_id",nullable = false)
    Event event;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    Users user;

    public Rate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
