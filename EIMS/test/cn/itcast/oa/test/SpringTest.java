package cn.itcast.oa.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	private ApplicationContext ac = new ClassPathXmlApplicationContext(
			"applicationContext.xml");

	@Test
	public void testBean() {
		TestAction ta = (TestAction) ac.getBean("testAction");
		System.out.println(ta);
	}
	@Test
	public void testSessionFactory() {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	@Test
	public void testTransaction() {
		TestService testService = (TestService) ac.getBean("testService");
		//testService.saveTwoUser();
		testService.saveMoreUser();
	}
}
