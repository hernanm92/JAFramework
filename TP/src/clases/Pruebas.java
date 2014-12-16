package clases;

import java.util.ArrayList;

import Annotations.AnnotationParaFramework;

public class Pruebas
{
	public static void main(String[] args)
	{		
		
	
		
		Framework<Alumno> sesion = new Framework<Alumno>(new Alumno());
		
		
		
		Alumno alumno1 = new Alumno("Juan", 1334441, 25, "Libertador 3333", "Juan@libertador.333", 'M', "Leo", true, "21/11/1995"); 
		sesion.ObjetoTratado=alumno1;
		sesion.DarAlta(alumno1);
		
		
		Alumno alumno2 = new Alumno("Pedro", 133451, 21, "Cabildo 2133", "Pedro@hotmail.com", 'M', "Géminis", true, "03/09/2001"); 
		sesion.ObjetoTratado=alumno2;
		sesion.DarAlta(alumno2);
		
		
		Alumno alumno3 = new Alumno("Federico", 124123, 55, "Maipu 124", "Fede_ok@gmail.com", 'M', "Acuario", true, "21/08/1988");
		sesion.ObjetoTratado=alumno3;
		sesion.DarAlta(alumno3);
		
		
		Alumno alumno4 = new Alumno("Paula", 214121, 31, "Florida 1955", "Paula-23@a.com", 'f', "Sagitario", false, "07/02/1992");
		sesion.ObjetoTratado=alumno4;
		sesion.DarAlta(alumno4);
		
		
		sesion.MostrarVentana();
		
		
		
		
		
		
		
	}
}
