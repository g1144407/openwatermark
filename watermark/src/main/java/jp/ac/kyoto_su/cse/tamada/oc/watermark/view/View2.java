package jp.ac.kyoto_su.cse.tamada.oc.watermark.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.*;

import java.util.List;

/**
 *
 */
public class View2 extends JFrame {
	//JPanel pane;
	private JTextArea westTextArea;
	private JTextArea eastTextArea;
	private Container container;

	public View2(List<String> sourceList, List<String> afterSourceList) {
		super();
		setTitle("JFrame");
		setBounds(0,0,700,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.container = getContentPane();
		this.container.setLayout(new BorderLayout());
		this.container.setBounds(0, 0, 700, 750);

		String source = "";
		for(String line : sourceList)
			source += line;
		westTextArea = new JTextArea(source);

		String afterSource = "";
		for(String afterLine : afterSourceList)
			afterSource += afterLine;
		eastTextArea = new JTextArea(afterSource);

		this.westTextArea.setLineWrap(true);
		this.eastTextArea.setLineWrap(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,600);
		setVisible(true);
		this.MyTextPane();
  	}

	public void MyTextPane() {
		//pane.setLayout(new BorderLayout());

		JScrollPane sourceScrollPane = new JScrollPane(this.westTextArea);
		JScrollPane afterScrollPane = new JScrollPane(this.eastTextArea);
		sourceScrollPane.setPreferredSize(new Dimension(400, 200));
		afterScrollPane.setPreferredSize(new Dimension(400, 200));

		this.container.add(sourceScrollPane,BorderLayout.WEST);
		this.container.add(afterScrollPane,BorderLayout.EAST);
		setVisible(true);
		//this.add(pane);
	}

	public void frameUpdate() {
		setVisible(false);
	}

}