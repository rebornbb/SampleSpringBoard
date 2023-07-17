package com.sample.spring;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//매퍼 XML을 실행시키는 DAO(Data Access Object) 클래스를 생성
@Repository
public class BookDao {
	 @Autowired
	 SqlSessionTemplate sqlSessionTemplate;
	 
	 //책 데이터 입력 쿼리를 실행하는 DAO 메소드를 만든다.
	 public int insert(Map<String, Object> map) {
		 return this.sqlSessionTemplate.insert("book.insert", map);
	 }
	 
	 public Map<String, Object> selectDetail(Map<String, Object> map) {
	     return this.sqlSessionTemplate.selectOne("book.select_detail", map);
	 }
	 
	 public int update(Map<String, Object> map) {  
		 return this.sqlSessionTemplate.update("book.update", map);  
	 }  
	 
	 public int delete(Map<String, Object> map) {  
		 return this.sqlSessionTemplate.delete("book.delete", map);  
	 }  
	 
	 //sqlSessionTemplate.selectList 메소드는 결과 집합 목록을 반환한다. 따라서 결과 집합 타입인 Map<String, Object>의 목록 List 타입으로 읽어들일 수 있다.
	 public List<Map<String, Object>> selectList(Map<String, Object> map) {  
	 return this.sqlSessionTemplate.selectList("book.select_list", map);  
	 }  
}

