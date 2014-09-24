package clases;

import java.lang.reflect.Method;

import Annotations.*;
import java.lang.reflect.Field;;

	


public class Alumno 
{
	
	
	@Cadena(label="Nombre", largoMax = 40, largoMin = 5)
	public String nombre;
	@Numero(label= "Legajo",valorMax=9999999, valorMin = 900000, unico = true)
	public int legajo;
	@Numero(label="Edad",valorMax = 99, valorMin = 1)
	public int edad;
	@Cadena(label="Domicilio")
	public String domicilio;
	@Cadena(label="Mail",largoMin = 5, regex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,3})$  ") //Supuestamente es el regex para mails 
	public String mail;
	@Letra(label="Sexo", regex="MF")
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
