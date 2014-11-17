package Annotations;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AnnotationParaFramework
{
	String label(); // etiqueta de la annotation
	String tipo(); // el tipo de la annotation  EJ: cadena, numero, fecha, char, etc-
	
	int largoMax() default 250; // cantidad maxima de caracteres (Cadena)
	int largoMin() default 0; // cantidad minima de caracteres (cadena)
	
	String regex() default ""; // la expresion regular para validar el campo
	
	boolean unico() default false;  // si atributo es clave
	
	String controlParaGUI() default "textbox";  // el control que usara  Ej: combobox, textbox etc
	
	int anioMax() default 2999; // Año maximo disponible (fecha)
	int anioMin() default 1900; // Año minimo disponible (fecha)
	
	int valorMax() default 999999999; //valor maximo (numero)
	int valorMin() default -99999999; // valor minimo (numero)
	
	String opcionesComboBox() default ""; //Opciones para el control combobox  EJ: Lunes/Martes/Miercoles...	
	
}
