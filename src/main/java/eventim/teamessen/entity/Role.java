
package eventim.teamessen.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")

public class Role {

    @Id
    @Column(name="role_name",unique = true, nullable = false,length = 100)        
    String roleName;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
    
    
    @OneToMany(mappedBy="role",cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Users> users;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }
}
