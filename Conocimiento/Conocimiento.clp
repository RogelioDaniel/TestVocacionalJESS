(deftemplate preguntas ;Creamos preguntas 
	(slot texto)
	(slot tipo)
	(slot identidad)
	(slot aux)
)

(deftemplate respuestas    ;Respuestas
	(slot identidad)
	(slot texto)
	
)

(deftemplate recomendacion ;Recomendaciones
	(slot carreraadecuada)
	(slot instructions)
)

(deftemplate detalles     ;Detalles
	(slot carreraadecuada)
	(slot details)
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;							
;;;;;;;;;;;;; cabecera
;;;;;;;;;;;;;							
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmodule inicio)

(defrule cabecera  
=> 
	(printout t "Escribe tu nombre y aprieta Enter ")
	(bind ?name (read))
	(printout t crlf "********************" crlf)
	(printout t "Hola, " ?name "." crlf)
	(printout t "Bienvenido al programa de Orientacion Vocacional" crlf crlf)
	(printout t "Responde " crlf)
	(printout t "Te dire que carreras son las mejores para ti" crlf)
	(printout t "y la caracteristica que la define" crlf)
	(printout t "*******************" crlf crlf)
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deffunction es-de-tipo (?respuestas ?tipo)
													;Funcion que evalua que esten bien escritas las respuestas
	
	(if (eq ?tipo si-no) then
		(return (or (eq ?respuestas si) (eq ?respuestas no) ))
	else 
	(if (eq ?tipo number) then
		(return (numberp ?respuestas) )
	
	else 
		(return (> (str-length ?respuestas) 0) )
	)
	)
)

(deffunction pregunta-usuario (?preguntas ?tipo) 
													;Se hace una pregunta y se regresa la respuesta
	(bind ?respuestas "")
	(while (not (es-de-tipo ?respuestas ?tipo)) do
		(printout t ?preguntas " ")
		(if (eq ?tipo si-no) then
			(printout t "(si o no) "))
		(bind ?respuestas (read)))
	(return ?respuestas)
)





(defmodule ask)

(defrule ask::ask-preguntas-by-id


	(declare (auto-focus TRUE))
	
	(MAIN::preguntas (identidad ?id) (texto ?texto) (tipo ?tipo))
	
	(not (MAIN::respuestas (identidad ?id)))
	
	?ask <- (MAIN::ask ?id)
	=>
	
	(bind ?respuestas (pregunta-usuario ?texto ?tipo))
	
	(assert (MAIN::respuestas (identidad ?id) (texto ?respuestas)))

	(retract ?ask)
	
	(return)
	
	
)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deffacts preguntas-data ;HECHOS
	
	(preguntas (identidad artes) (tipo si-no) 
		
		
		(texto " ??Ilustrar, dibujar y animar digitalmente?"))


	(preguntas (identidad cienciatecnologia) (tipo si-no)
		(texto "??Dise??ar programas de computaci??n y explorar nuevas aplicaciones tecnol??gicas para uso del internet.?"))
                
	(preguntas (identidad cienciasecologicas) (tipo si-no)
		(texto "??Criar, cuidar y tratar animales dom??sticos y de campo?"))


	(preguntas (identidad economia) (tipo si-no)
		(texto "??Seleccionar, capacitar y motivar al personal de una organizaci??n o empresa?"))

	(preguntas (identidad cienciassociales) (tipo si-no)
		(texto "??Realizar excavaciones para descubrir restos del pasado.?"))


	(preguntas (identidad cienciasecologicas) (tipo si-no)
		(texto "??Dise??ar cursos para ense??ar a la gente sobre temas de salud e higiene.?"))
         
      
     )
            
  (defglobal ?*crlf* = "
")    
	


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;							
;;;;;;;;;;;;;	 Preguntar		
;;;;;;;;;;;;;							
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defmodule entrevista) 


(defrule request-cienciasecologicas
=> 
	(assert (ask cienciasecologicas)))

(defrule request-cienciatecnologia
=>
	(assert (ask cienciatecnologia)))

(defrule request-artes
=> 
	(assert (ask artes)))

        
(defrule request-economia
=>
	(assert (ask economia)))
	
(defrule request-cienciassociales
=>
	(assert (ask cienciassociales)))
	

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;; REGLAS					
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defmodule recomendacion)

