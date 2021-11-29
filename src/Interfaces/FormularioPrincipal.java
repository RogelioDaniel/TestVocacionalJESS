package Interfaces;

import Institucion_Objetos.EncuestaAspirante;
import Institucion.InstitucionMotor;
import Institucion_Objetos.Aspirante;
import Institucion_Objetos.Gusto;
import Institucion.ManejadorEvento;
import Institucion.InstitucionEncuestaAspirante;
import Institucion.InstitucionAspirante;
import Institucion.InstitucionPregunta;
import Institucion.Ayudas;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import jess.JessException;

public class FormularioPrincipal extends javax.swing.JFrame {

    private String mensaje, titulo;
    private InstitucionMotor institucionMotor;
    private ManejadorEvento eventHandler;
    private InstitucionAspirante institucionAspirante;
    private Aspirante aspirante;
    private EncuestaAspirante encuestaAspirante;
    private InstitucionPregunta institucionPregunta;
    private InstitucionEncuestaAspirante institucionEncuestaAspirante;
    private int indiceCarreras, indiceGusto;

    public FormularioPrincipal() {
        initComponents();
        configurarJFrame();
        activarPanelDatosAspirante();
        mostrarListaPaises();
        institucionAspirante = new InstitucionAspirante();
    }

    private void configurarJFrame() {
        this.setTitle("Test de orientacion vocacional CHASIDE");
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void iniciarMotor() {
        try {
            institucionMotor = new InstitucionMotor();
        } catch (JessException ex) {
            System.out.println(ex.getMessage());
            //System.out.println("No jala");   
        }
        eventHandler = new ManejadorEvento(this);
        institucionMotor.agregarEscuchador(eventHandler);
    }

    private void limpiarDatosAspirante() {
        this.jtfNombre.setText("");
        this.jtfApellido.setText("");
        this.jcbPais.setSelectedIndex(0);
        this.jcbSexo.setSelectedIndex(0);
        this.jsEdad.setValue(1);
    }

    private void activarPanelPreguntasRespuestas() {
        jpDatosAspirante.setVisible(false);
        jpPreguntasRespuestas.setVisible(true);
        jtaResultados.setEditable(false);
        mostrarAspirante();
        configurarBotonComenzar();
        iniciarEncuesta();
    }

    private void mostrarAspirante() {
        lblAspirante.setText("<html><b>Aspirante: </b>" + aspirante.getApellido() + ", " + aspirante.getNombre() + "</html>");
    }

    private void configurarBotonComenzar() {
        jbtnComenzar.setText("Comenzar otro test");
        jbtnComenzar.setBackground(new Color(147, 145, 50));
    }

    private void iniciarEncuesta() {
        botonesRespuestaActivos(true, true);
        bgRespuestas.clearSelection();
        jtaResultados.setText("");
        iniciarMotor();
        institucionMotor.cargarCarrerasdeClips();
        institucionPregunta = InstitucionPregunta.getInstitucionPregunta(this);
        reiniciarEncuestaAspirante();
        institucionPregunta.reiniciarPreguntas();
        jbtnSiguientePregunta.setText("Siguiente pregunta");
    }

    public void reiniciarEncuestaAspirante() {
        encuestaAspirante = new EncuestaAspirante(aspirante);
        institucionEncuestaAspirante = new InstitucionEncuestaAspirante(encuestaAspirante);
    }

    public void finalizarEncuesta() {
        botonesRespuestaActivos(false, false);
        lblPregunta.setText("<html><b>¡Test Finalizado!</b></html>");
        lblCarrera.setText("");
        jbtnSiguientePregunta.setText("Volver a intentar");
        institucionEncuestaAspirante.guardarEncuesta();
    }

    private void activarPanelDatosAspirante() {
        jpPreguntasRespuestas.setVisible(false);
        jpDatosAspirante.setVisible(true);
        jbtnComenzar.setText("Comenzar Test");
        jbtnComenzar.setBackground(new Color(0, 102, 0));
    }

    private void mostrarIcono(String rutaIcono, JLabel label) {
        Image icono = new ImageIcon(rutaIcono).getImage();
        ImageIcon iconoImg = new ImageIcon(icono.getScaledInstance(56, 56, Image.SCALE_SMOOTH));
        label.setIcon(iconoImg);
    }

    private void mostrarListaPaises() {
        for (String pais : Ayudas.getPaises()) {
            jcbPais.addItem(pais);
        }
    }

    public void mostrarCarreras(String carrera) {
        lblCarrera.setText("<html><b>Carrera a evaluar: </b> " + carrera + "<html>");
    }

    public void setIndiceCarreras(int indiceCarreras) {
        this.indiceCarreras = indiceCarreras;
    }

    public void setIndiceGusto(int indiceGusto) {
        this.indiceGusto = indiceGusto;
    }

    public void mostrarPregunta(String pregunta) {
        lblPregunta.setText("<html>" + pregunta + "</html>");
    }

    public void agregarRespuesta(String respuesta) {
        // Si la respuesta no se encuentra ya en el JtextArea
        if (!jtaResultados.getText().contains(respuesta)) {
            institucionEncuestaAspirante.agregarEncuestaAspirante(respuesta);
            // Al principio, el resultado es sin preferencia
            // Si se agrega un carrera, se elimina el resultado de sin preferencia
            institucionEncuestaAspirante.verificarDiagnisticoSinPreferencia();
            jtaResultados.setText("");
            // Se agregan los resultados del aspirante 
            for (String resultado : encuestaAspirante.getResultados()) {
                jtaResultados.append(resultado + "\n");
            }
        }
    }

    private void botonesRespuestaActivos(boolean botonTrue, boolean botonFalse) {
        jrbTrue.setEnabled(botonTrue);
        jrbFalse.setEnabled(botonFalse);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgRespuestas = new javax.swing.ButtonGroup();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jpTitulo = new javax.swing.JPanel();
        lblTitulo1 = new javax.swing.JLabel();
        jpDatosAspirante = new javax.swing.JPanel();
        lblTitulo2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jtfNombre = new javax.swing.JTextField();
        jtfApellido = new javax.swing.JTextField();
        lblPais = new javax.swing.JLabel();
        jcbPais = new javax.swing.JComboBox();
        jcbSexo = new javax.swing.JComboBox();
        jsEdad = new javax.swing.JSpinner();
        lblApellido = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblEdad = new javax.swing.JLabel();
        jbtnSalir = new javax.swing.JButton();
        jbtnComenzar = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jpPreguntasRespuestas = new javax.swing.JPanel();
        lblTitulo3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblPregunta = new javax.swing.JLabel();
        jrbFalse = new javax.swing.JRadioButton();
        jrbTrue = new javax.swing.JRadioButton();
        lblRespuesta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaResultados = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        lblResultados = new javax.swing.JLabel();
        jbtnSiguientePregunta = new javax.swing.JButton();
        lblCarrera = new javax.swing.JLabel();
        lblAspirante = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpTitulo.setBackground(new java.awt.Color(255, 102, 0));
        jpTitulo.setForeground(new java.awt.Color(255, 255, 255));

        lblTitulo1.setFont(new java.awt.Font("Bernard MT Condensed", 1, 22)); // NOI18N
        lblTitulo1.setForeground(new java.awt.Color(255, 255, 255));
        lblTitulo1.setText("Test de orientacion Vocacional CHASIDE");

        javax.swing.GroupLayout jpTituloLayout = new javax.swing.GroupLayout(jpTitulo);
        jpTitulo.setLayout(jpTituloLayout);
        jpTituloLayout.setHorizontalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo1)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jpTituloLayout.setVerticalGroup(
            jpTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTituloLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblTitulo1)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        getContentPane().add(jpTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 110));

