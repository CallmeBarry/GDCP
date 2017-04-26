package com.qqdemo.administrator.gdcp.ui.activity;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.presenter.ChangePwdPresenter;
import com.qqdemo.administrator.gdcp.presenter.impl.ChangePwdPresenterImpl;
import com.qqdemo.administrator.gdcp.utils.StringUtils;
import com.qqdemo.administrator.gdcp.view.ChangePwdView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/10.
 */
public class ChangePwdActivity extends BaseActivity implements ChangePwdView {

    @BindView(R.id.ed_pwd)
    EditText mEdPwd;
    @BindView(R.id.ed_newpwd)
    EditText mEdNewpwd;
    @BindView(R.id.ed_newpwd2)
    EditText mEdNewpwd2;
    @BindView(R.id.btn_changepwd)
    Button mBtnChangepwd;
    @BindView(R.id.bar_title)
    TextView mBarTitle;
    @BindView(R.id.back)
    ImageView mBack;
    private ChangePwdPresenter mChangePwdPresenter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_changepwd;
    }

    @Override
    protected void init() {
        super.init();
        mBarTitle.setText("修改密码");
        mChangePwdPresenter = new ChangePwdPresenterImpl(this);
        mEdNewpwd2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    btn_changepwdonClick();
                    return true;

                }
                return false;
            }
        });

    }



    public void btn_changepwdonClick() {
        String oldpwd = mEdPwd.getText().toString();
        String newpwd = mEdNewpwd.getText().toString();
        String newpwd2 = mEdNewpwd2.getText().toString();

        if (!StringUtils.isValidPassword(oldpwd)) {
            mEdPwd.setError("请输入至少6位数密码");
            return;
        }
        if (!StringUtils.isValidPassword(newpwd)) {
            mEdNewpwd.setError("请输入至少6位数密码");
            return;
        }
        if (!newpwd.equals(newpwd2)) {
            mEdPwd.setError("两次密码输入不一致");
            return;
        }

        showProgressDialog("修改中···");
        mChangePwdPresenter.changePwd(oldpwd, newpwd, newpwd2);
    }


    @Override
    public void onChangeSuccess() {
        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
        clearEd();
    }

    @Override
    public void onChangeFailed(String result) {
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        clearEd();
    }

    private void clearEd() {
        hideProgressDialog();
        mEdPwd.setText("");
        mEdNewpwd.setText("");
        mEdNewpwd2.setText("");
    }

    @OnClick({R.id.back, R.id.btn_changepwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.btn_changepwd:
                btn_changepwdonClick();
                break;
        }
    }
}
