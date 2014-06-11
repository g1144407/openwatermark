package jp.ac.kyoto_su.cse.tamada.oc.watermark.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.io.FileNotFoundException;

import jp.ac.kyoto_su.cse.tamada.oc.watermark.model.SourceModel;
import jp.ac.kyoto_su.cse.tamada.oc.watermark.view.View;

import java.util.List;

/**
 * controllerクラス
 */
public class DisplayController implements ActionListener {

	/**
	 * Modelを保持するためのフィールド
	 */
	protected SourceModel model;

	/**
	 * Viewを保持するためのフィールド
	 */
	protected View view;

	/**
	 * 入力されたソースコードのファイルパスに対するcontrollerを保持するためのフィールド
	 */
	private InputDisplayController idc;

	/**
	 * 入力された透かし情報に対するcontrollerを保持するためのフィールド
	 */
	private EmbedDisplayController edc;


	//private RecognizeDisplayController rdc;

	/**
	 * DisplayControllerのコンストラクタ
	 * 各フィールドを初期化する。
	 * @param view Viewのインスタンス
	 */
	public DisplayController(View view) {
		this.model = SourceModel.getInstance();
		this.view = view;
		this.idc = new InputDisplayController();
		this.edc = new EmbedDisplayController();
		//this.rdc = new RecognizeDisplayController();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		List<String> sourceList, afterSourceList;
		System.out.println(cmd);
		if(cmd.equals("埋め込み")) {
			String source = this.view.getJtextAreaEmbedSource().getText();
			String waterMark = this.view.getJtextAreaEmbedWM().getText();
			if(source.length() == 0) {
				this.view.caution("ファイルが指定されていません");
			} else if(waterMark.length() == 0) {
				this.view.caution("埋め込む透かし情報が入力されていません");
			} else {
				try {
					sourceList = this.idc.controller(source);
					afterSourceList = this.edc.controller(waterMark);
					this.view.viewBeforeAfterSource(sourceList, afterSourceList);
				} catch(FileNotFoundException fnfe) {
					this.view.caution("ファイルパスが間違っています");
				}
			}
		} else if(cmd.equals("抽出")) {
			String source = this.view.getJtextAreaEmbedSource().getText();
			afterSourceList = this.edc.controller(this.view.getJtextAreaExtract().getText());
			if(afterSourceList.size() == 0)
				this.view.caution("ファイルが指定されていません");
			//else

		} else if(cmd.equals("認識")) {

		}
	}
}
