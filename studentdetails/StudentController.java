package com.db.tis.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.db.tis.model.ClientDO;
import com.db.tis.model.DocumentResource;
import com.db.tis.model.LoginDO;
import com.db.tis.model.Student;
import com.db.tis.service.StudentService;
import com.db.tis.util.Constants;

@Controller
@RequestMapping(value = "/Documents/test")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@RequestMapping(value="/get/save", method=RequestMethod.POST)
	@ResponseBody public String save(@RequestBody Student stud){
		System.out.println("----------------------------");
//		Employee emp=new Employee();
//		emp.setName(map.get("name"));
//		emp.setAddress(map.get("address"));
//		emp.setCreatedDate(map.get("createdDate"));s
//		emp.setCreatedUser(map.get("createdUser"));
		service.save(stud);
		return "success";
	}
	
	@RequestMapping(value="/get/update", method=RequestMethod.POST)
	@ResponseBody public String update(@RequestBody Student stud){
		System.out.println("update");
		service.updateStudent(stud);
		return "success";
	}
	
	@RequestMapping(value="/get", method=RequestMethod.GET)
	@ResponseBody public List<Student> get(){
		return service.get();
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView loadCurrency(@ModelAttribute("loginSession")LoginDO login,Model model){
		ModelAndView view=new ModelAndView("master/documentview");
		LoginDO loginDO=Constants.loadUserRights(login, "Documents");
		List<ClientDO> clientDOs = new ArrayList<ClientDO>();
		if (login.getClientDOs() !=null && !login.getClientDOs().isEmpty()) {
			 clientDOs=Constants.loadClientDOs(login.getClientDOs());
		}
		if(loginDO!=null && loginDO.getMenuUrl()==null ){
			view=new ModelAndView("unauthorized");
			view.addObject("clientDOs", clientDOs);
			view.addObject("currenctCompId", loginDO.getCurrenctCompId());
			view.addObject("loginDO",loginDO);
		}else{			

			DocumentResource DocumentDO=new DocumentResource();
			DocumentDO.setFlag("Save");
			model.addAttribute("loginSession",loginDO);
			//If Session is invalid then redirect to login page
	        if(login.getSessionId() == null)
	            return new ModelAndView("login");
	        
			view.addObject("clientDOs", clientDOs);
			view.addObject("currenctCompId", loginDO.getCurrenctCompId());
			view.addObject("discrepantDO",DocumentDO);		
			view.addObject("loginDO",loginDO);
		}
		return view;
	}
	@RequestMapping(value="/get/delete", method=RequestMethod.POST)
	@ResponseBody public String delete(@RequestBody String stud){
		service.deleteStudent(stud);
		return "success";
	}
	
	@RequestMapping(value="/get/edit", method=RequestMethod.POST)
	@ResponseBody public List<Student> edit(@RequestBody String id){
		
		return service.editStudent(id);
	}
}
