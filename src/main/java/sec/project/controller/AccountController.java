package sec.project.controller;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.repository.AccountRepository;

@Controller
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    public void init() {
        Account admin = new Account("admin", "admin");
        Account user = new Account("jack", "qwerty");
        accountRepository.save(admin);
        accountRepository.save(user);
    }

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String loadForm() {
        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String submitForm(Model model, @RequestParam String username, @RequestParam String password) {
        Account accountByName = accountRepository.findByUsername(username);
        Account accountByPassword = accountRepository.findByPassword(password);

        System.out.println(accountByName);
        if (accountByName == null) {
            model.addAttribute("error", "Username not found");
            return "user";
        } else if (accountByPassword == null) {
            model.addAttribute("error", "Password not found");
            return "user";
        } else {
            return "done";
        }
    }

}
