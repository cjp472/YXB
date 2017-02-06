package com.cicada.yuanxiaobao.utils;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cicada.yuanxiaobao.R;
import com.cicada.yuanxiaobao.common.Constant;
import com.cicada.yuanxiaobao.common.ConstantPermission;
import com.cicada.yuanxiaobao.common.GlideRoundTransform;
import com.cicada.yuanxiaobao.common.MyApplication;
import com.cicada.yuanxiaobao.permission.PermissionsChecker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * AUTHOR: Alex
 * DATE: 12/11/2015 09:47
 */
public class AppTools {

    /**
     * 获取日期
     *
     * @param textView
     * @return
     */
    public static void obtainData(Context activity, final TextView textView) {
        final DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                textView.setText(inspectDate(year, monthOfYear, dayOfMonth));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    /**
     * 获取日期
     *
     * @param textView
     * @return
     */
    public static void obtainData(final Context activity, final TextView textView, final String pattern) {
        final DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                GregorianCalendar cal = new GregorianCalendar();
                cal.set(year, monthOfYear, dayOfMonth);
                Date date = new Date(cal.getTimeInMillis());
                textView.setText(DateUtils.dateToStr(date, pattern));
                textView.setTag(DateUtils.getDateToString_YYYY_MM_DD_EN(date));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }



    /**
     * 获取日期
     *
     * @return
     */
    public static void obtainData(Context context, final Handler handler, final int what) {
        final DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                GregorianCalendar cal = new GregorianCalendar();
                cal.set(year, monthOfYear, dayOfMonth);
                Date date = new Date(cal.getTimeInMillis());
                handler.sendMessage(handler.obtainMessage(what, date));

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    /**
     * 获取日期时间
     *
     * @param textView
     */
    public static void obtainDataAndTime(final Activity activity, final TextView textView) {
        final DatePickerDialog datePickerDialog;
        final Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            boolean flag = true;

            @Override
            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {

                if (flag) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            textView.setText(inspectDate(year, monthOfYear, dayOfMonth) + "  " + inspectTime(hourOfDay, minute));

                        }
                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
                    timePickerDialog.show();
                    flag = false;

                }
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();


    }

    /**
     * 获取时间
     *
     * @param textView
     */
    public static void obtainTime(final Activity activity, final TextView textView) {
        final Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView.setText(inspectTime(hourOfDay, minute));
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }


    /**
     * 转换日期格式
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static String inspectDate(int year, int monthOfYear, int dayOfMonth) {

        String tempMonth = String.valueOf((monthOfYear + 1));
        String tempDay = String.valueOf((dayOfMonth));

        if ((monthOfYear + 1) < 10) {
            tempMonth = "0" + String.valueOf((monthOfYear + 1));
        }
        if (dayOfMonth < 10) {
            tempDay = "0" + String.valueOf((dayOfMonth));
        }
        String dateString = year + "." + tempMonth + "." + tempDay;

        return dateString;

    }

    /**
     * 转换日期格式
     *
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     * @return
     */
    public static String inspectDateSlip(int year, int monthOfYear, int dayOfMonth) {

        String tempMonth = String.valueOf((monthOfYear + 1));
        String tempDay = String.valueOf((dayOfMonth));

        if ((monthOfYear + 1) < 10) {
            tempMonth = "0" + String.valueOf((monthOfYear + 1));
        }
        if (dayOfMonth < 10) {
            tempDay = "0" + String.valueOf((dayOfMonth));
        }
        String dateString = year + "-" + tempMonth + "-" + tempDay;

        if (dayOfMonth == 0) {
            dateString = year + "-" + tempMonth;
        }
        return dateString;

    }

    /**
     * 格式化事件
     *
     * @param hourOfDay
     * @param minute
     * @return
     */
    public static String inspectTime(int hourOfDay, int minute) {

        String tempHour = String.valueOf((hourOfDay));
        String tempMin = String.valueOf((minute));

        if ((hourOfDay) < 10) {
            tempHour = "0" + String.valueOf(hourOfDay);
        }
        if (minute < 10) {
            tempMin = "0" + String.valueOf((minute));
        }
        String timeStr = tempHour + ":" + tempMin;

        return timeStr;

    }

    /**
     * 调用系统相册
     *
     * @param activity
     */
    public static void getSystemImage(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, Constant.SELECT_PICTURE);

    }

