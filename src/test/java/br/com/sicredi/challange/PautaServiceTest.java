package br.com.sicredi.challange;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.sicredi.challange.dto.PautaDTO;
import br.com.sicredi.challange.dto.NewPautaDTO;
import br.com.sicredi.challange.exception.BaseBusinessException;
import br.com.sicredi.challange.exception.NotFoundException;
import br.com.sicredi.challange.model.Pauta;
import br.com.sicredi.challange.repository.PautaRepository;
import br.com.sicredi.challange.service.PautaService;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceTest {

	@InjectMocks
	private PautaService service;

	@Mock
	private PautaRepository repository;

	@Test
	public void createSucess() {
		NewPautaDTO dto = new NewPautaDTO("Titulo", "Descrição");
		Pauta pauta = new Pauta(10l, "Titulo", "Descrição", null, null);
		PautaDTO verified = new PautaDTO(10l, "Titulo", "Descrição", null, null);

		when(repository.save(Mockito.any(Pauta.class))).thenReturn(pauta);

		PautaDTO returned = this.service.create(dto);
		assertEquals(returned, verified);
	}

	@Test
	public void openSession() {
		Optional<Pauta> pauta = Optional.ofNullable(new Pauta(12l, "Titulo", "Descrição", null, null));
		Pauta sessionOpened = new Pauta(12l, "Titulo", "Descrição", LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(5l));
		PautaDTO verified = new PautaDTO(12l, "Titulo", "Descrição", sessionOpened.getDtInicioVotacao(),
				sessionOpened.getDtFimVotacao());

		when(repository.findById(Mockito.any(Long.class))).thenReturn(pauta);
		when(repository.save(Mockito.any(Pauta.class))).thenReturn(sessionOpened);

		PautaDTO returned = this.service.openSession(12l, 5l);
		assertEquals(returned, verified);
	}

	@Test(expected = NotFoundException.class)
	public void openSessionNotFound() {
		when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
		this.service.openSession(12l, 5l);
	}
	
	@Test(expected = BaseBusinessException.class)
	public void openSessionAlreadyOpened() {
		Optional<Pauta> pauta = Optional.ofNullable(new Pauta(12l, "Titulo", "Descrição", LocalDateTime.now(), null));
		when(repository.findById(Mockito.any(Long.class))).thenReturn(pauta);
		this.service.openSession(12l, 5l);
	}

}
