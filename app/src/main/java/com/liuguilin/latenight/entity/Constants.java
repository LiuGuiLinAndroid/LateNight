package com.liuguilin.latenight.entity;

/*
 *  项目名：  lateNight
 *  包名：    com.liuguilin.latenight.entity
 *  文件名:   Constants
 *  创建者:   LGL
 *  创建时间:  2016/10/18 16:28
 *  描述：    常量&接口&方法&字段
 */

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuguilin.latenight.util.SharePreUtils;
import com.sdsmdg.tastytoast.TastyToast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

/*
 * 接口
 * Handler
 * ONE
 * KEY
 * SHARE
 * 设置下划线
 * 判断网络是否可用
 * 判断内存卡是否存在
 * 设置某个音量
 * 设置静音
 * 取消静音
 * 添加快捷方式
 * 删除快捷方式
 * share保存图片
 * share读取图片
 * 判断服务是否运行
 * 获取版本号
 */

public class Constants {


    //搜索API
    //category 后面可接受参数  | Android | iOS | 休息视频 | 福利 | 前端 | App
    //count 最大 50
    public static final String GANK_SEARCH = "http://gank.io/api/search/query/listview/category/Android/count/10/page/1 ";
    //每日更新API
    public static final String GANK_UPDATE_DAILY = "http://gank.io/api/data/Android/10/1";
    //获取某几日干货网站数据API
    public static final String GANK_FAW_DAY = "http://gank.io/api/history/content/2/1";
    //获取特定日期网站数据API
    public static final String GANK_SPECIFIC_DAY = "http://gank.io/api/history/content/day/2016/05/11";
    //获取发过干货日期API
    //GET
    public static final String GANK_SEND_DATA_LSIT = "http://gank.io/api/day/history";
    //支持提交干货到审核区API
    //POST
    public static final String GANK_POST_DATA = "https://gank.io/api/add2gank";
    //阅读页轮播图
    public static final String ONE_READING_IMG = "http://v3.wufazhuce.com:8000/api/reading/carousel";
    //首页
    public static final String ONE_FIORST_BOOK = "http://v3.wufazhuce.com:8000/api/hp/more/0";
    //电影接口
    public static final String ONE_MOIVE = "http://v3.wufazhuce.com:8000/api/movie/list/0";
    //电影详情
    public static final String ONE_MOIVE_MORE = "http://v3.wufazhuce.com:8000/api/movie/detail/";
    //电影故事
    public static final String ONE_MOIVE_STORY = "http://v3.wufazhuce.com:8000/api/movie/";
    public static final String ONE_MOIVE_STORY_RE = "/story/1/0";

    //笑话接口
    public static final String JOKE_TEXT_URL = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_text?page=";
    //图片笑话接口
    public static final String JOKE_PIC_URL = "http://apis.baidu.com/showapi_open_bus/showapi_joke/joke_pic?page=";


    //音乐列表
    public static final String ONE_MUSIC_LIST = "http://v3.wufazhuce.com:8000/api/music/idlist/0";
    //歌曲详情
    public static final String ONE_MUSIC_MORE = "http://v3.wufazhuce.com:8000/api/music/detail/";
    //相似歌曲
    public static final String ONE_MUSIC_MORE_SE = "http://v3.wufazhuce.com:8000/api/related/music/";
    //歌曲评论
    public static final String ONE_MUSIC_SPECK_LIST = "http://v3.wufazhuce.com:8000/api/comment/praiseandtime/music/468/";


    //版本更新地址
    public static final String UPDATE_APP_URL = "";

    //城市
    public static final String PROVINCE_URL = "http://www.hisihi.com/app.php?s=/school/province";
    //学校
    public static final String SCHOOL_URL = "http://www.hisihi.com/app.php?s=/school/school/provinceid/";

    //Bmob key
    public static final String BMOB_KEY = "c478860d32aa382ad179d59eec6049fc";
    //Bugly key
    public static final String BUGLY_KEY = "bd467eaf8d";
    //BaiDu key
    public static final String BAIDU_KEY = "ae937efd91e4d4e9f648978183523903";


