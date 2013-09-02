package com.xigoss.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame{
	
	private JPanel
		p1 = new JPanel(),
		p2 = new JPanel();
	
	private PersonPanel 
		pp1 = new PersonPanel("����", true),
		pp2 = new PersonPanel("���Ƕ���", false);
	
	private JButton
		b1 = new JButton("ȷ��"),
		b2 = new JButton("����");
	
	private MyFileChooser
    	fc1 = new MyFileChooser("��������"),
    	fc2 = new MyFileChooser("Ů������"),
    	fc3 = new MyFileChooser("��������");
	
	public MainFrame(){
		this.setTitle("��Ůƥ������");
		this.setLayout(new GridLayout(2, 2));
		this.add(pp1);
		this.add(p1);
		this.add(p2);
		this.add(pp2);
		
		this.setSize(600, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		p1.setBorder(new TitledBorder("��������ļ�"));
		p1.setLayout(new GridLayout(3, 1));
		p1.add(fc1);
		p1.add(fc2);
		p1.add(fc3);
		
		p2.add(b1);
		p2.add(b2);
		
		b1.addActionListener(new ConfigActionListener(this));
		b2.addActionListener(new ResetActionListener(this));
		}
	
	public PersonPanel getRole(){
		return this.pp1;
	}
	
	public PersonPanel getObject(){
		return this.pp2;
	}
	
	public MyFileChooser getfc1(){
		return this.fc1;
	}
	
	public MyFileChooser getfc2(){
		return this.fc2;
	}
	
	public MyFileChooser getfc3(){
		return this.fc3;
	}
	
	public static void main(String[] args){
		new MainFrame();
	}
	
}