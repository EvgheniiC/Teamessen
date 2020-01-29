
package eventim.teamessen.entity;



import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="users")
public class Users {
    @Id
    @Column(name="email",/*unique = true,*/ nullable = false,length = 200)
    String email;
    
    @Column(name="name",nullable = false,length = 200)
    String name;

    @Column(name="password",nullable = false,length = 128)
    @JsonIgnore 
    String password;

    @Column(name = "avatar", nullable = true)
    String avatar;
    
    @ManyToOne
    @JoinColumn(name="team_id", nullable=true)
    Team team;
/*

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_team", joinColumns = @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "fk_user_team__user"), nullable = false),
            inverseJoinColumns = @JoinColumn(name = "team_id",
                    foreignKey = @ForeignKey(name = "fk_user_team_team"), nullable = false))
    private Set<Team> teams;

    public Set<Team> getTeam() {
        return teams;
    }


    public void setTeam(Set<Team> teams) {
        this.teams = teams;
    }*/

    @ManyToOne
    @JoinColumn(name="role", nullable=false)
    @JsonIgnore        
    Role role;

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