    //延时启动
    public static final int HANDLER_WHAT_IS_FIRST = 10001;
    //倒计时
    public static final int HANDLER_WHAT_TIME_DOWN = 10002;
    //延时加载天气
    public static final int HANDLER_WHAT_INIT_WEATHER = 10003;

    //选择头像
    public static final int IMAGE_REQUEST_CODE = 10004;
    public static final int CAMERA_REQUEST_CODE = 10005;
    public static final int RESULT_REQUEST_CODE = 10006;

    //加载音乐列表
    public static final int HANDLER_LOFING_MUSIC_LIST = 10007;


    //第一次运行
    public static final String SHARE_IS_FIRST = "isFirst";
    //第一次登录
    public static final String SHARE_IS_FIRST_LOGIN = "isFirstLogin";
    //自动登录
    public static final String SHARE_AUTO_LOGIN = "autoLogin";
    //记住密码
    public static final String SHARE_KEEP_PASSWORD = "keep_password";
    //用户名
    public static final String SHARE_USER_NAME = "share_name";
    //密码
    public static final String SHARE_USER_PASSWORD = "share_password";
    //删除快捷方式的action
    public static final String ACTION_REMOVE_SHORTCUT = "com.android.launcher.action.UNINSTALL_SHORTCUT";
    //添加快捷方式的action
    public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";

    //用户信息
    public static final String SHARE_USER_AGE = "user_age";
    public static final String SHARE_USER_BIRTHDAY = "user_birthday";
    public static final String SHARE_USER_CONSTELLATION = "user_constellation";
    public static final String SHARE_USER_DESC = "user_desc";
    public static final String SHARE_USER_HEIGHT = "user_height";
    public static final String SHARE_USER_OCCUPATION = "user_occupation";
    public static final String SHARE_USER_PHOTO = "user_photo";
    public static final String SHARE_USER_SCHOOL = "user_school";
    public static final String SHARE_USER_SEX = "user_sex";
    public static final String SHARE_USER_WEIGHT = "user_weight";

    //密码正则
    public static final String PASSWORD_KEY = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

    //创建快捷方式开关
    public static final boolean SWITCH_SHORECUT = false;


    public static String[] videoUrls = {
            "http://video.jiecao.fm/8/17/bGQS3BQQWUYrlzP1K4Tg4Q__.mp4",
            "http://video.jiecao.fm/8/17/%E6%8A%AB%E8%90%A8.mp4",
            "http://video.jiecao.fm/8/18/%E5%A4%A7%E5%AD%A6.mp4",
            "http://video.jiecao.fm/8/16/%E8%B7%B3%E8%88%9E.mp4",
            "http://video.jiecao.fm/8/16/%E9%B8%AD%E5%AD%90.mp4",
            "http://video.jiecao.fm/8/16/%E9%A9%BC%E8%83%8C.mp4",
            "http://video.jiecao.fm/8/16/%E4%BF%AF%E5%8D%A7%E6%92%91.mp4",
            "http://video.jiecao.fm/5/1/%E8%87%AA%E5%8F%96%E5%85%B6%E8%BE%B1.mp4",
            "http://gslb.miaopai.com/stream/ed5HCfnhovu3tyIQAiv60Q__.mp4",
            "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"};

    public static String[] videoThumbs = {
            "http://img4.jiecaojingxuan.com/2016/8/17/bd7ffc84-8407-4037-a078-7d922ce0fb0f.jpg",
            "http://img4.jiecaojingxuan.com/2016/8/17/f2dbd12e-b1cb-4daf-aff1-8c6be2f64d1a.jpg",
            "http://img4.jiecaojingxuan.com/2016/8/18/ccd86ca1-66c7-4331-9450-a3b7f765424a.png",
            "http://img4.jiecaojingxuan.com/2016/8/16/2adde364-9be1-4864-b4b9-0b0bcc81ef2e.jpg",
            "http://img4.jiecaojingxuan.com/2016/8/16/2a877211-4b68-4e3a-87be-6d2730faef27.png",
            "http://img4.jiecaojingxuan.com/2016/8/16/aaeb5da9-ac50-4712-a28d-863fe40f1fc6.png",
            "http://img4.jiecaojingxuan.com/2016/8/16/e565f9cc-eedc-45f0-99f8-5b0fa3aed567.jpg",
            "http://img4.jiecaojingxuan.com/2016/5/1/3430ec64-e6a7-4d8e-b044-9d408e075b7c.jpg",
            "http://img4.jiecaojingxuan.com/2016/3/14/2204a578-609b-440e-8af7-a0ee17ff3aee.jpg",
            "http://cos.myqcloud.com/1000264/qcloud_video_attachment/842646334/vod_cover/cover1458036374.jpg"};

