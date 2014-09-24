package interfaces;
import java.util.ArrayList;

public interface JaInterface {
	void abrirVentana(Object miObjeto);
	//recibe un class
	void darAlta(Object miObjeto);
	void confirmarAlta(Object miObjeto);
	void confirmarBaja(Object miObjeto);
	ArrayList<Object> listar(Object miObjeto);
	void modificar(Object miObjeto);
	//void confirmarModificacion(Object miObjeto);
	ArrayList<Object> buscar(Object miObjeto);

}
