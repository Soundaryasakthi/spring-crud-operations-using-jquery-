package com.db.tis.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.db.tis.model.Student;



@Repository
public class StudentDAO extends BaseAbstractDao<Student>{

	@Autowired
	public StudentDAO(SessionFactory factory) {
		super(factory);
		// TODO Auto-generated constructor stub
	}
	String ident;
	public void saveStudent(Student stud){
		String qery="select max(id)+1 from STUDENT_DETAILS";
		Query qry=super.currentSession().createSQLQuery(qery);
		if(qry.list().get(0)==null) ident="1";
		else ident=qry.list().get(0).toString();
		String query="insert into STUDENT_DETAILS values("+ident+",'"+stud.getName()+"', '"+stud.getAddress()+"', '"+stud.getCreatedDate()+"', '"+stud.getCreatedUser()+"')";
		qry=super.currentSession().createSQLQuery(query);
		qry.executeUpdate();
	}
	
	
	public List<Student> get(){
		String query="select * from STUDENT_DETAILS";
		Query qry=super.currentSession().createSQLQuery(query);
		List<Student>  ls = qry.list();
		return ls;
	}
	
	public void updateStudent(Student stud){
		String query="update STUDENT_DETAILS set name='"+stud.getName()+"', address='"+stud.getAddress()+"', createdDate='"+stud.getCreatedDate()+"', createdUser='"+stud.getCreatedUser()+"' where id='"+stud.getId()+"'";
		Query qry=super.currentSession().createSQLQuery(query);
		qry.executeUpdate();
	}
	
	public void deleteStudent(String stud){
		String query="delete from STUDENT_DETAILS where id='"+stud+"'";
		Query qry=super.currentSession().createSQLQuery(query);
		qry.executeUpdate();
	}
	
	public List<Student> editStudent(String id){
		String query="select * from STUDENT_DETAILS where id="+id;
		Query qry=super.currentSession().createSQLQuery(query);
		return qry.list();
	}
}
