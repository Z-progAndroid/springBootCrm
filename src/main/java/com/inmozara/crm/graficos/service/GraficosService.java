package com.inmozara.crm.graficos.service;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.repository.CitaRepository;
import com.inmozara.crm.contrato.model.repository.ContratoRepository;
import com.inmozara.crm.graficos.model.dto.GraficoDTO;
import com.inmozara.crm.inmueble.model.repository.InmuebleRepository;
import com.inmozara.crm.tarea.model.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GraficosService {
    @Autowired
    ContratoRepository contratoRepository;
    @Autowired
    InmuebleRepository inmuebleRepository;
    @Autowired
    CitaRepository citaRepository;
    @Autowired
    TareaRepository tareaRepository;


    public GraficoDTO graficoAdmin() {
        GraficoDTO graficoDTO = new GraficoDTO();
        Optional.ofNullable(contratoRepository
                        .findContratosCreadosEsteMes(new Date())
                        .stream()
                        .collect(Collectors.groupingBy(contrato -> contrato.getEstadoContrato().getEstado(), Collectors.counting())))
                .ifPresent(graficoDTO::setContratos);

        Optional.ofNullable(inmuebleRepository.findAll()
                        .stream()
                        .collect(Collectors.groupingBy(inmueble -> inmueble.getEstadoInmueble().getEstado(), Collectors.counting())))
                .ifPresent(graficoDTO::setInmuebles);
        List<Cita> citaDelMes = citaRepository.findCitasCreadasEsteMes(new Date());
        Optional.ofNullable(citaDelMes
                        .stream()
                        .collect(Collectors.groupingBy(cita -> cita.getEstadoCita().getEstadoCita(), Collectors.counting())))
                .ifPresent(graficoDTO::setCitas);
        Optional.ofNullable(citaDelMes
                        .stream()
                        .collect(Collectors.groupingBy(cita -> cita.getTipoCita().getTipoCita(), Collectors.counting())))
                .ifPresent(graficoDTO::setVisitas);
        Optional.ofNullable(tareaRepository.findTareasCreadasEsteMes(new Date())
                        .stream()
                        .collect(Collectors.groupingBy(tarea -> tarea.getEstadoTarea().getEstadoTarea(), Collectors.counting())))
                .ifPresent(graficoDTO::setTareas);
        return graficoDTO;
    }

    public GraficoDTO graficoAgente(int idUsuario) {
        GraficoDTO graficoDTO = new GraficoDTO();
        Optional.ofNullable(contratoRepository
                        .findContratosCreadosEsteMesPorUsuario(new Date(),idUsuario)
                        .stream()
                        .collect(Collectors.groupingBy(contrato -> contrato.getEstadoContrato().getEstado(), Collectors.counting())))
                .ifPresent(graficoDTO::setContratos);

        Optional.ofNullable(inmuebleRepository.findAll()
                        .stream()
                        .collect(Collectors.groupingBy(inmueble -> inmueble.getEstadoInmueble().getEstado(), Collectors.counting())))
                .ifPresent(graficoDTO::setInmuebles);
        List<Cita> citaDelMes = citaRepository.findCitasCreadasEsteMesPorUsuario(new Date(),idUsuario);
        Optional.ofNullable(citaDelMes
                        .stream()
                        .collect(Collectors.groupingBy(cita -> cita.getEstadoCita().getEstadoCita(), Collectors.counting())))
                .ifPresent(graficoDTO::setCitas);
        Optional.ofNullable(citaDelMes
                        .stream()
                        .collect(Collectors.groupingBy(cita -> cita.getTipoCita().getTipoCita(), Collectors.counting())))
                .ifPresent(graficoDTO::setVisitas);
        Optional.ofNullable(tareaRepository.findTareasCreadasEsteMesPorUsuario(new Date(),idUsuario)
                        .stream()
                        .collect(Collectors.groupingBy(tarea -> tarea.getEstadoTarea().getEstadoTarea(), Collectors.counting())))
                .ifPresent(graficoDTO::setTareas);
        return graficoDTO;
    }

}
