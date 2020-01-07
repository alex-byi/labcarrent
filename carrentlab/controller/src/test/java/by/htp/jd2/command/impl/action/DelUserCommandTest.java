package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DelUserCommandTest {
    @Mock
    UserService userService;

    @InjectMocks
    DelUserCommand delUserCommand;

    @Test
    public void delUser() {
        ModelAndView modelAndView = delUserCommand.delUser(1);
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/admin/control_users");
    }
}