package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.EstadoContrato;
import com.inmozara.crm.contrato.model.dto.EstadoContratoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.EstadoContratoRepository;
import com.inmozara.crm.contrato.service.interfaces.IEstadoContrato;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoContratoService implements IEstadoContrato {
    @Autowired
    private EstadoContratoRepository estadoContratoRepository;
    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public EstadoContratoDTO save(EstadoContratoDTO estadoContratoDTO) {
        EstadoContrato estadoContrato = ObjectMapperUtils.map(estadoContratoDTO, EstadoContrato.class);
        estadoContrato = estadoContratoRepository.save(estadoContrato);
        return ObjectMapperUtils.map(estadoContrato, EstadoContratoDTO.class);

    }

    @Override
    public EstadoContratoDTO update(EstadoContratoDTO estadoContratoDTO) {
        EstadoContrato estadoContrato = ObjectMapperUtils.map(estadoContratoDTO, EstadoContrato.class);
        estadoContrato = estadoContratoRepository.save(estadoContrato);
        return ObjectMapperUtils.map(estadoContrato, EstadoContratoDTO.class);
    }

    @Override
    public MensajeDTO delete(Long idEstadoContrato) {
        if (!estadoContratoRepository.existsById(idEstadoContrato)) {
            throw new RecursoNoEncontrado("no existe un estado de contrato con el id: " + idEstadoContrato);
        }
        contratoRepository.actualizarContratosPorEstado(EstadoContrato.builder().idEstadoContrato(idEstadoContrato).build(), EstadoContrato.builder().idEstadoContrato(0L).build());
        estadoContratoRepository.deleteById(idEstadoContrato);
        return MensajeDTO.builder()
                .mensaje("El estado del contrato se ha eliminado correctamente con el id: " + idEstadoContrato)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public EstadoContratoDTO find(Long idEstadoContrato) {
        EstadoContrato estadoContrato = estadoContratoRepository.findById(idEstadoContrato)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro el estado del contrato con el id: " + idEstadoContrato));
        return ObjectMapperUtils.map(estadoContrato, EstadoContratoDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoContratoDTO> findAll() {
        List<EstadoContrato> estadoContratos = estadoContratoRepository.findAll();
        if (estadoContratos.isEmpty())
            throw new RecursoNoEncontrado("No se encontraron estados de contratos");
        estadoContratos = estadoContratos
                .stream()
                .filter(estadoContrato -> estadoContrato.getIdEstadoContrato() != 0)
                .collect(Collectors.toList());
        return ObjectMapperUtils.mapAll(estadoContratos, EstadoContratoDTO.class);
    }
}
