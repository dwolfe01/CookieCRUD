package wolfesoftware.integration.controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.ModelAndView;

import wolfesoftware.controller.CookieClientController;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring/test-application-context.xml")
public class CookieClientControllerTest {

	@Resource
	CookieClientController cookieController;
	@Mock
	MockHttpServletRequest request;
	@Mock
	MockHttpServletResponse response;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Ignore
	public void shouldShowCookies() throws IOException {
		Cookie cookie = new Cookie("test", "value");
		request.setCookies(cookie);
		System.out.println(request.getCookies().length);
		ModelAndView modelAndView = cookieController.showCookies(request, response);
		assertEquals(true, modelAndView.getModel().containsKey(""));
	}
}
