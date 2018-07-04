package com.db.tis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.tis.dao.StudentDAO;
import com.db.tis.model.Student;



@Service
@Transactional
public class StudentService {
	
		@Autowired
		StudentDAO rep;
		
	public void save(Student stud){
			
			rep.saveStudent(stud);
		}
		
		public List<Student> get(){
			try{
				return rep.get();
			}
			catch(Exception e){
				System.out.println(e);
			}
			return null;
		}
		
		public void updateStudent(Student stud){
			try{
			rep.updateStudent(stud);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
		public void deleteStudent(String stud){
			try{
			rep.deleteStudent(stud);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		public List<Student> editStudent(String id){
			try{
			return rep.editStudent(id);
			}
			catch(Exception e){
				System.out.println(e);
			}
			return null;
		}

}
