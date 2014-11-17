package clases;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PruebaFormulario extends JFrame{
	
	JLabel campo1;
	JLabel campo2;
	JLabel campo3;
	
	public PruebaFormulario(){
		
		//this.setBounds(100,100,1000,600);
		
		campo1 = new JLabel();
		campo1.setText("Campo1:");
		this.add(campo1);
		//campo1.setBounds(200,200,20,20);
		
		JTextField input1 = new JTextField();
		input1.setText("");
		input1.setSize(200, 24);
		this.add(input1);
		
		campo2 = new JLabel();
		campo2.setText("Campo2:");
		this.add(campo2);
		//campo1.setBounds(200,200,20,20);
		
		JTextField input2 = new JTextField();
		input2.setText("");
		input2.setSize(200, 24);
		this.add(input2);
		
		campo3 = new JLabel();
		campo3.setText("Campo3:");
		this.add(campo3);
		//campo1.setBounds(200,200,20,20);
		
		JTextField input3 = new JTextField();
		input3.setText("");
		input3.setSize(200, 24);
		this.add(input3);
		
		JButton boton = new JButton();
		boton.setText("Crear");
		this.add(boton);
		
		setLayout(new FlowLayout());
		setSize(900,500);
		setVisible(true);
		
	}

}
