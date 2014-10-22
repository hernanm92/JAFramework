package classes;

import java.lang.reflect.*;
import java.util.Collections;
import java.lang.annotation.Annotation;
import java.util.*;

import javax.swing.JOptionPane;

import annotations.*;


public class Framework<T>
{
	public Class<?> objetoClass;
	public ArrayList<T> listaObjetos = new ArrayList<T>();
	public double SiguientePosX = 10;
	public double SiguientePosY = 10;
	
	
	

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
			MostrarControl( ObtenerAnnotation(nombreDelCampo), SiguientePosX, SiguientePosX);
			
		}
		
	}
	
	public void MostrarControl(AnnotationParaFramework annot, double PosX, double PosY)
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
	
	public void MostrarTextBox(AnnotationParaFramework annot, double PosX, double PosY)
	{
		
		
	}
	
	public void MostrarComboBox(AnnotationParaFramework annot, double PosX, double PosY)
	{
		
	}
	
	public void MostrarLabel(String texto, double posX, double posY)
	{
		// Muestra una label con el texto y posicion enviados por parametro para luego ponerle al lado el campo para la introduccion de datos  EJ  NOMBRE :   [textbox para nombre] 
		
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
		if(Buscar(objetoAlta)<0)  //Si el objeto a dar de alta no está en la lista entonces...
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
	
	
	
	
	
	// Devuelve los datos del objeto como un string (Para la lista)
	public String LeerComoString(T objeto)
	{
		String ret = "";
		try
		{
			
			for(Field campo: objetoClass.getFields())
			{
				String metodoNombre = "get" + campo.getName().toUpperCase().charAt(0) + campo.getName().substring(1);
				Class[] tiposDeLosParametrosDelMetodo = { };
				Object[] valoresDeLosParametrosDelMetodo = { };
				Method met = objeto.getClass().getDeclaredMethod(metodoNombre,tiposDeLosParametrosDelMetodo);
				ret+= met.invoke(objeto,valoresDeLosParametrosDelMetodo) + " ";
				 
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
	
	
}

