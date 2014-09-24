package Annotations;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Fecha
{
	String label();
	int anioMax() default 2999;
	int anioMin() default 1900;
	boolean unico() default false;
	String controlParaGUI() default "comboBox";
}
