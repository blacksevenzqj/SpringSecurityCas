package ass.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan({"ass.management.admin.modules", "ass.management.business"})
public class SchoolAdminApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SchoolAdminApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolAdminApplication.class, args);
	}

}
