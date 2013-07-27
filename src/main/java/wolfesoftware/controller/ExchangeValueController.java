package wolfesoftware.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//http://localhost:9090/showvalue?cookieName=chocco&key=tamsyn&value=pumpkin
//http://localhost:9090/getvalue/tamsyn

@Controller
public class ExchangeValueController {

	String defaultCookieName = "chocco";
	String cookieCrudController = "http://localhost:9090";
	String me = "http://localhost:1010";
	

	@RequestMapping(value = "/sharevalue/{key}/{value}")
	public void addValue(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String key,
			@PathVariable String value) throws IOException {
		// TODO use String format
		response.sendRedirect(cookieCrudController + "/addcookie/"
				+ defaultCookieName + "/" + key + "/" + value
				+ "?redirectTo=" + me +"/showvalue");
	}

	@RequestMapping(value = "/getvalue/{key}")
	public void getValue(HttpServletRequest request,
			HttpServletResponse response, @PathVariable String key)
			throws IOException {
		response.sendRedirect(cookieCrudController + "/getvaluefromcookie/"
				+ defaultCookieName + "/" + key
				+ "?redirectTo=" + me +"/showvalue");
	}

	@RequestMapping(value = "/showvalue")
	public ModelAndView showValue(HttpServletRequest request,
			@RequestParam(defaultValue = "cookieName") String cookieName,
			@RequestParam(defaultValue = "key") String key,
			@RequestParam(defaultValue = "value") String value)
			throws IOException {
		ModelAndView modelAndView = new ModelAndView("showvalue");
		modelAndView.addObject("cookieName", cookieName);
		modelAndView.addObject("key", key);
		modelAndView.addObject("value", value);
		return modelAndView;
	}

}
