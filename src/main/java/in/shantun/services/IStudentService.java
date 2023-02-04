package in.shantun.services;
import in.shantun.dto.Student;

public interface IStudentService {
	
	String save(Student student); // for create operation.
	Student findById(Integer sid); // for read operation.
	String updateById(Student student); // update.
	String deleteById(Integer sid); // delete.	

}
