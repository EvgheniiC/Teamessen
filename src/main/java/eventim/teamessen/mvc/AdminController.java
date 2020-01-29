package eventim.teamessen.mvc;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventim.teamessen.entity.Event;
import eventim.teamessen.entity.Team;
import eventim.teamessen.entity.Users;
import eventim.teamessen.exception.NoSuchTeamException;
import eventim.teamessen.exception.NoSuchUserException;
import eventim.teamessen.form.EventForm;
import eventim.teamessen.form.TeamForm;
import eventim.teamessen.form.UserTeamForm;
import eventim.teamessen.form.UsersForm;
import eventim.teamessen.repos.EventRepository;
import eventim.teamessen.repos.OptionRepository;
import eventim.teamessen.repos.TeamRepository;
import eventim.teamessen.repos.UserRepositoryUserLong;
import eventim.teamessen.repos.UsersRepository;

@Controller
public class AdminController {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    OptionRepository optionRepository;
    @Autowired
    UsersRepository userRpRepository;
    @Autowired
    UserRepositoryUserLong userRepositoryUserLong;

    @GetMapping("/admin/team/all")
    List<Team> allTeams() {
        return teamRepository.findAll();
    }



    /*@GetMapping("/admin/user/all")
    List<Users> allUsers() {
        return userRpRepository.findAll();
    }*/

    @GetMapping("/admin/user/all")
    public String showAllUsers(Model model) {
        List<Users> users = userRpRepository.findAll();
        model.addAttribute("users", users);
        return "redirect:/admin";
    }

    @GetMapping("/admin/event/all")
    List<Event> allEvent() {
        return eventRepository.findAll();
    }

  /*  @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Users user = userRepositoryUserLong.findById(id)
                                  .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepositoryUserLong.delete(user);
        model.addAttribute("usersForm", userRepositoryUserLong.findAll());
        return "index";
    }*/




    @RequestMapping(value = "/admin/team/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Team getTeamById(@PathVariable long id) {
        return teamRepository.findById(id)
                             .orElseThrow(() -> new NoSuchTeamException("Team not found"));
    }

