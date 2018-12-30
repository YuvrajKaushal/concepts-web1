package sec.project.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private HttpSession session;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Account admin = new Account("admin", "admin");
        Account user = new Account("jabba", "tatooine");
        accountRepository.save(admin);
        accountRepository.save(user);
    }

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String index() {
        return "user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String login(Model model, @RequestParam String username, @RequestParam String password) {
        Account accountByName = accountRepository.findByUsername(username);
        Account accountByPassword = accountRepository.findByPassword(password);

        if (accountByName == null) {
            model.addAttribute("error", "Username not found");
            return "user";
        } else if (accountByPassword == null) {
            model.addAttribute("error", "Password not found");
            return "user";
        } else {
            session.setAttribute("user", username);
            return "redirect:/messages";
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @RequestParam String username, @RequestParam String password) {
        Account accountByName = accountRepository.findByUsername(username);

        if (accountByName != null) {
            model.addAttribute("createError", "Username already exists. Please use another.");
            return "user";
        } else {
            Account account = new Account(username, password);
            accountRepository.save(account);
            return "redirect:/user";
        }
    }

}
