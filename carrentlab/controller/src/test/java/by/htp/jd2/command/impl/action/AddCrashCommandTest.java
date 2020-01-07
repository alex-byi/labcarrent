package by.htp.jd2.command.impl.action;

import by.htp.jd2.service.CrashService;
import by.htp.jd2.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddCrashCommandTest {

    @Mock
    CrashService crashService;

    @Mock
    OrderService orderService;

    @InjectMocks
    AddCrashCommand addCrashCommand;

    @Test
    public void addCarCommand() {
        ModelAndView modelAndView = addCrashCommand.addCrashCommand("test",
                100, 1, 1, 1);
        Assert.assertEquals(modelAndView.getViewName(), "redirect:/admin/crash_page");
    }
}