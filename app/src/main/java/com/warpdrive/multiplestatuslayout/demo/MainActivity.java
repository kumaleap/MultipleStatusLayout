package com.warpdrive.multiplestatuslayout.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.warpdrive.multiplestatuslayout.MultipleStatusLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MultipleStatusLayout multipleStatusLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.empty).setOnClickListener(this);
        findViewById(R.id.error).setOnClickListener(this);
        findViewById(R.id.loading).setOnClickListener(this);
        findViewById(R.id.content).setOnClickListener(this);
        multipleStatusLayout = new MultipleStatusLayout(this).include(R.id.refreshLayout)
                .setEmptyView(R.layout.empty)
                .setLoadingView(R.layout.loading)
                .setErrorView(R.layout.error)
                .showLoading();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.empty:
                multipleStatusLayout.showEmpty();
                break;
            case R.id.content:
                multipleStatusLayout.showContent();
                break;
            case R.id.error:
                multipleStatusLayout.showError();
                break;
            case R.id.loading:
                multipleStatusLayout.showLoading();
                break;
        }
    }
}
