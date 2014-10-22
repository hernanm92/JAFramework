package annotations;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Cadena
{
	String label();
	int largoMax() default 250;
	int largoMin() default 0;
	String regex() default "";
	boolean unico() default false;
	String controlParaGUI() default "comboBox";
}








