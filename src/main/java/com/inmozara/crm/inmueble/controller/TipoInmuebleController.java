package com.inmozara.crm.inmueble.controller;

import com.inmozara.crm.inmueble.model.dto.TipoInmuebleDTO;
import com.inmozara.crm.inmueble.service.TipoInmuebleService;
import com.inmozara.crm.inmueble.service.interfaces.ITipoInmueble;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api_v1/crm/tipoInmueble")
public class TipoInmuebleController  implements ITipoInmueble {
    @Autowired
    TipoInmuebleService tipoInmuebleService;
    @Override
    public TipoInmuebleDTO save(@Valid @RequestBody TipoInmuebleDTO tipoInmuebleDTO) {
        return tipoInmuebleService.save(tipoInmuebleDTO);
    }

    @Override
    public TipoInmuebleDTO update(@Valid @RequestBody TipoInmuebleDTO tipoInmuebleDTO) {
        return tipoInmuebleService.update(tipoInmuebleDTO);
    }

    @Override
    public TipoInmuebleDTO delete(@RequestParam Long aLong) {
        return tipoInmuebleService.delete(aLong);
    }

    @Override
    public TipoInmuebleDTO find(@RequestParam Long aLong) {
        return tipoInmuebleService.find(aLong);
    }

    @Override
    public List<TipoInmuebleDTO> findAll() {
        return tipoInmuebleService.findAll();
    }
}
