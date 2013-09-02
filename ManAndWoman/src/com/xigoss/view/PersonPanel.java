package com.xigoss.view;

import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PersonPanel extends JPanel{
	private JLabel[] jl= new JLabel[8];
	private JTextField[] jtf = new JTextField[7];
	private JComboBox jcb = new JComboBox();
	
	public PersonPanel(String sex, boolean flag){
		jl[0] = new JLabel("�Ա�");
		jl[1] = new JLabel("�Ƹ�");
		jl[2] = new JLabel("��ò");
		jl[3] = new JLabel("Ʒ��");		
		jl[4] = new JLabel("�Ƹ�����");
		jl[5] = new JLabel("��ò����");		
		jl[6] = new JLabel("Ʒ������");
		jl[7] = new JLabel("id"); 
		JPanel jp1 = new JPanel(null);
		JPanel jp2 = new JPanel(null);
		
		
		jtf[0] = new JTextField();
		jtf[1] = new JTextField();
		jtf[2] = new JTextField();
		jtf[3] = new JTextField();
		jtf[4] = new JTextField();
		jtf[5] = new JTextField();
		jtf[6] = new JTextField();
		jcb.addItem("");
		jcb.addItem("��");
		jcb.addItem("Ů");
				
		this.setBorder(new TitledBorder(sex));
		this.setLayout(new GridLayout(4, 3));
		
		int tmp;
		for(int i=0; i<7; i++){
			if(i == 0){
				this.add(jl[0]);
				this.add(jcb);
				if(flag == false){
					this.add(jl[7]);
					this.add(jtf[0]);
					jtf[0].setText("");
				}else{
					this.add(jp1);
					this.add(jp2);
				}
			}else{
				if(i%2 == 1){
					tmp = (i + 1)/2;
				}else{
					tmp = i/2 +3;
				}
				this.add(jl[tmp]);
				this.add(jtf[tmp]);
			}
		}

		for(int i=0; i<7; i++){
			jtf[i].setEditable(flag);
		}	
		jcb.setEditable(flag);
	}
	
	public JTextField[] getValue(){
		return this.jtf;
	}
	public JComboBox getSex(){
		return this.jcb;
	}
}
