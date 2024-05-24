package web.mvc.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class GlobalExceptionAdvice {

	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView error(NumberFormatException e) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("errMsg",e.getMessage());
		mv.addObject("errClass",e.getClass().getName());
		
		mv.setViewName("error/numberError"); //WEB-INF/views/number 
		
		
		return mv;
		
	}
	

	 
	
	
}
