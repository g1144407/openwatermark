package jp.ac.kyoto_su.cse.tamada.oc.watermark.view;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.dnd.DropTarget;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import java.util.List;


import jp.ac.kyoto_su.cse.tamada.oc.watermark.controller.DisplayController;
import jp.ac.kyoto_su.cse.tamada.oc.watermark.controller.DropTargetController;

public class View extends JFrame{
	private JTextArea textAreaEmbedSource;
	private JTextArea textAreaEmbedWM;
	private JPanel panelEmbedSource;
	private JPanel panelEmbedWM;
	private JButton embedButton;

	private JTextArea textAreaExtract;
	private JPanel panelExtract;
	private JButton extractButton;

	private JTextArea textAreaRecognizeSource;
	private JTextArea textAreaRecognizeWM;
	private JPanel panelRecognizeSoure;
	private JPanel panelRecognizeWM;
	private JButton recognizeButton;

	private JButton filePathButton;
	private View2 beforeAfterSource;
	private DisplayController controller;

	public View(){
		this.controller = new DisplayController(this);

		setTitle("JFrame");
		setBounds(0,0,700,750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container container = getContentPane();
		container.setLayout(null);
		container.setBounds(0, 0, 700, 750);

		setEmbedPanel();
		setExtractPanel();
		setRecognizePanel();

		setThreeButton();

		DrawLine line = new DrawLine();

		if(textAreaEmbedSource == null){
			textAreaEmbedSource = new JTextArea();
			textAreaEmbedSource.setBounds(panelEmbedSource.getVisibleRect());
			textAreaEmbedSource.setName("EmbedSource");
		}
		if(textAreaEmbedWM == null){
			textAreaEmbedWM = new JTextArea();
			textAreaEmbedWM.setBounds(panelEmbedWM.getVisibleRect());
		}
		if(textAreaExtract == null){
			textAreaExtract = new JTextArea();
			textAreaExtract.setBounds(panelExtract.getVisibleRect());
			textAreaExtract.setName("Extract");
		}
		if(textAreaRecognizeSource == null){
			textAreaRecognizeSource = new JTextArea();
			textAreaRecognizeSource.setBounds(panelRecognizeSoure.getVisibleRect());
			textAreaRecognizeSource.setName("RecognizeSource");
		}
		if(textAreaRecognizeWM == null){
			textAreaRecognizeWM = new JTextArea();
			textAreaRecognizeWM.setBounds(panelRecognizeWM.getVisibleRect());
		}
		new DropTarget(textAreaEmbedSource,     new DropTargetController(this));
		new DropTarget(textAreaExtract,         new DropTargetController(this));
		new DropTarget(textAreaRecognizeSource, new DropTargetController(this));


		panelEmbedSource.add(new JScrollPane(textAreaEmbedSource));
		container.add(panelEmbedSource);
		panelEmbedWM.add(new JScrollPane(textAreaEmbedWM));
		container.add(panelEmbedWM);
		panelExtract.add(new JScrollPane(textAreaExtract));
		container.add(panelExtract);
		panelRecognizeSoure.add(new JScrollPane(textAreaRecognizeSource));
		container.add(panelRecognizeSoure);
		panelRecognizeWM.add(new JScrollPane(textAreaRecognizeWM));
		container.add(panelRecognizeWM);

		container.add(embedButton);
		container.add(extractButton);
		container.add(recognizeButton);
		getContentPane().add(line);
		container.add(line);
		setVisible(true);
	}

	private void setEmbedPanel() {
		this.panelEmbedSource = new JPanel(new GridLayout(1,1));
		this.panelEmbedWM = new JPanel(new GridLayout(1,1));
		this.panelEmbedSource.setBounds(50,50,150,50);
		this.panelEmbedWM.setBounds(50, 150, 150, 50);
	}

	private void setExtractPanel() {
		this.panelExtract = new JPanel(new GridLayout(1,1));
		this.panelExtract.setBounds(50,350,150,50);
	}

	private void setRecognizePanel() {
		this.panelRecognizeSoure = new JPanel(new GridLayout(1,1));
		this.panelRecognizeWM = new JPanel(new GridLayout(1,1));
		this.panelRecognizeSoure.setBounds(50, 550, 150, 50);
		this.panelRecognizeWM.setBounds(50, 650, 150, 50);
	}

	private void setThreeButton() {
		embedButton = new JButton("埋め込み");
		extractButton = new JButton("抽出");
		recognizeButton = new JButton("認識");
		embedButton.setBounds(300, 110, 80, 40);
		extractButton.setBounds(300, 350, 80, 40);
		recognizeButton.setBounds(300, 600, 80, 40);

		embedButton.addActionListener(this.controller);
		extractButton.addActionListener(this.controller);
		recognizeButton.addActionListener(this.controller);
	}

	public JTextArea getJtextAreaEmbedSource() {
		return this.textAreaEmbedSource;
	}

	public JTextArea getJtextAreaEmbedWM() {
		return this.textAreaEmbedWM;
	}

	public JTextArea getJtextAreaExtract() {
		return this.textAreaExtract;
	}

	public JTextArea getJTextAreaRecognizeSource() {
		return this.textAreaRecognizeSource;
	}

	public void viewBeforeAfterSource(List<String> sourceList, List<String> afterSourceList) {
			if(this.beforeAfterSource != null) {
				this.beforeAfterSource.frameUpdate();
				this.beforeAfterSource = null;
			}
			this.beforeAfterSource = new View2(sourceList, afterSourceList);
	}

	public void caution(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public class DrawLine extends JPanel {

		public void paint(Graphics g) {
			super.paint(g);
			g.drawLine(200, 75, 250, 75);
			g.drawLine(200, 175, 250, 175);
			g.drawLine(250, 75, 250, 175);
			g.drawLine(250, 125, 300, 125);

			g.drawLine(200, 375, 300, 375);
			g.drawLine(380, 375, 600, 375);

			g.drawLine(200, 625, 300, 625);
			g.drawLine(380, 625, 600, 625);
		}

	}
}
