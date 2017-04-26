package com.qqdemo.administrator.gdcp.ui.activity;


import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.model.User;
import com.qqdemo.administrator.gdcp.utils.StringUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/9.
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    String codeurl = "http://jw2012.gdcp.cn/CheckCode.aspx";
    @BindView(R.id.ed_id)
    EditText mEdId;
    @BindView(R.id.ed_pwd)
    EditText mEdPwd;
    @BindView(R.id.iv_code)
    ImageView mIvCode;
    @BindView(R.id.btn_changecode)
    Button mBtnChangecode;
    @BindView(R.id.ed_code)
    EditText mEdCode;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.switchButton)
    SwitchButton mSwitchButton;

    private SharedPreferences mSharedPreferences;
    private boolean mFlag = true;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();

        mSharedPreferences = getSharedPreferences("login", Activity.MODE_PRIVATE);

        boolean remember = mSharedPreferences.getBoolean("remember", false);
        if (remember) {
            String id = mSharedPreferences.getString("id", "");
            String pwd = mSharedPreferences.getString("pwd", "");
            mEdId.setText(id);
            mEdPwd.setText(pwd);
        }


        initCodeImg();
        mEdCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                    return true;
                }
                return false;
            }
        });
        mSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFlag = isChecked;
            }
        });

    }

    private void initCodeImg() {
        OkGo.get(codeurl)
                .tag(this)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(final Bitmap bitmap, Call call, Response response) {
                        mIvCode.setImageBitmap(bitmap);
                    }
                });

    }

    @OnClick({R.id.btn_changecode, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_changecode:
                initCodeImg();
                break;
            case R.id.btn_login:
                hidKeyboard();
                login();
                break;
        }
    }

    private void login() {
        final String id = mEdId.getText().toString().trim();

        final String pwd = mEdPwd.getText().toString().trim();
        String code = mEdCode.getText().toString().trim();
        if (!StringUtils.isValidUserName(id)) {
            mEdId.setError("请输入正确的用户名");
            return;
        }
        if (!StringUtils.isValidPassword(pwd)) {
            mEdPwd.setError("请输入至少6位数密码");
            return;
        }
        if (!StringUtils.isValidCode(code)) {
            mEdCode.setError("请输入验证码");
            return;
        }

        showProgressDialog("登陆中···");
        OkGo.post("http://jw2012.gdcp.cn")
                .params("__VIEWSTATE", "dDw3OTkxMjIwNTU7Oz72G0jnx2CVi9cEqCETKg2lgGSYBw==")
                .params("TextBox1", id)
                .params("TextBox2", pwd)
                .params("TextBox3", code)
                .params("RadioButtonList1", "学生")
                .params("Button1", "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Document doc = Jsoup.parse(s);
                        Element xhxm = doc.getElementById("xhxm");
                        if (xhxm != null) {
                            Elements elements = doc.select(".sub > li>a");
                            User.url = response.request().url().toString();
                            for (Element element : elements) {
                                User.nav.put(element.text(), "http://jw2012.gdcp.cn/" + element.attr("href"));
                            }
                            User.username = xhxm.text().toString();
                            hideProgressDialog();
                            rememberPWD(id, pwd);
                            goTo(MainActivity.class, true);
                        } else {
                            initCodeImg();
                            mEdCode.setText("");
                            hideProgressDialog();
                            Elements script = doc.select("script");
                            String str = script.get(1).html().toString();
                            String result = str.substring(str.indexOf('\'') + 1, str.lastIndexOf('\''));
                            Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void rememberPWD(String id, String pwd) {

        //实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (mFlag) {
            //用putString的方法保存数据
            editor.putBoolean("remember", true);
            editor.putString("id", id);
            editor.putString("pwd", pwd);
        } else {
            //用putString的方法保存数据
            editor.putBoolean("remember", false);
            editor.putString("id", "");
            editor.putString("pwd", "");
        }
        //提交当前数据
        editor.apply();

    }

}
