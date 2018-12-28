import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.app.dao.ReimbursementDao;
import com.app.dao.UserDao;
import com.app.pojo.Reimbursement;
import com.app.pojo.User;

public class ReimbursementAppTest {

	public static ReimbursementDao reimbDao = new ReimbursementDao();
	public static UserDao userDao = new UserDao();

	@Test
	public void findAllReimbDaoTest() {
		List<Reimbursement> r = reimbDao.findAll();
		assertNotNull(r);
	}

	@Test
	public void findReimbDaoByIdTest() {
		List<Reimbursement> r = reimbDao.findByUser(1);
		assertNotNull(r);
	}

	@Test
	public void findAllUserDaoTest() {
		List<User> u = userDao.findAll();
		assertNotNull(u);
	}

	@Test
	public void findUserByIdTest() {
		User u = userDao.findById(21);
		assertNotNull(u);
	}
}
