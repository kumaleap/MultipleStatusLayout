package com.warpdrive.multiplestatuslayout;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by wulijie on 2018/1/31.
 * 多状态布局容器-用于管理多状态页面
 */
public class MultipleStatusLayout extends FrameLayout {

    /* 未知状态 **/
    private static final int VIEW_STATE_UNKNOWN = -1;
    /* 正常状态 **/
    private static final int VIEW_STATE_CONTENT = 0;
    /* 错误状态 **/
    private static final int VIEW_STATE_ERROR = 1;
    /* 空白状态 **/
    private static final int VIEW_STATE_EMPTY = 2;
    /* loading状态 **/
    private static final int VIEW_STATE_LOADING = 3;

    private LayoutInflater mInflater;

    private View mContentView;

    private View mLoadingView;

    private View mErrorView;

    private View mEmptyView;

    private Context context;

    private int mViewState = VIEW_STATE_UNKNOWN;

    private FrameLayout.LayoutParams defaultLayoutParams =
            new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);


    public MultipleStatusLayout(Context context) {
        this(context, null);
    }


    public MultipleStatusLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mInflater = LayoutInflater.from(getContext());
    }

    public MultipleStatusLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        mInflater = LayoutInflater.from(getContext());
    }

    /**
     * 指定loading布局
     *
     * @param loadingView loadingView
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout setLoadingView(View loadingView) {
        this.mLoadingView = loadingView;
        return this;
    }

    /**
     * 指定loading布局
     *
     * @param loadingViewResId loadingViewResId
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout setLoadingView(int loadingViewResId) {
        setLoadingView(mInflater.inflate(loadingViewResId, this, false));
        return this;
    }

    /**
     * 指定空白布局
     *
     * @param emptyView emptyView
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
        return this;
    }

    /**
     * 指定空白布局
     *
     * @param emptyViewResId emptyViewResId
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout setEmptyView(int emptyViewResId) {
        setEmptyView(mInflater.inflate(emptyViewResId, this, false));
        return this;
    }

    /**
     * 指定错误布局
     *
     * @param errorView errorView
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout setErrorView(View errorView) {
        this.mErrorView = errorView;
        return this;
    }

    /**
     * 指定错误布局
     *
     * @param errorViewResId errorViewResId
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout setErrorView(int errorViewResId) {
        setErrorView(mInflater.inflate(errorViewResId, this, false));
        return this;
    }

    public MultipleStatusLayout include(View contentView) {
        this.mContentView = contentView;
        if (null == mContentView)
            throw new NullPointerException("include contentView error : contentView == null");
        ViewGroup contentParent = (ViewGroup) mContentView.getParent();
        if (contentParent != null) {
            contentParent.removeView(mContentView);
            addView(mContentView, mContentView.getLayoutParams());//添加
            contentParent.addView(this);
        }
        return this;
    }

    /**
     * 指定要包裹的正文布局id
     *
     * @param contentId contentId
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout include(int contentId) {
        if (context instanceof Activity) include(((Activity) context).findViewById(contentId));
        return this;
    }

    /**
     * 显示loading
     *
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout showLoading() {
        if (mViewState == VIEW_STATE_LOADING) return this;//已经显示 拒绝再次执行
        mViewState = VIEW_STATE_LOADING;
        if (mLoadingView == null) return this;
        if (!contain(mLoadingView)) addView(mLoadingView, mLoadingView.getLayoutParams());
        showByType();
        return this;
    }

    /**
     * 显示错误页面
     *
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout showError() {
        if (mViewState == VIEW_STATE_ERROR) return this;//已经显示 拒绝再次执行
        mViewState = VIEW_STATE_ERROR;
        if (mErrorView == null) return this;
        if (!contain(mErrorView)) addView(mErrorView, mErrorView.getLayoutParams());
        showByType();
        return this;
    }

    /**
     * 显示空白页面
     *
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout showEmpty() {
        if (mViewState == VIEW_STATE_EMPTY) return this;//已经显示 拒绝再次执行
        mViewState = VIEW_STATE_EMPTY;
        if (mEmptyView == null) return this;
        if (!contain(mEmptyView)) addView(mEmptyView, mEmptyView.getLayoutParams());
        showByType();
        return this;
    }


    /**
     * 显示内容
     *
     * @return MultipleStatusLayout
     */
    public MultipleStatusLayout showContent() {
        if (mViewState == VIEW_STATE_CONTENT) return this;//已经显示 拒绝再次执行
        mViewState = VIEW_STATE_CONTENT;
        if (mContentView == null) return this;
        showByType();
        return this;
    }

    /**
     * 是否包含view 并隐藏 所有的view
     *
     * @param view view
     * @return boolean
     */
    private boolean contain(View view) {
        int childCount = getChildCount();
        if (childCount <= 0) return false;
        for (int i = 0; i < childCount; i++) {
            View v = getChildAt(i);
            //如果存在 则返回true
            if (v == view) return true;
        }
        return false;
    }

    /**
     * 根据类型显示view
     */
    private void showByType() {
        if (mEmptyView != null)
            mEmptyView.setVisibility(mViewState == VIEW_STATE_EMPTY ? View.VISIBLE : View.GONE);
        if (mErrorView != null)
            mErrorView.setVisibility(mViewState == VIEW_STATE_ERROR ? View.VISIBLE : View.GONE);
        if (mContentView != null)
            mContentView.setVisibility(mViewState == VIEW_STATE_CONTENT ? View.VISIBLE : View.GONE);
        if (mLoadingView != null)
            mLoadingView.setVisibility(mViewState == VIEW_STATE_LOADING ? View.VISIBLE : View.GONE);
    }
}
