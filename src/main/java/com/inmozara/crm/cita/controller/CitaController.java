package com.inmozara.crm.cita.controller;

import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.cita.service.CitaService;
import com.inmozara.crm.cita.service.interfaces.ICita;
import com.inmozara.crm.config.MensajeDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api_v1/crm/cita")
public class CitaController implements ICita {
    @Autowired
    private CitaService citaService;

    @Override
    @PostMapping
    public CitaDTO save(@Valid @RequestBody CitaDTO citaDTO) {
        return citaService.save(citaDTO);
    }

    @Override
    @PutMapping
    public CitaDTO update(@Valid @RequestBody CitaDTO citaDTO) {
        return citaService.update(citaDTO);
    }

    @Override
    @DeleteMapping
    public MensajeDTO delete(@RequestParam Integer id) {
        return citaService.delete(id);
    }

    @Override
    @GetMapping
    public CitaDTO find(@RequestParam Integer id) {
        return citaService.find(id);
    }

    @Override
    @GetMapping("/all")
    public List<CitaDTO> findAll() {
        return citaService.findAll();
    }

    @Override
    @PostMapping("/search")
    public List<CitaDTO> findAllByParams(@RequestBody CitaDTO citaDTO) {
        return citaService.findAllByParams(citaDTO);
    }

    @GetMapping("/checkAvailability")
    public MensajeDTO checkAvailability(@RequestParam("startDate") String startDateStr,
                                     @RequestParam("endDate") String endDateStr,
                                     @RequestParam("idInmueble") int idInmueble)  {
        return citaService.checkAvailability(startDateStr, endDateStr, idInmueble);
    }
    @GetMapping("/pendientesYActivas")
    public List<CitaDTO> findAllPendienteYActivas() {
        return citaService.findAllPendienteYActivas();
    }
    @GetMapping("/citasUsuarioNoEliminadas")
    public List<CitaDTO> obtenerCitasUsuarioNoEliminadas(Integer idUsuario) {
        return citaService.obtenerCitasUsuarioNoEliminadas(idUsuario);
    }
    @GetMapping("/citasCreadasParaElAgente")
    public List<CitaDTO> citasCreadasParaElAgente(Integer idUsuario) {
        return citaService.findCitasCreadasParaElAgente(idUsuario);
    }
    @GetMapping(value = "/download-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> downloadPdf(@RequestParam int idCita) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=archivo.pdf");
        ByteArrayOutputStream outputStream = citaService.generarCitaPdf(idCita);
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(outputStream.size())
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}

