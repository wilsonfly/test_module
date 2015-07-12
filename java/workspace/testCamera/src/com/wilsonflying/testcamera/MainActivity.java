package com.wilsonflying.testcamera;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private SurfaceView cameraPreview;
	private Camera camera = null;
	private Callback cameraPreviewHolderCallback = new Callback() {

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			stopPreview();
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			startPreview();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
		cameraPreview.getHolder().addCallback(cameraPreviewHolderCallback);

		findViewById(R.id.btnTakePic).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						camera.takePicture(null, null,
								new Camera.PictureCallback() {

									@Override
									public void onPictureTaken(byte[] data,
											Camera camera) {

										String path = null;
										if ((path = saveTempFile(data)) != null) {

											Intent i = new Intent(
													MainActivity.this,
													ImagePreviewAty.class);
											i.putExtra("path", path);
											startActivity(i);
										} else {
											Toast.makeText(MainActivity.this,
													"保存照片失败",
													Toast.LENGTH_SHORT).show();
										}
									}
								});
					}
				});
	}

	private String saveTempFile(byte[] bytes) {

		try {
			File f = File.createTempFile("img", "");
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bytes);
			fos.flush();
			fos.close();

			return f.getAbsolutePath();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void startPreview() {
		camera = Camera.open();
		try {
			camera.setPreviewDisplay(cameraPreview.getHolder());
			camera.setDisplayOrientation(90);
			camera.startPreview();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void stopPreview() {
		camera.stopPreview();
		camera.release();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
