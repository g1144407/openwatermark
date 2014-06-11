package jp.ac.kyoto_su.cse.tamada.oc.watermark.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import jp.ac.kyoto_su.cse.tamada.oc.watermark.view.View;


/**
 * ウィンドウにドラッグ＆ドロップを実現させるクラス
 */
public class DropTargetController extends DropTargetAdapter {
	/**
	 * Viewを保持するためのフィールド。
	 */
	View view;

	/**
	 * DropTargetControllerのコンストラクタ。
	 * 何もしない。
	 */
	public DropTargetController(){}

	/**
	 * DropTargetControllerのコンストラクタ。
	 * Viewのインスタンスをセットする。
	 * @param Viewのインスタンス
	 */
	public DropTargetController(View view){
		this.view = view;
	}

	/**
	 * ドラッグ＆ドロップのイベントからテキストエリアにファイルパスを表示させる。
	 * @param e イベント
	 */
	public void drop(DropTargetDropEvent e){
		try {
			Transferable transfer = e.getTransferable();
			if(transfer.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				e.acceptDrop(DnDConstants.ACTION_COPY);
				List<File> list = (List<File>)transfer.getTransferData(DataFlavor.javaFileListFlavor);
				list.forEach(file -> {
					String name = ((DropTarget)e.getSource()).getComponent().getName();
					if(name.equals("EmbedSource"))
						view.getJtextAreaEmbedSource().setText(file.toString() + "\n");
					else if(name.equals("Extract"))
						view.getJtextAreaExtract().setText(file.toString() + "\n");
					else if(name.equals("RecognizeSource"))
						view.getJTextAreaRecognizeSource().setText(file.toString() + "\n");
				});
				/*
				for(File file:list){
					String name = ((DropTarget) e.getSource()).getComponent().getName();
					System.out.println(name);
					if(name.equals("EmbetSource"))
						view.getJtextAreaEmbetSource().setText(file.toString() + "\n");
					else if(name.equals("Extract"))
						view.getJtextAreaExtract().setText(file.toString() + "\n");
					else if(name.equals("RecognizeSource"))
						view.getJTextAreaRecognizeSource().setText(file.toString() + "\n");
				}*/
			}
		} catch(UnsupportedFlavorException e1){
			e1.printStackTrace();
		} catch(IOException e2){
			e2.printStackTrace();
		}
	}
}
