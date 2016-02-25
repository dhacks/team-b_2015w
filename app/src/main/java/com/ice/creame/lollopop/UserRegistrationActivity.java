package com.ice.creame.lollopop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.ice.creame.lollopop.MethodLibrary.*;

/**
 * Created by hideya on 2016/02/20.
 */

public class UserRegistrationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.userresistration);

        LinearLayout parent = (LinearLayout) findViewById(R.id.parent4);
        parent.setBackgroundResource(BACK_GROUND_IMAGE);

        RelativeLayout r1 = (RelativeLayout) findViewById(R.id.r4_1);
        r1.setBackgroundColor(COLOR_3);

        TextView t = (TextView) findViewById(R.id.textView4);
        t.setText("名前を入力してください");
        t.setTextSize(TEXT_SIZE3);
        t.setTextColor(TITLE_COLOR);
        t.setTypeface(tf);

        LinearLayout li_la = (LinearLayout) findViewById(R.id.ll4);

        makeTextView(" ", 10, Color.RED, NO_ID, li_la, null, this);
        makeTextView("※名前は6文字まで", TEXT_SIZE2, TEXT_COLOR_3, NO_ID, li_la, null, this).setGravity(Gravity.CENTER_HORIZONTAL);
        makeTextView(" ", 10, Color.RED, NO_ID, li_la, null, this);
        int textSize = (int) (p.x / 28.8);

        //エディットテキスト用
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(WC, WC);
        param.addRule(RelativeLayout.CENTER_HORIZONTAL);

        //男
        for (int i = 0; i < globals.indexFlag; i++) {
            makeTextView("男" + (i + 1), 40, TEXT_COLOR_3, NO_ID, li_la, null, this).setGravity(Gravity.CENTER_HORIZONTAL);

            EditText et = makeEditText(DEFAULT_NAME_M[i], textSize, i, NO_TAG, makeRelativeLayout(COLOR_1, li_la, null, this)
                    , param, this);
            et.setWidth((int) (p.x / 1.44));
            //入力文字数制限
            InputFilter[] _inputFilter = new InputFilter[1];
            _inputFilter[0] = new InputFilter.LengthFilter(LIMIT_NAME); //文字数指定
            et.setFilters(_inputFilter);
            et.setBackgroundResource(R.drawable.layout_shape); //XMLでフレーム定義
            makeTextView(" ", 20, Color.RED, NO_ID, li_la, null, this);
        }

        //女
        for (int i = 0; i < globals.indexFlag; i++) {
            makeTextView("女" + (i + 1), 40, TEXT_COLOR_3, NO_ID, li_la, null, this).setGravity(Gravity.CENTER_HORIZONTAL);
            EditText et = makeEditText(DEFAULT_NAME_F[i], textSize, i + globals.indexFlag, NO_TAG, makeRelativeLayout(COLOR_1, li_la, null, this)
                    , param, this);
            et.setWidth((int) (p.x / 1.44));
            //入力文字数制限
            InputFilter[] _inputFilter = new InputFilter[1];
            _inputFilter[0] = new InputFilter.LengthFilter(LIMIT_NAME); //文字数指定
            et.setFilters(_inputFilter);
            et.setBackgroundResource(R.drawable.layout_shape); //XMLでフレーム定義
            makeTextView(" ", 20, Color.RED, NO_ID, li_la, null, this);
        }

        int w = (int) (p.x / 1.3);
        int h = (int) (p.y / 7.9);

        RelativeLayout.LayoutParams param1 = new RelativeLayout.LayoutParams(w, h);
        param1.addRule(RelativeLayout.CENTER_HORIZONTAL);


        //plus
        RelativeLayout re = new RelativeLayout(this);
        li_la.addView(re);

        FrameLayout fl = new FrameLayout(this);
        fl.setLayoutParams(param1);
        re.addView(fl);

        Button b1 = new Button(this);
        b1.setBackgroundResource(R.drawable.button);
        b1.setText("スタート");
        b1.setTypeface(tf);
        b1.setTextSize(TEXT_SIZE3);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //音の再生
                seplay(globals.soundpool,globals.sound1,globals.soundFlag);

                for (int i = 0; i < globals.indexFlag; i++) {
                    EditText et = (EditText) findViewById(i);
                    globals.nameM.add(et.getText().toString());
                }

                for (int i = 0; i < globals.indexFlag; i++) {
                    EditText et = (EditText) findViewById(i + globals.indexFlag);
                    globals.nameF.add(et.getText().toString());
                }
                //画面遷移
                Intent intent = new Intent();
                intent.setClassName("com.ice.creame.lollopop", "com.ice.creame.lollopop.BeforeQuestionActivity");
                startActivity(intent);
                UserRegistrationActivity.this.finish();
            }
        });
        fl.addView(b1);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            intent.setClassName("com.ice.creame.lollopop", "com.ice.creame.lollopop.IndexActivity");
            startActivity(intent);
            UserRegistrationActivity.this.finish();
        }
        return false;
    }

}

