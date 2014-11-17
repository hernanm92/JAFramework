package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	public int MaxBajar = -540;
	public int MaxSubir = 0;
	public Framework<T> fw;
	public JPanel ListaPanel;
	
    public Formulario(Framework<T> framework) 
    {
    	fw = framework;
        setLayout(null);
        setBounds(150,150,800,600);
        setBackground(Color.GREEN);
        setTitle("ABM FRAMEWORK ALGORITMOS II");
        setResizable(false);
        AgregarPanelDeAlta();
        AgregarPanelDeLista();
        AgregarBotonSubir();
        AgregarBotonBajar();
        
        
        
    }
    
    public void AgregarPanelDeAlta()
    {
    	AltaPanel = new JPanel();
        AltaPanel.setLayout(null);
        AltaPanel.setBounds(0,0,400,800);
        AltaPanel.setBackground(Color.lightGray);
        add(AltaPanel);
    }
    
    public void AgregarPanelDeLista()
    {
    	ListaPanel = new JPanel();
    	ListaPanel.setLayout(null);
    	ListaPanel.setBounds(400,15,400,800);
    	ListaPanel.setBackground(Color.BLUE);
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
		                	comp.setLocation(comp.location().x,comp.location().y-10);
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
	                    	comp.setLocation(comp.location().x,comp.location().y+10);
	                    	MaxSubir+=10;
	                    	MaxBajar-=10;
                    	}
                    }	
            	}
                
            }
        });
        scrollDown.setBounds(320,370,70,30);
        scrollDown.setFocusable(false);
        AltaPanel.add(scrollDown);
    }
    
    public void AgregarScrollPanel(Component componente)
    {
    	ScrollPane = new JScrollPane(componente);
    	//ScrollPane.setLayout(null);
    	ScrollPane.setBounds(componente.location().x,componente.location().y,componente.getWidth()+30,componente.getHeight());
    	ScrollPane.setBackground(Color.BLACK);
    	//ScrollPane.add(Tabla);
    	//ListaPanel.add(ScrollPane);
    	
    }
    
    public void AgregarTabla()
    {
    	//Instanciar el modelo de tabla
    	dtm = new DefaultTableModel(fw.listaObjetos.size(),fw.objetoClass.getFields().length);		
		
    	
    	//Crear tabla
    	Tabla = new JTable(dtm);
    	Tabla.setCellSelectionEnabled(false); 
		Tabla.setRowSelectionAllowed(true);
        Tabla.setBounds(15,300,1300, 100);
        Tabla.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
        Tabla.setFillsViewportHeight(false);
    	
    	
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
		 
		
		ScrollPane.setBounds(10,300, 50, 50);
		ScrollPane.setViewportView(Tabla);
		ScrollPane.add(Tabla);
		AltaPanel.add(ScrollPane,BorderLayout.CENTER);
		
		
		
		
		
		
		
		
    }
}