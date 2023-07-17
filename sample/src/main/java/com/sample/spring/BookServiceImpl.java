package com.sample.spring;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
	
	 @Autowired
	 BookDao bookDao;
	 
	 //INSERT
	 @Override
	 public String create(Map<String, Object> map) {
		 int affectRowCount = this.bookDao.insert(map);//사용자가쓴값을 받아 insert문실행 
		 
		  if (affectRowCount ==  1) {//실행이 잘되면 1
		        return map.get("book_id").toString();//book_id는 자동으로 increase해서 붙는 값 
		    }
		 
		 return null;
	 }
	 
	 //SELECT
	 @Override
	 public Map<String, Object> detail(Map<String, Object> map){
	     return this.bookDao.selectDetail(map);
	 } //서비스는 DAO를 호출한 결과를 바로 리턴하는 일만 한다.
	 
	 //UPDATE
	 @Override  
	 public boolean edit(Map<String, Object> map) {  
		 int affectRowCount = this.bookDao.update(map);  
		 return affectRowCount == 1;  //실행이 잘되면 1 -> true //수정의 경우 1개의 행이 제대로 영향받았는지만 검사하면 된다.
	 }  
	 
	 //DELETE
	 @Override  
	 public boolean remove(Map<String, Object> map) {  
		 int affectRowCount = this.bookDao.delete(map);  
		 return affectRowCount == 1;  //실행이 잘되면 1 -> true //수정의 경우 1개의 행이 제대로 영향받았는지만 검사하면 된다.
	 }  
	 
	 //SELECTLIST
	 @Override  
	 public List<Map<String, Object>> list(Map<String, Object> map){  
	 return this.bookDao.selectList(map);  
	 }  
}
