# MultipleStatusLayout

[ ![Download](https://api.bintray.com/packages/wulijie/maven/MultipleStatusLayout/images/download.svg) ](https://bintray.com/wulijie/maven/MultipleStatusLayout/_latestVersion)

`MultipleStatusLayout` —多状态布局-处理页面多种状态的切换

# 导入

```sh
compile 'com.warpdrive.multiplestatuslayout:library:{latestVersion}'
```
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
![image1](https://raw.githubusercontent.com/wulijie/MultipleStatusLayout/master/Screenshots/Screenshot_1.png)
![image1](https://raw.githubusercontent.com/wulijie/MultipleStatusLayout/master/Screenshots/Screenshot_2.png)
![image1](https://raw.githubusercontent.com/wulijie/MultipleStatusLayout/master/Screenshots/Screenshot_3.png)
![image1](https://raw.githubusercontent.com/wulijie/MultipleStatusLayout/master/Screenshots/Screenshot_4.png)



# License

    Copyright 2016 wuliji    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.







