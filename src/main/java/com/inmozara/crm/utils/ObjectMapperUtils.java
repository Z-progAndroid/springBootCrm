package com.inmozara.crm.utils;

import com.inmozara.crm.cita.model.Cita;
import com.inmozara.crm.cita.model.dto.CitaDTO;
import com.inmozara.crm.contrato.model.Contrato;
import com.inmozara.crm.contrato.model.dto.ContratoDTO;
import com.inmozara.crm.inmueble.model.*;
import com.inmozara.crm.inmueble.model.dto.*;
import com.inmozara.crm.tarea.model.Tarea;
import com.inmozara.crm.tarea.model.dto.TareaDTO;
import com.inmozara.crm.usuario.model.Usuario;
import com.inmozara.crm.usuario.model.dto.UsuarioDTO;
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
        modelMapper.addMappings(INMUEBLEDTO_TO_INMUEBLE());
        modelMapper.addMappings(INMUEBLE_TO_INMUEBLEDTO());
        modelMapper.addMappings(PROVINCIA_TO_PROVINCIADTO());
        modelMapper.addMappings(PROVINCIAD_TO_PROVINCIA());
        modelMapper.addMappings(MUNICIPIO_TO_MUNICIPIODTO());
        modelMapper.addMappings(MUNICIPIODTO_TO_MUNICIPIO());
        modelMapper.addMappings(BARRIO_TO_BARRIODTO());
        modelMapper.addMappings(BARRIODTO_TO_BARRIO());
        modelMapper.addMappings(USUARIODTO_TO_USUARIO());
        modelMapper.addMappings(USUARIO_TO_USUARIODTO());
        modelMapper.addMappings(PAISDTO_TO_PAIS());
        modelMapper.addMappings(PAIS_TO_PAISDTO());
    }

    private static PropertyMap<Usuario, UsuarioDTO> USUARIO_TO_USUARIODTO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdUsuario(source.getIdUsuario());
                map().setNombre(source.getNombre());
                map().setApellido(source.getApellido());
                map().setEmail(source.getEmail());
                map().setUsername(source.getUsername());
                map().setPassword(source.getPassword());
                map().setTelefono(source.getTelefono());
                map().setDireccion(source.getDireccion());
                map().setDni(source.getDni());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setIdEstadoUsuario(source.getEstadoUsuario().getIdEstadoUsuario());
                map().setIdRol(source.getRol().getIdRol());
                map().setEstadoUsuario(source.getEstadoUsuario().getEstadoUsuario());
                map().setRol(source.getRol().getRol());
            }
        };
    }

    private static PropertyMap<UsuarioDTO, Usuario> USUARIODTO_TO_USUARIO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdUsuario(source.getIdUsuario());
                map().setNombre(source.getNombre());
                map().setApellido(source.getApellido());
                map().setEmail(source.getEmail());
                map().setUsername(source.getUsername());
                map().setPassword(source.getPassword());
                map().setTelefono(source.getTelefono());
                map().setDireccion(source.getDireccion());
                map().setDni(source.getDni());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getEstadoUsuario().setIdEstadoUsuario(source.getIdEstadoUsuario());
                map().getRol().setIdRol(source.getIdRol());
            }
        };
    }

    private static PropertyMap<BarrioDTO, Barrio> BARRIODTO_TO_BARRIO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdBarrio(source.getIdBarrio());
                map().setBarrio(source.getBarrio());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getMunicipio().setIdMunicipio(source.getIdMunicipio());
            }
        };
    }

    private static PropertyMap<Barrio, BarrioDTO> BARRIO_TO_BARRIODTO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdBarrio(source.getIdBarrio());
                map().setBarrio(source.getBarrio());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setIdMunicipio(source.getMunicipio().getIdMunicipio());
            }
        };
    }

    private static PropertyMap<MunicipoDTO, Municipio> MUNICIPIODTO_TO_MUNICIPIO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdMunicipio(source.getIdMunicipio());
                map().setMunicipio(source.getMunicipio());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getProvincia().setIdProvincia(source.getIdProvincia());
            }
        };
    }

    private static PropertyMap<Municipio, MunicipoDTO> MUNICIPIO_TO_MUNICIPIODTO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdMunicipio(source.getIdMunicipio());
                map().setMunicipio(source.getMunicipio());
                map().setIdProvincia(source.getProvincia().getIdProvincia());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
            }
        };
    }

    private static PropertyMap<ProvinciaDTO, Provincia> PROVINCIAD_TO_PROVINCIA() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdProvincia(source.getIdProvincia());
                map().setProvincia(source.getProvincia());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getPais().setPais(source.getIdPais());
            }
        };
    }

    private static PropertyMap<Provincia, ProvinciaDTO> PROVINCIA_TO_PROVINCIADTO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdProvincia(source.getIdProvincia());
                map().setProvincia(source.getProvincia());
                map().setIdPais(source.getPais().getIdPais());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
            }
        };
    }

    private static PropertyMap<PaisDTO, Pais> PAISDTO_TO_PAIS() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdPais(source.getIdPais());
                map().setPais(source.getPais());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
            }
        };
    }

    private static PropertyMap<Pais, PaisDTO> PAIS_TO_PAISDTO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdPais(source.getIdPais());
                map().setPais(source.getPais());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
            }
        };
    }

    private static PropertyMap<Inmueble, InmuebleDTO> INMUEBLE_TO_INMUEBLEDTO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdInmueble(source.getIdInmueble());
                map().setDescripcion(source.getDescripcion());
                map().setDireccion(source.getDireccion());
                map().setCodigoPostal(source.getCodigoPostal());
                map().setPrecio_venta(source.getPrecio_venta());
                map().setPrecio_alquiler(source.getPrecio_alquiler());
                map().setNumHabitaciones(source.getNumHabitaciones());
                map().setNumBanos(source.getNumBanos());
                map().setMetros_cuadrados(source.getMetros_cuadrados());
                map().setAno_construccion(source.getAno_construccion());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setIdTipoInmueble(source.getTipoInmueble().getId());
                map().setIdEstadoInmueble(source.getEstadoInmueble().getIdEstadoInmueble());
                map().setIdPais(source.getPais().getIdPais());
                map().setIdProvincia(source.getProvincia().getIdProvincia());
                map().setIdMunicipio(source.getMunicipio().getIdMunicipio());
                map().setIdUsuario(source.getUsuario().getIdUsuario());
                map().setIdBarrio(source.getBarrio().getIdBarrio());
                map().setImagen1(source.getImagen1());
                map().setImagen2(source.getImagen2());
                map().setImagen3(source.getImagen3());
                map().setImagen4(source.getImagen4());
            }
        };
    }

    private static PropertyMap<InmuebleDTO, Inmueble> INMUEBLEDTO_TO_INMUEBLE() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdInmueble(source.getIdInmueble());
                map().setDescripcion(source.getDescripcion());
                map().setDireccion(source.getDireccion());
                map().setCodigoPostal(source.getCodigoPostal());
                map().setPrecio_venta(source.getPrecio_venta());
                map().setPrecio_alquiler(source.getPrecio_alquiler());
                map().setNumHabitaciones(source.getNumHabitaciones());
                map().setNumBanos(source.getNumBanos());
                map().setMetros_cuadrados(source.getMetros_cuadrados());
                map().setAno_construccion(source.getAno_construccion());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setImagen1(source.getImagen1());
                map().setImagen2(source.getImagen2());
                map().setImagen3(source.getImagen3());
                map().setImagen4(source.getImagen4());
                map().getTipoInmueble().setId(source.getIdTipoInmueble());
                map().getEstadoInmueble().setIdEstadoInmueble(source.getIdEstadoInmueble());
                map().getPais().setIdPais(source.getIdPais());
                map().getProvincia().setIdProvincia(source.getIdProvincia());
                map().getMunicipio().setIdMunicipio(source.getIdMunicipio());
                map().getBarrio().setIdBarrio(source.getIdBarrio());
                map().getUsuario().setIdUsuario(source.getIdUsuario());
            }
        };
    }

    private static PropertyMap<Cita, CitaDTO> CITA_TO_CITADTO() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdCita(source.getIdCita());
                map().setDescripcion(source.getDescripcion());
                map().setFechaIncio(source.getFechaInicio());
                map().setFechaFin(source.getFechaFin());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().setIdTipoCita(source.getTipoCita().getIdTipoCita());
                map().setIdEstadoCita(source.getEstadoCita().getIdEstadoCita());
                map().setIdInmueble(source.getInmueble().getIdInmueble());
                map().setIdUsuarioCliente(source.getCliente().getIdUsuario());
            }
        };
    }

    private static PropertyMap<CitaDTO, Cita> CITADTO_TO_CITA() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setIdCita(source.getIdCita());
                map().setDescripcion(source.getDescripcion());
                map().setFechaInicio(source.getFechaIncio());
                map().setFechaFin(source.getFechaFin());
                map().setFechaCreacion(source.getFechaCreacion());
                map().setFechaModificacion(source.getFechaModificacion());
                map().setModificado(source.getModificado());
                map().getTipoCita().setIdTipoCita(source.getIdTipoCita());
                map().getEstadoCita().setIdEstadoCita(source.getIdEstadoCita());
                map().getInmueble().setIdInmueble(source.getIdInmueble());
                map().getCliente().setIdUsuario(source.getIdUsuarioCliente());
            }
        };
    }

    private static PropertyMap<Tarea, TareaDTO> TAREA_TO_TAREADTO() {
        return new PropertyMap<>() {
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
                map().setEstadoTarea(source.getEstadoTarea().getEstadoTarea());
                map().setNombre(source.getUsuario().getNombre());
                map().setApellido(source.getUsuario().getApellido());

            }
        };
    }

    private static PropertyMap<TareaDTO, Tarea> TAREADTO_TO_TAREA() {
        return new PropertyMap<>() {
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
        return new PropertyMap<>() {
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
                map().setCliente(source.getCliente().getIdUsuario());
                map().setEstadoContrato(source.getEstadoContrato().getEstado());
            }
        };
    }

    private static PropertyMap<ContratoDTO, Contrato> CONTRATODTO_TO_CONTRATO() {
        return new PropertyMap<>() {
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