    /**
     * 调用系统相册 Fragment
     *
     * @param fragment
     */
    public static void getSystemImage(Fragment fragment) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fragment.startActivityForResult(intent, Constant.SELECT_PICTURE);
    }

    /**
     * 调用系统相机 Fragment
     * @param fragment
     */
    public static String getSystemCamera(Fragment fragment, Activity activity) {
        if (hasCamera()) {
            if (PermissionsChecker.getPermissionsChecker().lacksPermissions(activity, ConstantPermission.PERMISSIONS_PICTURE)) {
                PermissionsChecker.getPermissionsChecker().startPermissionsActivity(activity, ConstantPermission.PERMISSIONS_PICTURE);
            } else {
                String path = getImageSavePath(activity) + "/" + System.currentTimeMillis() + ".jpg";
                File f = new File(path);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                fragment.startActivityForResult(intent, Constant.CAMERA_REQUEST_CODE);
                return path;
            }
        }
        return "";
    }

    /**
     * 调用系统相机 Activity
     *
     * @param activity
     */
    public static String getSystemCamera(Activity activity) {
        if (hasCamera()) {
            if (PermissionsChecker.getPermissionsChecker().lacksPermissions(activity, ConstantPermission.PERMISSIONS_PICTURE)) {
                PermissionsChecker.getPermissionsChecker().startPermissionsActivity(activity, ConstantPermission.PERMISSIONS_PICTURE);
            } else {
                String path = getImageSavePath(activity) + "/" + System.currentTimeMillis() + ".jpg";
                File f = new File(path);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                activity.startActivityForResult(intent, Constant.CAMERA_REQUEST_CODE);
                return path;
            }
        }
        return "";
    }


    /**
     * 方法名:获取图片文件存取路径
     * <p>
     * 功能说明：获取图片文件存取路径
     * </p>
     *
     * @return
     */
    public static String getImageSavePath(Context context) {
        if (AppTools.getSDPath().equals("")) {
            return context.getFilesDir().getPath();
        }
        File file = new File(AppTools.getSDPath() + "/YXB/image");
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            }
            return "";
        }
        return file.getPath();
    }

    /**
     * 方法名:获取文件存取路径
     * <p>
     * 功能说明：获取文件存取路径
     * </p>
     *
     * @return
     */
    public static String getFileSavePath(Context context) {
        if (AppTools.getSDPath().equals("")) {
            return context.getFilesDir().getPath();
        }
        File file = new File(AppTools.getSDPath() + "/YXB/downloads");
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            }
            return "";
        }
        return file.getPath();
    }

    /**
     * 方法名: 判断SD卡是否存在
     * <p>
     * 功能说明： 判断SD卡是否存在, 如果存在返回SD卡路径 , 如果不存在返回路径为空
     * </p>
     *
     * @return
     */
    public static String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            File sdDir = Environment.getExternalStorageDirectory();
            return sdDir.toString();
        }
        return "";
    }

    /**
     * 更具uri获取绝对路径
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getAbsolutePath(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        if (uri.toString().contains("file://")) {

            String path = uri.toString().replace("file://", "");

            return Uri.decode(path);
        }
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null,
                null, null);
        if (cursor == null) {
            return null;
        }
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    /**
     * 日期转换 .  / -
     *
     * @param date
     * @return
     */
    public static String convertDateFormat(String date) {

        String result = "";
        if (!TextUtils.isEmpty(date) && date.contains(".")) {
            result = date.replace(".", "-");
        } else if (!TextUtils.isEmpty(date) && date.contains("-")) {
            result = date.replace("-", ".");
        }

        return result;
    }


    /**
     * 忽略map中指定字段
     *
     * @param map
     * @param property
     * @return
     */
    public static Map<String, String> ignoreProperty(Map<String, String> map, String property) {
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (property.equals(key)) {
                iterator.remove();        //删除createTime字段
                map.remove(key);
            }
        }
        return map;
    }


    /**
     * 发短信
     *
     * @param phone
     */
    public static void sendSMS(Context context, String phone) {
        Uri smsToUri = Uri.parse("smsto:" + (TextUtils.isEmpty(phone) ? "" : phone));
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        if (!hasApk(intent)) {
            ToastUtil.showShort("无法发送短信");
            return;
        }
        intent.putExtra("sms_body", "");
        context.startActivity(intent);

    }

    /**
     * 打电话
     *
     * @param context
     * @param phone
     */
    public static void callPhone(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        if (!hasApk(intent)) {
            ToastUtil.showShort("无法打电话");
            return;
        }
        intent.setData(Uri.parse("tel:" + (TextUtils.isEmpty(phone) ? "" : phone)));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        context.startActivity(intent);

    }

    /**
     * 异步加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImageViewPicture(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.drawable.defaultavatar).error(R.drawable.defaultavatar).transform(new GlideRoundTransform(context)).into(imageView);
    }

    /**
     * 异步加载方块图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void setImageViewClub(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.drawable.defaultavatar).error(R.drawable.defaultavatar).into(imageView);
    }

    /**
     * 切换动画效果
     *
     * @param view
     * @param alpha
     * @param startScaleX
     * @param startScaleY
     * @param duration
     */
    public static void fadeInView(View view, float alpha, float startScaleX, float startScaleY, int duration) {
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", alpha, 1f);
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", startScaleX, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", startScaleY, 1f);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animatorAlpha).with(animatorScaleX).with(animatorScaleY);
        animatorSet.setDuration(duration);
        animatorSet.start();
    }

    /**
     * 千分位
     *
     * @param s
     * @return
     */
    public static String getNumKb(String s) {
        if (!TextUtils.isEmpty(s)) {
            NumberFormat formatter = new DecimalFormat("###,###");
            String result = formatter.format(Double.parseDouble(s));
            return result;
        }
        return s;
    }

    /**
     * 千分位 两位小数
     *
     * @param s
     * @return
     */
    public static String getNumKbDot(String s) {
        if (!TextUtils.isEmpty(s)) {
            NumberFormat formatter = new DecimalFormat("###,##0.00");
            String result = formatter.format(Double.parseDouble(s));
            return result;
        }
        return s;
    }

    public static String getNumKbDotSpecile(String s) {
        if (!TextUtils.isEmpty(s)) {
            NumberFormat formatter = new DecimalFormat("###,##0.0000");
            String result = formatter.format(Double.parseDouble(s));
            return result;
        }
        return s;
    }

    /**
     * 是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 方法名:getFileByte
     * <p>
     * 功能说明：将字节数转换成文件流
     * </p>
     *
     * @param filePath
     * @return
     */
    public static String getFileByte(String filePath) {
        int count;
        int num = 0;
        File file = new File(filePath);
        long len = file.length();
        if (file.exists()) {
            FileInputStream fis = null;
            StringBuffer sb = new StringBuffer();
            try {
                fis = new FileInputStream(file);
                byte[] buffer = new byte[(int) len];
                while ((count = fis.read(buffer)) != -1) {
                    sb.append(Base64.encodeToString(buffer, Base64.DEFAULT));
                    num = count++;
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * 遍历参数
     *
     * @param map
     */
    public static void ergodicParameters(Map<String, String> map) {
        if (map != null) {
            Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, String> entry = entries.next();
                Log.i("Retrofit", "Key = " + entry.getKey() + ", Value = " + entry.getValue());
            }
        }
    }


    /**
     * 图片压缩
     *
     * @param paths
     * @return
     */
    public static List<String> getConvertImages(List<String> paths) {
        List<String> result = new ArrayList<>();

        for (String s : paths) {
            result.add(getCompressImage(s));
        }

        return result;
    }

    /**
     * 按比例压缩图片
     *
     * @param srcPath 路径
     * @return
     */
    public static String getCompressImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        newOpts.inJustDecodeBounds = false;

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        if (bitmap == null) {
            return null;
        }
        int degree = readPictureDegree(srcPath);
        if (degree != 0) {
            bitmap = rotateBitmap(bitmap, degree);
        }
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    /**
     * 按质量压缩
     *
     * @param image
     * @return
     */
    public static String compressImage(Bitmap image) {

        String path = getFileSavePath(MyApplication.getInstance()) + "/" + (Math.random() * 9000 + 1000) + ".png";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 50, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
//        int options = 30;
//        while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
//            baos.reset();//重置baos即清空baos
//            image.compress(Bitmap.CompressFormat.PNG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
//            options -= 10;//每次都减少10
//            if (options <= 0) {
//                break;
//            }
//        }
            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
            Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
            FileOutputStream stream = new FileOutputStream(path);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return path;
    }


    /**
     * 读取图片角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转至0°
     *
     * @param bitmap
     * @param degree
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degree);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    /**
     * 判断系统中是否存在可以启动的相机应用
     *
     * @return 存在返回true，不存在返回false
     */
    public static boolean hasCamera() {
        PackageManager packageManager = MyApplication.getInstance().getPackageManager();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * 图片压缩
     *
     * @param path
     * @param w
     * @param h
     */
    public static void compressImage(final String path, int w, int h) {

        Glide.with(MyApplication.getInstance()).load(path).asBitmap().centerCrop().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                FileOutputStream out = null;
                try {
                    File f = new File(path);
                    if (f.exists()) {
                        f.delete();
                    }
                    out = new FileOutputStream(new File(path));
                    resource.compress(Bitmap.CompressFormat.JPEG, 100, out);
                    out.flush();
                    out.close();

                    ExifInterface exifInterface = null;
                    try {

                        exifInterface = new ExifInterface(path);
                        int height = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的高度
                        int width = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, ExifInterface.ORIENTATION_NORMAL);//获取图片的宽度
                        Logger.i("h= " + height + " w= " + width);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    try {
                        if (out != null)
                            out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

                return false;
            }
        }).into(w, h);
    }

    /**
     * 判断手机号码是否合法
     *
     * @param mobiles
     * @return
     */
    public static boolean isPhone(String mobiles) {
        Pattern p = Pattern.compile("1[34578]\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /**
     * 判断字符是否为中文
     * <p/>
     * Í
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断系统中是否存在可以启动相应的apk
     *
     * @return 存在返回true，不存在返回false
     */
    public static boolean hasApk(Intent intent) {
        PackageManager packageManager = MyApplication.getInstance().getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /**
     * 扩大View的触摸和点击响应范围,最大不超过其父View范围
     *
     * @param view
     * @param top
     * @param bottom
     * @param left
     * @param right
     */
    public static void expandViewTouchDelegate(final View view, final int top, final int bottom, final int left, final int right) {

        try {
            ((View) view.getParent()).post(new Runnable() {
                @Override
                public void run() {
                    Rect bounds = new Rect();
                    view.setEnabled(true);
                    view.getHitRect(bounds);

                    bounds.top -= top;
                    bounds.bottom += bottom;
                    bounds.left -= left;
                    bounds.right += right;

                    TouchDelegate touchDelegate = new TouchDelegate(bounds, view);

                    if (View.class.isInstance(view.getParent())) {
                        ((View) view.getParent()).setTouchDelegate(touchDelegate);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
