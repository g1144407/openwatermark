package jp.ac.kyoto_su.cse.tamada.oc.watermark.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;

import java.math.BigInteger;

import jp.ac.kyoto_su.cse.tamada.oc.watermark.Source;
import jp.ac.kyoto_su.cse.tamada.oc.watermark.AfterSource;
import jp.ac.kyoto_su.cse.tamada.oc.watermark.view.View;
import jp.ac.kyoto_su.cse.tamada.oc.watermark.WaterMark;
import jp.ac.kyoto_su.cse.tamada.oc.watermark.Key;


/**
 * Modelクラス
 */
public class SourceModel {

	/**
	 * 透かし情報を埋め込む前のソースコードを保持するためのフィールド
	 */
	private Source source = new Source();

	/**
	 * 埋め込んだ透かし情報、透かし情報を埋め込んだソースコードを保持するためフィールド
	 */
	private AfterSource afterSource = new AfterSource();

	/**
	 * 自クラスのインスタンスを保持するフィールド</br>
	 * このクラスはシングルトンのため、個々に保持されたインスタンス以外は存在しない。
	 */
	static SourceModel model = null;

	/**
	 * Viewを保持するためのフィールド
	 */
	private View aView;

	/**
	 * 公開鍵と秘密鍵の情報を保存するためのフィールド
	 */
	private Key key;

	/**
	 * SourceModelのコンストラクタanngouka</br>
	 * 何もしない。
	 */
	private SourceModel() {}

	/**
	 * シングルトンクラスのため、自インスタンスを返す。
	 * @return Modelのインスタンス
	 */
	public static SourceModel getInstance() {
		if(model == null)
			model = new SourceModel();
		return model;
	}

	/**
	 * 埋め込んだ透かし情報、透かし情報を埋め込んだソースコードを持つインスタンスを返す。
	 */
	public AfterSource getAfterSource() {
		return this.afterSource;
	}

	/**
	 * ウィンドウを構成する。
	 */
	public void start() {
		this.aView = new View();
	}

	/**
	 * 入力された透かし情報を暗号化する。
	 * @param waterMark 入力された透かし情報
	 * @return 暗号化された透かし情報を持つリスト
	 */
	private List<String> tranceWater(String waterMark) {
		List<String> unicodeWMList = new ArrayList<>();
		this.key = new Key();
		char[] charArray = waterMark.toCharArray();
		for(char ch : charArray) {
			String unicode = String.format("%1$06d", (int)ch);
			unicodeWMList.add(unicode);
		}

		this.key.generateKey();
		Map<String, Integer> publicKeyMap = this.key.getPublicKey();
		List<String> encodedWMList = unicodeWMList.stream()
			.map((String unicodeWM) -> {
				BigInteger encodedWM = new BigInteger(unicodeWM);
				encodedWM = encodedWM.modPow(new BigInteger(publicKeyMap.get("E").toString())
											 , new BigInteger(publicKeyMap.get("N").toString()) );
				System.out.println(new String(encodedWM.toByteArray(), 0, 1));
				return encodedWM.toString();
			})
			.collect(Collectors.toList());
/*
		List<String> binaryWMList = unicodeWMList.stream()
			.map((String unicode) -> {
				int binaryInt = Integer.parseInt(unicode);
				String binary = Integer.toBinaryString(binaryInt);
				return binary;
			})
			.collect(Collectors.toList());
*/
		restorationWater(encodedWMList);
		return encodedWMList;
		/*
		//16進数(Unicode)に変換
		//2進数を10進数に変換　*/
	}

