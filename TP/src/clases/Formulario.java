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
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

public class Formulario<T> extends JFrame 
{   
	public JPanel AltaPanel;
	public JTable Tabla;
	public JScrollPane ScrollPane;
	public DefaultTableModel dtm;
	public int MaxBajar = 00;
	public int MaxSubir = 0;
	public Framework<T> fw;
	public JPanel ListaPanel;
	public JPanel BotonesPanel;
	public JButton BotonAlta;
	public JButton BotonBaja;
	public JButton BotonModificar;
	
    public Formulario(Framework<T> framework) 
    {
    	fw = framework;
        setLayout(null);
        setBounds(150,50,800,650);
        setTitle("ABM FRAMEWORK ALGORITMOS II");
        setResizable(false);
        agregarFiltro();
        AgregarPanelDeAlta();
        AgregarPanelDeLista();
        AgregarPanelBotones();
        AgregarBotonSubir();
        AgregarBotonBajar();
        AgregarBotonAlta();
        AgregarBotonBaja();
        AgregarBotonModificar();
        
        
        this.addKeyListener(new KeyListener()
        {		

			@Override
			public void keyReleased(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.out.println("Cerrando");
				
			}

			@Override
			public void keyTyped(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
				
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
				
			}

        	
        });
        
        
        
    }
    
   
    public void AgregarPanelBotones()
    {
    	BotonesPanel = new JPanel();
    	BotonesPanel.setLayout(new FlowLayout());
    	BotonesPanel.setBackground(Color.WHITE);
    	BotonesPanel.setBounds(0,550,450,80);
    	add(BotonesPanel);
    }
    
    public void AgregarPanelDeAlta()
    {
    	AltaPanel = new JPanel();
        AltaPanel.setLayout(null);
        AltaPanel.setBounds(0,50,400,500);
        add(AltaPanel);
    }
    
    public void AgregarPanelDeLista()
    {
    	ListaPanel = new JPanel();
    	ListaPanel.setLayout(new FlowLayout());
    	ListaPanel.setBounds(400,50,400,800);
    	add(ListaPanel);
    }
    
    public void AgregarBotonSubir()
    {
    	JButton scrollUp = new JButton("Subir");
    	scrollUp.addActionListener(new ActionListener() {
       	 
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
            {
                if(MaxSubir>9)
                {
	            	for (Component comp:AltaPanel.getComponents())
	                {
	            		if(comp.getClass() == JLabel.class || comp.getClass()== JTextField.class)
	            		{
		                	comp.setLocation(comp.location().x,comp.location().y+10);
		                	MaxBajar+=10;
		                	MaxSubir-=10;
	            		}
	                	
	                }
                }
                
            }
        });
        scrollUp.setBounds(320,0,70,30);
        scrollUp.setFocusable(false);
        AltaPanel.add(scrollUp);
        
    }
    
    public void AgregarBotonBajar()
    {
    	JButton scrollDown = new JButton("Bajar");        
        scrollDown.addActionListener(new ActionListener() 
        {
       	 
            @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
            {
            	if(MaxBajar>9)
            	{

                    for (Component comp:AltaPanel.getComponents())
                    {
                    	if(comp.getClass() == JLabel.class || comp.getClass()== JTextField.class)
                    	{
	                    	comp.setLocation(comp.location().x,comp.location().y-10);
	                    	MaxSubir+=10;
	                    	MaxBajar-=10;
	                    	System.out.println(MaxSubir + " " + MaxBajar);
                    	}
                    }	
            	}
                
            }
        });
        scrollDown.setBounds(320,450,70,30);
        scrollDown.setFocusable(false);
        AltaPanel.add(scrollDown);
    }


    public void AgregarBotonAlta()
    {    	
    	BotonAlta = new JButton("ALTA");
    	BotonesPanel.add(BotonAlta);
    }
    
    public void AgregarBotonBaja()
    {
    	BotonBaja = new JButton("BAJA");
    	BotonesPanel.add(BotonBaja); 
    }
    
    public void AgregarBotonModificar()
    {
    	BotonModificar = new JButton("MODIFICAR");
    	BotonesPanel.add(BotonModificar);
    }
    
        
    public void AgregarScrollPanel(Component componente)
    {
    	ScrollPane = new JScrollPane(componente, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	ListaPanel.add(ScrollPane);
    	
    }
    
    public void AgregarTabla()
    {
    	//Instanciar el modelo de tabla
    	dtm = new DefaultTableModel(fw.listaObjetos.size(),fw.objetoClass.getFields().length)
    	{
    	      public boolean isCellEditable(int rowIndex, int mColIndex) {
    	          return false;
    	        }
    	};		
		
    	
    	//Crear tabla
    	Tabla = new JTable(dtm);
    	Tabla.setCellSelectionEnabled(false); 
		Tabla.setRowSelectionAllowed(true);
		Tabla.setColumnSelectionAllowed(false);
        Tabla.setPreferredScrollableViewportSize(new Dimension(350,500));
        
    	int j=0;
		// Agregar datos a la tabla
		for(T obj:fw.listaObjetos) // COLUMNAS<----------
        {			        	
        	int i = 0;
        	for(Field campo :fw.objetoClass.getFields()) //  FILAS<----------
        	{        		
        		dtm.setValueAt(fw.InvocarGetter(obj,campo.getName()),j,i);
        		
        		i++;
        		
        	}        	
        	j++;
        }
        
		
		
		AgregarScrollPanel(Tabla);   // Se inserta la tabla en un ScrollPanel para que se pueda scrollear
		 	
    }
    
    public void agregarFiltro(){
    	JLabel campo1 = new JLabel();
		campo1.setText("Busqueda:");
		this.add(campo1);
		campo1.setBounds(160,13,100,15);
		
		JTextField input1 = new JTextField();
		input1.setText("");
		input1.setBounds(230,5,420,30);
		this.add(input1);
    }
    
    
}