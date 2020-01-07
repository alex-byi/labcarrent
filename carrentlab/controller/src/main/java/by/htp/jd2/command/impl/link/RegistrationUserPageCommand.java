package by.htp.jd2.command.impl.link;


import by.htp.jd2.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RegistrationUserPageCommand {

    @RequestMapping(value = "/common/registration_user", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("/common/registration_user");
        return modelAndView;
    }
}
