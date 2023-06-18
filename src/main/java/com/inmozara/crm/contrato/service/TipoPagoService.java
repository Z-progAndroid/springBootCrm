package com.inmozara.crm.contrato.service;

import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.TipoPago;
import com.inmozara.crm.contrato.model.dto.TipoPagoDTO;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.contrato.model.repository.TipoPagoRepository;
import com.inmozara.crm.contrato.model.search.ContratoSearch;
import com.inmozara.crm.contrato.service.interfaces.ITipoPago;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagoService implements ITipoPago {
    @Autowired
    private TipoPagoRepository tipoPagoRepository;
    @Autowired
    private ContratoRepository contratoRepository;

    @Override
    public TipoPagoDTO save(TipoPagoDTO tipoPagoDTO) {
        TipoPago tipoPago = ObjectMapperUtils.map(tipoPagoDTO, TipoPago.class);
        TipoPago tipoPago1 = tipoPagoRepository.save(tipoPago);
        return ObjectMapperUtils.map(tipoPago1, TipoPagoDTO.class);
    }

    @Override
    public TipoPagoDTO update(TipoPagoDTO tipoPagoDTO) {
        TipoPago tipoPago = ObjectMapperUtils.map(tipoPagoDTO, TipoPago.class);
        TipoPago tipoPago1 = tipoPagoRepository.save(tipoPago);
        return ObjectMapperUtils.map(tipoPago1, TipoPagoDTO.class);
    }

    @Override
    public MensajeDTO delete(Long idTipoPago) {
        if (!tipoPagoRepository.existsById(idTipoPago)) {
            throw new RecursoNoEncontrado("El tipo de pago no existe");
        }
        List<Contrato> contratos = contratoRepository.findAll(ContratoSearch.builder()
                .contrato(Contrato.builder().tipoPago(TipoPago.builder().idTipoPago(idTipoPago).build()).build()).build());
        if (!contratos.isEmpty()) {
            contratos.forEach(contrato -> {
                contrato.setTipoPago(TipoPago.builder().idTipoPago(0L).build());
                contratoRepository.save(contrato);
            });
        }
        tipoPagoRepository.deleteById(idTipoPago);
        return MensajeDTO.builder().mensaje("El tipo de pago se ha eliminado correctamente con el id: " + idTipoPago).estado(HttpStatus.OK.value()).fecha(UtilsDates.now()).build();
    }

    @Override
    public TipoPagoDTO find(Long idTipoPago) {
        TipoPago tipoPago = tipoPagoRepository.findById(idTipoPago).orElseThrow(() -> new RecursoNoEncontrado("El tipo de pago no existe"));
        return ObjectMapperUtils.map(tipoPago, TipoPagoDTO.class);
    }

    @Override
    public List<TipoPagoDTO> findAll() {
        List<TipoPago> tipoPagos = tipoPagoRepository.findAll();
        if (tipoPagos.isEmpty()) {
            throw new RecursoNoEncontrado("No hay tipos de pagos");
        }
        return ObjectMapperUtils.mapAll(tipoPagos, TipoPagoDTO.class);
    }
}
