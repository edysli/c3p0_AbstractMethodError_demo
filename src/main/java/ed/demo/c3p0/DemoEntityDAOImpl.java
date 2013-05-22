package ed.demo.c3p0;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DemoEntityDAOImpl implements DemoEntityDAO {

	@Inject
	private SessionFactory sessionFactory;

	public DemoEntityDAOImpl() {
	}

	public DemoEntityDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private void setSchema(final String schemaName) {
		getSession().doWork(new Work() {
		    @Override
		    public void execute(Connection connection) throws SQLException {
		    	connection.setSchema(schemaName);
		    }
		});
	}

	@Override
	public void createDemoEntityInAllSchemas(DemoEntity entityToWrite) {
		setSchema("Demo1");
		getSession().save(entityToWrite);
		setSchema("Demo2");
		getSession().save(entityToWrite);
	}
}
