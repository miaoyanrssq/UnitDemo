package com.zjrb.bizman.utils_component.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.zjrb.bizman.utils_component.log.LogUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


public class Utils {
    private static final String LOG_TAG = Utils.class.getSimpleName();

    public static boolean checkLoginPwd(String paramString) {
        return Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$").matcher(paramString).matches();
    }

    public static boolean checkPhone(String paramString) {
        return Pattern.compile("^(1[34578])\\d{9}$").matcher(paramString).matches() && paramString.length() == 11;
    }
    
    /**
     * 检查密码是否为6-25位的数字，下划线，字母的组合
     * @param paramString
     * @return
     */
    public static boolean checkPassword(String paramString) {
        return Pattern.compile("[a-zA-Z0-9_]{6,25}").matcher(paramString).matches();
    }
    public static boolean checkIDCard(String paramString) {
        String matchString15 = "^[1-9]\\d{7}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}$";
        String matchString18 = "^[1-9]\\d{5}[1-9]\\d{3}((0[1-9])||(1[0-2]))((0[1-9])||(1\\d)||(2\\d)||(3[0-1]))\\d{3}([0-9]||X)$";
        return Pattern.compile(matchString15).matcher(paramString).matches() || Pattern.compile(matchString18).matcher(paramString).matches();
    }

    public static boolean checkMoney(String paramString) {
        return Pattern.compile("-?[0-9]*$?").matcher(paramString).matches();
    }

    public static boolean checkNickname(String paramString) {
        return Pattern.compile("^[a-z0-9A-Z\\u4e00-\\u9fa5\\u002d\\u005f]+$").matcher(paramString).matches();
    }

    public static boolean checkCode(String paramString) {
        return Pattern.compile("^\\d{6}$").matcher(paramString).matches();
    }

    public static boolean isEmpty(String paramString) {
        return !(paramString != null && !paramString.equals("") && !paramString.equals("null"));
    }

    public static void addToList(List before, List after) {
        if (before == null || after == null) {
            return;
        }

        if (before.size() == 0) {
            before.addAll(after);
        } else {
            for (int i = 0; i < after.size(); i++) {
                if (!before.contains(after.get(i))) {
                    before.add(after.get(i));
                }
            }
        }
    }

