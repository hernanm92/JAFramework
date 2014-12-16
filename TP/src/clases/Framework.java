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
	public T ObjetoParaModificar;
	public T ObjetoTratado;
	
	
	
	

	public Framework(T objeto)
	{
		objetoClass = objeto.getClass();
	}
	
	
	
	
	
	
	
	//Muestra la interfaz grafica
	public void MostrarVentana() // Abre la ventana principal
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
		{   // Completar			
			case "textbox": 
			{
				MostrarTextBox(annot, PosX, PosY);
				break;
			}
			case "combobox":
			{
				MostrarComboBox(annot, PosX, PosY);
				break;
			}
			case "checkbox":
			{
				MostrarCheckBox(annot,PosX,PosY);
				break;
			}
			default: 
			{
				break;
			}
		}
		SiguientePosY += 30;
	}
	
	public void MostrarTextBox(AnnotationParaFramework annot, int PosX, int PosY)
	{
		// Muestra un textbox 
		JTextField textbox = new JTextField();
		textbox.setBounds(100,PosY,200,30);
		textbox.setName(annot.label() + "tb");
		form.AltaPanel.add(textbox);
				
	}
	
	public void MostrarComboBox(AnnotationParaFramework annot, int PosX, int PosY)
	{ 
		JComboBox comboBox = new JComboBox<>();
		comboBox.setBounds(100,PosY,200,30);
		comboBox.setName(annot.label() + "cb");
		String opciones = annot.opcionesComboBox();
		String acumulador = "";
		while(opciones!="")
		{			
			if(opciones.charAt(0)=='/') 
				{
				 	if(acumulador!="")
				 		{
				 			comboBox.addItem(acumulador);
				 			acumulador = "";
				 		}
				}
			else acumulador+=opciones.charAt(0);
			if(opciones.length()>1) opciones = opciones.substring(1);
			else opciones = ""; 
			
		}
		if(acumulador!="")
 		{
 			comboBox.addItem(acumulador);
 			acumulador = "";
 		}
		form.AltaPanel.add(comboBox);
	}
	
	public void MostrarCheckBox(AnnotationParaFramework annot, int PosX, int PosY)
	{// Completar
		JCheckBox checkbox = new JCheckBox();
		checkbox.setBounds(100,PosY,200,30);
		checkbox.setName(annot.label() + "ch");
		form.AltaPanel.add(checkbox);
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
	
	public AnnotationParaFramework ObtenerAnnotation(String label) // Devuelve la annotation que contiene el campo "label"
	{	
		//Devuelve la annotation buscada
		for(Field campo : objetoClass.getFields())
		{
			if (campo.getName()==label) return campo.getAnnotation(AnnotationParaFramework.class);
		}
		return null;
	
	}
	
//--------------------Manejo de datos---------------------------------	
	// Alta simple
	public boolean DarAlta(T objetoAlta )
	{		
		if(ValidarAlta(objetoAlta))
		{
			listaObjetos.add(objetoAlta);  // Agregalo a la lista
			ObjetoTratado=null;
			return true;
		}		
		else // De lo constrario ...
		{
			
		}		
		ObjetoTratado=null;
		return false;
					
	}

	// Alta masiva
	public void DarAlta(T[] objetosAlta) 
	{
		for(T objAlta: objetosAlta) DarAlta(objAlta); //Por cada elemento de la lista, damelos de alta por separado.
	}
	
	// Devuelve la posicion en el que esta el objeto buscado
	public int Buscar(T objetoBuscado)
	{	
		int i = 0;
		for(T objeto: listaObjetos)
		{			
			if(LeerComoString(objeto).contentEquals(LeerComoString(objetoBuscado))) return i;
			i++;
		}
		return -1;			
	}
	
	// Modificar 
	public void Modificar(T objetoModificar, T objetoModificado)
	{
		DarBaja(objetoModificar);
		if(!DarAlta(objetoModificado)) DarAlta(objetoModificar);
		ObjetoTratado=null;
		
	}

	// Baja simple
	public void DarBaja(T objetoBaja)
	{
		listaObjetos.remove(Buscar(objetoBaja));
		ObjetoTratado=null;
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

	// Devuelve el Objeto esperado por la lista mostrada en pantalla
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

	// Acciona el metodo definido por el usuario para mandar los datos a la base de datos que el defina
	public void AccionarMetodoDeUsuario()
	{
		try
		{
			boolean encontrado = false;
			for(Method met: objetoClass.getMethods())
			{
				if(met.getName().contentEquals("PasarABaseDeDatos"))
				{
					encontrado=true;
					T instancia = (T)objetoClass.newInstance();
					Object[] parametros = { listaObjetos };	
					met.invoke(instancia,parametros);
				}
			}
			if(encontrado)
			{
				
			}
			else MostrarMensaje("El usuario no definió el método.");
		}
		catch(Exception ex)
		{
			
		}
		
	}
	
	
	
//------------- Metodos varios -------------------------	
	public Object InvocarGetter(T objeto, String nombre) // Invoca un getter
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

	public boolean InvocarSetter(T objeto, String nombre,Class[] tipos, Object[] parametros) // Invoca un getter
	{
		// Invoca un setter 
		try
		{			
			String metodoNombre = "set" + nombre.toUpperCase().charAt(0) + nombre.substring(1);
			Class[] tiposDeLosParametrosDelMetodo = tipos;
			Object[] valoresDeLosParametrosDelMetodo = parametros;
			for(int i = 0; i<valoresDeLosParametrosDelMetodo.length;i++)
			{				
				Object aux = valoresDeLosParametrosDelMetodo[i].toString();				
				switch(tiposDeLosParametrosDelMetodo[i].toString())  // Las conversiones segun el tipo que sea necesario
				{
					case "int" : 
					{					
						try
						{
							int a = Integer.parseInt(aux.toString());
							aux = Integer.parseInt(aux.toString());
						}
						catch(Exception ex)
						{
							MostrarMensaje("Valores no válidos.");
							return false;
						}						
						break;
					}
					case "class java.lang.Boolean": 
					{
						aux = (aux.toString().toLowerCase().contentEquals("true") ? true : false);
						break;
					}
					case "float":
					{
						aux = Float.parseFloat(aux.toString());
						break;
					}
					case "double":
					{
						aux = Double.parseDouble(aux.toString());
						break;
					}
					case "byte":
					{
						aux = aux.toString().getBytes()[0];
						break;
					}
					case "char":
					{
						aux = aux.toString().toUpperCase().charAt(0);
						break;
					}
					case "long":
					{
						aux = Long.parseLong(aux.toString());
						break;
					}
					case "short":
					{
						aux = Short.parseShort(aux.toString());
						break;
					}
					default:
					{
						break;
					}
				}
				
				valoresDeLosParametrosDelMetodo[i]= aux;
				
			}
			Method met = objeto.getClass().getDeclaredMethod(metodoNombre,tiposDeLosParametrosDelMetodo);
			//System.out.println("Se invoca el metodo: " + met.getName() + " (" + tiposDeLosParametrosDelMetodo[0].toString() + ")  Con el valor:" + valoresDeLosParametrosDelMetodo[0].getClass().toString() + " " + valoresDeLosParametrosDelMetodo[0].toString() );
			met.invoke(objeto,valoresDeLosParametrosDelMetodo);
			return true;
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
	
	public String LeerComoString(T objeto)	// Devuelve los datos del objeto como un string (Para la lista)
	{
		String ret = "";
		try
		{
			
			for(Field campo: objetoClass.getFields())
			{				
				ret+= (InvocarGetter(objeto,campo.getName()).toString() + " ").toLowerCase();
				 
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		
		
		return ret;
	}
	
	public boolean ValidarCampos(T objeto)  //Valida todos los campos
	{
		for(Field field: objeto.getClass().getFields())
		{
			if(!ValidarAnnotation(field,field.getAnnotation(AnnotationParaFramework.class), objeto)) return false;
		}
		return true;
	}
	
	public boolean ValidarAnnotation(Field field, AnnotationParaFramework annot, T objetoTrat)
	{	
		
		if(annot.unico()==true && !ValidarQueSeaUnico(field, objetoTrat))   //Verificar que sea unico si la annotation lo dice.
		{
			MostrarMensaje("El campo "+ annot.label() + " ya está utilizado y debe ser único.");
			return false;
		}
		
		
		if(annot.tipo().contentEquals("cadena")) //Validar las restricciones de la cadena
		{
			String cadena;
			try
			{
				cadena = (String)InvocarGetter(objetoTrat,annot.label());
				if(annot.largoMax()<cadena.length() || annot.largoMin()>cadena.length())
				{
					MostrarMensaje("El campo " + annot.label() + " debe tener entre " + annot.largoMin() + " y " + annot.largoMax() + " caracteres.");
					return false;
				}
				if(!ValidarRegex(annot.regex(), cadena))
				{
					MostrarMensaje("El campo " + annot.label() + " no cumple con el regex.");
					return false;
				}
			
			}
			catch(IllegalArgumentException ex)
			{
				
				ex.printStackTrace();
			}
			
		}
		else if(annot.tipo().contentEquals("numero")) //Validar las restricciones del numero
		{
			int numero;
			try
			{
				numero=(int)field.get(objetoTrat);
				if(numero<annot.valorMin() || numero>annot.valorMax()) 
					{
					MostrarMensaje("El campo " + annot.label() + " debe estar entre " + annot.valorMin() + " y " + annot.valorMax() + ".");
					return false;
					}
			}
			catch(IllegalArgumentException ex)
			{
				
				ex.printStackTrace();
			}
			catch(IllegalAccessException ex)
			{
				
				ex.printStackTrace();
			}
			
		}
		else if(annot.tipo().contentEquals("caracter")) //Validar las restricciones del caracter
		{
			
			char caracter;
			try
			{
				
				caracter = (char)InvocarGetter(objetoTrat,annot.label());
				String cadena = "" + caracter;
				if(!annot.regex().contentEquals(""))
				{
					
					if(!annot.regex().toLowerCase().contains(cadena.toLowerCase()))
						{
							MostrarMensaje("El campo " + annot.label() + " no cumple con el regex.");
							return false;
						}
				}
			
			}
			catch(IllegalArgumentException ex)
			{
				
				ex.printStackTrace();
			}
		}
		else if(annot.tipo().contentEquals("bool")) //Validar las restricciones del boolean
		{
			String cadena;
			try
			{
				boolean bool = (boolean)InvocarGetter(objetoTrat,annot.label());
				cadena = "false";
				if(bool) cadena = "true";
				if(!(cadena.toLowerCase()=="true" || cadena.toLowerCase()=="false")) return false;
			
			}
			catch(IllegalArgumentException ex)
			{
				
				ex.printStackTrace();
			}	
		}
		else if(annot.tipo().contentEquals("fecha"))
		{
			String cadena;
			try
			{
				cadena = (String)InvocarGetter(objetoTrat,annot.label());
				if(!ValidarRegex(annot.regex(), cadena))
				{
					MostrarMensaje("La fecha en el campo " + annot.label() + " es inválida.");
					return false;
				}
				if(annot.anioMax()<obtenerAnio(cadena) || annot.anioMin()>obtenerAnio(cadena))
				{
					MostrarMensaje("La fecha en el campo " + annot.label() + " debe tener entre el año " + annot.anioMin() + " y " + annot.anioMax() + ".");
					return false;
				}
				
			
			}
			catch(IllegalArgumentException ex)
			{
				
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean ValidarQueSeaUnico(Field field, T objetoTrat) // Valida que un campo único del objeto tratado no exista en la lista
	{		
		for(T objeto: listaObjetos)
		{
			try
			{
				if(field.get(objetoTrat).equals(field.get(objeto))) return false;
			}
			catch(IllegalArgumentException ex)
			{
				
				ex.printStackTrace();
			}
			catch(IllegalAccessException ex)
			{
				
				ex.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean ValidarRegex(String regex, String cadena) // Valida el regex de la annotation del campo
	{
		
		if(regex.contentEquals("mail"))
		{
			return ValidarMail(cadena);
		}
		else if(regex.contentEquals("fecha"))
		{
			return ValidarFecha(cadena);
		}
		//Continuar agregando validador de regex
		
		
		return true;
	}
	
	public void GenerarInstancia() //Genera una instancia del elemento genérico y la asigna a la variable "OBJETOTRATADO"
	{
		try
		{
			ObjetoTratado = (T)objetoClass.newInstance();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}		 
	}
	
	public boolean CargarleAtributosALaInstancia(ArrayList<String> Atributos)
	{
		GenerarInstancia();
		int AtributoIndex=0;
		for(Field field: objetoClass.getFields())
		{			
			if(field.getAnnotations()[0].annotationType()==AnnotationParaFramework.class)
			{	
				Class[] tipos = { field.getType() };
				Object[] parametros = { (Object)Atributos.get(AtributoIndex)};
				if(!InvocarSetter(ObjetoTratado,field.getName(),tipos, parametros)) return false;
				AtributoIndex++;
			}			
		}
		return true;
	}
	
	public boolean ValidarAlta(T objeto)
	{
		if(ValidarCampos(objeto))
		{
			return true;
		}
		return false;
	}
	
	public static void MostrarMensaje(String texto)
    {
        JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.INFORMATION_MESSAGE);
    }

// ------------- Control de REGEX ----------------------
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
		
		int dia = 0;
		int mes = 0;
		int anio = 0;
		int cont = 0;
		String c = "";
		String caracteresValidos = "01234567890/";
		for(int i=0; i<fecha.length();i++)  // validar que los caracteres sean validos
		{
			c = fecha.substring(i,i+1);
			if(c.contentEquals("/")) cont++;
			if(!caracteresValidos.contains(c)) return false;
			
		}
		
		if(cont!=2 || fecha.startsWith("/") || fecha.endsWith("/") || fecha.contains("//"))
		{
			MostrarMensaje("Fecha inválida.");
			return false;
		}
		String acumulador = "";
		for(int i=0; i<fecha.length();i++) //obtener dias mes y anio
		{
			if(!(fecha.charAt(i)=='/'))
			{
				acumulador+=fecha.charAt(i);
				
			}
			else 
			{
				if(dia==0)
				{
					dia=Integer.parseInt(acumulador);
					acumulador = "";
				}
				else if(mes==0) 
				{
					mes= Integer.parseInt(acumulador);
					acumulador= "";
				}				
			}			
			
		}
		anio = Integer.parseInt(acumulador);
		
		if(dia<1 || dia>31) 
			{
				MostrarMensaje("Día inválido.");
				return false;
			}
		if(mes<1 || mes>12)
			{
				MostrarMensaje("Mes inválido.");
				return false;
			}
			
		
		
		return true;
	}
	
	public int obtenerAnio(String fecha)
	{
		return Integer.parseInt(fecha.substring(fecha.lastIndexOf('/')+1));
	}
}

