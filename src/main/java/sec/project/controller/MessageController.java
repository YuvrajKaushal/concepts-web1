package sec.project.controller;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Message;
import sec.project.repository.MessageRepository;

@Controller
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private HttpSession session;

    @PostConstruct
    public void init() {
        Message first = new Message("admin", "First to post. Glorious!");
        Message second = new Message("admin", "Next there will be bans.");
        messageRepository.save(first);
        messageRepository.save(second);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String showMessages(Model model) {
        List<Message> allMessages = messageRepository.findAll();
        model.addAttribute("messages", allMessages);
        return "messages";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String submitForm(@RequestParam String content) {
        String currentUser = (String) session.getAttribute("user");
        Message message = new Message(currentUser, content);
        messageRepository.save(message);
        return "redirect:/messages";
    }

}
