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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * add money to user by USER
 *
 * @author alexey
 */
@RestController
public class AddMoneyUserCommand {
    private static final Logger LOG = LogManager.getLogger(AddMoneyUserCommand.class.getName());
    private static final String ERROR = "ADD money by USER ERROR";
    private static final String DEBUG = "Add money by USER command";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/addMoneyUser", method = RequestMethod.POST)
    public ModelAndView addMoneyUser(@RequestParam int idUser, int moneyCol, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        try {
            userService.addMoney(moneyCol, idUser);

        } catch (ServiceException e) {
            LOG.error(ERROR, e);
        }
        user.setCash(user.getCash() + moneyCol);
        session.setAttribute("user", user);
        LOG.debug(DEBUG);
        return new ModelAndView("redirect:/user/orders");
    }
}
