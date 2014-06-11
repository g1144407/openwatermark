package jp.ac.kyoto_su.cse.tamada.oc.watermark;

import java.util.List;

/**
 * 透かし情報を埋め込む前のソースコードを保持するクラス
 */
public class Source {
	private List<String> source;
	private String name;
	public List<String> getSource() {
		return source;
	}
	public void setSource(List<String> source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public void setName(String path) {
		String[] strgs = path.split("/");
		String filename =  strgs[strgs.length - 1];
		this.name = filename;
	}
}
