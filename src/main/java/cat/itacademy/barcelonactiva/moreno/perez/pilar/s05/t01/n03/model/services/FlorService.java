package cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03.model.services;

import java.time.Duration;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03.model.domain.FlorEntity;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03.model.dto.FlorDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class FlorService {

	private final WebClient webClient;

	public FlorService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder
				.baseUrl("http://localhost:9001/flor")
				.build();
	}
		
	public Flux<FlorDTO> getAllFlors(){
		return this.webClient.get().uri("/getAll")
				.retrieve()
				.bodyToFlux(FlorDTO.class);
	}
	
	public Mono<FlorDTO> getFlorById(Integer id){
		return this.webClient.get().uri("/getOne/{id}",id)
				.retrieve()
				.bodyToMono(FlorDTO.class);
	}

	public Mono<FlorEntity> saveFlor(FlorDTO florDTO){
		FlorEntity florEntity = new FlorEntity();
		
		florEntity.setNomFlor(florDTO.getNomFlor());
		florEntity.setPaisFlor(florDTO.getPaisFlor());
		System.out.println("SErvice: " + florEntity);
		return this.webClient.post().uri("/add")
				.body(Mono.just(florEntity), FlorEntity.class)
				.retrieve()
				.bodyToMono(FlorEntity.class)
				.timeout(Duration.ofMillis(10_000));
	}
	
	public Mono<FlorDTO> updateFlor(FlorDTO florDTO){
		return webClient.put()
				.uri("/update/" + florDTO.getPk_FlorID())
				.body(Mono.just(florDTO), FlorDTO.class)
				.retrieve()
				.bodyToMono(FlorDTO.class);
	}
	
	public Mono<Void> deleteFlor(Integer id){
		return this.webClient.delete().uri("/delete/{id}",id)
				.retrieve()
				.bodyToMono(Void.class);
	}
		
	
	
	
	
}
