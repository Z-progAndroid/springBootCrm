package com.inmozara.crm.utils;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.tarea.model.Tarea;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapperUtils {
    @Autowired
    private static final ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);
        modelMapper.addMappings(CONTRATODTO_TO_CONTRATO());
        modelMapper.addMappings(CONTRATO_TO_CONTRATODTO());
        modelMapper.addMappings(TAREADTO_TO_TAREA());
        modelMapper.addMappings(TAREA_TO_TAREADTO());
        modelMapper.addMappings(CITADTO_TO_CITA());
        modelMapper.addMappings(CITA_TO_CITADTO());
    }

    private static PropertyMap<Cita, CitaDTO> CITA_TO_CITADTO() {
        return new PropertyMap<Cita, CitaDTO>() {
            @Override
            protected void configure() {
                map().setIdCita(source.getIdCita());
                map().setDescripcion(source.getDescripcion());
                map().setFechaCita(source.getFechaCita());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setIdTipoCita(source.getTipoCita().getIdTipoCita());
                map().setIdEstadoCita(source.getEstadoCita().getIdEstadoCita());
                map().setIdInmueble(source.getInmueble().getIdInmueble());
                map().setIdUsuarioAgente(source.getAgente().getIdUsuario());
                map().setIdUsuarioCliente(source.getCliente().getIdUsuario());
            }
        };
    }

    private static PropertyMap<CitaDTO, Cita> CITADTO_TO_CITA() {
        return new PropertyMap<CitaDTO, Cita>() {
            @Override
            protected void configure() {
                map().setIdCita(source.getIdCita());
                map().setDescripcion(source.getDescripcion());
                map().setFechaCita(source.getFechaCita());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getTipoCita().setIdTipoCita(source.getIdTipoCita());
                map().getEstadoCita().setIdEstadoCita(source.getIdEstadoCita());
                map().getInmueble().setIdInmueble(source.getIdInmueble());
                map().getAgente().setIdUsuario(source.getIdUsuarioAgente());
                map().getCliente().setIdUsuario(source.getIdUsuarioCliente());
            }
        };
    }

    private static PropertyMap<Tarea, TareaDTO> TAREA_TO_TAREADTO() {
        return new PropertyMap<Tarea, TareaDTO>() {
            @Override
            protected void configure() {
                map().setIdTarea(source.getIdTarea());
                map().setTitulo(source.getTitulo());
                map().setDescripcion(source.getDescripcion());
                map().setFechaInicio(source.getFechaInicio());
                map().setFechaFin(source.getFechaFin());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setIdEstadoTarea(source.getEstadoTarea().getIdEstadoTarea());
                map().setIdUsuario(source.getUsuario().getIdUsuario());
            }
        };
    }

    private static PropertyMap<TareaDTO, Tarea> TAREADTO_TO_TAREA() {
        return new PropertyMap<TareaDTO, Tarea>() {
            @Override
            protected void configure() {
                map().setIdTarea(source.getIdTarea());
                map().setTitulo(source.getTitulo());
                map().setDescripcion(source.getDescripcion());
                map().setFechaInicio(source.getFechaInicio());
                map().setFechaFin(source.getFechaFin());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getEstadoTarea().setIdEstadoTarea(source.getIdEstadoTarea());
                map().getUsuario().setIdUsuario(source.getIdUsuario());
            }
        };
    }

    private static PropertyMap<Contrato, ContratoDTO> CONTRATO_TO_CONTRATODTO() {
        return new PropertyMap<Contrato, ContratoDTO>() {
            @Override
            protected void configure() {
                map().setIdContrato(source.getIdContrato());
                map().setTitulo(source.getTitulo());
                map().setFechaInicio(source.getFechaInicio());
                map().setFechaFinalizacion(source.getFechaFinalizacion());
                map().setDescripcion(source.getDescripcion());
                map().setTerminosYCondiciones(source.getTerminosYCondiciones());
                map().setValor(source.getValor());
                map().setObservaciones(source.getObservaciones());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setIdTipoContrato(source.getTipoContrato().getIdTipoContrato());
                map().setIdTipoPago(source.getTipoPago().getIdTipoPago());
                map().setIdInmueble(source.getInmueble().getIdInmueble());
                map().setIdEstadoContrato(source.getEstadoContrato().getIdEstadoContrato());
                map().setAgente(source.getAgente().getIdUsuario());
                map().setCliente(source.getCliente().getIdUsuario());
            }
        };
    }

    private static PropertyMap<ContratoDTO, Contrato> CONTRATODTO_TO_CONTRATO() {
        return new PropertyMap<ContratoDTO, Contrato>() {
            @Override
            protected void configure() {
                map().setIdContrato(source.getIdContrato());
                map().setTitulo(source.getTitulo());
                map().setFechaInicio(source.getFechaInicio());
                map().setFechaFinalizacion(source.getFechaFinalizacion());
                map().setDescripcion(source.getDescripcion());
                map().setTerminosYCondiciones(source.getTerminosYCondiciones());
                map().setValor(source.getValor());
                map().setObservaciones(source.getObservaciones());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getTipoContrato().setIdTipoContrato(source.getIdTipoContrato());
                map().getTipoPago().setIdTipoPago(source.getIdTipoPago());
                map().getInmueble().setIdInmueble(source.getIdInmueble());
                map().getEstadoContrato().setIdEstadoContrato(source.getIdEstadoContrato());
                map().getAgente().setIdUsuario(source.getAgente());
                map().getCliente().setIdUsuario(source.getCliente());
            }
        };
    }

    /**
     * Hide from public usage.
     */
    private ObjectMapperUtils() {
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param <D>      type of result object.
     * @param <T>      type of source object to map from.
     * @param entity   entity that needs to be mapped.
     * @param outClass class of result object.
     * @return new object of <code>outClass</code> type.
     */
    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    /**
     * <p>Note: outClass object must have default constructor with no arguments</p>
     *
     * @param entityList list of entities that needs to be mapped
     * @param outCLass   class of result list element
     * @param <D>        type of objects in result list
     * @param <T>        type of entity in <code>entityList</code>
     * @return list of mapped object with <code><D></code> type.
     */
    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outCLass) {
        return entityList.stream()
                .map(entity -> map(entity, outCLass))
                .collect(Collectors.toList());
    }

    /**
     * Maps {@code source} to {@code destination}.
     *
     * @param source      object to map from
     * @param destination object to map to
     */
    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }
}
