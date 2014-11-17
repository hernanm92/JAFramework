package clases;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * @author RLR
 */

public class FormularioSencillo extends JFrame{

    Container cn;
    FlowLayout gy;
    JPanel p1,p2;

    public FormularioSencillo(String nameFormulario)
    {

        super(nameFormulario);
        setSize(new Dimension(300,300));  
        cn = getContentPane(); 

        // AÑADIR LAYOUT AL CONTENEDOR
        gy = new FlowLayout(FlowLayout.CENTER);
        cn.setLayout(gy);

        // CONSTRUCCION DE PANELES
        p1 = new JPanel(gy);
        p1.setBackground(Color.GREEN);
        JLabel lb1 = new JLabel("Nombre");
        lb1.setForeground(Color.blue);        
        JTextField tx1 = new JTextField("Escribir-aqui",10);
        tx1.setForeground(Color.red);        
        p1.add(lb1);
        p1.add(tx1);

        p2 = new JPanel(gy);
        p2.setBackground(Color.GREEN);
        JLabel lb2 = new JLabel("Apellidos");
        lb2.setForeground(Color.blue);        
        JTextField tx2 = new JTextField("",15);
        tx2.setForeground(Color.red);        
        p2.add(lb2);
        p2.add(tx2);

        // AÑADIR PANELES
        cn.add(p1);
        cn.add(p2);        

        // LECTURA DE DATOS INTRODUCIDOS

        String texto=tx1.getText(); // TEXTO INICIAL

        tx1.addActionListener(new Lector1());
        tx2.addActionListener(new Lector2());

        // FINAL: CIERRE DE VENTANA
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("ADIOS " + texto);  

    }

    public static void main(String[] args) 
    {
       FormularioSencillo frame = new FormularioSencillo("Ejemplo Formulario");
       frame.setVisible(true);
    }

    // LEE CUANDO SE PRESIONE RETURN EN EL CAMPO CORRESPONDIENTE
    class Lector1 implements ActionListener 
    {        
          private int i;

            @Override
           public void actionPerformed(ActionEvent e) 
           {
                 System.out.print("NOMBRE " + (++i) + ": ");
                 System.out.println(((JTextField)e.getSource()).getText());
            }
     }

    class Lector2 implements ActionListener 
    {        
          private int i;

            @Override
           public void actionPerformed(ActionEvent e) 
           {
                 System.out.print("APELLIDO " + (++i) + ": ");
                 System.out.println(((JTextField)e.getSource()).getText());
            }
     }

}
