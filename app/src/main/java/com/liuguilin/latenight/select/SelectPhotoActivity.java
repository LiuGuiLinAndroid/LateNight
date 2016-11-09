package com.liuguilin.latenight.select;

/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.select
 *  文件名:   SelectPhotoActivity
 *  创建者:   LGL
 *  创建时间:  2016/11/1 12:46
 *  描述：    选择头像
 */

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.view.CustomDialog;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;


public class SelectPhotoActivity extends AppCompatActivity implements View.OnClickListener {

    //头像名称
    private static final String IMAGE_FILE_NAME = "faceImage.jpg";
    private File tempFile = null;

    private CircleImageView profile_image;
    private CustomDialog dialog;

    private Button btn_camera;
    private Button btn_gallery;
    private Button btn_cancel;

    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_select_photo);

        initView();
    }

    //初始化View
    private void initView() {
        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        profile_image.setOnClickListener(this);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        dialog = new CustomDialog(this, 0, 0, R.layout.dialog_select_photo, R.style.pop_anim_style, Gravity.BOTTOM, 0);

        btn_camera = (Button) dialog.findViewById(R.id.btn_camera);
        btn_camera.setOnClickListener(this);
        btn_gallery = (Button) dialog.findViewById(R.id.btn_gallery);
        btn_gallery.setOnClickListener(this);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                dialog.show();
                break;
            case R.id.btn_cancel:
                dialog.dismiss();
                break;
            case R.id.btn_camera:
                toCamera();
                break;
            case R.id.btn_gallery:
                toGallery();
                break;
            case R.id.btn_next:
                Constants.putImgToShare(this,profile_image);
                startActivity(new Intent(this,SelectSexActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case Constants.IMAGE_REQUEST_CODE:
                    toZoom(data.getData());
                    break;
                case Constants.CAMERA_REQUEST_CODE:
                    tempFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
                    L.i("tempFile:" + tempFile);
                    toZoom(Uri.fromFile(tempFile));
                    break;
                case Constants.RESULT_REQUEST_CODE:
                    if (data != null) {
                        setImageToView(data);
                        if (tempFile != null) {
                            tempFile.delete();
                        }
                    }
                    break;
            }
        }
    }

    //跳转相机
    private void toCamera() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 判断存储卡是否可以用，可用进行存储
        intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
        this.startActivityForResult(intentFromCapture, Constants.CAMERA_REQUEST_CODE);
        dialog.dismiss();
    }

    //跳转图库
    private void toGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        this.startActivityForResult(intent, Constants.IMAGE_REQUEST_CODE);
        dialog.dismiss();
    }

    //裁剪
    private void toZoom(Uri uri) {
        if (uri == null) {
            L.i("The uri is not exist.");
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, Constants.RESULT_REQUEST_CODE);
    }

    //保存图片
    private void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            final Bitmap photo = extras.getParcelable("data");
            profile_image.setImageBitmap(photo);
        }
    }

}
