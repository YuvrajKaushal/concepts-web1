//package sec.project.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import sec.project.domain.User;
//import sec.project.repository.UserRepository;
//
//@Controller
//public class SignupController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @RequestMapping("*")
//    public String defaultMapping() {
//        return "redirect:/form";
//    }
//
//    @RequestMapping(value = "/form", method = RequestMethod.GET)
//    public String loadForm() {
//        return "form";
//    }
//
//    @RequestMapping(value = "/user", method = RequestMethod.POST)
//    public String submitForm(@RequestParam String username, @RequestParam String password) {
//        userRepository.save(new User(username, password));
//        return "done";
//    }
//
//}
