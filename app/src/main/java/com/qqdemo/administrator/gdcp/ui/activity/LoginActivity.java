package com.qqdemo.administrator.gdcp.ui.activity;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.StringCallback;
import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.model.User;

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

    String codeurl = "http://jw2012.gdcp.cn/CheckCode.aspx";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        super.init();

        initCodeImg();


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
        showProgressDialog("登陆中···");
        OkGo.post("http://jw2012.gdcp.cn")
                .params("__VIEWSTATE", "dDw3OTkxMjIwNTU7Oz72G0jnx2CVi9cEqCETKg2lgGSYBw==")
                .params("TextBox1", "1513157221")// mEdId.getText().toString().trim()
                .params("TextBox2", "1111111")//mEdPwd.getText().toString().trim()
                .params("TextBox3", mEdCode.getText().toString().trim())
                .params("RadioButtonList1", "学生")
                .params("Button1", "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        Log.i(TAG, "onResponse: " + s.toString());
                        Log.i(TAG, "onResponse: " + response.request().url());

                        Document doc = Jsoup.parse(s);
                        Element xhxm = doc.getElementById("xhxm");
                        if(xhxm!=null){
                            Elements elements = doc.select(".sub > li>a");
                            User.url=response.request().url().toString();
                            for(Element element:elements){
                                User.nav.put(element.text(),"http://jw2012.gdcp.cn/"+element.attr("href"));
                            }
                            User.username=xhxm.text().toString();
                            hideProgressDialog();
                            goTo(MainActivity.class,true);
                        }else{
                            initCodeImg();
                            mEdCode.setText("");
                            hideProgressDialog();
                            Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}