    @RequestMapping(value = "/admin/team/{id}/event", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Event> getEventByTeam(@PathVariable long id) throws NoSuchTeamException {
        Team team = teamRepository.findById(id)
                                  .orElseThrow(() -> new NoSuchTeamException("Team not found"));
        List<Event> events = eventRepository.getEventByTeam(team);
        return events;

    }

    @RequestMapping(value = "/admin/team/{id}/user", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Users> getUserByTeam(@PathVariable long id) {
        Team team = teamRepository.findById(id)
                                  .orElseThrow(() -> new NoSuchTeamException("Team not found"));
        List<Users> users = userRpRepository.getUsersByTeam(team);
        return users;
    }

    @RequestMapping(value = "/admin/team", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addTeam(Model model, @ModelAttribute("teamForm") @Validated TeamForm teamForm,
            BindingResult result, final RedirectAttributes redirectAttributes) {
        System.out.println("addTeam invoked");
        if (result.hasErrors()) {
            return "/admin";
        }
        Team newTeam = new Team();
        newTeam.setName(teamForm.getName());
        newTeam.setUrl(teamForm.getUrl());
        teamRepository.save(newTeam);
        return "teamList";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser( String email ,Model model,@ModelAttribute("usersForm") @Validated UsersForm usersForm,
            BindingResult result, final RedirectAttributes redirectAttributes) {
        System.out.println("deleteUser invoked");
        Users user = userRpRepository.findById(email).orElseThrow(() -> new IllegalArgumentException("Invalid user email:" + email));
        userRpRepository.delete(user);
        model.addAttribute("users", userRpRepository.findAll());
        return "userList";
    }

    @RequestMapping(value = "/admin/user" , method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("usersForm", userRpRepository.findAll());
        return "userList";
    }

    @RequestMapping(value = "/admin/team" , method = RequestMethod.GET)
    public String teamList(Model model) {
        model.addAttribute("teamForm", teamRepository.findAll());
        return "teamList";
    }

    @RequestMapping(value = "/admin/event" , method = RequestMethod.GET)
    public String eventList(Model model) {
        model.addAttribute("eventForm",eventRepository.findAll());
        return "eventList";
    }


    @RequestMapping(value = "/admin/event", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String addEvent(Model model, @ModelAttribute("eventForm") @Validated EventForm eventForm,
            BindingResult result, final RedirectAttributes redirectAttributes) {
        System.out.println("addEvent invoked");
       /* if (result.hasErrors()) {
       model.addAttribute("userForm", userForm);
            return "/admin";
        }*/
        Event newEvent = new Event();
        newEvent.setName(eventForm.getName());
        newEvent.setBeginDate(eventForm.getBeginDate());
        eventRepository.save(newEvent);
        return "eventList";
    }

/*    @RequestMapping(value = "/admin/team/{id}/user/{email}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Users addUserToTeam(@PathVariable long id, @PathVariable String email) {
        System.out.println("addUserToTeam invoked");
        Team team = teamRepository.findById(id)
                                  .orElseThrow(() -> new NoSuchTeamException("Team not found"));
        Users user = userRpRepository.findById(email)
                                     .orElseThrow(() -> new NoSuchUserException("User not found"));
        user.setTeam(team);
        user = userRpRepository.save(user);
        return user;
    }*/

    @RequestMapping(value = "/admin/team/{id}/user/{email}", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String addUserToTeam(@PathVariable long id, @PathVariable String email) {
        System.out.println("addUserToTeam invoked");
        Team team = teamRepository.findById(id)
                                  .orElseThrow(() -> new NoSuchTeamException("Team not found"));
        Users user = userRpRepository.findById(email)
                                     .orElseThrow(() -> new NoSuchUserException("User not found"));
        user.setTeam(team);
        userRpRepository.save(user);
        return "userTeam";
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("teamForm",new TeamForm());
        model.addAttribute("eventForm",new EventForm());
        model.addAttribute("usersForm",new UsersForm());
        model.addAttribute("userTeamForm",new UserTeamForm());
        model.addAttribute("users",new Users());

        return "admin";
    }

}



/*    //    @RequestMapping(value = "/admin/team", method = RequestMethod.POST,
    //            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    //    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
    //        Team newTeam = new Team();
    //        newTeam.setName(team.getName());
    //        newTeam.setUrl(team.getUrl());
    //        newTeam.setAvatar(team.getAvatar());
    //        newTeam = teamRepository.save(newTeam);
    //        return new ResponseEntity<Team>(newTeam, HttpStatus.OK);
    //    }*/


 /*   @RequestMapping(value = "/admin/addTeam", method = RequestMethod.POST)//"/admin/team"//
    public String addTeam(@RequestBody Team team) {
        Team newTeam = new Team();
        newTeam.setName(team.getName());
        newTeam.setUrl(team.getUrl());
        newTeam.setAvatar(team.getAvatar());
        teamRepository.save(newTeam);
        return "redirect:/teamList";
    }

    @RequestMapping(value = { "/admin/addTeam" }, method = RequestMethod.GET)//
    public String showAddTeamPage(Model model) {
        TeamForm teamForm = new TeamForm();
        model.addAttribute("teamForm", teamForm);
        return "addTeam";
    }*/
/*
 @RequestMapping(value = "/admin/team/{id}/event", method = RequestMethod.POST,
         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
 public Event addEvent(@PathVariable long id, @RequestBody Event event) {
     Team team = teamRepository.findById(id)
                               .orElseThrow(() -> new NoSuchTeamException("Team not found"));
     Event newEvent = new Event();
     newEvent.setName(event.getName());
     newEvent.setBeginDate(event.getBeginDate());
     newEvent.setTeam(team);
     newEvent = eventRepository.save(newEvent);
     return newEvent;

 }*/
