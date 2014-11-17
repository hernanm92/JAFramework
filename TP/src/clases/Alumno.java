package clases;

import java.lang.reflect.Method;

import Annotations.*;
import java.lang.reflect.Field;;

	


public class Alumno 
{
	
	
	@AnnotationParaFramework(label="nombre", tipo="cadena", largoMax = 40, largoMin = 5)
	public String nombre;
	@AnnotationParaFramework(label= "legajo", tipo ="numero",valorMax=9999999, valorMin = 900000, unico = true)
	public int legajo;
	@AnnotationParaFramework(label="edad", tipo="numero" ,valorMax = 99999999, valorMin = 1)
	public int edad;
	@AnnotationParaFramework(label="domicilio", tipo="cadena")
	public String domicilio;
	@AnnotationParaFramework(label="mail",tipo="cadena" ,largoMin = 5, regex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$  ") //Supuestamente es el regex para mails 
	public String mail;
	@AnnotationParaFramework(label="sexo", tipo="cadena", regex="MF", largoMin=1, largoMax=1)
	public char sexo;
	
	public Alumno()
	{
		
	}
	
	public Alumno(String nombree, int legajoo, int edadd, String domicilioo, String maill, char sexoo)
	{
		setNombre(nombree);
		setLegajo(legajoo);
		setEdad(edadd);
		setDomicilio(domicilioo);
		setMail(maill);
		setSexo(sexoo);
		
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
	
	
	
	
	
}
