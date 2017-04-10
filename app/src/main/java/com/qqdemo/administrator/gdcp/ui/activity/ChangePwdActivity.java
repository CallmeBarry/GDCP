package com.qqdemo.administrator.gdcp.ui.activity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.presenter.ChangePwdPresenter;
import com.qqdemo.administrator.gdcp.presenter.impl.ChangePwdPresenterImpl;
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
    private ChangePwdPresenter mChangePwdPresenter;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_changepwd;
    }

    @Override
    protected void init() {
        super.init();
        mChangePwdPresenter = new ChangePwdPresenterImpl(this);
    }


    @OnClick(R.id.btn_changepwd)
    public void onClick() {
        String oldpwd=mEdPwd.getText().toString();
        String newpwd=mEdNewpwd.getText().toString();
        String newpwd2=mEdNewpwd2.getText().toString();
        showProgressDialog("修改中···");
        mChangePwdPresenter.changePwd(oldpwd,newpwd,newpwd2);
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
    private void clearEd(){
        hideProgressDialog();
        mEdPwd.setText("");
        mEdNewpwd.setText("");
        mEdNewpwd2.setText("");
    }
}
