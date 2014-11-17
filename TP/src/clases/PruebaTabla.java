package clases;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PruebaTabla extends JFrame
{

	public JTable tabla;
	public JScrollPane scroll;
	public DefaultTableModel model;
	public JPanel panel;
	
	public PruebaTabla()
	{
		this.setBounds(0,0,800,600);
		
		panel = new JPanel();
		panel.setBackground(Color.RED);
		panel.setBounds(10,10,100,100);
		
		model = new DefaultTableModel(100,3);
		tabla = new JTable(model);
		for(int i=0; i<model.getRowCount(); i++)
		{
			for(int j=0; j<model.getColumnCount(); j++)
			{
				String dato = ("" + i + j);
				tabla.setValueAt(dato,i,j);
			}
		}
		//tabla.setBounds(10,10,100,100);
		scroll = new JScrollPane(tabla);
		tabla.setPreferredScrollableViewportSize(new Dimension(tabla.getPreferredScrollableViewportSize().width,200));
		
		//scroll.setBounds(10,10,100,100);
		this.add(panel);
		panel.add(scroll);
		
		this.setVisible(true);
		
	}
	
	
}
