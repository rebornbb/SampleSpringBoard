package com.sample.spring;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	//INSERT화면
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("book/create");
	}
	
	//INSERT
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam Map<String, Object> map) {
		 ModelAndView mav = new ModelAndView();
		 
		 String bookId = this.bookService.create(map);//{title=처음배우는스프링레거시, category=IT, price=20000} 들어가고
		 
		 if (bookId == null) {//쿼리문실행이잘안되서 1이아니라 null이면 
		        mav.setViewName("redirect:/create");
		    }else {
		        mav.setViewName("redirect:/detail?bookId=" + bookId); 
		    }  
		
		return mav;
	}
	
	//SELECT
	//@RequestParam 어노테이션에 의해 쿼리 스트링 파라미터를 읽을 수 있다. 
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(@RequestParam Map<String, Object> map) {//bookId를 받는다
	    Map<String, Object> detailMap = this.bookService.detail(map); //id를받아서 select한결과를 반환

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("data", detailMap);
	    String bookId = map.get("bookId").toString();
	    mav.addObject("bookId", bookId);
	    mav.setViewName("/book/detail");
	    return mav;
	}
	
	//UPDATE화면
	@RequestMapping(value = "/update", method = RequestMethod.GET)  
	public ModelAndView update(@RequestParam Map<String, Object> map) {  
		Map<String, Object> detailMap = this.bookService.detail(map);  //상세정보가져오기
	
		ModelAndView mav = new ModelAndView();  
		mav.addObject("data", detailMap);  
		mav.setViewName("/book/update");  
		return mav;  
	}  
	
	//UPDATE
	@RequestMapping(value = "update", method = RequestMethod.POST)  
	public ModelAndView updatePost(@RequestParam Map<String, Object> map) {  
		ModelAndView mav = new ModelAndView();  
	
		boolean isUpdateSuccess = this.bookService.edit(map); //수정결과를 리턴받는다
		
		if (isUpdateSuccess) {  //수정이잘되었으면
			String bookId = map.get("bookId").toString();  
			mav.setViewName("redirect:/detail?bookId=" + bookId);  //새로고침
		}else {  
			mav = this.update(map);  //만약 갱신이 안 되었을 경우 GET 메소드로 리다이렉트하는 방법도 있겠지만, 갱신 화면을 바로 다시 보여줄 수도 있다.
		}  
	
		return mav;  
	}  
	
	//DELETE
	@RequestMapping(value = "/delete", method = RequestMethod.POST)  
	public ModelAndView deletePost(@RequestParam Map<String, Object> map) {  
		ModelAndView mav = new ModelAndView();  
	
		boolean isDeleteSuccess = this.bookService.remove(map); //삭제결과를 리턴받는다
		if (isDeleteSuccess) {  //삭제가 성공했으면 상세 페이지가 없으므로 목록으로 리다이렉트한다.
			mav.setViewName("redirect:/list");  
		}else {  
			String bookId = map.get("bookId").toString();  
			mav.setViewName("redirect:/detail?bookId=" + bookId);  //삭제가 실패할시 다시 상세페이지로
		}  
	
		return mav;  
	}  
	
	//SELECTLIST
	@RequestMapping(value = "list")  
	public ModelAndView list(@RequestParam Map<String, Object> map) {  
	
		List<Map<String, Object>> list = this.bookService.list(map); //책 목록을 데이터베이스에서 가지고 온다.
	
		ModelAndView mav = new ModelAndView();  
		mav.addObject("data", list); 
		
		//목록 페이지에는 keyword HTTP 파라미터가 있을 수도 있고, 없을 수도 있다. 따라서 파라미터가 있는 지 검사한다.
		if (map.containsKey("keyword")) {  
			//파라미터가 있다면 뷰에 keyword를 전달한다.
			mav.addObject("keyword", map.get("keyword"));  
			}  
		
		mav.setViewName("/book/list");  
		return mav;  
	} 

}
