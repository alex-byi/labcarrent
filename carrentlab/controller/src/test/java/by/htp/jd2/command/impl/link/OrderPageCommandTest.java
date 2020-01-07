package by.htp.jd2.command.impl.link;

import by.htp.jd2.config.AppConfig;
import by.htp.jd2.entity.Order;
import by.htp.jd2.service.OrderService;
import by.htp.jd2.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class OrderPageCommandTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserService userService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Autowired
    private OrderService orderService;

    @Test
    public void orderPage() throws Exception {
        List<Order> orders = orderService.getAllOrders();
                this.mockMvc.perform(get("/user/order_page")).andDo(print())
                .andExpect(view().name("user/order_page"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}