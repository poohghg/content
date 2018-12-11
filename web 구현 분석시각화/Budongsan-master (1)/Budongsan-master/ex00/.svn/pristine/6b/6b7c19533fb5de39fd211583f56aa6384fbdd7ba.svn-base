package org.zerock.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	private ModelAndView errorModelAndView(Exception e) {
		// ModelAndView -> jsp 로 전달할 데이터(Model) 보여줄 jsp에 대한 정보
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/error_common"); // jsp 정보 셋팅
		modelAndView.addObject("exception", e);
		return modelAndView;
	}
	
}
