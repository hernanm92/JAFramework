package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;

import Annotations.AnnotationParaFramework;


public class Formulario<T> extends JFrame 
{   
	public JPanel AltaPanel; // Panel que contiene los controles para los datos de las altas.
	public JTable Tabla; // Tabla con la lista de los elementos dados de alta
	public JScrollPane ScrollPaneTabla; 
	public DefaultTableModel dtm;
	public Framework<T> fw;
	public JPanel ListaPanel; // 
	public JPanel BotonesPanel; // Panel con los botones ALTA BAJA y MODIFICAR
	public JButton BotonAlta;
	public JButton BotonBaja;
	public JButton BotonModificar;
	public JButton BotonCancelar;
	public JButton BotonEjecutarMetodoDefinidoPorElUsuario;
	public JScrollPane AltaScroll;
	public JTextField Buscador;
	public String TextoBuscado = "";
	
	
    public Formulario(Framework<T> framework) 
    {
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	fw = framework;
        this.setLayout(null);
        setBounds(150,150,800,600);
        setTitle("ABM FRAMEWORK ALGORITMOS II");
        setResizable(false);
        AgregarPanelDeAlta();
        AgregarPanelDeLista();
        AgregarBuscador();
        AgregarPanelBotones();
        AgregarBotonSubir();
        AgregarBotonBajar();
        AgregarBotonParaEjecutarMetodoDefinidoPorElUsuario();
        AgregarBotonAlta();
        AgregarBotonBaja();
        AgregarBotonModificar();
        AgregarBotonCancelar();
        
        
    }
    
    public void AgregarBuscador()
    {
    	Buscador = new JTextField();
    	Buscador.setBounds(15,0,350,25);
    	Buscador.getDocument().addDocumentListener(new DocumentListener()
		{
			
			@Override
			public void removeUpdate(DocumentEvent e)
			{				
				if(!TextoBuscado.contentEquals(Buscador.getText()))
				{
					ActualizarTabla();
					TextoBuscado = Buscador.getText();
				}
				
				
			}
			
			@Override
			public void insertUpdate(DocumentEvent e)
			{				
				if(!TextoBuscado.contentEquals(Buscador.getText()))
				{
					ActualizarTabla();
					TextoBuscado = Buscador.getText();
				}
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e)
			{				
				//ActualizarTabla();
				
			}
		});
    	JPanel elpanel = new JPanel(null);
    	elpanel.setBounds(400,0,400,20);
    	elpanel.add(Buscador);
    	this.add(elpanel);
    	
    }
   
    public void AgregarPanelBotones() // Panel que contiene los botones ALTA BAJA Y MODIFICAR
    {
    	BotonesPanel = new JPanel();
    	BotonesPanel.setLayout(new FlowLayout());
    	BotonesPanel.setBounds(0,500,450,80);
    	add(BotonesPanel);
    }
    
    public void AgregarPanelDeAlta()  // Panel que contiene los campos para completar datos
    {
    	
    	AltaPanel = new JPanel();
        AltaPanel.setLayout(null);
        AltaPanel.setBounds(0,0,400,500);
        add(AltaPanel);
        
    }
    
    public void AgregarPanelDeLista() // Panel que contiene la lista de los datos ya cargados
    {
    	ListaPanel = new JPanel();
    	ListaPanel.setLayout(new FlowLayout());
    	ListaPanel.setBounds(400,20,400,780);
    	add(ListaPanel);
    }
     
