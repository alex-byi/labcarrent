package by.htp.jd2.command.impl.action;

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

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class ChangePassword {


    private static final Logger LOG = LogManager.getLogger(ChangePassword.class.getName());
    private static final String DEBUG = "Change password";

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/admin/change_password", "/user/change_password"})
    public ModelAndView changePassword(@RequestParam int idUser, String oldPassword, String newPassword) throws ServiceException, NoSuchAlgorithmException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByLogin(authentication.getName());

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(oldPassword));
        String oldFormPassword = String.format("%032x", new BigInteger(1, md5.digest()));

        if (oldFormPassword.equals(user.getPassword())) {
            md5.update(StandardCharsets.UTF_8.encode(newPassword));
            String password = String.format("%032x", new BigInteger(1, md5.digest()));
            userService.changePassword(idUser, password);
        }

        if (user.getType().equals("ADMIN")) {
            LOG.debug(DEBUG);
            return new ModelAndView("redirect:/admin/user_page");
        } else {
            LOG.debug(DEBUG);
            return new ModelAndView("redirect:/user/user_page");
        }
    }
}