	/**
	 * 暗号化された透かし情報を、入力された透かし情報に復号する。
	 * @param 暗号化された透かし情報を持つリスト
	 * @return 復号された透かし情報を持つリスト
	 */
	private List<String> restorationWater(List<String> encodedWMList) {
		Map<String, Integer> privateKeyMap = this.key.getPrivateKey();
		List<String> decodedWMList = encodedWMList.stream()
			.map((String encodedWM) -> {
				BigInteger decodedWM = new BigInteger(encodedWM);
				decodedWM = decodedWM.modPow(new BigInteger(privateKeyMap.get("D").toString())
											, new BigInteger(privateKeyMap.get("N").toString()) );
				int byteLength = decodedWM.toByteArray().length;
				return (byteLength > 1) ? new String(new int[]{Integer.parseInt(decodedWM.toString())}, 0, 1)
											: new String(decodedWM.toByteArray(), 0, 1);
			})
			.collect(Collectors.toList());

		/*
		List<String> embededWMList = binaryWMList.stream()
			.map((String binary) -> {
				int[] decimalWM = new int[]{Integer.parseInt(binary, 2)};
				String embededString = new String(decimalWM, 0, 1);
				return embededString;
			})
			.collect(Collectors.toList());*/
		System.out.println("変換した透かし: " + encodedWMList + ",  復元した透かし" + decodedWMList);
		//10進数を2進数に復元
		//2進数を文字コードに復元
		//文字コードを文字に復元
		return decodedWMList;
	}
	/*
	//抽出
	public AfterSource extractSource(){
		List<String> beforeSourceList = this.source.getSource();
		List<String> afterSourceList = this.afterSource.getSource();

		List<String> embededWMList = afterSourceList.stream()
			.filter((String line) -> (beforeSourceList.indexOf(line) == -1))
		String after
		for(String line : sourceList) {

			if(line.contains("main")) {

			}
		}

		String[] strgs =splitString(this.source.getSource());
		String str ="";
		String str2="";
		System.out.println(strgs.length);
		for (int i = 0 ; i < strgs.length ; i++){
			//System.out.println("a"+i);
			Pattern p = Pattern.compile("main");
			Matcher m = p.matcher(strgs[i]);
			str += strgs[i];
			if(m.find()){
				for(int t = 1 ; t <= 6 ; t++){
					//System.out.println("c");
					str2+=strgs[i+t];

				}
				System.out.print(str2);
				//System.out.println("test");
				//System.out.println(strgs[i+1]);
				i=i+6;
			}
		}
		afterSource.setP(str2);
		this.waterMark.setSorce(str);
		int[] num = new int[2];
		String [] str3 = str2.split("\n");
		num[0] = str3[1].indexOf("=") + 1;
		num[1] = str3[1].indexOf(";");
		String literal = "";
		for(int t = 1; t <= 5;t++){
			literal += str3[t].substring(num[0], num[1]);
		}
		watermark.setWaterMark(restorationWater(literal));
		System.out.println(watermark.getWaterMark());
		afterSource.setW(waterMark);
		return afterSource;

		//mainメソッドを探す
		//PとWに分ける
		//WからwaterMark(文字列)を復元する。
	}

	/*
	//認識度
	public float recognition(){
		//テキストから配列の中身をとってきて
		//if文で認識する。こっちで
		return ;
	}
	*/
	//public Source save(){
	//}
	//inputからソースをつくっている

	/**
	 * ソースコードのファイルパスからソースコードを作成する。
	 * @param path ソースコードのファイルパス
	 * @throws FileNotFoundException ファイルがパスが不正な場合のエラー
	 * @return 透かし情報を埋め込む前のソースコード
	 */
	public Source createSource(String path) throws FileNotFoundException {
		if(path.contains("\n")) {
			String filePath = path.substring(0, path.indexOf("\n"));
			System.out.println("path is " + filePath + ".");
			List<String> lines = callText(filePath);
			this.source.setSource(lines);
			this.source.setName(filePath);
			return source;
		} else {
			throw new FileNotFoundException();
		}
		//System.out.println("createTest"+this.source.getSource());
		//ファイルをロードする
		//テキストに変換する
		//Sourceにセットする
		//returnでクラスを返す
	}

	/**
	 * ソースコードのファイルパスからソースコードのリストを作成する。
	 * @param path ソースコードのファイルパス
	 * @return ソースコードのリスト
	 */
	private List<String> callText(String path) {
		List<String> sourceList = new ArrayList<>();

		try(FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr)) {
			br.lines().forEach((String line) -> sourceList.add(line + "\n"));
			/*
			String line;
			while((line = br.readLine()) != null)
				sourceList.add(line + "\n");
			*/
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return sourceList;
	}

	/**
	 * デスクトップに透かし情報を埋め込んだソースコードを保存する。
	 * @param text 暗号化された透かし情報を埋め込んだソースコード
	 */
	private void printFile(AfterSource text) {
		String path = System.getProperty("user.home");
		try(FileWriter filewriter = new FileWriter(new File(path + "/Desktop/" + source.getName()));
			BufferedWriter bw     = new BufferedWriter(filewriter);
			PrintWriter pw        = new PrintWriter(bw)) {
			for(String line : text.getP())
				pw.println(line);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ソースコードに透かし情報を埋め込む。
	 * @param waterMark 入力された透かし情報
	 * @return 暗号化された透かし情報を埋め込んだソースコード
	 */
	public AfterSource createPw(String waterMark){
		List<String> beforeSource = this.source.getSource();
		List<String> transformedWM = tranceWater(waterMark);
		List<String> embededWMSource = new ArrayList<>();
		WaterMark embededWaterMark = new WaterMark();
		embededWaterMark.setWaterMark(waterMark);
		embededWaterMark.setTransformedWaterMark(transformedWM);
		this.afterSource.setW(embededWaterMark);

		for(String line : beforeSource) {
			if(line.contains("main")) {
				String transLine = line + "\t\t" + "int[] a = new int[" + String.valueOf(waterMark.length()) + "];\n";
				for(int i = 0; i < waterMark.length(); i++)
					transLine += "\t\t" + "a[" + String.valueOf(i) + "] = " + transformedWM.get(i) + ";\n";
				embededWMSource.add(transLine);
			} else {
				embededWMSource.add(line);
			}
		}

		this.afterSource.setP(embededWMSource);
		printFile(this.afterSource);
		return this.afterSource;

		//WaterMarkにセットする
		//mainメソッドがあるか調べる
		//文字を10進数に変換する
		//mainの中に埋め込む
		//なかったらmain作成
		//配列を5つ追加、埋め込む
		//Sourceにセットする
		//.javaの生成
		//returnする
	}
}
