package by.htp.jd2.command.impl.link;

import java.util.List;

import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * go to "Control users" page
 *
 * @author alexey
 */
@Controller
public class ControlUsersPageCommand {
    private static final Logger LOG = LogManager.getLogger(ControlUsersPageCommand.class.getName());
    private static final String ERROR = "go to Control users page error";
    private static final String DEBUG = "Go to User control page command";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/control_users")
    public ModelAndView controlUsers() {
        List<User> users = null;

        try {
            users = userService.getAllUsers();
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/control_users");
        modelAndView.addObject("allUsers", users);
        LOG.debug(DEBUG);
        return modelAndView;
    }
}
