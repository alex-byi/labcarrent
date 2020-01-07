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
 * add money to user by ADMIN
 *
 * @author alexey
 */
@RestController
public class AddMoneyCommand {
    private static final Logger LOG = LogManager.getLogger(AddMoneyCommand.class.getName());
    private static final String ERROR = "ADD money ERROR";
    private static final String DEBUG = "Add money command";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/addMoneyAdmin", method = RequestMethod.POST)
    public ModelAndView addMoneyAdmin(@RequestParam  int idUser, int moneyCol) {
        try {
            userService.addMoney(moneyCol, idUser);

        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/admin/control_users");
    }

}
