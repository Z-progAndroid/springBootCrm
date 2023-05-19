package com.inmozara.crm.contrato.service;

import com.inmozara.crm.contrato.model.TipoPago;
import com.inmozara.crm.contrato.model.dto.TipoPagoDTO;
import com.inmozara.crm.contrato.model.repository.TipoPagoRepository;
import com.inmozara.crm.contrato.service.interfaces.ITipoPago;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoPagoService implements ITipoPago {
    @Autowired
    private TipoPagoRepository tipoPagoRepository;

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
    public TipoPagoDTO delete(Long idTipoPago) {
        if (!tipoPagoRepository.existsById(idTipoPago)) {
            throw new RecursoNoEncontrado("El tipo de pago no existe");
        }
        tipoPagoRepository.deleteById(idTipoPago);
        return null;
    }

    @Override
    public TipoPagoDTO find(Long idTipoPago) {
        TipoPago tipoPago = tipoPagoRepository.findById(idTipoPago)
                .orElseThrow(() -> new RecursoNoEncontrado("El tipo de pago no existe"));
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