    public static float StringToFloat(String value) {
        float result = 0;
        try {
            result = Float.valueOf(value);
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    public static double StringToDouble(String value) {
        double result = 0.0;
        try {
            result = Double.valueOf(value);
        } catch (Exception e) {
            result = 0;
        }

        return result;
    }

    public static void setButtonEnable(View... views) {
        for (View view : views) {
            view.setEnabled(true);
            view.setClickable(true);
        }
    }

    public static void setButtonDisable(View... views) {
        for (View view : views) {
            view.setEnabled(false);
            view.setClickable(false);
        }
    }

    public static String moneyFormat(long money, String unit) {
        //从"分"转化为"元"
        money = money / 100;
        if (money >= 10000) {
            //因为有些值不是整万,例如49999,直接除以10000后,值会失真
            String tailStr = "";
            if (money % 10000 != 0) {
                tailStr = "." + String.valueOf((int) money % 10000);
            }
            return String.valueOf(money / 10000) + tailStr + unit;
        } else {
            return String.valueOf(money);
        }
    }

    public static String moneyFormat(double money, String unit) {
        if (money > 1000000) {
            return String.valueOf(money / 1000000) + unit;
        } else {
            return String.valueOf(money / 100);
        }
    }

    public static String moneyFormat(double money) {
        DecimalFormat df = new DecimalFormat("#########0");
        return String.valueOf(df.format(money));
    }

    public static String moneyFormatTwo(double money) {
        DecimalFormat df = new DecimalFormat("#########0.00");
        return String.valueOf(df.format(money));
    }

    public static String formatMoney(String str, String unit) {
        try {
            DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
            String result;
            Double money = Double.parseDouble(str);
            if (money > 1000000) {
                result = String.valueOf(df.format(money / 100 / 10000)) + unit;
            } else {
                result = String.valueOf(df.format(money / 100));
            }

            return result;
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return new String();
        }
    }

    public static String formatMoney(String str) {
        try {
            DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
            String result;
            Double money = Double.parseDouble(str);
            result = String.valueOf(df.format(money / 100));

            return result;
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return new String();
        }
    }

    public static String formatMoneyTwo(String str) {
        try {
            DecimalFormat df = new DecimalFormat("#,###,###,##0.00");
            String result;
            Double money = Double.parseDouble(str);
            result = String.valueOf(df.format(money));

            return result;
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return new String();
        }
    }

    public static void numFormat(TextView textview, String num, int positiveColor, int nagativeColor, int defaultcolor) {
        try {
            Double yieldYear = Double.parseDouble(num);
            if (yieldYear > 0) {
                textview.setText("+" + num + "%");
                textview.setTextColor(positiveColor);
            } else if (yieldYear < 0) {
                textview.setText(num + "%");
                textview.setTextColor(nagativeColor);
            } else {
                textview.setText(num + "%");
                textview.setTextColor(defaultcolor);
            }
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            if (num == null || num.equals("-") || num.equals("")) {
                textview.setText("-.--");
                textview.setTextColor(defaultcolor);
            }
        }
    }

    public static String timeFormatChange(SimpleDateFormat informat, SimpleDateFormat outFormat, String inValue) {
        try {
            Date date = informat.parse(inValue);
            return outFormat.format(date);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return inValue;
        }
    }

    public static String timeFormatChange(SimpleDateFormat informat, String inValue) {
        try {
            Date date = informat.parse(inValue);
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM-dd");
            SimpleDateFormat weekFormat = new SimpleDateFormat("EEEE");
            return monthFormat.format(date) + "    " + weekFormat.format(date);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return inValue;
        }
    }

    public static String timeFormat(String inValue) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Long time = Long.parseLong(inValue);
            Date date = new Date(time);
            return format.format(date);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return inValue;
        }
    }
    
