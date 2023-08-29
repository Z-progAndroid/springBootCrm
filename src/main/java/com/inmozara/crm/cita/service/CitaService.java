package com.inmozara.crm.cita.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.CitaSearch;
import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.cita.service.interfaces.ICita;
import com.inmozara.crm.config.MensajeDTO;
import com.inmozara.crm.excepcion.RecursoNoEncontrado;
import com.inmozara.crm.utils.ObjectMapperUtils;
import com.inmozara.crm.utils.UtilsDates;
import com.inmozara.crm.utils.pdf.CitaDetallePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaService implements ICita {
    @Autowired
    private CitaRepository citaRepository;

    @Override
    public CitaDTO save(CitaDTO citaDTO) {
        Cita cita = ObjectMapperUtils.map(citaDTO, Cita.class);
        cita = citaRepository.save(cita);
        return ObjectMapperUtils.map(cita, CitaDTO.class);
    }

    @Override
    public CitaDTO update(CitaDTO citaDTO) {
        Cita cita = ObjectMapperUtils.map(citaDTO, Cita.class);
        cita = citaRepository.save(cita);
        return ObjectMapperUtils.map(cita, CitaDTO.class);
    }

    @Override
    public MensajeDTO delete(Integer idCita) {
        if (!citaRepository.existsById(idCita)) {
            throw new RecursoNoEncontrado("No existe la cita");
        }
        citaRepository.deleteById(idCita);
        return MensajeDTO.builder()
                .mensaje("La cita se ha eliminado correctamente con el id: " + idCita)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    @Override
    public CitaDTO find(Integer integer) {
        Cita cita = citaRepository.findById(integer)
                .orElseThrow(() -> new RecursoNoEncontrado("No existe la cita"));
        return ObjectMapperUtils.map(cita, CitaDTO.class);
    }

    @Override
    public List<CitaDTO> findAll() {
        List<Cita> citas = citaRepository.findAll();
        if (citas.isEmpty())
            throw new RecursoNoEncontrado("No existen citas");
        return ObjectMapperUtils.mapAll(citas, CitaDTO.class);
    }

    @Override
    public List<CitaDTO> findAllByParams(CitaDTO citaDTO) {
        List<Cita> citas = citaRepository.findAll(CitaSearch.builder()
                .cita(ObjectMapperUtils.map(citaDTO, Cita.class))
                .build());
        if (citas.isEmpty())
            throw new RecursoNoEncontrado("No existen citas con los parametros enviados");
        return ObjectMapperUtils.mapAll(citas, CitaDTO.class);
    }

    public MensajeDTO checkAvailability(String startDateStr, String endDateStr, int idInmueble) {
        List<Cita> conflictingCitas = citaRepository.countConflictingCitas(
                LocalDateTime.parse(startDateStr, DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse(endDateStr, DateTimeFormatter.ISO_DATE_TIME),
                idInmueble);
        String horarios = conflictingCitas
                .stream()
                .map(cita -> cita.getFechaInicio().format(UtilsDates.DATE_TIME_FORMATTER_DD_MM_YYYY_HH_MM_SS) + " - " + cita.getFechaFin().format(UtilsDates.DATE_TIME_FORMATTER_DD_MM_YYYY_HH_MM_SS))
                .collect(Collectors.joining(" , "));
        String mensaje = conflictingCitas.size() > 1
                ? "El inmueble no esta disponible en el horario seleccionado ya hay " + conflictingCitas.size() + "citas con horarios \n" + horarios
                : "El inmueble no esta disponible en el horario seleccionado ya hay una cita con horario \n" + horarios;

        return MensajeDTO.builder()
                .error(conflictingCitas.size() == 0)
                .mensaje(mensaje)
                .estado(HttpStatus.OK.value())
                .fecha(UtilsDates.now())
                .build();
    }

    public List<CitaDTO> findAllPendienteYActivas() {
        List<Cita> citas = citaRepository.findCitasPendientesYActivas();
        if (citas.isEmpty())
            throw new RecursoNoEncontrado("No existen citas");
        return ObjectMapperUtils.mapAll(citas, CitaDTO.class);
    }

    public List<CitaDTO> obtenerCitasUsuarioNoEliminadas(Integer userId) {
        List<Cita> citas = citaRepository.findCitasByUserIdAndEstadoIn(userId);
        if (citas.isEmpty())
            throw new RecursoNoEncontrado("No existen citas");
        return ObjectMapperUtils.mapAll(citas, CitaDTO.class);
    }

    public ByteArrayOutputStream generarCitaPdf(int idCita) {
        Cita cita = citaRepository.findById(idCita)
                .orElseThrow(() -> new RecursoNoEncontrado("No se encontro la cita para generar el pdf"));
        return CitaDetallePdf
                .builder()
                .cita(Optional.ofNullable(cita))
                .build()
                .generarPdf();
    }
}
