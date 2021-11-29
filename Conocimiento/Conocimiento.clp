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
		
		
		(texto " ¿Ilustrar, dibujar y animar digitalmente?"))


	(preguntas (identidad cienciatecnologia) (tipo si-no)
		(texto "¿Diseñar programas de computación y explorar nuevas aplicaciones tecnológicas para uso del internet.?"))
                
	(preguntas (identidad cienciasecologicas) (tipo si-no)
		(texto "¿Criar, cuidar y tratar animales domésticos y de campo?"))


	(preguntas (identidad economia) (tipo si-no)
		(texto "¿Seleccionar, capacitar y motivar al personal de una organización o empresa?"))

	(preguntas (identidad cienciassociales) (tipo si-no)
		(texto "¿Realizar excavaciones para descubrir restos del pasado.?"))


	(preguntas (identidad cienciasecologicas) (tipo si-no)
		(texto "¿Diseñar cursos para enseñar a la gente sobre temas de salud e higiene.?"))
         
      
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
	(bind ?in "Diseño gráfico, diseño y decoración de interiores, diseño de jardines, diseño de 
modas, diseño de joyas, artes plásticas (pintura, escultura, danza, teatro, artesanía, 
cerámica), dibujo publicitario, restauración y museología, modelaje, fotografía, 
gestión gráfica y publicitaria, locución y publicidad, actuación, camarografía, arte 
industrial, producción audiovisual y multimedia, comunicación y producción en 
radio y televisión, diseño del paisaje, cine y video, comunicación escénica para 
televisión, música")
	(assert (recomendacion (carreraadecuada Artes) (instructions ?in)))
)

(defrule Ciencias-sociales

	(respuestas (identidad cienciassociales) (texto si))
	(respuestas (identidad cienciasecologicas) (texto no))
	(respuestas (identidad cienciatecnologia) (texto no))
    (respuestas (identidad economia) (texto no))
	(respuestas (identidad artes) (texto no))
	=>
	(bind ?in "Psicología, trabajo social, idiomas, educación internacional, historia y geografía, 
periodismo, periodismo digital, derecho, ciencias políticas, sociología, antropología, 
arqueología, gestión social y desarrollo, consejería familiar, comunicación y 
publicidad, administración educativa, educación especial, psicopedagogía, 
estimulación temprana, traducción simultánea, lingüística, educación de párvulos, 
bibliotecología, museología, relaciones internacionales y diplomacia, comunicación 
social con énfasis en márquetin y gestión de empresas, redacción creativa y 
publicitaria, relaciones públicas y comunicación organizacional, hotelería y turismo, 
teología, institución sacerdotal")
	(assert (recomendacion (carreraadecuada C-Sociales) (instructions ?in)))
)

(defrule Economia
        
    (respuestas (identidad economia) (texto si))
	(respuestas (identidad cienciassociales) (texto no))
	(respuestas (identidad cienciasecologicas) (texto no))
	(respuestas (identidad cienciatecnologia) (texto no))
	(respuestas (identidad artes) (texto no))

	=>
	(bind ?in "Administración de empresas, contabilidad, auditoría, ventas, márquetin estratégico, 
gestión y negocios internacionales, gestión empresarial, gestión financiera, ingeniería 
comercial, comercio exterior, banca y finanzas, gestión de recursos humanos, 
comunicaciones integradas en márquetin, administración de empresas ecoturísticas y 
de hospitalidad, ciencias económicas y financieras, administración y ciencias políticas, 
ciencias empresariales, comercio electrónico, emprendimiento, gestión de organismos 
públicos (municipios, ministerios, etc.), gestión de centros educativos.")
	(assert (recomendacion (carreraadecuada Economia) (instructions ?in)))
)

(defrule Ciencia-y-tecno

    (respuestas (identidad cienciatecnologia) (texto si))
	(respuestas (identidad cienciassociales) (texto no))
	(respuestas (identidad cienciasecologicas) (texto no))
    (respuestas (identidad economia) (texto no))
	(respuestas (identidad artes) (texto no))

	=>
	(bind ?in "Ingeniería en sistemas computacionales, geología, ingeniería civil, arquitectura, 
electrónica, telemática, telecomunicaciones, ingeniería mecatrónica (robótica), 
imagen y sonido, minas, petróleo y metalurgia, ingeniería mecánica, ingeniería 
industrial, física, matemáticas aplicadas, ingeniería en estadística, ingeniería 
automotriz, biotecnología ambiental, ingeniería geográfica, carreras militares (marina, 
aviación, ejército), ingeniería en costas y obras portuarias, estadística informática, 
programación y desarrollo de sistemas, tecnología en informática educativa, 
astronomía, ingeniería en ciencias geográficas y desarrollo sustentable.
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
	(bind ?in "Biología, bioquímica, farmacia, biología marina, bioanálisis, biotecnología, 
ciencias ambientales, zootecnia, veterinaria, nutrición y estética, cosmetología, 
dietética y estética, medicina, obstetricia, urgencias médicas, odontología, 
enfermería, tecnología, oceanografía y ciencias ambientales, agronomía, 
horticultura y fruticultura, ingeniería de alimentos, gastronomía, cultura física, 
deportes y rehabilitación, gestión ambiental, ingeniería ambiental, optometría, 
homeopatía, reflexología")
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


