package in.shantun.factory;

import in.shantun.services.IStudentService;
import in.shantun.services.StudentServiceImpl;


public class StudentServiceFactory {
	private static IStudentService studentServiceImpl = null;
	private StudentServiceFactory() {
		
	}
	public static IStudentService getStudentService() {
		if(studentServiceImpl == null) studentServiceImpl = new StudentServiceImpl();
		return studentServiceImpl;		
	}

}
