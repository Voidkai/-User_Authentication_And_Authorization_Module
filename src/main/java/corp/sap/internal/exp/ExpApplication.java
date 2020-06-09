package corp.sap.internal.exp;

import corp.sap.internal.exp.listener.MyApplicationEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ExpApplication.class);
		app.addListeners(new MyApplicationEventListener());
		app.run(args);
	}

}
