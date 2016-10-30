# 深夜

**《深夜》，陪你度过每一个无聊的虚度时光**

>免责声明：本软件所使用到的图片资源以及接口都来源于网络,有任何侵权行为请告知删除

##一.截图
>截图分每一个阶段截图，也可以分版本截图，但是都不是最终的版本截图，因为项目现在还在开发...

- Version1.0.0

![这里写图片描述](http://img.blog.csdn.net/20161030100318539)

- Version1.0.1

![这里写图片描述](http://img.blog.csdn.net/20161030140834393)


##二.使用到的图标

- Material icons:https://design.google.com/icons/
- iconfont:http://www.iconfont.cn/

##三.使用到的接口

- 1.Gank:http://www.gank.io/api
- 2.知乎日报:https://github.com/izzyleung/ZhihuDailyPurify/wiki/知乎日报-API-分析
- 3.One一个:暂未公布

##四.使用到的框架

- 1.RxVolley:https://github.com/kymjs/RxVolley
    
- 2.PhotoView:https://github.com/chrisbanes/PhotoView/
    
- 3.Shimmer:https://github.com/RomainPiel/Shimmer-android
    
- 4.Bmob:http://www.bmob.cn/

- 5.Bugly:https://bugly.qq.com/
   
- 6.TastyToast:https://github.com/yadav-rahul/TastyToast
    
- 7.Glide:https://github.com/bumptech/glide
    
- 8.CircleViewPager:https://github.com/CodingForAndroid/CircleViewPager

- 9.CircleImageView:https://github.com/hdodenhof/CircleImageView

- 10.ScaleScrollView:https://github.com/LichFaker/ScaleView

- 11.InfiniteCycleViewPager:https://github.com/DevLight-Mobile-Agency/InfiniteCycleViewPager

- 12.uCrop:https://github.com/Yalantis/uCrop
    
##五.更新日志

###Version1.0.1

- 1.实现StandardAdapter
- 2.更名StandardData数据类
- 3.创建AboutActivity关于
- 4.新增CoordinatorLayout
- 5.新增AppBarLayout
- 6.新增CollapsingToolbarLayout
- 7.新增Toolbar
- 8.实现Android界面
- 9.实现IOS/前端
- 10.创建ReadingActivity
- 11.实现轮播
- 12.完善资料选择
- 13.完成星座页面
- 14.创建CoverFlow 3D画廊
- 15.创建ZoomScrollView滑动下拉放大
- 16.创建AppActivity
- 17.新增快捷方式

  
###Version1.0.0

- 1.创建项目上传到Github
- 2.创建一系列package
- 3.创建SplashActivity(闪屏页)
- 4.创建BaseApplication
- 5.创建BaseActivity
- 6.实现BaseActivity逻辑
- 7.自定义全屏Style
- 8.自定义CustomDialog
- 9.创建BootCompletedReceiver开机监听
- 10.创建ConnectionChangeReceiver网络监听
- 11.实现网络状态监听的逻辑
- 12.自定义Log类L
- 13.自定义SharedPreferences封装
- 14.新增RxVolley
- 15.新增PhotoView
- 16.新增Shimmer
- 17.创建GuideActivity引导页
- 18.创建LoginActivity登录页
- 19.创建RegisteredActivity注册页
- 20.创建Constants常量类
- 21.新增Bmob
- 22.新增Bugly
- 23.创建TermsActivity隐私条约
- 24.创建ForgetActivity忘记密码
- 25.调整登录UI
- 26.新增icon
- 27.新增密码正则表达式
- 28.创建SettingActivity设置页
- 29.新增MD5加密类
- 30.新增正则表达式
- 31.新增TastyToast
- 32.创建SetPassWordActivity设置密码页
- 33.实现短信验证验证功能
- 33.实现手机号注册功能
- 34.新增Glide
- 35.新增CircleViewPager
- 36.封装GlideUtils
- 37.创建PhoneFormatCheckUtils手机号码正则类
- 38.注册功能完成
- 39.完成設置密碼功能
- 40.更改注册逻辑
- 41.新增CircleImageView
- 42.登录功能完成
- 43.创建SelectSexActivity选择性别
- 44.创建SelectAgeActivity选择年龄
- 45.新增ScaleScrollView
- 46.创建SelectHeightActivity选择身高
- 47.创建SelectWeightActivity选择体重
- 48.创建SelectConstellationActivity选择星座
- 49.新增Ticker
- 50.新增InfiniteCycleViewPager
- 51.新增天气接口
- 52.主页完成
- 53.完善开源库，新增主页点击接口setOnPagerItemClickListener
- 54.完善选择性别
- 55.增加尺子测量监听
- 56.完善主页功能
- 57.创建WebViewActivity网页界面
- 58.新增uCrop
- 59.创建AndroidActivity
- 60.创建IOSActivity
- 61.创建WebActivity前端
- 62.创建MusicActivity音乐
- 63.创建GirlActivity妹子
- 64.创建MusicService
- 65.新增几个封装方法
- 66.新增记住密码
- 67.新增自动登录
- 68.新增Multidex
- 69.解决Jar冲突的问题
- 70.新增百度地图Jar
- 71.实现自动定位
- 72.创建ChargingService充电服务
- 73.创建ShortcutsProvider小组件
- 74.去除Ticker
- 75.自定义RiseNumberTextView自动滚动
- 76.完善天气逻辑
- 77.新增Android逻辑
- 78.创建StandardAdapter标准的Adapter
- 79.创建AndroidData实体类
- 80.版本更新至1.0.1

## 六.常规性的bug

###1.Jar冲突

**OKHttp**
```java
Error:Execution failed for task ':app:transformClassesWithJarMergingForDebug'.
com.android.build.api.transform.TransformException: java.util.zip.ZipException: duplicate entry: okhttp3/Address.class
```

**RxAndroid**
```java
Error:Execution failed for task ':app:transformClassesWithJarMergingForDebug'.
> com.android.build.api.transform.TransformException: java.util.zip.ZipException: duplicate entry: rx/android/MainThreadSubscription$1.class
```
**OKio**
```java
Error:Execution failed for task ':app:transformClassesWithJarMergingForDebug'.
> com.android.build.api.transform.TransformException: java.util.zip.ZipException: duplicate entry: com/okio/AsyncTimeOut.class
```

>Jar包重复添加了，可以尝试下使用这篇Blog的方法: [安卓开发之引入第三方库导致jar包冲突解决办法](http://blog.csdn.net/cx1229/article/details/52786168)
>我的解决办法:

```java
 //RxVolley
    compile ('com.kymjs.rxvolley:rxvolley:1.1.0'){
       exclude group: 'com.squareup.okhttp3',module: 'okhttp'
       exclude group: 'com.squareup.okio'
       exclude group: 'io.reactivex'
    }
 //下载模块
    compile ('com.kymjs.rxvolley:okhttp:1.1.0'){
        exclude group: 'com.squareup.okhttp3'
        exclude group: 'com.squareup.okio'
    }
 //Bmob
    compile ('cn.bmob.android:bmob-sdk:3.5.0'){
        exclude group: 'com.squareup.okhttp3',module: 'okhttp'
        exclude group: 'io.reactivex:rxandroid'
        exclude group: 'io.reactivex:rxjava'
        exclude group: 'com.squareup.okio'
    }
//uCrop  竟然没有想到一个裁剪库也有okhttp，罪过，罪过...
    compile ('com.yalantis:ucrop:2.2.0-native'){
        exclude group: 'com.squareup.okhttp3',module: 'okhttp'
        exclude group: 'com.squareup.okio'
    }
```



##七.联系方式

####[点击关注我的微博](http://weibo.com/Glorystys)
####QQ邮箱：748778890@qq.com
####Google邮箱：liuguilin74@gmail.com
###￥博客地址：http://blog.csdn.net/qq_26787115

####**我的公众号，期待你的关注**

![weixin](http://img.blog.csdn.net/20160108203741937)


##八.License
  
>开源协议[Apache-2.0](https://opensource.org/licenses/apache2.0.php)