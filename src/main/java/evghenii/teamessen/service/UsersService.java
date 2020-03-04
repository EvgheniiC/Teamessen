package eventim.teamessen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eventim.teamessen.entity.Role;
import eventim.teamessen.entity.Users;
import eventim.teamessen.repos.RoleRepository;
import eventim.teamessen.repos.UsersRepository;

@Service
public class UsersService {

    @Autowired
    UsersRepository usersRepos;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public String createUser(String login, String name, String password, String avatar) {
        String error = null;
        Users user;
        try {
            user = new Users();
            user.setName(name);
            user.setEmail(login);
            user.setAvatar(avatar);
            user.setPassword(passwordEncoder.encode(password));
            Role role = roleRepository.findById("ROLE_USER")
                                      .get();
            user.setRole(role);
            usersRepos.save(user);
        } catch(Exception e) {
            error = e.getLocalizedMessage();
        }
        return error;
    }

}
