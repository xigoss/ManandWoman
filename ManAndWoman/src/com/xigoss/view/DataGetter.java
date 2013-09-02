package com.xigoss.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
	
public class DataGetter {
	static final int MALE = 1;
	static final int FEMALE = 0;
	static final int DELETE = -2;
	static final int ROLE = -1;
	static final int NONE = -4;
	private int[][] manArray;
	private int[][] womanArray;
	private int[] role;
	//private int[] role = {-1,99,99,99,33,33,34,NONE};
	private int sex;
	private File manFile, womanFile;
	
	public DataGetter(MainFrame mf) throws IOException{
		manArray = new int[100][8];
		womanArray = new int[100][8];
		role = new int[8];
		manFile = mf.getfc1().getFile();
		womanFile = mf.getfc2().getFile();
		if(mf.getRole().getSex().getSelectedItem().toString() == "男"){
			sex = MALE;
		}else{
			sex = FEMALE;
		}
		
		initRole(mf, role);
		initArray(manFile, manArray);
		initArray(womanFile, womanArray);
		if(sex == MALE){
			merge(role, manArray);
		}else{
			merge(role, womanArray);
		}
								
		while(true){
			int manId,manLine = 0, womanLine = 0, womanId;
			womanId = getBestWomanId(manArray, womanArray);	
			womanLine = getLineFromId(womanId, womanArray);
			manId = getBestManId(manArray, womanArray[womanLine]);
			manLine = getLineFromId(manId, manArray);
			if(womanId == ROLE){
				System.out.println("被选女为主角");										
				JTextField[] jtf = mf.getObject().getValue();
				JComboBox jcb = mf.getObject().getSex();
				jcb.setSelectedItem("男");
				for(int i=1; i<7; i++){
					jtf[i].setText(Integer.toString(manArray[manLine][i]));
				}
				jtf[0].setText(Integer.toString(manId));				
				break;
			}
			
			else{							
				if(manId == ROLE){
					System.out.println("被选男为主角");
					JTextField[] jtf = mf.getObject().getValue();
					JComboBox jcb = mf.getObject().getSex();
					jcb.setSelectedItem("女");
					for(int i=1; i<7; i++){
						jtf[i].setText(Integer.toString(womanArray[womanLine][i]));
					}
					jtf[0].setText(Integer.toString(womanId));					
					break;
				}
				
				manArray[manLine][7] = DELETE;
				womanArray[womanLine][7] = DELETE;
				reset(manArray);
				reset(womanArray);
			}						
		}
	}
	
	public void initArray(File file, int[][] Array) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = null;
		int line = 0;
		String[] tmpArray;
		while((str = br.readLine()) != null){
			if(line == Array.length){
				break;
			}
			tmpArray = str.split(",");
			for(int i=0; i<tmpArray.length; i++){
				Array[line][i] = Integer.parseInt(tmpArray[i]);
//				System.out.print(Array[line][i]+" ");
			}
//			System.out.print("\n");
			Array[line][Array[line].length-1] = NONE;
			line++;
		}
	}
	
	public void initRole(MainFrame mf, int[] role){
		JTextField[] tmpText = mf.getRole().getValue();
		role[0] = ROLE;
		System.out.print(role[0]+" ");
		for(int i=1; i<role.length-1; i++){
			role[i] = Integer.parseInt(tmpText[i].getText().toString());
			System.out.print(role[i]+" ");
		}
		role[role.length-1] = NONE;
	}
	
	int getSum(int[] x, int[] y){
		int sum = 0;
		for(int i=1; i<4; i++){
			sum += x[i+3]*y[i];
		}		
		return sum;
	}
	
	void merge(int[] role, int[][] person){
		for(int i=0; i<role.length; i++){
			person[person.length-1][i] = role[i];
		}
	}
	
	int getBestWomanId(int[][] manArray, int[][] womanArray){
		int value, max=0,line = 0,Max=0;
		int[] womanInviteNum = new int[100];
		for(int i=0; i<100; i++){
			womanInviteNum[i] = 0;
		}
		for(int k=0; k<manArray.length; k++){
			if(manArray[k][7] == DELETE){
				continue;
			}
			for(int i=0; i<womanArray.length; i++){
				if(womanArray[i][7] == DELETE){
					continue;
				}
				value = getSum(manArray[k], womanArray[i]);
				if(value > max){							
					max = value;
					line = i;					
				}														
			}
			womanInviteNum[line]++;
			manArray[k][7] = womanArray[line][0];
			max = 0;
		}
		for(int j=0; j<womanInviteNum.length; j++){
			if(womanInviteNum[j] > Max){
				Max = womanInviteNum[j];
				line = j;
			}
			if(womanInviteNum[j] == Max){
				if(womanArray[j][1]+womanArray[j][2]+womanArray[j][3] >
					womanArray[line][1]+womanArray[line][2]+womanArray[line][3]){
					line = j;
				}
			}
		}
		System.out.println("被选女士"+womanArray[line][0]+"行数"+line);
		System.out.println("被选女士邀请次数"+Max);
		return womanArray[line][0];
	}
	
	int getLineFromId(int id, int[][] person){
		for(int i=0; i<person.length; i++){
			if(id == person[i][0]){
				return i;
			}
		}
		return NONE;
	}
	
	private int getBestManId(int[][] manArray, int[] woman) {
		// TODO Auto-generated method stub
		int max=0, value, id=0;
		for(int i=0; i<manArray.length; i++){
			if(manArray[i][7] == woman[0]){
				value = getSum(woman, manArray[i]);
				if(value > max){
					max = value;
					id = manArray[i][0];
				}
			}
		}
		
		System.out.println("被选男士"+id);
		System.out.println("满意度为"+max);
		return id;
	}
	
	void reset(int[][] person) {
		// TODO Auto-generated method stub
		for(int i=0; i<person.length; i++){
			if(person[i][7] != DELETE){
				person[i][7] = NONE;
			}
		}
	}
}
