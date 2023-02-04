package in.shantun.services;

import in.shantun.dto.Student;
import in.shantun.dao.IStudentDao;
import in.shantun.factory.StudentDaoFactory;


public class StudentServiceImpl implements IStudentService {
	
	IStudentDao studentDao;

	@Override
	public String save(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.save(student);
	}

	@Override
	public Student findById(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.findById(sid);
	}

	@Override
	public String updateById(Student student) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.updateById(student);
	}

	@Override
	public String deleteById(Integer sid) {
		studentDao = StudentDaoFactory.getStudentDao();
		return studentDao.deleteById(sid);
	}

}
