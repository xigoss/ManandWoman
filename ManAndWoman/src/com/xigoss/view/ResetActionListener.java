package com.xigoss.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ResetActionListener implements ActionListener {
	MainFrame mf;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		PersonPanel pp = mf.getRole();
		pp.getSex().setSelectedItem("");
		JTextField[] jtf = pp.getValue();
		for(int i=1; i<jtf.length; i++){
			jtf[i].setText("");
		}
	}
	
	public ResetActionListener(MainFrame mf){
		this.mf = mf;
	}
}
