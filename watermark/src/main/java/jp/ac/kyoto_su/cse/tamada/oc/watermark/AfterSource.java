package jp.ac.kyoto_su.cse.tamada.oc.watermark;

import java.util.List;

/**
 * 透かし情報と透かし情報を埋め込んだソースコードを保持するクラス
 */
public class AfterSource extends Source {
	/**
	 * 透かし情報を保持するためのフィールド
	 */
	private WaterMark W;

	/**
	 * 透かし情報を埋め込んだソースコードのリストを保持するためのフィールド
	 */
	private List<String> afterSource;

	/**
	 * 透かし情報を返す。
	 * @return 透かし情報を持つインスタンス
	 */
	public WaterMark getW() {
		return W;
	}

	/**
	 * 透かし情報をセットする。
	 * @param w 透かし情報を持つインスタンス
	 */
	public void setW(WaterMark w) {
		W = w;
	}

	/**
	 * 透かし情報を埋め込んだソースコードのリストを返す。
	 * @return 透かし情報を埋め込んだソースコードのリスト
	 */
	public List<String> getP() {
		return this.afterSource;
	}

	/**
	 * 透かし情報を埋め込んだソースコードのリストをセットする。
	 * @param afterSource 透かし情報を埋め込んだソースコードのリスト
	 */
	public void setP(List<String> afterSource) {
		this.afterSource = afterSource;
	}
}
