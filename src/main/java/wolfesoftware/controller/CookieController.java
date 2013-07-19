package wolfesoftware.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import wolfesoftware.cookiecrud.CookieBakery;

@Controller
public class CookieController {
	
	private final CookieBakery bakery;

	@Autowired
	public CookieController(CookieBakery bakery){
		this.bakery = bakery;
	}
	
	@RequestMapping(value="/")
	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<String> cookies = new ArrayList<String>();
		for (Cookie cookie:request.getCookies()){
			cookies.add(getCookieAsString(cookie));
		}
		ModelAndView modelAndView = new ModelAndView("cookie");
		modelAndView.addObject("listOfCookies", cookies);
		return modelAndView;
	}

	private String getCookieAsString(Cookie cookie) {
		return "name:" + cookie.getName() + " value:" + cookie.getValue() + " age:" + cookie.getMaxAge() + " path:" + cookie.getPath() + " domain:" + cookie.getDomain();
	}
}
