package clases;

import Annotations.AnnotationParaFramework;

public class Pruebas
{
	public static void main(String[] args)
	{		
		
		Framework<AlumnoDatosCompletos> sesion = new Framework<AlumnoDatosCompletos>(new AlumnoDatosCompletos());
		//Alumno alumno1 = new Alumno("Juan", 1334441, 25, "Libertador 3333", "Juan@libertador.333", 'M'); 
		//sesion.DarAlta(alumno1);
		//Alumno alumno2 = new Alumno("asdasd", 1334441, 25, "Libertador 3333", "Juan@libertador.333", 'M'); 
		//for(int i = 0; i<100;i++)sesion.DarAlta(alumno2);
		sesion.MostrarVentana();
		
		
		//PruebaTabla tabla = new PruebaTabla();
		//PruebaFormulario formulario= new PruebaFormulario();
		
	}
}
