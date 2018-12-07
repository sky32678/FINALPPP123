package com.example.sky32.finalppp;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
    Camera camera;

    SurfaceHolder holder;

    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Camera.Parameters para = camera.getParameters();
        List<Camera.Size> sizes = para.getSupportedPictureSizes();
        Camera.Size m = null;
        for (Camera.Size size : sizes) {
            m = size;
        }
        if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE) {
            para.set("orientaiton", "portrait");
            camera.setDisplayOrientation(90);
            para.setRotation(90);
        } else{
            para.set("orientation", "landscape");
            camera.setDisplayOrientation(0);
            para.setRotation(0);
        }
        para.setPictureSize(m.width,m.height);
        camera.setParameters(para);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();

    }


}
