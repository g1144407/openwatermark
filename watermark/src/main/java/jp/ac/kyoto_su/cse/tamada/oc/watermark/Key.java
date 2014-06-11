package jp.ac.kyoto_su.cse.tamada.oc.watermark;

import java.util.Map;
import java.util.HashMap;

/**
 * 公開鍵、秘密鍵の情報を持つクラス
 */
public class Key {
	/**
	 * 公開鍵の情報を保存するためのフィールド
	 */
	private Map<String, Integer> publicKeyMap;

	/**
	 * 秘密鍵の情報を保存するためのフィールド
	 */
	private Map<String, Integer> privateKeyMap;

	/**
	 * 素数テーブルを保存するためのフィールド
	 */
	private boolean[] primeTable = new boolean[501];

	/**
	 * Keyのコンストラクタ。</br>
	 * 公開鍵、秘密鍵のマップと素数テーブルを初期化する。
	 */
	public Key() {
		this.publicKeyMap = new HashMap<>();
		this.privateKeyMap = new HashMap<>();
		initPrimeTable();
	}

	/**
	 * 素数テーブルを初期化する。
	 */
	private void initPrimeTable() {
		primeTable[0] = false;
		primeTable[1] = false;
		for(int i = 2; i < primeTable.length; i++)
			primeTable[i] = true;
		for(int i = 2; i < primeTable.length; i++) {
			if(primeTable[i])
				for(int j = i * 2; j < primeTable.length; j += i)
					primeTable[j] = false;
		}
	}

	/**
	 * 公開鍵と秘密鍵を生成する。
	 */
	public void generateKey() {
		int firstPrime = (int)(Math.random() * 500);
		int secondPrime = (int)(Math.random() * 500);
		int n;

		while(true) {
			while(!primeTable[firstPrime])
				firstPrime = (int)(Math.random() * 500);
			while(true) {
				secondPrime = (int)(Math.random() * 500);
				if(primeTable[secondPrime] && (firstPrime != secondPrime))
					break;
			}
			n = firstPrime * secondPrime;
			if(n > 100000)
				break;
		}

		int l = calculateLCM(firstPrime - 1, secondPrime - 1);
		int e = (int)(Math.random() * 500);
		while(true) {
			e = (int)(Math.random() * 500);
			if((euclidGCM(e, l) == 1) && (e < l) && (e > 1))
				break;
		}

		int d = l - 1;
		while(!((e*d % l) == 1))
			d--;

		this.publicKeyMap.put("E", new Integer(e));
		this.publicKeyMap.put("N", new Integer(n));

		this.privateKeyMap.put("D", new Integer(d));
		this.privateKeyMap.put("N", new Integer(n));
	}

	/**
	 * ユークリッドの互除法を行う。
	 * @param x 1つ目の正整数
	 * @param y 2つ目の正整数
	 * @return 正整数x, yの最大公約数
	 */
	private int euclidGCM(int x, int y) {
		int gcd;
		if(y == 0)
			gcd = x;
		else
			gcd = euclidGCM(y, x % y);
		return gcd;
	}

	/**
	 * 最小公倍数を求める。
	 * @param x 1つ目の正整数
	 * @param y 2つ目の正整数
	 * @return 正整数x, yの最小公倍数
	 */
	private int calculateLCM(int x, int y) {
		return (x*y / euclidGCM(x, y));
	}

	/**
	 * 公開鍵の情報を持つマップを返す。
	 * @return 公開鍵の情報を持つマップ
	 */
	public Map<String, Integer> getPublicKey() {
		return this.publicKeyMap;
	}

	/**
	 * 秘密鍵の情報を持つマップを返す。
	 * @return 秘密鍵の情報を持つマップ
	 */
	public Map<String, Integer> getPrivateKey() {
		return this.privateKeyMap;
	}

}