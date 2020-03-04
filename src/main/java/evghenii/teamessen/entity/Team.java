
package eventim.teamessen.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="team")
public class Team  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    Long id;
    
    @Column
    String name;

    @Column
    String url;

    @Lob
    @Column(name = "avatar",nullable = true,columnDefinition="BLOB")
    String avatar;
    
    
    @OneToMany(mappedBy="team",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    Set<Users> users = new HashSet<>();

  /*  @ManyToMany(cascade = CascadeType.ALL) //by default fetch - LAZY
    @JoinTable(name = "user_team", joinColumns = @JoinColumn(name = "team_id",
            foreignKey = @ForeignKey(name = "fk_user_team__team"), nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    foreignKey = @ForeignKey(name = "fk_user_team_user"), nullable = false))
    private Set<Users> users;

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }*/

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
