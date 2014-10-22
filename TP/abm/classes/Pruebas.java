package classes;

import annotations.AnnotationParaFramework;

public class Pruebas
{
	public static void main(String[] args)
	{
		String linea = "";
		Framework<Alumno> sesion = new Framework<Alumno>(new Alumno());
		System.out.print("Sesion iniciada \n");
		
		linea = sesion.ObtenerAnnotation("mail").regex().toString();
				
		System.out.print(linea);
	}
}
