package in.shantun.dao;

import in.shantun.dto.Student;

public interface IStudentDao {
	String save(Student student); // for create operation.
	Student findById(Integer sid); // for read operation.
	String updateById(Student student); // update.
	String deleteById(Integer sid); // delete.	
}
