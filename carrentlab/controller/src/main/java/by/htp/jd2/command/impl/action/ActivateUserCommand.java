package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.htp.jd2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * activate user by userID
 *
 * @author alexey
 */
@RestController
public class ActivateUserCommand {
    private static final Logger LOG = LogManager.getLogger(ActivateUserCommand.class.getName());
    private static final String ERROR = "User activate ERROR";
    private static final String DEBUG = "Activate user command";

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/admin/activateUser", method = RequestMethod.POST)
    public ModelAndView activateUser(@RequestParam int idUser) {
        try {
            userService.activateUser(idUser);
        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/control_users");
    }

}