        jpDatosAspirante.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo2.setFont(new java.awt.Font("Bernard MT Condensed", 3, 24)); // NOI18N
        lblTitulo2.setText("Datos del aspirante");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lblPais.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblPais.setText("País:");

        jcbPais.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbPaisActionPerformed(evt);
            }
        });

        jcbSexo.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jcbSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino", "No especificar" }));

        jsEdad.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        jsEdad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 170, 1));

        lblApellido.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblApellido.setText("Apellido(s):");

        lblNombre.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblNombre.setText("Nombre(s): ");

        lblSexo.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblSexo.setText("Sexo:");

        lblEdad.setFont(new java.awt.Font("Maiandra GD", 1, 14)); // NOI18N
        lblEdad.setText("Edad:");

        javax.swing.GroupLayout jpDatosAspiranteLayout = new javax.swing.GroupLayout(jpDatosAspirante);
        jpDatosAspirante.setLayout(jpDatosAspiranteLayout);
        jpDatosAspiranteLayout.setHorizontalGroup(
            jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jpDatosAspiranteLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpDatosAspiranteLayout.createSequentialGroup()
                        .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblPais, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfNombre)
                            .addComponent(jtfApellido)
                            .addGroup(jpDatosAspiranteLayout.createSequentialGroup()
                                .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jcbPais, 0, 170, Short.MAX_VALUE)
                                        .addComponent(jcbSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jsEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(42, 42, 42))
        );
        jpDatosAspiranteLayout.setVerticalGroup(
            jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDatosAspiranteLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblTitulo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(jtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellido)
                    .addComponent(jtfApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPais)
                    .addComponent(jcbPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDatosAspiranteLayout.createSequentialGroup()
                        .addComponent(lblSexo)
                        .addGap(36, 36, 36)
                        .addGroup(jpDatosAspiranteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEdad)
                            .addComponent(jsEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jcbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(119, Short.MAX_VALUE))
        );

        getContentPane().add(jpDatosAspirante, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 660, 470));

        jbtnSalir.setBackground(new java.awt.Color(204, 0, 0));
        jbtnSalir.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        jbtnSalir.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSalir.setText("Salir");
        jbtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 590, 100, 50));

        jbtnComenzar.setBackground(new java.awt.Color(0, 102, 0));
        jbtnComenzar.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        jbtnComenzar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnComenzar.setText("Comenzar diagnóstico");
        jbtnComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnComenzarActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnComenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 590, 250, 50));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 640, -1));

        jpPreguntasRespuestas.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo3.setFont(new java.awt.Font("Bernard MT Condensed", 3, 24)); // NOI18N
        lblTitulo3.setText("Test Vocacional para las carreras del C.U. UAEM Zumpango");

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        lblPregunta.setFont(new java.awt.Font("Maiandra GD", 0, 15)); // NOI18N
        lblPregunta.setText("<html> Percibir situaciones o acontecimientos como amenazantes, incluso cuando no lo son. </html>");

        bgRespuestas.add(jrbFalse);
        jrbFalse.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jrbFalse.setText("No");

        bgRespuestas.add(jrbTrue);
        jrbTrue.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jrbTrue.setText("Si");
        jrbTrue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbTrueActionPerformed(evt);
            }
        });

        lblRespuesta.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        lblRespuesta.setText("Respuesta");

        jtaResultados.setColumns(20);
        jtaResultados.setRows(5);
        jScrollPane1.setViewportView(jtaResultados);

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));

        lblResultados.setFont(new java.awt.Font("Maiandra GD", 1, 16)); // NOI18N
        lblResultados.setText("Resultados");

        jbtnSiguientePregunta.setBackground(new java.awt.Color(0, 102, 153));
        jbtnSiguientePregunta.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jbtnSiguientePregunta.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSiguientePregunta.setText("Siguiente pregunta");
        jbtnSiguientePregunta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSiguientePreguntaActionPerformed(evt);
            }
        });

        lblCarrera.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblCarrera.setText("Carrera en evaluación:");

        lblAspirante.setFont(new java.awt.Font("Maiandra GD", 0, 14)); // NOI18N
        lblAspirante.setText("Aspirante:");

        javax.swing.GroupLayout jpPreguntasRespuestasLayout = new javax.swing.GroupLayout(jpPreguntasRespuestas);
        jpPreguntasRespuestas.setLayout(jpPreguntasRespuestasLayout);
        jpPreguntasRespuestasLayout.setHorizontalGroup(
            jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                        .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPregunta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTitulo3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jpPreguntasRespuestasLayout.createSequentialGroup()
                                .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblResultados)
                                    .addComponent(lblRespuesta))
                                .addGap(42, 42, 42)
                                .addComponent(jrbTrue)
                                .addGap(72, 72, 72)
                                .addComponent(jrbFalse)
                                .addGap(47, 47, 47)
                                .addComponent(jbtnSiguientePregunta))
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                        .addComponent(lblCarrera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAspirante)
                        .addGap(228, 228, 228))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPreguntasRespuestasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpPreguntasRespuestasLayout.setVerticalGroup(
            jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblTitulo3)
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAspirante, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(lblCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jpPreguntasRespuestasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrbTrue)
                            .addComponent(jrbFalse)
                            .addComponent(jbtnSiguientePregunta))
                        .addGap(17, 17, 17))
                    .addGroup(jpPreguntasRespuestasLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblRespuesta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(lblResultados)
                        .addGap(12, 12, 12)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(jpPreguntasRespuestas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 660, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jbtnSalirActionPerformed

    private void jbtnComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnComenzarActionPerformed
        String nombre, apellido, pais, sexo;
        int edad;
        boolean aspiranteValido;
        if (jpDatosAspirante.isVisible()) {
            aspiranteValido = institucionAspirante.aspiranteValido(jtfNombre.getText(), jtfApellido.getText());
            if (aspiranteValido) {
                nombre = jtfNombre.getText();
                apellido = jtfApellido.getText();
                pais = (String) jcbPais.getSelectedItem();
                sexo = (String) jcbSexo.getSelectedItem();
                edad = (int) jsEdad.getValue();
                aspirante = new Aspirante(nombre, apellido, pais, sexo, edad);
                activarPanelPreguntasRespuestas();
            } else {
                mensaje = "Error, verifique el formato en los campos Nombre y Apellido. \n \n "
                        + "No se admite: \n "
                        + "- Campo vacío \n "
                        + "- Números \n "
                        + "- Símbolos";
                titulo = "Error";
                JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            limpiarDatosAspirante();
            activarPanelDatosAspirante();
        }
    }//GEN-LAST:event_jbtnComenzarActionPerformed

    private void jcbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbPaisActionPerformed

    private void jbtnSiguientePreguntaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSiguientePreguntaActionPerformed
        Gusto gusto;

        if (!jrbTrue.isSelected() && !jrbFalse.isSelected()) {
            mensaje = "Error, para ir a la siguiente pregunta debe seleccionar una respuesta.";
            titulo = "Error";
            JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
        } else {
            if (jbtnSiguientePregunta.getText().equals("Siguiente pregunta")) {
                institucionEncuestaAspirante.setRespuestaGustoCarrera(jrbTrue.isSelected(), indiceCarreras, indiceGusto);
                gusto = institucionEncuestaAspirante.getInclinacionCarrera(indiceCarreras, indiceGusto);
                try {
                    institucionMotor.agregarGusto(gusto);
                } catch (JessException ex) {
                    System.out.println("Error al agregar el gusto. " + ex.getMessage());
                }
                if (jrbFalse.isSelected()) {
                    institucionPregunta.pasarSiguienteRamaOrArbol();
                }
                institucionPregunta.determinarSiguientePreguntaoCarrera();
            } else {
                // Si se hace click en el botón con el texto Volver a intentar
                iniciarEncuesta();
            }
        }
    }//GEN-LAST:event_jbtnSiguientePreguntaActionPerformed

    private void jrbTrueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbTrueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jrbTrueActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgRespuestas;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton jbtnComenzar;
    private javax.swing.JButton jbtnSalir;
    private javax.swing.JButton jbtnSiguientePregunta;
    private javax.swing.JComboBox jcbPais;
    private javax.swing.JComboBox jcbSexo;
    private javax.swing.JPanel jpDatosAspirante;
    private javax.swing.JPanel jpPreguntasRespuestas;
    private javax.swing.JPanel jpTitulo;
    private javax.swing.JRadioButton jrbFalse;
    private javax.swing.JRadioButton jrbTrue;
    private javax.swing.JSpinner jsEdad;
    private javax.swing.JTextArea jtaResultados;
    private javax.swing.JTextField jtfApellido;
    private javax.swing.JTextField jtfNombre;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblAspirante;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblPregunta;
    private javax.swing.JLabel lblRespuesta;
    private javax.swing.JLabel lblResultados;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JLabel lblTitulo2;
    private javax.swing.JLabel lblTitulo3;
    // End of variables declaration//GEN-END:variables
}
