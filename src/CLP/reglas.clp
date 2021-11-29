; Las reglas van a insertar las carreras adecuadas, o las tendencias a las que se podrian inclinar, según los gustos de la persona, que seran las preguntas contestadas
; ? indica que es una variable
; el valor del slot se asigna a una varible, y el valor de esa variable es igual a 

(import Institucion_Objetos.Gusto)
(deftemplate Gusto (declare (from-class Gusto)))

(batch CLP/gustos.clp)

(defrule inicio
=>
(printout t "Iniciando inferencia..." crlf)
)

(defrule  administrativas_y_Contables 
(Gusto 
(nombre ?nombre&:(eq ?nombre "\"conocimiento_PBI\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre1&:(eq ?nombre1 "\"conocimiento_macroeconomia_y_microeconomia\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre2&:(eq ?nombre2 "\"investigacion_distribucion_riquezas\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto
(nombre ?nombre3&:(eq ?nombre3 "\"puesto_gerente_de_comercializacion\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert( administrativas_y_Contables   administrativas-y-contables ))
)



(defrule carreras_artisticas
(Gusto 
(nombre ?nombre&:(eq ?nombre "\"alma_fiesta\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre1&:(eq ?nombre1 "\"constancia_en_las_artes\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre2&:(eq ?nombre2 "\"combinar_ropa_colores\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre3&:(eq ?nombre3 "\"regalo_instrumento\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(carreras_artisticas carreras-artisticas))
)

(defrule carreras_ciencias_exactas
(Gusto 
(nombre ?nombre&:(eq ?nombre "\"funcionamiento_aparatos\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre1&:(eq ?nombre1 "\"trabajo_laboratorio_estudio\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre2&:(eq ?nombre2 "\"significado_ADN_ARN\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre3&:(eq ?nombre3 "\"conocimiento_big_bang\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(carreras_ciencias_exactas carreras_ciencias_exactas))
)

(defrule carreras_defensa_y_seguridad
(Gusto 
(nombre ?nombre&:(eq ?nombre "\"grupo_defensa\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre1&:(eq ?nombre1 "\"diversidad\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre2&:(eq ?nombre2 "\"empleo_lejos\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre3&:(eq ?nombre3 "\"opinion_cuerpo_soldados\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(carreras_defensa_y_seguridad carreras-defensa-y-seguridad))
)

(defrule carreras_humanisticas_y_sociales
(Gusto 
(nombre ?nombre&:(eq ?nombre "\"explicar_temas\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre1&:(eq ?nombre1 "\"evolucion_hombre\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre2&:(eq ?nombre2 "\"act_peligro\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre3&:(eq ?nombre3 "\"trabajar_ninos\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(carreras_humanisticas_y_sociales carreras-humanisticas-y-sociales))
)

(defrule carreras_ingenieria_y_computacion
(Gusto 
(nombre ?nombre&:(eq ?nombre "\"gusto_video_juegos\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre1&:(eq ?nombre1 "\"gusto_video_juegos1\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre2&:(eq ?nombre2 "\"gusto_video_juegos2\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre3&:(eq ?nombre3 "\"gusto_video_juegos3\"")) (respuesta ?res&:(eq ?res TRUE)))
(Gusto 
(nombre ?nombre4&:(eq ?nombre4 "\"gusto_video_juegos4\"")) (respuesta ?res&:(eq ?res TRUE)))
    =>
(assert(carreras_ingenieria_y_computacion carreras-ingenieria-y-computacion))
)




(defrule sin_preferencia
(not ( administrativas_y_Contables  ?))
(not (carreras_artisticas ?))
(not (carreras_ciencias_exactas ?))
(not (carreras_defensa_y_seguridad ?))
(not (carreras_humanisticas_y_sociales ?))
(not (carreras_ingenieria_y_computacion ?))
=>
(assert (sin_preferencia sin-preferencia))



)
