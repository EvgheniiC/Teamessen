package eventim.teamessen.mvc;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventim.teamessen.form.UsersForm;
import eventim.teamessen.service.UsersService;
import eventim.teamessen.validator.UsersValidator;

@Controller
public class MainController {

    @Autowired
    private UsersValidator userValidator;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    ServletContext context;

    @Autowired
    UsersService service;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form target
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        System.out.println("Target=" + target);

        if (target.getClass() == UsersForm.class) {
            dataBinder.setValidator(userValidator);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "loginPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String page403(Model model) {
        model.addAttribute("title", "Logout");
        return "403";
    }

    @RequestMapping("/registerSuccessful")
    public String viewRegisterSuccessful(Model model) {
        return "/registerSuccessfulPage";
    }
    // Show Register page.

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model) {

        UsersForm form = new UsersForm();
        model.addAttribute("userForm", form);
        return "/registerPage";
    }

    // This method is called to save the registration information.
    // @Validated: To ensure that this Form
    // has been Validated before this method is invoked.


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
            @ModelAttribute("userForm") @Validated UsersForm userForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
        System.out.println(userForm.getAvatar());
        // Validate result
        if (result.hasErrors()) {
            model.addAttribute("userForm", userForm);
            return "registerPage";
        }
        String error = service.createUser(userForm.getEmail(), userForm.getName(), userForm.getPassword(),
                userForm.getAvatar());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("errorMessage", error);
            return "registerPage";
        }
        model.addAttribute("login", userForm.getEmail());
        return "registerSuccessPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        String uploadsDir = "/uploads/";

        String realPathtoUploads = request.getServletContext()
                                          .getRealPath(uploadsDir);
        if (!new File(realPathtoUploads).exists()) {
            new File(realPathtoUploads).mkdir();
        }

        String ext = getExtensionByStringHandling(file.getOriginalFilename()).get();
        String orgName = UUID.randomUUID()
                             .toString() + "." + ext;
        String filePath = realPathtoUploads + orgName;
        File dest = new File(filePath);

        if (!file.isEmpty()) {
            try {
                file.transferTo(dest);
                return new ResponseEntity<String>("/uploads/" + orgName, HttpStatus.OK);
            } catch(Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Error: Empty File", HttpStatus.BAD_REQUEST);
        }
    }

    private Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                       .filter(f -> f.contains("."))
                       .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
