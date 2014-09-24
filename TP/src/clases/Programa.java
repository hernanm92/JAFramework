package clases;

import java.util.ArrayList;

public class Programa
{
	public static void main(String[] args)
	{
		Framework<Alumno> sesion = new Framework<Alumno>(new Alumno());
		Alumno alumno1 = new Alumno("Juan", 1334441, 25, "Libertador 3333", "Juan@libertador.333", 'M'); 
		sesion.DarAlta(alumno1);
		Alumno alumno2 = new Alumno("Sofia", 224441, 35, "Cabildo 3333", "Sofi@asdasdrtador.333", 'F');
		sesion.DarAlta(alumno2);
		listarPorConsola(sesion);
		System.out.println("\n---------- Ahora se modifica el nombre de Juan por Pedro -----------");
		Alumno alumno3 = alumno1;
		alumno3.nombre = "Pedro";
		sesion.Modificar(alumno1,alumno3);
		listarPorConsola(sesion);
		System.out.println("\n---------- Ahora se da de baja Sofia -----------");
		sesion.DarBaja(alumno2);
		listarPorConsola(sesion);
		System.out.println("\n---------- Ahora se da una Alata Masiva -----------");
		Alumno alumno4 = new Alumno("Federico", 124441, 11, "Pueyrredon 3333", "a@gmail.com", 'M');
		Alumno alumno5 = new Alumno("Sebastian", 543341, 44, "Santa Fe 654", "hola@hotmail.com", 'M');
		Alumno[] altaMasiva = {alumno4, alumno5};
		sesion.DarAlta(altaMasiva);
		listarPorConsola(sesion);
		System.out.println("\n---------- En que posicion esta Federico? -----------");
		System.out.println("Esta en la posicion: " + sesion.Buscar(alumno4));
		System.out.println("\n---------- De toda la lista quien tiene un \"441\" ? -----------");
		listarPorConsola(sesion, "441");
		
	}
	
	
	
	
	
	public static void listarPorConsola(Framework<Alumno> sesion)
	{
		for(Alumno obj: sesion.Listar())
		{
			System.out.println(sesion.LeerComoString(obj));
		}
	}
	
	public static void listarPorConsola(Framework<Alumno> sesion, String busqueda)
	{
		for(Alumno obj: sesion.Listar(busqueda))
		{
			System.out.println(sesion.LeerComoString(obj));
		}
	}
}
