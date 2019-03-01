package com.changhao.weidu_project.ui.fragment;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.changhao.weidu_project.R;
import com.changhao.weidu_project.contract.IUpLoadHeaderContract;
import com.changhao.weidu_project.entity.UpLoadHeaderEntity;
import com.changhao.weidu_project.presenter.UpLoadHeaderpresenter;
import com.changhao.weidu_project.ui.activity.MyCircleActivity;
import com.changhao.weidu_project.ui.activity.UserDataActivity;
import com.changhao.weidu_project.ui.activity.UserWalletActivity;
import com.changhao.weidu_project.ui.base.BaseFragment;
import com.changhao.weidu_project.utils.BitmapUtil;
import com.changhao.weidu_project.utils.DensityUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.InvokeParam;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends BaseFragment implements IUpLoadHeaderContract.IUpLoadHeaderView {

    @BindView(R.id.my_image_header)
    SimpleDraweeView my_image_header;
    @BindView(R.id.my_text_name)
    TextView my_text_name;
    @BindView(R.id.my_text_personal)
    TextView my_text_personal;
    @BindView(R.id.my_text_circle)
    TextView my_text_circle;
    @BindView(R.id.my_text_foot)
    TextView my_text_foot;
    @BindView(R.id.my_text_wallet)
    TextView my_text_wallet;
    @BindView(R.id.my_text_address)
    TextView my_text_address;

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private UpLoadHeaderpresenter upLoadHeaderpresenter;
    // 声明PopupWindow
    private PopupWindow popupWindow;
    // 声明PopupWindow对应的视图
    private View popupView;
    // 声明平移动画
    private TranslateAnimation animation;

    private final int CAMERA = 1000;
    private final int PICTURE = 2000;


    @Override
    protected int getViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
        initClick();
    }

    private void initClick() {
        my_image_header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getActivity(), "点击了头像", Toast.LENGTH_SHORT).show();*/

                switch (v.getId()) {
                    case R.id.my_image_header:
                        // TODO 弹出popupwind选择拍照或者从相册选择
                        changeIcon(v);
                        /*lightoff();*/
                        break;


                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null) {

            // 拍照
            Bundle bundle = data.getExtras();
            // 获取相机返回的数据，并转换为图片格式
            Bitmap bitmap = (Bitmap) bundle.get("data");
            // bitmap圆形裁剪
            bitmap = BitmapUtil.zoom(bitmap, DensityUtil.dip2pxRatio(getActivity(), 62), DensityUtil.dip2pxRatio(getActivity(), 62));
            Bitmap circleBitmap = BitmapUtil.circleBitmap(bitmap);

            /*//TODO 将图片上传到服务器的
            ivIcon.setImageBitmap(circleBitmap);*/
            // 将图片保存在本地
            try {
                saveImage(circleBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICTURE && resultCode == RESULT_OK && data != null) {
            //图库
            Uri selectedImage = data.getData();
            //这里返回的uri情况就有点多了
            //**:在4.4.2之前返回的uri是:content://media/external/images/media/3951或者file://....在4.4.2返回的是content://com.android.providers.media.documents/document/image:3951或者
            //总结：uri的组成，eg:content://com.example.project:200/folder/subfolder/etc
            //content:--->"scheme"
            //com.example.project:200-->"host":"port"--->"authority"[主机地址+端口(省略) =authority]
            //folder/subfolder/etc-->"path" 路径部分
            //android各个不同的系统版本,对于获取外部存储上的资源，返回的Uri对象都可能各不一样,所以要保证无论是哪个系统版本都能正确获取到图片资源的话
            //就需要针对各种情况进行一个处理了
            String pathResult = getPath(selectedImage);

            Bitmap decodeFile = BitmapFactory.decodeFile(pathResult);
            Bitmap zoomBitmap = BitmapUtil.zoom(decodeFile, DensityUtil.dip2pxRatio(getActivity(), 62), DensityUtil.dip2pxRatio(getActivity(), 62));
            // bitmap圆形裁剪p
            Bitmap circleImage = BitmapUtil.circleBitmap(zoomBitmap);
            /*// 真实项目当中，是需要上传到服务器的..这步我们就不做了。
            ivIcon.setImageBitmap(circleImage);*/
            try {
                // 保存图片到本地
                saveImage(circleImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    // 将修改后的图片保存在本地存储中：storage/sdcard/Android/data/应用包名/files/xxx.png
    private void saveImage(Bitmap bitmap) throws FileNotFoundException {

        String path = this.getCacheDir() + "com.changhao.weidu_project/cache" + "/tx.png";
        Log.e("TAG", "path = " + path);
        try {
            FileOutputStream fos = new FileOutputStream(path);
            // bitmap压缩(压缩格式、质量、压缩文件保存的位置)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//
//            File externalFilesDir = this.getExternalFilesDir(null);
//            File file = new File(externalFilesDir, "icon.png");
//            // 将Bitmap持久化
//            circleBitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(file));
//        }
    }

    // 根据系统相册选择的文件获取路径
    @SuppressLint("NewApi")
    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        // 高于4.4.2的版本
        if (sdkVersion >= 19) {
            Log.e("TAG", "uri auth: " + uri.getAuthority());
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(getActivity(), contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(getActivity(), contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = getActivity().managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(getActivity(), uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        upLoadHeaderpresenter = new UpLoadHeaderpresenter(this);

        my_text_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserDataActivity.class);
                startActivity(intent);
            }
        });

        my_text_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserWalletActivity.class);
                startActivity(intent);
            }
        });

        my_text_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyCircleActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected String getCacheDir() {
        String path = "storage/sdcard/Android/data/";
        return path;
    }

    @Override
    public void onSuccess(UpLoadHeaderEntity upLoadHeaderEntity) {
        if (upLoadHeaderEntity != null) {
            String status = upLoadHeaderEntity.getStatus();
            if (status.equals("0000")) {
                Uri uri = Uri.parse(upLoadHeaderEntity.getMessage());
                my_image_header.setImageURI(uri);
                Toast.makeText(getActivity(), "" + upLoadHeaderEntity.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailed(String msg) {

    }


    /*private void lighton() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }*/

    private Window getWindow() {

        return null;
    }

    private void changeIcon(View v) {
        if (popupWindow == null) {
            popupView = View.inflate(getActivity(), R.layout.item_popupwindow_view, null);
            // 参数2,3：指明popupwindow的宽度和高度
            popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    /*lighton();*/
                }
            });

            // 设置背景图片， 必须设置，不然动画没作用
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);

            // 设置点击popupwindow外屏幕其它地方消失
            popupWindow.setOutsideTouchable(true);

            // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
            animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                    Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setDuration(200);

            popupView.findViewById(R.id.tv_photograph).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 打开系统拍照程
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(camera, CAMERA);
                    popupWindow.dismiss();
                    /*lighton();*/
                }
            });
            popupView.findViewById(R.id.tv_photo_album).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 打开系统图库选择图片
                    Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(picture, PICTURE);
                    popupWindow.dismiss();
                    /* lighton();*/
                }
            });

            popupView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });
        }

        // 在点击之后设置popupwindow的销毁
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
            /*lighton();*/
        }

        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
        popupWindow.showAtLocation(getView().findViewById(R.id.mine_view), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        popupView.startAnimation(animation);
    }



    /*private void lightoff() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.3f;
        getWindow().setAttributes(lp);
    }*/
}
