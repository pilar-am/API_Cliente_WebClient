package cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;




@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flors API amb WebClient", version = "1.0", description = "Consumint una API externa amb WebClient"))
public class S05T01N03MorenoPerezPilarApplication {


	
	public static void main(String[] args) {
		SpringApplication.run(S05T01N03MorenoPerezPilarApplication.class, args);
		
	}

	//http://localhost:9002/v3/api-docs  para el json de swagger
	//http://localhost:9002/swagger-ui/index.html  para la interfaz
	
}
