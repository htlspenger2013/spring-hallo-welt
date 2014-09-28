package at.spenger.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import at.spenger.spring.service.HelloWorldMessageProvider;
import at.spenger.spring.service.MessageProvider;
import at.spenger.spring.service.MessageRenderer;
import at.spenger.spring.service.StandardOutMessageRenderer;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application implements CommandLineRunner {

	// Logging is still configured in Spring boot!
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	@Override
	public void run(String... args) throws Exception {
		MessageRenderer mr = new StandardOutMessageRenderer();
		MessageProvider mp = new HelloWorldMessageProvider();
		mr.setMessageProvider(mp);
		mr.render();

	}

    public static void main(String[] args) {
    	logger.info("Hallo Logger!");
        
    	// Kurzversion mit context
    	// ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
    	// Langversion
    	SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        app.setLogStartupInfo(false);
        ApplicationContext ctx = app.run(args);
        
        // Ausgeben der Beans
        for (String bean : ctx.getBeanDefinitionNames()) {
       		String[] a = bean.split("\\.");
			System.out.printf("Bean: %s / %s %n", a[a.length-1], bean);
		}
        
	    // Langversion, mittels Fluent API
        /*
        new SpringApplicationBuilder(Application.class)
	    .showBanner(false)
	    .logStartupInfo(false)
	    .run(args);
	    */
    }
}
