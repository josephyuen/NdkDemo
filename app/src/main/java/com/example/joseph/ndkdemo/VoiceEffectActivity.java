package com.example.joseph.ndkdemo;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by joseph on 2017/11/10.
 */

public class VoiceEffectActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_effect);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, 0);
        }
    }


    public void mFix(View view) {

       String voicePath = "file:///android_asset/README.md";

       copyAssetsToDst(this,"","");

//        String voicePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar
//                + "drumloop.wav";

copyAssetsToDst(this,"","");

        int type = VoiceEffectUtils.TYPE_NORMAL;
        switch (view.getId()) {
            case R.id.btn_record:
                type = VoiceEffectUtils.TYPE_NORMAL;
                break;
            case R.id.btn_luoli:
                type = VoiceEffectUtils.TYPE_LUOLI;
                break;
            case R.id.btn_dashu:
                type = VoiceEffectUtils.TYPE_DASHU;
                break;
            case R.id.btn_jingsong:
                type = VoiceEffectUtils.TYPE_JINGSONG;
                break;
            case R.id.btn_gaoguai:
                type = VoiceEffectUtils.TYPE_GAOGUAI;
                break;
            case R.id.btn_kongling:
                type = VoiceEffectUtils.TYPE_KONGLING;
                break;
        }
//        play(voicePath, type);
    }

    private void play(final String voicePath, final int type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                VoiceEffectUtils.playFixVoice(voicePath, type);
            }
        }).start();
    }



    private void copyAssetsToDst(Context context, String srcPath, String dstPath) {
        try {
            String fileNames[] = context.getAssets().list(srcPath);
            if (fileNames.length > 0) {
                File file = new File(Environment.getExternalStorageDirectory(), dstPath);
                if (!file.exists()) file.mkdirs();
                for (String fileName : fileNames) {
                    if (!srcPath.equals("")) { // assets 文件夹下的目录
                        copyAssetsToDst(context, srcPath + File.separator + fileName, dstPath + File.separator + fileName);
                    } else { // assets 文件夹
                        copyAssetsToDst(context, fileName, dstPath + File.separator + fileName);
                    }
                }
            } else {
                File outFile = new File(Environment.getExternalStorageDirectory(), dstPath);
                InputStream is = context.getAssets().open(srcPath);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] buffer = new byte[1024];
                int byteCount;
                while ((byteCount = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }



}





//feature-acagrid.com/application/api/controller/College.php(184): app\college\model\CollegeStats->
//        generateAccountDemoData('127', '704', '9802') #4 [internal function]: app\api\controller\College->create() #5 /var/www/feature-aca