package jp.ac.kyoto_su.cse.tamada.oc.watermark.controller;

import java.io.FileNotFoundException;
import jp.ac.kyoto_su.cse.tamada.oc.watermark.model.SourceModel;

import java.util.List;

/**
 * 入力されたソースコードのファイルパスに対するcontrollerクラス
 */
public class InputDisplayController {

	/**
	 * ファイルパスからソースコードのリストを作成する。
	 * @param filePath ソースコードのファイルパス
	 * @return ソースコードのリスト
	 */
	public List<String> controller(String filePath) throws FileNotFoundException {
		List<String> sourceList = SourceModel.getInstance().createSource(filePath).getSource();
		return sourceList;
	}
}
