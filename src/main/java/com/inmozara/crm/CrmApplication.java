package com.inmozara.crm;

import com.google.gson.Gson;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CrmApplication {
    public static void main(String[] args) {
        ContratoDTO contratoDTO = new ContratoDTO();
        contratoDTO.setIdContrato(1L);
        contratoDTO.setTitulo("Contrato de arrendamiento");
        contratoDTO.setFechaInicio(new Date());
        contratoDTO.setFechaFinalizacion(new Date());
        contratoDTO.setDescripcion("Contrato de arrendamiento de un inmueble");
        contratoDTO.setTerminosYCondiciones("Contrato de arrendamiento de un inmueble");
        contratoDTO.setValor(1000000);
        contratoDTO.setObservaciones("Contrato de arrendamiento de un inmueble");
        contratoDTO.setFechaCreacion(new Date());
        contratoDTO.setFechaModificacion(new Date());
        contratoDTO.setModificado("Contrato de arrendamiento de un inmueble");
        contratoDTO.setIdTipoContrato(1L);
        contratoDTO.setIdTipoPago(1L);
        contratoDTO.setIdInmueble(1L);
        contratoDTO.setIdInmueble(1L);
        Gson gson = new Gson();
        System.out.println(gson.toJson(contratoDTO));


        SpringApplication.run(CrmApplication.class, args);
    }

}
