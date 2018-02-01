# MultipleStatusLayout



`MultipleStatusLayout` —多状态布局-处理页面多种状态的切换

# 导入

```sh
compile 'com.warpdrive.multiplestatuslayout:library:{latestVersion}'
```
[ ![Download](https://api.bintray.com/packages/wulijie/maven/MultipleStatusLayout/images/download.svg) ](https://bintray.com/wulijie/maven/MultipleStatusLayout/_latestVersion)[![API](https://img.shields.io/badge/API-8%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=8) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) [![GitHub stars](https://img.shields.io/github/stars/wulijie/MultipleStatusLayout.svg?style=plastic&label=Star) ](https://github.com/wulijie/MultipleStatusLayout)
# 使用

```java
new MultipleStatusLayout(this).include(R.id.refreshLayout)
                .setEmptyView(R.layout.empty)
                .setLoadingView(R.layout.loading)
                .setErrorView(R.layout.error)
                .showLoading();
//show
multipleStatusLayout.showEmpty();//显示空白页面
multipleStatusLayout.showContent();//显示正文
multipleStatusLayout.showError();//显示错误页面
multipleStatusLayout.showLoading();//显示loading页面

```

# 效果图

[点击下载 Demo.apk](https://raw.githubusercontent.com/wulijie/MultipleStatusLayout/master/app-debug.apk)

<img src="https://raw.githubusercontent.com/wulijie/MultipleStatusLayout/master/demo.gif" width="300">