    /**
     * 时间格式转换
     * @param format
     * @param time
     * @return
     */
    public static String timeFormat(SimpleDateFormat format, long time) {
        try {
            Date date = new Date(time);
            return format.format(date);
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return "";
        }
    }
    public static void layoutBanner(Context activity, ImageView imageView, String width, String height) {
        WindowManager wm = (WindowManager) activity.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

        int displayWidth = wm.getDefaultDisplay().getWidth();
        int bannerWidth = displayWidth;
        int bannerHeight;
        try {
            int widthX = Integer.parseInt(width);
            int heightY = Integer.parseInt(height);
            bannerHeight = displayWidth * heightY / widthX;
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            bannerHeight = 0;
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.height = bannerHeight;//设置图片的高度
        layoutParams.width = bannerWidth; //设置图片的宽度
        imageView.setLayoutParams(layoutParams);
    }

    public static String getTopActivity(Activity context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        if (runningTaskInfos != null)
            return (runningTaskInfos.get(0).topActivity).getClassName();
        else
            return null;
    }

    public static boolean isOnTop(Activity context, String activityName) {
        return getTopActivity(context) != null && getTopActivity(context).equals(activityName);
    }

    public static void copy(Context context, String url) {
        // 从API11开始android推荐使用android.content.ClipboardManager
        // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(url);
    }

    public static void startAnimation(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.01f, 1.0f);
        //动画持续时间
        alphaAnimation.setDuration(600);
        //使用View的startAnimation开始执行动画
        view.startAnimation(alphaAnimation);
        //给这个动画绑定动画监听器
        //监听动画结束或重复的动画事件
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }
        });
    }

    public static void endAlphAnimation(final View view) {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.01f);
        animation.setDuration(250);
        animationSet.addAnimation(animation);
        animationSet.setInterpolator(new AccelerateInterpolator());
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }
        });
        view.startAnimation(animationSet);
    }

    public static void endAnimation(final View childview, final View newView) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -1f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
        animation.setDuration(250);
        animation.setFillAfter(true);
        AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.01f);
        animation1.setDuration(250);
        animationSet.addAnimation(animation);
        animationSet.addAnimation(animation1);
        animationSet.setInterpolator(new AccelerateInterpolator());
        childview.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                childview.setVisibility(View.GONE);
                startAlphaAnimation(newView);
            }
        });
    }

    public static void startAlphaAnimation(final View view) {
        view.setVisibility(View.VISIBLE);
        AnimationSet animationSet2 = new AnimationSet(true);
        AlphaAnimation animation2 = new AlphaAnimation(0.01f, 1.0f);
        animation2.setDuration(250);
        animation2.setStartOffset(100);
        animationSet2.addAnimation(animation2);
        animationSet2.setInterpolator(new AccelerateInterpolator());
        animationSet2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.VISIBLE);
            }
        });
        view.startAnimation(animationSet2);
    }

    public static String md5Encode(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
            return "";
        }

        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static boolean copyFiles(Context context, String inPath, String outPath) {
        LogUtils.i(LOG_TAG, "copyFiles() inPath:" + inPath + ", outPath:" + outPath);
        String[] fileNames = null;
        try {// 获得Assets一共有几多文件
            fileNames = context.getAssets().list(inPath);
        } catch (IOException e1) {
            e1.printStackTrace();
            return false;
        }
        if (fileNames.length > 0) {//如果是目录
            File fileOutDir = new File(outPath);
            if (fileOutDir.isFile()) {
                boolean ret = fileOutDir.delete();
                if (!ret) {
                    LogUtils.e(LOG_TAG, "delete() FAIL:" + fileOutDir.getAbsolutePath());
                }
            }
            if (!fileOutDir.exists()) { // 如果文件路径不存在
                if (!fileOutDir.mkdirs()) { // 创建文件夹
                    LogUtils.e(LOG_TAG, "mkdirs() FAIL:" + fileOutDir.getAbsolutePath());
                    return false;
                }
            }
            for (String fileName : fileNames) { //递归调用复制文件夹
                String inDir = inPath;
                String outDir = outPath + File.separator;
                if (!inPath.equals("")) { //空目录特殊处理下
                    inDir = inDir + File.separator;
                }
                copyFiles(context, inDir + fileName, outDir + fileName);
            }

            return true;
        } else {//如果是文件
            try {
                File fileOut = new File(outPath);
                if (fileOut.exists()) {
                    boolean ret = fileOut.delete();
                    if (!ret) {
                        Log.e(LOG_TAG, "delete() FAIL:" + fileOut.getAbsolutePath());
                    }
                }
                FileOutputStream fos = new FileOutputStream(fileOut);
                InputStream is = context.getAssets().open(inPath);
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                while ((byteCount = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();//刷新缓冲区
                is.close();
                fos.close();

                return true;
            } catch (Exception e) {
                LogUtils.e(LOG_TAG, e.getMessage());
                return false;
            }
        }
    }

    public static boolean hasInternet(Context context) {
        ConnectivityManager localConnectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean ret = false;
        if (localConnectivityManager == null) {
            return ret;
        }
        NetworkInfo[] arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
        if (arrayOfNetworkInfo != null) ;
        for (int j = 0; j < arrayOfNetworkInfo.length; j++) {
            if (arrayOfNetworkInfo[j].getState() == NetworkInfo.State.CONNECTED) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public static Uri getImageUri(String path) {
        return Uri.fromFile(new File(path));
    }

    public static String encodeUrl(String str) {
        if (str == null)
            return null;

        String encodeStr = new String(str);
        try {
            encodeStr = URLEncoder.encode(encodeStr, "utf-8");
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }

        return encodeStr;
    }

    public static String decodeUrl(String str) {
        String decodeStr = null;
        if (str == null)
            return decodeStr;

        try {
            decodeStr = URLDecoder.decode(str, "utf-8");
        } catch (Exception e) {
            LogUtils.e(LOG_TAG, e.getMessage());
        }

        return decodeStr;
    }

    //重置交易密码验证码已发送
    public static boolean checkApplyTradePasswordGetCode(String data) {
        return data != null && data.equals("S");
    }

    //重置交易密码审核中
    public static boolean checkApplyTradePasswording(String data) {
        return data != null && (data.equals("S") || data.equals("A"));
    }

    //时间处理方法
    public static String disposeTime(String mTime) {
        try {
            String[] mTimeList = mTime.split(" ");
            return mTimeList[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String();
    }

    //时间处理方法二
    public static String disposeTime2(String mTime) {
        String[] mTimeList = mTime.split(" ");
        String[] mMonthList = mTimeList[0].split("\\-");
        if (mMonthList.length >= 3) {
            return mMonthList[1] + "月" + mMonthList[2] + "日";
        } else {
            return mTimeList[0];
        }
    }

    //控制EditText输入字符长度
    public static int getEditTextInputLength(CharSequence s, EditText mEditText, int maxLen) {
        Editable editable = mEditText.getText();
        int count1 = s.length();
        int dindex = 0;
        int count2 = 0;
        int mEditLen;
        while (count2 <= maxLen && dindex < count1) {
            char c = s.charAt(dindex++);
            if (c < 128) {
                count2 = count2 + 1;
            } else {
                count2 = count2 + 2;
            }
        }
        mEditLen = count2;
        if (count2 > maxLen) {
            mEditLen = maxLen;
            int selEndIndex = Selection.getSelectionEnd(editable);
            String str = editable.toString();
            //截取新字符串
            String newStr = str.substring(0, dindex - 1);
            mEditText.setText(newStr);
            editable = mEditText.getText();

            //新字符串的长度
            int newLen = editable.length();
            //旧光标位置超过字符串长度
            if (selEndIndex > newLen) {
                selEndIndex = editable.length();
            }
            //设置新光标所在的位置
            Selection.setSelection(editable, selEndIndex);
        }
        return mEditLen;
    }

    //昵称截取长度
    public static String cutNickname(String mNickname) {
        if (mNickname != null) {
            String mNick = mNickname.trim();
//			if (mNick.length() > 11) {
//				return mNick.substring(0, 7) + "...";
//			} else {
            return mNick;
//			}
        } else {
            return "";
        }
    }

    /**
     * 判断微信是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 判断qq是否可用
     *
     * @param context
     * @return
     */
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断微博是否可用
     *
     * @param context
     * @return
     */
    public static boolean isWeiboAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.sina.weibo")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 截取提定长度的字符串，中文为1，非中文为0.5
     *
     * @param str 原字符串
     * @param len 需要截取的长度
     * @return 截取长度后的字符串
     */
    public static String getStrByLen(String str, int len) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String srcStr = str.trim();
        int strLen = srcStr.length();
        if (strLen <= len) {
            return str;
        }
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strLen; i++) {
            String temp = srcStr.substring(i, i + 1);
            if (temp.matches(chinese)) {
                valueLength += 1;
            } else {
                valueLength += 0.5;
            }
            if (valueLength <= len) {
                sb.append(temp);
            } else {
                break;
            }
        }
        if (valueLength > len) {
            sb.append("...");
        }
        return sb.toString();
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
    
    /**
     * 判断某个activity是否存在
     * @param context
     
     * @return
     */
    public static boolean isExistActivity(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }
    
    /**
     * 隐藏导航栏（底部虚拟按键）
     * @param decorView getWindow().getDecorView()
     */
    public static void hideNavigationBar(View decorView){
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenWidthPixels(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }
}
