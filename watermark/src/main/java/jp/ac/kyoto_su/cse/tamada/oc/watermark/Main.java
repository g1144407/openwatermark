package jp.ac.kyoto_su.cse.tamada.oc.watermark;

import jp.ac.kyoto_su.cse.tamada.oc.watermark.model.SourceModel;


/**
 * Mainクラス
 */
public class Main {
	public static void main(String[] args){
		SourceModel model = SourceModel.getInstance();
		model.start();
	}
}
