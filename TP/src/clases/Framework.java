package clases;

import Annotations.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.*;
import java.util.Collections;
import java.lang.annotation.Annotation;
import java.util.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class Framework<T>
{
	public Class<?> objetoClass;
	public ArrayList<T> listaObjetos = new ArrayList<T>();
	public int SiguientePosX = 10;
	public int SiguientePosY = 10;
	public Formulario<T> form = new Formulario<T>(this);
	
	
	

	public Framework(T objeto)
	{
		objetoClass = objeto.getClass();
	}
	
	
	
	
	
	
	
	//Muestra la interfaz grafica
	public void MostrarVentana()
	{
		for(Field campo: objetoClass.getFields())
		{			
			
			String nombreDelCampo = campo.getName();
			MostrarLabel(nombreDelCampo, SiguientePosX, SiguientePosY);
			MostrarControl( ObtenerAnnotation(nombreDelCampo), SiguientePosX, SiguientePosY);
			
		}
		MostrarTabla();
		form.setVisible(true);
		
	}
	
	public void MostrarTabla()
	{		
		form.AgregarTabla();
	}
	
	public void MostrarControl(AnnotationParaFramework annot, int PosX, int PosY)
	{
		// Muestra el control dependiendo de la annotation recibida
		switch (annot.controlParaGUI())
		{
			case "textbox": 
			{
				MostrarTextBox(annot, PosX, PosY);
				break;
			}
			case "combobox":
			{
				MostrarComboBox(annot, PosX, PosY);
			}
			default: 
			{
				
			}
		}
		
	}
	
	public void MostrarTextBox(AnnotationParaFramework annot, int PosX, int PosY)
	{
		// Muestra un textbox 
		JTextField textbox = new JTextField();
		textbox.setBounds(100,PosY,200,30);
		textbox.setName(annot.label() + "tb");
		form.AltaPanel.add(textbox);
		SiguientePosY += 30;
		form.MaxBajar+=30;
		
		
	}
	
	public void MostrarComboBox(AnnotationParaFramework annot, int PosX, int PosY)
	{
		
	}
	
	public void MostrarLabel(String texto, int posX, int posY)
	{		
		// Muestra una label con el texto y posicion enviados por parametro para luego ponerle al lado el campo para la introduccion de datos  EJ  NOMBRE :   [textbox para nombre] 
		JLabel label = new JLabel(texto.substring(0,1).toUpperCase() + texto.substring(1) + ":");
		label.setName(texto+ "l");
		label.setBounds(10,posY,70,30);
		SiguientePosX += + 50;
		form.AltaPanel.add(label);		
		
				
		
	}
	
	
	public AnnotationParaFramework ObtenerAnnotation(String label)
	{	
		//Devuelve la annotation buscada
		for(Field campo : objetoClass.getFields())
		{
			if (campo.getName()==label) return campo.getAnnotation(AnnotationParaFramework.class);
		}
		return null;
	
	}
	
	
	// Alta simple
	public void DarAlta(T objetoAlta )
	{
		if(Buscar(objetoAlta)<0 || true)  //Si el objeto a dar de alta no está en la lista entonces...
		{
			listaObjetos.add(objetoAlta);  // Agregalo a la lista
		}
		else // De lo constrario ...
		{
			JOptionPane.showMessageDialog(null, "El "+ objetoClass.getSimpleName() + " ya existe"); //Tirame un dialogo de que el objeto ya existe
		}
		
	}
	
	
	
	
	
	
	// Alta masiva
	public void DarAlta(T[] objetosAlta) 
	{
		for(T objAlta: objetosAlta) DarAlta(objAlta); //Por cada elemento de la lista, damelos de alta por separado.
	}
	
	
	
	
	
	
	
	// Devuelve la posicion en el que esta el objeto buscado
	public int Buscar(T objetoBuscado)
	{	
		return listaObjetos.indexOf(objetoBuscado);			
	}
	
	
	
	
	
	
	// Modificar 
	public void Modificar(T objetoModificar, T objetoModificado)
	{		
		if(listaObjetos.contains(objetoModificar))
		{
			this.DarBaja(objetoModificar);
			this.DarAlta(objetoModificado);
		}
	}
	
	
	
	
	
	
	
	
	// Baja simple
	public void DarBaja(T objetoBaja)
	{
		listaObjetos.remove(Buscar(objetoBaja));
	}
		
	
	
	
	
	
	
	// Baja masiva
	public void DarBaja(List<T> objetosBaja) 
	{
		for(T objBaja: objetosBaja) DarBaja(objBaja);		
	}
	
	
	
	
	
	
	
	
	// Listar todo
	public ArrayList<T> Listar()
	{		
		return listaObjetos;
	}
	
	public Object[][] ListarParaLista()
	{
		//Devuelve el tipo de objeto que espera JList para armar la lista por pantalla
		Object ret[][] = new Object[100][100];
		int i=0;
		for(T obj: listaObjetos )
		{
			int j =0;
			for(Field campo: objetoClass.getFields())
			{
				try
				{

					String metodoNombre = "get" + campo.getName().toUpperCase().charAt(0) + campo.getName().substring(1);
					@SuppressWarnings("rawtypes")
					Class[] tiposDeLosParametrosDelMetodo = { };
					Object[] valoresDeLosParametrosDelMetodo = { };
					Method met = objetoClass.getDeclaredMethod(metodoNombre,tiposDeLosParametrosDelMetodo);
					ret[i][j] = met.invoke(obj,valoresDeLosParametrosDelMetodo).toString() + " ";
					 
					j++;	
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					throw new RuntimeException(ex);
				}
			}
			
			i++;	
		}
		return ret;	
	}
	
	public Object[] ObtenerNombreDeColumnasParaLista()
	{
		// retorna los nombres de los atributos del Objeto para para la lista del GUI
		Object ret[] = {};
		int i = 0;
		System.out.print(objetoClass.getFields().length);
		for(Field campo : objetoClass.getFields())
		{
			ret[i] = campo.getName().toUpperCase();
		}
		return ret;
	}
	
	
	
	// Listar aquellos que cumplan con la busqueda
	public ArrayList<T> Listar(String busqueda)
	{		
		ArrayList<T> ret = new ArrayList<T>();
		for(T obj: Listar())
		{
			if(this.LeerComoString(obj).contains(busqueda))
			{
				ret.add(obj);
			}
		
		}
		return ret;
	}
	
	
	
	public Object InvocarGetter(T objeto, String nombre)
	{
		// Invoca un getter 
		try
		{

			String metodoNombre = "get" + nombre.toUpperCase().charAt(0) + nombre.substring(1);
			Class[] tiposDeLosParametrosDelMetodo = { };
			Object[] valoresDeLosParametrosDelMetodo = { };
			Method met = objeto.getClass().getDeclaredMethod(metodoNombre,tiposDeLosParametrosDelMetodo);
			return met.invoke(objeto,valoresDeLosParametrosDelMetodo);
		
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
	
	
	// Devuelve los datos del objeto como un string (Para la lista)
	public String LeerComoString(T objeto)
	{
		String ret = "";
		try
		{
			
			for(Field campo: objetoClass.getFields())
			{				
				ret+= InvocarGetter(objeto,campo.getName()).toString() + " ";
				 
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
		
		return ret;
	}
	
	
	public boolean ValidarCampo()
	{
		return true;
	}
	
	public boolean ValidarCampos()
	{
		return true;
	}
	
	public boolean ValidarRegex()
	{
		
		return true;
	}
	
	public boolean ValidarMail(String mail)
	{
		String c = "";
		String caracteresValidos = "01234567890qwertyuiopasdfghjklñzxcvbnmQWERTYUIOPASDFGHJKLÑZXCVBNM.-_@";
		for(int i=0; i<mail.length();i++)
		{
			c = mail.substring(i,i); 
			if(!caracteresValidos.contains(c)) return false;
		}
		String ret = mail;
		if(ret.contains("@"))
		{
			ret = ret.substring(ret.indexOf("@")+1);
			if(ret.contains(".") && !ret.contains("@"))
			{
				ret = ret.substring(ret.indexOf(".")+1);
				if(ret.length()>0 && !ret.endsWith(".") && !ret.contains("_") && !ret.contains("-")) return true;
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	public boolean ValidarFecha(String fecha)
	{
		return true;
	}
}

