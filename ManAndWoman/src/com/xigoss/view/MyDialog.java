package com.xigoss.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyDialog extends JDialog{
	public MyDialog(JFrame parent, String text){
        super(parent, "My Dialog", true);
        setLayout(new FlowLayout());
        add(new JLabel(text));
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(ok);
        setSize(150, 125);
    }
}
