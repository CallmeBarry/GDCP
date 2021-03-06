package com.qqdemo.administrator.gdcp.ui.activity;

import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.qqdemo.administrator.gdcp.R;
import com.qqdemo.administrator.gdcp.adapter.ScoreListAdapter;
import com.qqdemo.administrator.gdcp.presenter.PerformancePresenter;
import com.qqdemo.administrator.gdcp.presenter.impl.PerformancePresenterImpl;
import com.qqdemo.administrator.gdcp.view.PerformanceView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.lzy.okgo.OkGo.getContext;

public class PerformanceActivity extends BaseActivity implements PerformanceView {


    @BindView(R.id.sp_year)
    Spinner mSpYear;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.sp_term)
    Spinner mSpTerm;
    @BindView(R.id.rv_score)
    RecyclerView mRvScore;
    @BindView(R.id.activity_performance)
    LinearLayout mActivityPerformance;

    PerformancePresenter mPerformancePresenter;
    @BindView(R.id.tv_gpa)
    TextView mTvGpa;
    @BindView(R.id.tv_people)
    TextView mTvPeople;
    @BindView(R.id.tv_xf)
    TextView mTvXf;
    @BindView(R.id.cv_title)
    CardView mCvTitle;
    @BindView(R.id.bar_title)
    TextView mBarTitle;
    @BindView(R.id.back)
    ImageView mBack;

    private List<String> mListYear;
    private String mYear;
    private int mTerm;
    private ScoreListAdapter mScoreListAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_performance;
    }

    @Override
    protected void init() {
        super.init();
        mBarTitle.setText("成绩查询");
        mPerformancePresenter = new PerformancePresenterImpl(this);
        initRecyclerView();
        mPerformancePresenter.init();


    }


    @Override
    public void onInitSuccess() {
        Toast.makeText(this, "初始化完成", Toast.LENGTH_SHORT).show();
        mSpYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mYear = mListYear.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mYear = mListYear.get(0).toString();
            }
        });
        mSpTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mTerm = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTerm = 1;
            }
        });
    }

    @Override
    public void setAdapter() {

        mListYear = mPerformancePresenter.getListYear();

        //适配器
        ArrayAdapter adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mListYear);
        //设置样式
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        mSpYear.setAdapter(adapter1);
        String[] termList = {"1", "2", "3"};
        ArrayAdapter adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, termList);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        mSpYear.setAdapter(adapter1);
        mSpTerm.setAdapter(adapter2);

    }

    @Override
    public void onLoadScore() {
        hideProgressDialog();
        mScoreListAdapter.notifyDataSetChanged();

    }

    @Override
    public void showTitle() {
        mCvTitle.setVisibility(View.VISIBLE);
        mTvGpa.setText(mPerformancePresenter.getGPA());
        mTvPeople.setText(mPerformancePresenter.getPeople());
        mTvXf.setText(mPerformancePresenter.getXF());
    }

    private void initRecyclerView() {
        mScoreListAdapter = new ScoreListAdapter(mPerformancePresenter.getScoreList());
        mRvScore.setHasFixedSize(true);
        mRvScore.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvScore.setAdapter(mScoreListAdapter);
    }



    @OnClick({R.id.back, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                this.finish();
                break;
            case R.id.btn_search:
                showProgressDialog("查询中···");
                mPerformancePresenter.requestBy(mYear, mTerm);
                break;
        }
    }
}
