/* TIPO CONTRATO */
INSERT INTO crm.tipo_contratos
(id_tipo_contrato, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(0, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'POR DEFECTO');
INSERT INTO crm.tipo_contratos
(id_tipo_contrato, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(1, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CONTRATO DE ARRENDAMIENTO');
INSERT INTO crm.tipo_contratos
(id_tipo_contrato, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(2, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CONTRATO DE COMPRAVENTA');
INSERT INTO crm.tipo_contratos
(id_tipo_contrato, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(3, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CONTRATO DE EXCLUSIVIDAD');
INSERT INTO crm.tipo_contratos
(id_tipo_contrato, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(4, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CONTRATO DE ARRENDAMIENTO CON OPCIÓN DE COMPRA');
/* TIPOS INMUEBLES */
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(0, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'POR DEFECTO');
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(1, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'APARTAMENTO');
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(2, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CASA UNIFAMILIAR');
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(3, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'LOCAL COMERCIAL');
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(4, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'OFICINA');
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(5, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'TERRENO');
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(6, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CHALET');
INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(7, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'EDIFICIO');

INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(8, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'NAVE INDUSTRIAL');

INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(9, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'FINCA RÚSTICA');

INSERT INTO crm.tipo_inmuebles
(id_tipo_inmueble, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(10, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'PLAZA');

/* TIPO PAGO */
INSERT INTO crm.tipo_pagos
(id_tipo_pago, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(0, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'POR DEFECTO');
INSERT INTO crm.tipo_pagos
(id_tipo_pago, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(1, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'TRANSFERENCIA BANCARIA');
INSERT INTO crm.tipo_pagos
(id_tipo_pago, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(2, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'TARJETA DE CRÉDITO');
INSERT INTO crm.tipo_pagos
(id_tipo_pago, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(3, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'EFECTIVO');
INSERT INTO crm.tipo_pagos
(id_tipo_pago, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(4, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CHEQUE');
INSERT INTO crm.tipo_pagos
(id_tipo_pago, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(5, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'HIPOTECA');
INSERT INTO crm.tipo_pagos
(id_tipo_pago, fecha_creacion, fecha_modificacion, modificado, tipo)
VALUES(6, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'PAGO ELECTRONICO');
/* TIPO SEGUIMINETOS */
INSERT INTO crm.tipo_seguimientos
(id_tipo_seguimiento, fecha_creacion, fecha_modificacion, modificado, tipo_seguimiento)
VALUES(0, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'POR DEFECTO');
INSERT INTO crm.tipo_seguimientos
(id_tipo_seguimiento, fecha_creacion, fecha_modificacion, modificado, tipo_seguimiento)
VALUES(1, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'VISITA');
INSERT INTO crm.tipo_seguimientos
(id_tipo_seguimiento, fecha_creacion, fecha_modificacion, modificado, tipo_seguimiento)
VALUES(2, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'CREACION CONTRATO');
INSERT INTO crm.tipo_seguimientos
(id_tipo_seguimiento, fecha_creacion, fecha_modificacion, modificado, tipo_seguimiento)
VALUES(3, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'ASIGNACION AGENTE');
/* TIPO CITA*/
INSERT INTO crm.tipos_citas
(id_tipo_cita, fecha_creacion, fecha_modificacion, modificado, estado_cita)
VALUES(0, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'POR DEFECTO');
INSERT INTO crm.tipos_citas
(id_tipo_cita, fecha_creacion, fecha_modificacion, modificado, estado_cita)
VALUES(1, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'VISTA PROPIEDAD');
INSERT INTO crm.tipos_citas
(id_tipo_cita, fecha_creacion, fecha_modificacion, modificado, estado_cita)
VALUES(2, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'EVALUACIÓN DE PROPIEDAD');
INSERT INTO crm.tipos_citas
(id_tipo_cita, fecha_creacion, fecha_modificacion, modificado, estado_cita)
VALUES(3, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'FIRMA DE CONTRATOS');
INSERT INTO crm.tipos_citas
(id_tipo_cita, fecha_creacion, fecha_modificacion, modificado, estado_cita)
VALUES(4, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'NEGOCIACIÓN');
INSERT INTO crm.tipos_citas
(id_tipo_cita, fecha_creacion, fecha_modificacion, modificado, estado_cita)
VALUES(5, CURRENT_DATE(), CURRENT_DATE(),'CREACION_INICIAL', 'ASESORAMIENTO');
