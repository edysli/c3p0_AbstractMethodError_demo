package ed.demo.c3p0;

import static org.junit.Assert.*;

import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = ApplicationConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class IntegrationTest {

	@Inject
	private DemoEntityDAO dao;

	@Test
	public void createEntityShouldNotThrowException() {
		DemoEntity demoEntity = new DemoEntity();
		demoEntity.setId(0);
		demoEntity.setLastModifiedDate(new Date());
		dao.createDemoEntityInAllSchemas(demoEntity);
	}
}
