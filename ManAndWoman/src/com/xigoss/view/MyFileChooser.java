package com.xigoss.view;

import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MyFileChooser extends Panel{
	private JLabel jl = new JLabel();
	private JTextField jtf = new JTextField(10);
	private JButton jb = new JButton("Ìí¼Ó");
	private File file;
	private boolean state = false;
	public MyFileChooser(String str){
		jl.setText(str);
		this.setLayout(new FlowLayout());
		this.add(jl);
		this.add(jtf);
		this.add(jb);
		jtf.setEditable(false);
		jb.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser jfc = new JFileChooser();
				int rVal = jfc.showOpenDialog(MyFileChooser.this);
				if(rVal == JFileChooser.APPROVE_OPTION){
					file = jfc.getSelectedFile();
					jtf.setText(file.getName());
					state = true;
				}				
			}			
		});
	}
	
	boolean getState(){
		return this.state;
	}
	
	File getFile(){
		return this.file;
	}
}
