package clases;

import java.lang.reflect.Method;

import Annotations.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

	


public class Alumno 
{
	
	
	@AnnotationParaFramework(label="Nombre", tipo="cadena", largoMax = 40, largoMin = 1)
	public String nombre;
	@AnnotationParaFramework(label= "Legajo", tipo ="numero",valorMax=9999999, valorMin = 1, unico = true)
	public int legajo;
	@AnnotationParaFramework(label="Edad", tipo="numero" ,valorMax = 100, valorMin = 1)
	public int edad;
	@AnnotationParaFramework(label="Domicilio", tipo="cadena")
	public String domicilio;
	@AnnotationParaFramework(label="Signo", tipo="cadena", controlParaGUI="combobox", opcionesComboBox="Leo/Libra/Sagitario/Capricornio/Acuario/Géminis")
	public String signo;
	@AnnotationParaFramework(label="Mail",tipo="cadena" ,largoMin = 5, regex = "mail") 
	public String mail;
	@AnnotationParaFramework(label="Rubio", tipo="bool", controlParaGUI="checkbox")
	public Boolean rubio;
	@AnnotationParaFramework(label="Sexo", tipo="caracter", regex="MF", largoMin=1, largoMax=1)
	public char sexo;
	@AnnotationParaFramework(label="Nacimiento", tipo="fecha", regex="fecha", anioMin=1900, anioMax=2015)
	public String nacimiento;
	
	







	public void PasarABaseDeDatos(ArrayList<Alumno> ListaObjetos)
	{
		// METODO DEFINIDO POR EL USUARIO QUE RECIBE LA LISTA DE OBJETOS FINAL DEL FRAMEWORK Y SE ENCARGA DE ENVIARLOS 
		// A LA BASE DE DATOS 
	}
	
	

	public Alumno()
	{
		
	}
	
	public Alumno(String nombree, int legajoo, int edadd, String domicilioo, String maill, char sexoo, String signoo, boolean rubioo, String nacimientoo)
	{
		setNombre(nombree);
		setLegajo(legajoo);
		setEdad(edadd);
		setDomicilio(domicilioo);
		setMail(maill);
		setSexo(sexoo);
		setSigno(signoo);
		setRubio(rubioo);
		setNacimiento(nacimientoo);
	}
	
	
	
	public String getNacimiento()
	{
		return nacimiento;
	}



	public void setNacimiento(String nacimiento)
	{
		this.nacimiento=nacimiento;
	}
	
	
	
	public Boolean getRubio()
	{
		return rubio;
	}



	public void setRubio(Boolean rubio)
	{
		this.rubio=rubio;
	}
	
	public char getSexo()
	{
		return sexo;
	}

	public void setSexo(char sexo)
	{
		this.sexo=sexo;
	}

	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	public int getLegajo()
	{
		return legajo;
	}
	public void setLegajo(int legajo)
	{
		this.legajo=legajo;
	}
	public int getEdad()
	{
		return edad;
	}
	public void setEdad(int edad)
	{
		this.edad=edad;
	}
	public String getDomicilio()
	{
		return domicilio;
	}
	public void setDomicilio(String domicilio)
	{
		this.domicilio=domicilio;
	}
	public String getMail()
	{
		return mail;
	}
	public void setMail(String mail)
	{
		this.mail=mail;
	}
	
	public String getSigno()
	{
		return signo;
	}

	public void setSigno(String signo)
	{
		this.signo=signo;
	}
	
	
	
}
