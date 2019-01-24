package br.com.sicredi.challange.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sicredi.challange.dto.NewPautaDTO;
import br.com.sicredi.challange.dto.NewVotoDTO;
import br.com.sicredi.challange.dto.PautaDTO;
import br.com.sicredi.challange.dto.ResultDTO;
import br.com.sicredi.challange.service.PautaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/pauta")
@Api(value = "Pauta", description = "REST para interação com a pauta (Criação, abertura de sessão, votação e obtenção de resultado)")
public class PautaController {

	@Autowired
	private PautaService service;

	@PostMapping("/criar")
	@ApiOperation(value = "Criar uma nova pauta", response = PautaDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pauta criada com sucesso.") })
	public ResponseEntity<PautaDTO> create(@Valid @ApiParam(value = "Pauta") @RequestBody NewPautaDTO newPautaDTO) {
		return new ResponseEntity<PautaDTO>(this.service.create(newPautaDTO), HttpStatus.CREATED);
	}

	@PatchMapping("/{id}/sessao")
	@ApiOperation(value = "Abrir uma sessão de votação em uma pauta, com duração default de 1 minuto", response = PautaDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sessão de votação da pauta aberta com sucesso."),
			@ApiResponse(code = 404, message = "Pauta inexistente."),
			@ApiResponse(code = 409, message = "Sessão de votação da pauta em andamento ou encerrada.") })
	public ResponseEntity<PautaDTO> openSession(
			@ApiParam(value = "Identificador da pauta", example = "8") @PathVariable Long id) {
		return ResponseEntity.ok(service.openSession(id, 1l));
	}

	@PatchMapping("/{id}/sessao/{duration}")
	@ApiOperation(value = "Abrir uma sessão de votação em uma pauta, com duração especificada", response = PautaDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sessão de votação da pauta aberta com sucesso."),
			@ApiResponse(code = 404, message = "Pauta inexistente."),
			@ApiResponse(code = 409, message = "Sessão de votação da pauta em andamento ou encerrada.") })
	public ResponseEntity<PautaDTO> openSession(
			@ApiParam(value = "Identificador da pauta", example = "8") @PathVariable Long id,
			@ApiParam(value = "Duração em minutos", example = "5") @PathVariable Long duration) {
		return ResponseEntity.ok(service.openSession(id, duration));
	}

	@PostMapping("/votar")
	@ApiOperation(value = "Realizar um voto", response = NewVotoDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Voto na pauta realizado com sucesso."),
			@ApiResponse(code = 404, message = "Pauta inexistente."),
			@ApiResponse(code = 409, message = "Sessão de votação da pauta não iniciada ou encerrada.") })
	public ResponseEntity<NewVotoDTO> create(@Valid @ApiParam(value = "Voto") @RequestBody NewVotoDTO newVotoDTO) {
		return new ResponseEntity<NewVotoDTO>(this.service.vote(newVotoDTO), HttpStatus.CREATED);
	}

	@GetMapping("/{id}/resultado")
	@ApiOperation(value = "Resultado da votação da pauta", response = ResultDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Resultado da votação na pauta obtido com sucesso."),
			@ApiResponse(code = 404, message = "Pauta inexistente.") })
	public ResponseEntity<ResultDTO> result(
			@ApiParam(value = "Identificador da pauta", example = "8") @PathVariable Long id) {
		return ResponseEntity.ok(service.result(id));
	}
}
