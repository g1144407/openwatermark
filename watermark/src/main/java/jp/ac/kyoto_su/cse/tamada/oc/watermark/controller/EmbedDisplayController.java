package jp.ac.kyoto_su.cse.tamada.oc.watermark.controller;

import jp.ac.kyoto_su.cse.tamada.oc.watermark.model.SourceModel;

import java.util.List;

/**
 * 入力された透かし情報に対するcontrollerクラス
 */
public class EmbedDisplayController {
	/**
	 * 入力された透かし情報から、透かし情報を埋め込んだソースコードを作成する。
	 * @param waterMark 入力された透かし情報
	 * @return embededWMSourceList 透かし情報を埋め込んだソースコードのリスト
	 */
	public List<String> controller(String waterMark) {
		List<String> embededWMSourceList = SourceModel.getInstance().createPw(waterMark).getP();
		return embededWMSourceList;
	}
}
