package cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03.model.domain.FlorEntity;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03.model.dto.FlorDTO;
import cat.itacademy.barcelonactiva.moreno.perez.pilar.s05.t01.n03.model.services.FlorService;
import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/flor")
public class FlorController {

	@Autowired
	FlorService florService;
	
	@Operation(summary = "Retorna una flor segons el seu id")
	@GetMapping("/clientFlorsGetOne/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Mono<FlorDTO> getFlorById(@PathVariable Integer id){
		return florService.getFlorById(id);
	}
	
	@Operation(summary = "Retorna totes les flors")
	@GetMapping(value = "/clientFlorsAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Flux<FlorDTO> getAllFlors(){
		try {
			return florService.getAllFlors();
		}catch(WebClientRequestException e) {
			System.out.println(e);
		}
		return null;	
	}
	
	@Operation(summary = "Guarda una flor a la base de dades")
	@PostMapping("/clientFlorsAdd")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<FlorEntity> saveFlor(@RequestBody FlorDTO florDTO) {
		System.out.println(florDTO);
		return florService.saveFlor(florDTO);
	}
		
	@Operation(summary = "Actualitza una flor segons el seu id")
	@PutMapping("/clientFlorsUpdate/{id}")
	@ResponseStatus(HttpStatus.OK)
    public Mono<FlorDTO> updateFlor(@RequestBody FlorDTO florDTO, @PathVariable("id") Integer id) {
    	florDTO.setPk_FlorID(id);
        return florService.updateFlor(florDTO);
    }
	
	@Operation(summary = "Elimina una flor de la base de dades")
	@DeleteMapping("/clientFlorsDelete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteFlor(@PathVariable Integer id){
		return florService.deleteFlor(id);
	}
	
}
