package clases;

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
		panel.setBounds(10,10,100,100);
		
		model = new DefaultTableModel(10,3);
		tabla = new JTable(model);
		for(int i=0; i<10; i++)
		{
			for(int j=0; j<3; j++)
			{
				String dato = ("" + i + j);
				tabla.setValueAt(dato,i,j);
			}
		}
		scroll = new JScrollPane(tabla);
		this.add(panel);
		panel.add(scroll);
		this.setVisible(true);
		
	}
	
	
}
