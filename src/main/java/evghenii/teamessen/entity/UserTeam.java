package eventim.teamessen.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*@Entity
@Table(name = "user_team", uniqueConstraints = @UniqueConstraint
       (columnNames = {"user_id", "team_id"},name = "uniq_some"))*/
public class UserTeam {
/*
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_team__user"), nullable = false)
         private Users user;

         @ManyToOne(cascade = CascadeType.ALL)
         @JoinColumn(name = "team_id", foreignKey = @ForeignKey(name = "fk_user_team__team"), nullable = false)
         private Team team;
         @ManyToOne(cascade = CascadeType.ALL)
         @JoinColumn(name = "role_name", nullable = false)
         @JsonIgnore
         private Role role;*/
}
