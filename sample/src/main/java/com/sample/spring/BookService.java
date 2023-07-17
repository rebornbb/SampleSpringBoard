package com.sample.spring;

import java.util.List;
import java.util.Map;

public interface BookService {

	String create(Map<String, Object> map);	//INSERT

	Map<String, Object> detail(Map<String, Object> map); //SELECT

	boolean edit(Map<String, Object> map); //UPDATE

	boolean remove(Map<String, Object> map); //DELETE

	List<Map<String, Object>> list(Map<String, Object> map); //SELECTLIST


}
