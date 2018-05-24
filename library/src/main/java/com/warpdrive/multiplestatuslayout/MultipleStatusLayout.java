package com.warpdrive.multiplestatuslayout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by wulijie on 2018/1/31.
 * 多状态布局容器-用于管理多状态页面
 */
public class MultipleStatusLayout {

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
    private int mViewState = VIEW_STATE_UNKNOWN;

    private View mContentView;
    private View mLoadingView;
    private View mErrorView;
    private View mEmptyView;
    private ViewGroup container;

    private MultipleStatusLayout(Builder builder) {
        this.mContentView = builder.mContentView;
        this.mLoadingView = builder.mLoadingView;
        this.mErrorView = builder.mErrorView;
        this.mEmptyView = builder.mEmptyView;
        this.container = builder.container;
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
        if (!contain(mLoadingView))
            container.addView(mLoadingView, mLoadingView.getLayoutParams());
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
        if (!contain(mErrorView)) container.addView(mErrorView, mErrorView.getLayoutParams());
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
        if (!contain(mEmptyView)) container.addView(mEmptyView, mEmptyView.getLayoutParams());
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
        int childCount = container.getChildCount();
        if (childCount <= 0) return false;
        for (int i = 0; i < childCount; i++) {
            View v = container.getChildAt(i);
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

    public static class Builder {
        private LayoutInflater mInflater;
        //实际的页面内容
        public View mContentView;
        //加载页面
        public View mLoadingView;
        //错误页面
        public View mErrorView;
        //空白页面
        public View mEmptyView;
        //上下文
        private Context context;
        //父容器
        public ViewGroup container;

        /**
         * 构造
         *
         * @param context 上下文- activity
         * @return Builder
         */
        public Builder(Context context) {
            this.context = context;
            container = new FrameLayout(context);
            mInflater = LayoutInflater.from(context);
        }

        /**
         * 指定loading布局
         *
         * @param loadingView loadingView
         * @return Builder
         */
        public Builder setLoadingView(View loadingView) {
            this.mLoadingView = loadingView;
            return this;
        }

        /**
         * 指定loading布局
         *
         * @param loadingViewResId loadingViewResId
         * @return Builder
         */
        public Builder setLoadingView(int loadingViewResId) {
            setLoadingView(mInflater.inflate(loadingViewResId, container, false));
            return this;
        }

        /**
         * 指定空白布局
         *
         * @param emptyView emptyView
         * @return Builder
         */
        public Builder setEmptyView(View emptyView) {
            this.mEmptyView = emptyView;
            return this;
        }

        /**
         * 指定空白布局
         *
         * @param emptyViewResId emptyViewResId
         * @return Builder
         */
        public Builder setEmptyView(int emptyViewResId) {
            setEmptyView(mInflater.inflate(emptyViewResId, container, false));
            return this;
        }

        /**
         * 指定错误布局
         *
         * @param errorView errorView
         * @return Builder
         */
        public Builder setErrorView(View errorView) {
            this.mErrorView = errorView;
            return this;
        }

        /**
         * 指定错误布局
         *
         * @param errorViewResId errorViewResId
         * @return Builder
         */
        public Builder setErrorView(int errorViewResId) {
            setErrorView(mInflater.inflate(errorViewResId, container, false));
            return this;
        }

        /**
         * 指定要包裹的正文布局
         *
         * @param contentView
         * @return Builder
         */
        public Builder include(View contentView) {
            this.mContentView = contentView;
            if (null == mContentView)
                throw new NullPointerException("include contentView error : contentView == null");
            ViewGroup contentParent = (ViewGroup) mContentView.getParent();
            if (contentParent != null) {
                contentParent.removeView(mContentView);
                container.addView(mContentView, mContentView.getLayoutParams());//添加
                contentParent.addView(container, contentParent.getLayoutParams());
            }
            return this;
        }

        /**
         * 指定要包裹的正文布局id
         *
         * @param contentId contentId
         * @return Builder
         */
        public Builder include(int contentId) {
            if (context instanceof Activity) include(((Activity) context).findViewById(contentId));
            return this;
        }

        public MultipleStatusLayout build() {
            return new MultipleStatusLayout(this);
        }
    }

}
