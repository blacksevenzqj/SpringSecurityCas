package ass.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"ass.management.admin.modules", "ass.management.business"})
public class SchoolAdminApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SchoolAdminApplication.class, args);
	}

}
