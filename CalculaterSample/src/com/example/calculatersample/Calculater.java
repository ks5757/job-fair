package com.example.calculatersample;

import java.math.BigDecimal;

public class Calculater {

	// 入力中の文字列
	StringBuilder mInputNumber = new StringBuilder();
	// 入力中の演算子
	String mOperator;
	// 計算結果
	BigDecimal mResult = new BigDecimal("0.00");

	/**
	 * パラメーターのKeyが数値ならtrueを返却
	 */

	private boolean isNumber(String key) {
		try {
			Integer.parseInt(key);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	/*
	 * パラメーターのKeyがサポートしている演算子ならtrueを返却
	 */

	private boolean isSupportedOperator(String key) {
		if (key.equals("+")) {
			return true;
		} else if (key.equals("-")) {
			return true;
		} else if (key.equals("*")) {
			return true;
		} else if (key.equals("/")) {
			return true;
		} else if (key.equals("=")) {
			return true;
		} else if (key.equals(".")) {
			return true;
		}

		return false;
	}

	/*
	 * 演算を実施
	 */

	private void doCalculation(String ope) {
		if (ope.equals("+")) {
			mResult = mResult.add(new BigDecimal(mInputNumber.toString()));
		} else if (ope.equals("-")) {
			mResult = mResult.subtract(new BigDecimal(mInputNumber.toString()));
		} else if (ope.equals("*")) {
			mResult = mResult.multiply(new BigDecimal(mInputNumber.toString()));
		} else if (ope.equals("/")) {
			if (new BigDecimal(mInputNumber.toString()).equals("0") == false) {
				mResult = mResult.divide(
						new BigDecimal(mInputNumber.toString()), 3,
						BigDecimal.ROUND_HALF_UP);
			}
		}

		mInputNumber = new StringBuilder();
	}

	/*
	 * 入力をクリアする
	 */
	public void reset() {
		mOperator = null;
		mResult = new BigDecimal("0.00");
		mInputNumber = new StringBuilder();
	}

	/*
	 * 入力中の文字の最後尾を削除する
	 */

	public String backSpace() {
		if (mInputNumber.length() > 0) {
			mInputNumber = mInputNumber.deleteCharAt(mInputNumber.length() - 1);
			return mInputNumber.toString();
		}
		return null;
	}

	/*
	 * 入力された文字をもとに処理を行い、ディスプレイに表示する結果を返却する
	 */
	public String putInput(String key) {
		if (isNumber(key) || key.equals(".")) {
			// 数値の場合次の入力を待つ
			if (mInputNumber.length() <= 0)
				mInputNumber.append("0");
			mInputNumber.append(key);
			return null;
		} else if (isSupportedOperator(key)) {
			// サポートしている演算子の場合、入力中の数値をもとに演算を行う
			if (key.equals("=")) {
				// =なら演算を行い結果を返却する
				if (mOperator != null) {
					doCalculation(mOperator);
					mOperator = null;
				}

				return mResult.toString();
			} else {
				if (mOperator != null) {
					// 入力中の演算子があるなら前回の結果をも用いて演算を行う
					doCalculation(mOperator);
					mOperator = null;
				} else if (mInputNumber.length() > 0) {
					// 初めての演算子なら入力中の数値を確定する
					mResult = new BigDecimal(mInputNumber.toString());
					mInputNumber = new StringBuilder();
				}
				mOperator = key;
				return mResult.toString();
			}
		} else {
			return null;
		}
	}
}
