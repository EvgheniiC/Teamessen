
package eventim.teamessen;
import eventim.teamessen.entity.Role;
import eventim.teamessen.entity.Users;
import eventim.teamessen.repos.RoleRepository;
import eventim.teamessen.repos.UsersRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class SetupDataLoader implements  ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    @Autowired
    private UsersRepository userRepository;



    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;




    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges

        // == create initial roles
        //'ROLE_USER','ROLE_ADMIN','ROLE_CHIEF'
        final Role adminRole =  createRoleIfNotFound("ROLE_ADMIN");
        final Role userRole = createRoleIfNotFound("ROLE_USER");



        // == create initial user
        createUserIfNotFound("admin", "admin", "admin", adminRole);



        alreadySetup = true;
    }



    @Transactional
    private final Role createRoleIfNotFound(final String roleName) {
        Role role = roleRepository.findById(roleName).orElse(null);
        if (role == null) {
            role = new Role(roleName);
        }
        role = roleRepository.save(role);
        return role;
    }




    @Transactional
    private final Users createUserIfNotFound(final String login, final String name, final String password,Role role) {
        Users user = userRepository.findById(login).orElse(null);
        if (user == null) {
            user = new Users();
            user.setName(name);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(login);

        }
        user.setRole(role);
        user = userRepository.save(user);
        return user;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
