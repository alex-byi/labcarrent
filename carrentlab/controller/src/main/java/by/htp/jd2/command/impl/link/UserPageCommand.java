package by.htp.jd2.command.impl.link;


import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to "User page"
 *
 * @author alexey
 */

@RestController
public class UserPageCommand {

    @Autowired
    private UserService userService;

    private static final Logger LOG = LogManager.getLogger(UserPageCommand.class.getName());
    private static final String DEBUG = "Go to user page";

    @RequestMapping(value = {"/admin/user_page", "/user/user_page"})
    public ModelAndView userPageAdmin() throws ServiceException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByLogin(authentication.getName());
        ModelAndView modelAndView = new ModelAndView();
        if (user.getType().equals("ADMIN")) {
            modelAndView.setViewName("admin/user_page");
        } else if (user.getType().equals("USER")){
            modelAndView.setViewName("user/user_page");
        }
        modelAndView.addObject("user", user);
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
