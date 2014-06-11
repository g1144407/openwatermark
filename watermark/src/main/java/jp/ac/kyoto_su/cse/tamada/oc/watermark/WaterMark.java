package jp.ac.kyoto_su.cse.tamada.oc.watermark;

import java.util.List;

/**
 * 透かし情報を保持するクラス
 */
public class WaterMark {
	//文字列

	/**
	 * 入力された透かし情報を保持するためのフィールド
	 */
	private String waterMark;

	/**
	 * 2進数に変換された透かし情報を保持するためのフィールド
	 */
	private List<String> tranformedWaterMark;

	/**
	 * 入力された透かし情報を返す。
	 * @return 入力された透かし情報
	 */
	public String getWaterMark() {
		return waterMark;
	}

	/**
	 * 入力された透かし情報をセットする。
	 * @param waterMark 入力された透かし情報
	 */
	public void setWaterMark(String waterMark) {
		this.waterMark = waterMark;
	}

	/**
	 * 2進数に変換された透かし情報を持つリストを返す。
	 * @return 2進数に変換された透かし情報を持つリスト
	 */
	public List<String> getTranformedWaterMark() {
		return tranformedWaterMark;
	}

	/**
	 * 2進数に変換された透かし情報を持つリストをセットする。
	 * @param waterMark 2進数に変換された透かし情報を持つリスト
	 */
	public void setTransformedWaterMark(List<String> waterMark) {
		this.tranformedWaterMark = waterMark;
	}


}