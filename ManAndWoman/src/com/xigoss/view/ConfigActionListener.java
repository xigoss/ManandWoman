package com.xigoss.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class ConfigActionListener implements ActionListener {
	MainFrame mf;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		if(!isChecked()){
//			new MyDialog(mf, "请正确输入属性或添加文件 ").setVisible(true);			
//		}else{
			try {
				DataGetter dg = new DataGetter(mf);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//		}
	}
	
	private boolean isChecked(){
		JTextField[] roleValue = mf.getRole().getValue();
		JComboBox sexBox = mf.getRole().getSex();
		String sex = (String)sexBox.getSelectedItem();
		if(sex.equals("")){
			return false;
		}
		int temp, sum = 0; 
		for(int i=1; i<7; ++i){
			temp = Integer.parseInt(roleValue[i].getText());
			if(temp<1 || temp>100){
				return false;			
			}
			if(i>3){
				if(temp>98){
					return false;
				}
				sum += temp;
			}
		}
		if(sum != 100){
			return false;
		}
		System.out.println(sum);
		
		if(!(mf.getfc1().getState()&&mf.getfc2().getState())){
			return false;
		}
		return true;
	}
	
	public ConfigActionListener(MainFrame mf){
		this.mf = mf;
	}
}
