package springBootTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springBootTest.model.User;
import springBootTest.repos.UserRepo;

import java.util.List;
import java.util.Map;


@Controller
public class HelloController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/")
    public String main(Map<String, Object> model) {
        return "login";
    }

    @GetMapping("/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @GetMapping("/add")
    public String add(Map<String, Object> model) {
        return "add";
    }


    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam String email,
                      @RequestParam String password,
                      @RequestParam String passwordCheck,
                      Map<String, Object> model) {
        if(password!=null&&password.equals(passwordCheck)&&passwordCheck.equals(password)){
            User user = new User(name, email, password);
            userRepo.save(user);
        }
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        return "add";
    }

    @PostMapping("/login")
    public String filter(@RequestParam String email,
                         @RequestParam String password,
                         Map<String, Object> model) {
        Iterable<User> users = null;

        if (email != null && !email.isEmpty()) {
            if (!userRepo.findUserByEmail(email).isEmpty()) {
                users = userRepo.findUserByEmail(email);
                if(((List<User>) users).get(0).getPassword().equals(password)){
                    return "content";
                } else return "loginUser";
            }
        }
        model.put("users", users);
        return "login";
    }


}