    public static String[] videoTitles = {
            "嫂子出来",
            "嫂子溢出",
            "嫂子我姓王",
            "嫂子趴好了",
            "嫂子很渴",
            "嫂子这样不好",
            "嫂子别笑",
            "嫂子坐火车",
            "嫂子打游戏",
            "嫂子稳当的"};


    //设置下划线
    public static void setHtml(TextView textView) {
        //设置下划线
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        //抗锯齿
        textView.getPaint().setAntiAlias(true);
    }

    //判断网络是否有用
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    //判断内存卡是否存在
    public static boolean isSdCardExist() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    private static int mStreamVolume = 0;

    // 设置到某个音量值--vain
    public static int setVolume(Context context, int volumeValue) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        am.setStreamVolume(AudioManager.STREAM_MUSIC, volumeValue, 0);
        // 返回当前媒体音量
        return am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    // 音量加减----vain
    public static int RaiseOrLowerVolume(Context context, boolean isAdd, int volumeValue) {
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (isAdd) {
            mStreamVolume += volumeValue;
            if (mStreamVolume >= am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) {
                mStreamVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            }
            am.setStreamVolume(AudioManager.STREAM_MUSIC, mStreamVolume, 0);
        } else {
            mStreamVolume -= volumeValue;
            if (mStreamVolume <= 0) {
                mStreamVolume = 0;
            }
            am.setStreamVolume(AudioManager.STREAM_MUSIC, mStreamVolume, 0);
        }
        // 返回当前媒体音量
        return am.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    //设置静音
    public static void setMute(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) != 0) {
            mStreamVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        }
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
    }

    //取消静音
    public static void setUnMute(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        // 隐藏音乐进度条
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (mStreamVolume != 0) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mStreamVolume, 0);
        } else {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        }
    }

    //增加快捷方式
    public static void addShortcut(Context context, Intent actionIntent, String name, boolean allowRepeat, int iconBitmap) {
        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);
        // 是否允许重复创建
        addShortcutIntent.putExtra("duplicate", allowRepeat);
        // 快捷方式的标题
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        // 快捷方式的图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, iconBitmap);
        // 快捷方式的动作
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        context.sendBroadcast(addShortcutIntent);
        TastyToast.makeText(context, "快捷方式创建成功", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
    }

    //删除快捷方式
    public static void removeShortcut(Context context, Intent actionIntent, String name) {
        Intent intent = new Intent(ACTION_REMOVE_SHORTCUT);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        intent.putExtra("duplicate", false);
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        context.sendBroadcast(intent);
    }

    //share保存图片
    public static void putImgToShare(Context mContext, ImageView imageView) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        SharePreUtils.putString(mContext, Constants.SHARE_USER_PHOTO, imageString);
    }

    //读取share保存图片
    public static void getImgToShare(Context mContext, ImageView imageView) {
        String imageString = SharePreUtils.getString(mContext, "image_title", "");
        if (!imageString.equals("")) {
            byte[] byteArray = Base64.decode(imageString, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            Bitmap bitmap = BitmapFactory.decodeStream(byteArrayInputStream);
            imageView.setImageBitmap(bitmap);
        }
    }

    //判断服务是否运行
    public static boolean isServiceRunning(Context mContext, String serviceName) {
        // 活动管理器
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        // 获取运行的服务,参数表示最多返回的数量
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : runningServices) {
            String className = runningServiceInfo.service.getClassName();
            if (className.equals(serviceName)) {
                // 判断服务是否运行
                return true;
            }
        }
        return false;
    }

    //获取版本号
    public static String getVersion(Context mContext) {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            String version = info.versionName;
            return "版本:" + version;
        } catch (Exception e) {
            return "无法获取版本号";
        }
    }
}
