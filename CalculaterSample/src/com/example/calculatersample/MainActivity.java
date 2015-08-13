package com.example.calculatersample;

//アンドロイド電卓アプリ
//１０日でおぼえるAndroidアプリ開発入門教室を参考にしました。

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private TextView mTextView1; // 計算の答え表示
	private TextView mTextView2; // 計算式の表示

	private Button mBtn[] = new Button[10]; // 数字ボタン0～9

	// 各種ボタン
	private Button mBtnCopy;
	private Button mBtnAc;
	private Button mBtnBs;
	private Button mBtnC;
	private Button mBtnDiv;
	private Button mBtnMul;
	private Button mBtnMinus;
	private Button mBtnPlus;
	private Button mBtnDot;
	private Button mBtnEqual;
	Calculater mCalculater = new Calculater();
	ClipData.Item item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView1 = (TextView) findViewById(R.id.txtEdit1);
		mTextView2 = (TextView) findViewById(R.id.txtEdit2);

		for (int i = 0; i < 10; i++) {
			mBtn[i] = new Button(this);
		}

		// 各種ボタンのidを設定
		mBtn[0] = (Button) findViewById(R.id.btn0);
		mBtn[1] = (Button) findViewById(R.id.btn1);
		mBtn[2] = (Button) findViewById(R.id.btn2);
		mBtn[3] = (Button) findViewById(R.id.btn3);
		mBtn[4] = (Button) findViewById(R.id.btn4);
		mBtn[5] = (Button) findViewById(R.id.btn5);
		mBtn[6] = (Button) findViewById(R.id.btn6);
		mBtn[7] = (Button) findViewById(R.id.btn7);
		mBtn[8] = (Button) findViewById(R.id.btn8);
		mBtn[9] = (Button) findViewById(R.id.btn9);

		mBtnCopy = (Button) findViewById(R.id.btnCopy);
		mBtnC = (Button) findViewById(R.id.btnC);
		mBtnBs = (Button) findViewById(R.id.btnBs);
		mBtnAc = (Button) findViewById(R.id.btnAc);
		mBtnMul = (Button) findViewById(R.id.btnMul);
		mBtnDiv = (Button) findViewById(R.id.btnDiv);
		mBtnPlus = (Button) findViewById(R.id.btnPlus);
		mBtnMinus = (Button) findViewById(R.id.btnMinus);
		mBtnDot = (Button) findViewById(R.id.btnDot);
		mBtnEqual = (Button) findViewById(R.id.btnEqual);

		// 各種ボタンのクリックリスナー登録

		for (int i = 0; i < 10; i++) {

			mBtn[i].setOnClickListener(this);
		}

		mBtnMul.setOnClickListener(this);
		mBtnDiv.setOnClickListener(this);
		mBtnPlus.setOnClickListener(this);
		mBtnMinus.setOnClickListener(this);
		mBtnEqual.setOnClickListener(this);
		mBtnDot.setOnClickListener(this);

		mBtnCopy.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ

				item = new ClipData.Item(mTextView1.getText());

				// MIMETYPEの作成
				String[] mimeType = new String[1];
				mimeType[0] = ClipDescription.MIMETYPE_TEXT_URILIST;

				// クリップボードに格納するClipDataオブジェクトの作成
				ClipData cd = new ClipData(new ClipDescription("text_data",
						mimeType), item);

				// クリップボードにデータを格納
				ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				cm.setPrimaryClip(cd);
			}

		});

		mBtnC.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ

				// 入力中の数値と計算結果表示をリセット
				mCalculater.reset();
				mTextView1.setText("0");

			}

		});

		mBtnBs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ

				// 入力中の数値を一文字削除する
				StringBuilder temp = new StringBuilder();
				temp.append(mTextView2.getText().toString());

				if (mCalculater.backSpace() != null) {
					temp = temp.deleteCharAt(temp.length() - 1);
					mTextView2.setText(temp.toString());

				}
			}

		});

		mBtnAc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ

				// 入力中の数値と計算結果表示と計算式表示をリセット
				mCalculater.reset();
				mTextView1.setText("0");
				mTextView2.setText("");
			}

		});

	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ

		// 数字ボタンと演算器号、ドットをタップした時の処理
		Button btn = (Button) v;
		String input = btn.getText().toString();
		mTextView2.append(input);
		String dispText = mCalculater.putInput(input);
		if (dispText != null) {
			mTextView1.setText(dispText);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
