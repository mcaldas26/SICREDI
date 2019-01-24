package br.com.sicredi.challange.service;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sicredi.challange.dto.NewPautaDTO;
import br.com.sicredi.challange.dto.NewVotoDTO;
import br.com.sicredi.challange.dto.PautaDTO;
import br.com.sicredi.challange.dto.ResultDTO;
import br.com.sicredi.challange.exception.BaseBusinessException;
import br.com.sicredi.challange.exception.NotFoundException;
import br.com.sicredi.challange.exception.ServerException;
import br.com.sicredi.challange.model.Pauta;
import br.com.sicredi.challange.model.Voto;
import br.com.sicredi.challange.model.VotoId;
import br.com.sicredi.challange.repository.PautaRepository;
import br.com.sicredi.challange.repository.VotoRepository;

@Service
public class PautaService {

	@Autowired
	private PautaRepository pautaRepository;

	@Autowired
	private VotoRepository votoRepository;

	public PautaDTO create(NewPautaDTO newPauta) {
		Pauta pauta = new Pauta();
		PautaDTO pautaDTO = new PautaDTO();
		try {
			BeanUtils.copyProperties(pauta, newPauta);
			BeanUtils.copyProperties(pautaDTO, pautaRepository.save(pauta));
			return pautaDTO;
		} catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
			throw new ServerException("Falha interna na criação da pauta.");
		}
	}

	public PautaDTO openSession(Long id, Long duration) {
		Optional<Pauta> optional = pautaRepository.findById(id);
		PautaDTO pautaDTO = new PautaDTO();
		if (!optional.isPresent()) {
			throw new NotFoundException(String.format("Pauta com identificação %d não encontrada.", id));
		}
		Pauta pauta = optional.get();
		if (pauta.getDtInicioVotacao() != null) {
			throw new BaseBusinessException(
					String.format("Sessão de votação da pauta com identificação %d em andamento ou encerrada.", id));
		}
		pauta.setDtInicioVotacao(LocalDateTime.now());
		pauta.setDtFimVotacao(LocalDateTime.now().plusMinutes(duration));
		try {
			BeanUtils.copyProperties(pautaDTO, pautaRepository.save(pauta));
			return pautaDTO;
		} catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
			throw new ServerException("Falha interna na atualização da pauta.");
		}
	}

	public ResultDTO result(Long id) {
		Optional<Pauta> optional = pautaRepository.findById(id);
		ResultDTO resultadoPautaDTO = new ResultDTO();
		if (!optional.isPresent()) {
			throw new NotFoundException(String.format("Pauta com identificação %d não encontrada.", id));
		}
		try {
			BeanUtils.copyProperties(resultadoPautaDTO, optional.get());
			resultadoPautaDTO.setTotalVotosSim(optional.get().getVotos().stream().filter(v -> v.getVoto()).count());
			resultadoPautaDTO.setTotalVotosNao(optional.get().getVotos().stream().filter(v -> !v.getVoto()).count());
			resultadoPautaDTO.setResultado(
					resultadoPautaDTO.getTotalVotosSim() > resultadoPautaDTO.getTotalVotosNao() ? "Aprovada"
							: "Rejeitada");
			return resultadoPautaDTO;
		} catch (IllegalAccessException | InvocationTargetException | RuntimeException e) {
			throw new ServerException("Falha interna no processamento do resultado da pauta.");
		}

	}

	public NewVotoDTO vote(NewVotoDTO newVoto) {
		if (isSessionOpen(newVoto.getPauta()) && isAbleToVote(newVoto.getPauta(), newVoto.getAssociado())) {
			try {
				votoRepository
						.save(new Voto(new VotoId(newVoto.getPauta(), newVoto.getAssociado()), newVoto.getVoto()));
			} catch (RuntimeException e) {
				throw new ServerException(String.format(
						"Falha interna ao processar o voto do associado %s para pauta com identificação %d.",
						newVoto.getPauta(), newVoto.getAssociado()));
			}
		}
		return newVoto;
	}

	private Boolean isSessionOpen(Long id) {
		return this.pautaRepository.getPautaByIdAndActive(id, LocalDateTime.now()).map(p -> Boolean.TRUE)
				.orElseThrow(() -> new BaseBusinessException(String
						.format("Pauta com identificação %d não encontrada ou com sessão de votação encerrada.", id)));
	}

	private Boolean isAbleToVote(Long pauta, String associado) {
		this.votoRepository.findById(new VotoId(pauta, associado)).ifPresent(p -> {
			throw new BaseBusinessException(
					String.format("Associado %s já votou na pauta com identificação %d.", associado, pauta));
		});

		return Boolean.TRUE;
	}

}
