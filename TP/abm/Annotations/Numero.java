package Annotations;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Numero
{
	String label();
	int valorMax() default 999999999;
	int valorMin() default -99999999;
	boolean soloPositivo() default false;
	boolean unico() default false;
	String controlParaGUI() default "comboBox";
}