(defrule Arte-y-creatividad

	(respuestas (identidad artes) (texto si)) 
	(respuestas (identidad cienciassociales) (texto no))
    (respuestas (identidad cienciasecologicas) (texto no))
	(respuestas (identidad cienciatecnologia) (texto no))
    (respuestas (identidad economia) (texto no))
	=>
	(bind ?in "Dise??o gr??fico, dise??o y decoraci??n de interiores, dise??o de jardines, dise??o de 
modas, dise??o de joyas, artes pl??sticas (pintura, escultura, danza, teatro, artesan??a, 
cer??mica), dibujo publicitario, restauraci??n y museolog??a, modelaje, fotograf??a, 
gesti??n gr??fica y publicitaria, locuci??n y publicidad, actuaci??n, camarograf??a, arte 
industrial, producci??n audiovisual y multimedia, comunicaci??n y producci??n en 
radio y televisi??n, dise??o del paisaje, cine y video, comunicaci??n esc??nica para 
televisi??n, m??sica")
	(assert (recomendacion (carreraadecuada Artes) (instructions ?in)))
)

(defrule Ciencias-sociales

	(respuestas (identidad cienciassociales) (texto si))
	(respuestas (identidad cienciasecologicas) (texto no))
	(respuestas (identidad cienciatecnologia) (texto no))
    (respuestas (identidad economia) (texto no))
	(respuestas (identidad artes) (texto no))
	=>
	(bind ?in "Psicolog??a, trabajo social, idiomas, educaci??n internacional, historia y geograf??a, 
periodismo, periodismo digital, derecho, ciencias pol??ticas, sociolog??a, antropolog??a, 
arqueolog??a, gesti??n social y desarrollo, consejer??a familiar, comunicaci??n y 
publicidad, administraci??n educativa, educaci??n especial, psicopedagog??a, 
estimulaci??n temprana, traducci??n simult??nea, ling????stica, educaci??n de p??rvulos, 
bibliotecolog??a, museolog??a, relaciones internacionales y diplomacia, comunicaci??n 
social con ??nfasis en m??rquetin y gesti??n de empresas, redacci??n creativa y 
publicitaria, relaciones p??blicas y comunicaci??n organizacional, hoteler??a y turismo, 
teolog??a, instituci??n sacerdotal")
	(assert (recomendacion (carreraadecuada C-Sociales) (instructions ?in)))
)

(defrule Economia
        
    (respuestas (identidad economia) (texto si))
	(respuestas (identidad cienciassociales) (texto no))
	(respuestas (identidad cienciasecologicas) (texto no))
	(respuestas (identidad cienciatecnologia) (texto no))
	(respuestas (identidad artes) (texto no))

	=>
	(bind ?in "Administraci??n de empresas, contabilidad, auditor??a, ventas, m??rquetin estrat??gico, 
gesti??n y negocios internacionales, gesti??n empresarial, gesti??n financiera, ingenier??a 
comercial, comercio exterior, banca y finanzas, gesti??n de recursos humanos, 
comunicaciones integradas en m??rquetin, administraci??n de empresas ecotur??sticas y 
de hospitalidad, ciencias econ??micas y financieras, administraci??n y ciencias pol??ticas, 
ciencias empresariales, comercio electr??nico, emprendimiento, gesti??n de organismos 
p??blicos (municipios, ministerios, etc.), gesti??n de centros educativos.")
	(assert (recomendacion (carreraadecuada Economia) (instructions ?in)))
)

(defrule Ciencia-y-tecno

    (respuestas (identidad cienciatecnologia) (texto si))
	(respuestas (identidad cienciassociales) (texto no))
	(respuestas (identidad cienciasecologicas) (texto no))
    (respuestas (identidad economia) (texto no))
	(respuestas (identidad artes) (texto no))

	=>
	(bind ?in "Ingenier??a en sistemas computacionales, geolog??a, ingenier??a civil, arquitectura, 
electr??nica, telem??tica, telecomunicaciones, ingenier??a mecatr??nica (rob??tica), 
imagen y sonido, minas, petr??leo y metalurgia, ingenier??a mec??nica, ingenier??a 
industrial, f??sica, matem??ticas aplicadas, ingenier??a en estad??stica, ingenier??a 
automotriz, biotecnolog??a ambiental, ingenier??a geogr??fica, carreras militares (marina, 
aviaci??n, ej??rcito), ingenier??a en costas y obras portuarias, estad??stica inform??tica, 
programaci??n y desarrollo de sistemas, tecnolog??a en inform??tica educativa, 
astronom??a, ingenier??a en ciencias geogr??ficas y desarrollo sustentable.
")
	(assert (recomendacion (carreraadecuada Ciencia-y-Tecnologia)	(instructions ?in)))
)

(defrule Ciencias-eco

	(respuestas (identidad cienciassociales) (texto no))
	(respuestas (identidad cienciasecologicas) (texto si))
	(respuestas (identidad cienciatecnologia) (texto no))
    (respuestas (identidad economia) (texto no))
	(respuestas (identidad artes) (texto no))
	=>
	(bind ?in "Biolog??a, bioqu??mica, farmacia, biolog??a marina, bioan??lisis, biotecnolog??a, 
ciencias ambientales, zootecnia, veterinaria, nutrici??n y est??tica, cosmetolog??a, 
diet??tica y est??tica, medicina, obstetricia, urgencias m??dicas, odontolog??a, 
enfermer??a, tecnolog??a, oceanograf??a y ciencias ambientales, agronom??a, 
horticultura y fruticultura, ingenier??a de alimentos, gastronom??a, cultura f??sica, 
deportes y rehabilitaci??n, gesti??n ambiental, ingenier??a ambiental, optometr??a, 
homeopat??a, reflexolog??a")
	(assert (recomendacion (carreraadecuada Ciencias-ecologicas)	(instructions ?in)))
)

(defrule Nada

	(respuestas (identidad cienciassociales) (texto no))
	(respuestas (identidad cienciasecologicas) (texto no))
	(respuestas (identidad cienciatecnologia) (texto no))
    (respuestas (identidad economia) (texto no))
	(respuestas (identidad artes) (texto no))
	=>
	(bind ?in "No tienes interes en ningun ambito")
	(assert (recomendacion (carreraadecuada Indefinido)	(instructions ?in)))
)
(defrule Todo

	(respuestas (identidad cienciassociales) (texto si))
	(respuestas (identidad cienciasecologicas) (texto si))
	(respuestas (identidad cienciatecnologia) (texto si))
    (respuestas (identidad economia) (texto si))
	(respuestas (identidad artes) (texto si))
	=>
	(bind ?in "Todo te interesa, debes definir algo especifico")
	(assert (recomendacion (carreraadecuada Indefinido)	(instructions ?in)))
)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;											   
;;;;;;;;;;; Recomendacion de carrera
;;;;;;;;;;;											   
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(defmodule Salida)

(defrule sort-and-print
	(recomendacion (carreraadecuada ?s) (instructions ?i))
	=>
	(printout t crlf crlf)
	(printout t "Tu carrera adecuada sera: " ?s crlf)
	(printout t "Caracteristicas: " ?i crlf crlf)
	(assert (detalles (carreraadecuada ?s)))

)


