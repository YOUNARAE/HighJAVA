package kr.or.ddit.basic;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener, ServletContextAttributeListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener]" + "contextDestroyed() 호출됨.");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("[MyServletContextListener]" + "contextInitialized() 호출됨.");
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		System.out.println("[MyServletContextListener]" + "attributeAdded() 호출됨." + event.getName() + " = " + event.getValue());
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println("[MyServletContextListener]" + "attributeRemoved() 호출됨." + event.getName() + " = " + event.getValue());
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println("[MyServletContextListener]" + "attributeReplaced() 호출됨." + event.getName() + " = " + event.getValue());
	}

}
