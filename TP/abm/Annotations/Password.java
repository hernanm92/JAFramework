package Annotations;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Password
{
	String label();
	String regex() default "";
	int largoMin() default 5;
	int largoMax() default 30;
	String controlParaGUI() default "comboBox";
}