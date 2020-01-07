package by.htp.jd2.command.impl.action;

import by.htp.jd2.command.impl.link.UserPageCommand;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ChangeEmail {

    private static final Logger LOG = LogManager.getLogger(ChangeEmail.class.getName());
    private static final String DEBUG = "Change email";

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin/change_email", "/user/change_email"})
    public ModelAndView changeEmail(@RequestParam int idUser, String email) throws ServiceException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByLogin(authentication.getName());

        userService.changeEmail(idUser, email);

        if (user.getType().equals("ADMIN")) {
            LOG.debug(DEBUG);
            return new ModelAndView("redirect:/admin/user_page");
        } else {
            LOG.debug(DEBUG);
            return new ModelAndView("redirect:/user/user_page");
        }
    }
}
