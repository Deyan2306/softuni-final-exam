package bg.softuni.movieapp.web;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class TVSeriesControllerTest {

    @Test
    public void testGetPage() throws Exception {

        TVSeriesController controller = new TVSeriesController();

        ModelAndView modelAndView = controller.tvSeriesHome();

        assertEquals("not-constructed", modelAndView.getViewName());
    }
}