    public void AgregarBotonSubir()  // Boton para subir en los controles cuando son demasiados y no entran en pantalla
    {
    	JButton scrollUp = new JButton("Subir");
    	scrollUp.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				MoverControlesParaAbajo();
				
			}
		});
        scrollUp.setBounds(320,0,70,30);
        scrollUp.setFocusable(false);
        scrollUp.setName("ScrollUp");
        AltaPanel.add(scrollUp);
        
    }
    
    public void AgregarBotonBajar() // Lo mismo que el otro, pero al revéz
    {
    	JButton scrollDown = new JButton("Bajar");        
        scrollDown.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				MoverControlesParaArriba();
				
			}
		});
        scrollDown.setBounds(320,450,70,30);
        scrollDown.setFocusable(false);
        scrollDown.setName("ScrollDown");
        AltaPanel.add(scrollDown);
    }

    public void MoverControlesParaAbajo() 
    {
    	if(AltaPanel.getComponents()[2].location().y<10)
    	{
    		for (Component comp:AltaPanel.getComponents())
            {    			
    			if(comp.getName()!="ScrollDown" && comp.getName()!="ScrollUp") 
    				comp.setLocation(comp.location().x,comp.location().y+10);         
            }
    	}
    }

    public void MoverControlesParaArriba()
    {
    	if(AltaPanel.getComponents()[AltaPanel.getComponents().length-1].location().y>400)
    	{
    		for (Component comp:AltaPanel.getComponents())
            {
    			if(comp.getName()!="ScrollDown" && comp.getName()!="ScrollUp") 
    				comp.setLocation(comp.location().x,comp.location().y-10);
    			         
            }
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//-------------------Botones ---------------------    
    public void AgregarBotonParaEjecutarMetodoDefinidoPorElUsuario()
    {
    	BotonEjecutarMetodoDefinidoPorElUsuario = new JButton("***");
    	BotonEjecutarMetodoDefinidoPorElUsuario.setToolTipText("Ejecutar método definido por el usuario");
    	BotonEjecutarMetodoDefinidoPorElUsuario.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseClicked(MouseEvent e)
			{					
				fw.AccionarMetodoDeUsuario();
				
				
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
		});
    	BotonesPanel.add(BotonEjecutarMetodoDefinidoPorElUsuario);	
    }
    
    public void AgregarBotonAlta()
    {    	
    	BotonAlta = new JButton("ALTA");
    	BotonAlta.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseClicked(MouseEvent e)
			{					
				if(CrearInstanciaDeElementoConLosDatosEnElPanelDeAlta() && fw.DarAlta(fw.ObjetoTratado))
				{
					if(fw.ObjetoTratado == null) VaciarCampos();
					ActualizarTabla();
					
					
				}
				
				
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
		});
    	BotonesPanel.add(BotonAlta);
    }
    
    public void AgregarBotonBaja()
    {
    	BotonBaja = new JButton("BAJA");
    	BotonBaja.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				fw.DarBaja(fw.ObjetoTratado);				
				if(fw.ObjetoTratado == null) VaciarCampos();
				ActualizarTabla();
				BotonAlta.setVisible(true);
	    		BotonBaja.setVisible(false);
	    		BotonModificar.setVisible(false);
	    		BotonCancelar.setVisible(false);
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
		});
    	BotonesPanel.add(BotonBaja); 
    	BotonBaja.setVisible(false);
    }
    
    public void AgregarBotonModificar()
    {
    	BotonModificar = new JButton("MODIFICAR");
    	BotonModificar.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(CrearInstanciaDeElementoConLosDatosEnElPanelDeAlta())
				{					
					fw.Modificar(fw.ObjetoParaModificar,fw.ObjetoTratado);	
					if(fw.ObjetoTratado == null) VaciarCampos();
					ActualizarTabla();
					BotonAlta.setVisible(true);
		    		BotonBaja.setVisible(false);
		    		BotonModificar.setVisible(false);
		    		BotonCancelar.setVisible(false);					
				}				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
		});
    	BotonesPanel.add(BotonModificar);
    	BotonModificar.setVisible(false);
    }
    
    public void AgregarBotonCancelar()
    {    	
    	BotonCancelar = new JButton("Cancelar");
    	BotonCancelar.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				BotonAlta.setVisible(true);
	    		BotonBaja.setVisible(false);
	    		BotonModificar.setVisible(false);
	    		BotonCancelar.setVisible(false);
	    		Tabla.clearSelection();
	    		VaciarCampos();
				
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				
				
			}
		});
    	BotonesPanel.add(BotonCancelar);
    	BotonCancelar.setVisible(false);
    }
        

    
    
    // ----------------- TABLA ------------------------
    public void AgregarTabla()

    {
    	//Instanciar el modelo de tabla
    	dtm = new DefaultTableModel(fw.Listar(Buscador.getText()).size(),fw.objetoClass.getFields().length)
    	{
    		 // Hacer que las celdas no sean editables
    	      public boolean isCellEditable(int rowIndex, int mColIndex) {
    	          return false;
    	        }
    	};				
    	
    	//Crear tabla
    	Tabla = new JTable(dtm);
    	Tabla.setCellSelectionEnabled(false);  // Que no se puedan seleccionar celdas sueltas
		Tabla.setRowSelectionAllowed(true); // Que se pueda seleccionar de a filas
		Tabla.setColumnSelectionAllowed(false); // Que no se pueda seleccionar de a columnas
        Tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Tabla.setPreferredScrollableViewportSize(new Dimension(350,500));
        Tabla.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
        AgregarDatosATabla();				
		Tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			
			@Override
			public void valueChanged(ListSelectionEvent e)
			{				
				SeleccionarItem();				
			}
		});
        
        
		AgregarScrollPanel(Tabla);   // Se inserta la tabla en un ScrollPanel para que se pueda scrollear
		AgregarTitulosAColumnasDeTabla(Tabla);
		AjustarColumnasDeTabla(Tabla);		
		
    }
    public void AgregarScrollPanel(Component componente)
    {
    	ScrollPaneTabla = new JScrollPane(componente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	ListaPanel.add(ScrollPaneTabla);
    	
    	
    }
    public void AgregarDatosATabla()
    {
    	// Agregar datos a la tabla
        int j=0;
        for(T obj:fw.Listar(Buscador.getText())) // FILAS<----------
        {			        	
        	int i = 0;
        	for(Field campo :fw.objetoClass.getFields()) //  COLUMNAS<----------
        	{        		
        		dtm.setValueAt(fw.InvocarGetter(obj,campo.getName()),j,i);
        		
        		i++;
        		
        	}        	
        	j++;
        }
    }
    
    public void AgregarTitulosAColumnasDeTabla(JTable tabla)
    {
    	int i = 0;
    	for(Field field : fw.objetoClass.getFields())
    	{
    		
    		tabla.getColumnModel().getColumn(i).setHeaderValue(((AnnotationParaFramework)field.getAnnotations()[0]).label().toUpperCase().substring(0,1)
    				+((AnnotationParaFramework)field.getAnnotations()[0]).label().substring(1));
    		i++;
    	}
    }
    
    public void AjustarColumnasDeTabla(JTable tabla) // Ajusta el tamaño de las columnas para que se vea todo su contenido
    {
	    TableColumnModel columnModel = tabla.getColumnModel();
	    for (int columna = 0; columna < tabla.getColumnCount(); columna++) 
	    {
	        int AnchoDeColumna = 50; // Minimo ancho
	        for (int fila = 0; fila < tabla.getRowCount(); fila++) 
	        {	            
	        	AnchoDeColumna = Math.max(Tabla.getValueAt(fila,columna).toString().length()*10, AnchoDeColumna);
	        }
	        columnModel.getColumn(columna).setPreferredWidth(AnchoDeColumna);
	    }
	}    
    public void ActualizarTabla() // Actualiza la tabla para cuando se hace un cambio
    {      	
    	ListaPanel.remove(ScrollPaneTabla);
    	AgregarTabla();
    	ListaPanel.validate();
    	
    }
    public void SeleccionarItem()
    {
    	int columnaIndex = Tabla.getSelectedRow();
    	if(columnaIndex >= 0)     // Si se selecciono un elemento de la lista, cargarlo en el alta panel y habilitar botones correspondientes
    		{
    		CargarDatosDeSeleccion(fw.listaObjetos.get(columnaIndex));
    		fw.ObjetoTratado = fw.listaObjetos.get(columnaIndex);
    		fw.ObjetoParaModificar = fw.ObjetoTratado;
    		BotonAlta.setVisible(false);
    		BotonBaja.setVisible(true);
    		BotonModificar.setVisible(true);
    		BotonCancelar.setVisible(true);
    		
    		}
    	else   // Sino oculta boton modificar y boton baja
    		{
    		BotonAlta.setVisible(true);
    		BotonBaja.setVisible(false);
    		BotonModificar.setVisible(false);
    		BotonCancelar.setVisible(false);
    		
    		}
    }
    
    
    
    // -------------- Métodos varios -----------------------
    public void VaciarCampos() // Vacia todos los campos de datos del panel de alta
    {
    	for(Component comp: AltaPanel.getComponents())
    	{    		
    		if(comp.getClass()==javax.swing.JTextField.class) //Vaciar TEXTFIELDS
    		{
    			JTextField tb = (JTextField)comp;
    			tb.setText("");
    		}
    		else if(comp.getClass()==javax.swing.JComboBox.class) 
    		{
    			JComboBox cb = (JComboBox)comp;
    			cb.setSelectedIndex(0);
    		}
    		else if(comp.getClass()==javax.swing.JCheckBox.class)
    		{
    			JCheckBox cb = (JCheckBox)comp;
    			cb.setSelected(false);
    		}
    		//Agregar "Vaciar" para otro tipo de controles
    	}
    }
    
    public void CargarDatosDeSeleccion(T elemento) // Carga los datos del elemento seleccionado en la lista
    {    	
    	for(Field field: elemento.getClass().getFields())
    	{
    		AnnotationParaFramework annot = field.getAnnotation(AnnotationParaFramework.class);
    		Component componenteParaActualizar = null;
    		for(Component comp: AltaPanel.getComponents())
    		{
    			if (comp.getName().substring(0, comp.getName().length()-2).contentEquals(annot.label()))
    			{       				
    				componenteParaActualizar = comp;    				
    			}
    		}
    		if(componenteParaActualizar.getClass()==JTextField.class)
    		{    			
    			JTextField campo = (JTextField)componenteParaActualizar;
    			campo.setText(fw.InvocarGetter(elemento,field.getName()).toString());
    		}
    		else if(componenteParaActualizar.getClass()==JComboBox.class)
    		{    			
    			JComboBox campo = (JComboBox)componenteParaActualizar;
    			campo.setSelectedItem(fw.InvocarGetter(elemento,field.getName()).toString());
    		}
    		else if(componenteParaActualizar.getClass()==JCheckBox.class)
    		{    			
    			JCheckBox campo = (JCheckBox)componenteParaActualizar;
    			boolean checked = (boolean)fw.InvocarGetter(elemento,field.getName());
    			campo.setSelected(checked);
    		}
    		//Completar 
    	}
    }
    
    public boolean CrearInstanciaDeElementoConLosDatosEnElPanelDeAlta()
    {
    	fw.GenerarInstancia();
    	ArrayList<String> Atributos = new ArrayList<>();
    	for(Component comp: AltaPanel.getComponents())
    	{
    		if(comp.getClass()==JTextField.class)
    		{
    			JTextField textField = (JTextField)comp;
    			Atributos.add(textField.getText());
    		}
    		else if(comp.getClass()==JComboBox.class)
    		{
    			JComboBox comboBox = (JComboBox)comp;
    			Atributos.add(comboBox.getSelectedItem().toString());
    		}
    		else if(comp.getClass()==JCheckBox.class)
    		{
    			JCheckBox checkBox = (JCheckBox)comp;
    			String checked = "false";
    			if(checkBox.isSelected()) checked = "true";
    			Atributos.add(checked);
    		}
    		//completar con los otros tipos de componentes
    	}
    	if(!fw.CargarleAtributosALaInstancia(Atributos)) return false;
    	return true;
    }
    
    
}