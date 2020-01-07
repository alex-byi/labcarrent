package by.htp.jd2.command.impl.action;

import by.htp.jd2.entity.User;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Search user by login or by half login
 *
 * @author alexey
 */
@RestController
public class UserSearchCommand {

    private static final Logger LOG = LogManager.getLogger(UserSearchCommand.class.getName());
    private static final String ERROR = "SEARCH USER ERROR";
    private static final String DEBUG = "SEARCH USER command";

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/admin/userSearch", method = RequestMethod.POST)
    public ModelAndView userSearchCommand(@RequestParam String searchLogin) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            List<User> list = userService.searchUser(searchLogin);
            modelAndView.addObject("searchedUsers", list);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        modelAndView.setViewName("/admin/control_users");
        return modelAndView;
    }
}
