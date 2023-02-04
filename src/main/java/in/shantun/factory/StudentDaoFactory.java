package in.shantun.factory;

import in.shantun.dao.IStudentDao;
import in.shantun.dao.StudentDaoImpl;

public class StudentDaoFactory {
	private static IStudentDao studentDao;
	
	private StudentDaoFactory() {
		
	}
	
	public static IStudentDao getStudentDao() {
		if(studentDao == null) studentDao = new StudentDaoImpl();
		return studentDao;
	}
}
