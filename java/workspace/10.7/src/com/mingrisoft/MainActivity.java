package com.mingrisoft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;




public class MainActivity extends Activity {
	private Camera camera; // �������
	private boolean isPreview = false; // �Ƿ�ΪԤ��ģʽ

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // ����ȫ����ʾ
		setContentView(R.layout.main);
		/****************** �ж��Ƿ�װSD�� *********************************/
		if (!android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			Toast.makeText(this, "�밲װSD����", Toast.LENGTH_SHORT).show(); // ������Ϣ��ʾ����ʾ��ʾ��Ϣ
		}
		/******************************************************************/
		SurfaceView sv = (SurfaceView) findViewById(R.id.surfaceView1); // ��ȡSurfaceView�����������ʾ���Ԥ��
		final SurfaceHolder sh = sv.getHolder();
		sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // ���ø�SurfaceHolder�Լ���ά������

		Button preview = (Button) findViewById(R.id.preview); // ��ȡ��Ԥ������ť
		preview.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// ������Ϊ��Ԥ��ģʽ��������
				if (!isPreview) {
					camera=Camera.open(); // �����
				}
				try {
					camera.setPreviewDisplay(sh); // ����������ʾԤ����SurfaceView
					Camera.Parameters parameters = camera.getParameters();	//��ȡ�������
					parameters.setPictureSize(640, 480);	//����Ԥ������ĳߴ�
					parameters.setPictureFormat(PixelFormat.JPEG);	//ָ��ͼƬΪJPEGͼƬ
					parameters.set("jpeg-quality", 80);	//����ͼƬ������
					parameters.setPictureSize(640, 480); 	//��������ͼƬ�ĳߴ�
					camera.setParameters(parameters);	//���������������
					camera.startPreview();	//��ʼԤ��
					camera.autoFocus(null); // �����Զ��Խ�
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});

		Button takePhoto = (Button) findViewById(R.id.takephoto); // ��ȡ�����ա���ť
		takePhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(camera!=null){
					camera.takePicture(null, null, jpeg); // ��������
				}
			}
		});
	}
	//ʵ�����յĻص��ӿ�
	final PictureCallback jpeg = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera camera) {
			// �����������õ����ݴ���λͼ
			final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
					data.length);
			// ����layout/save.xml�ļ���Ӧ�Ĳ�����Դ
			View saveView = getLayoutInflater().inflate(R.layout.save, null);
			final EditText photoName = (EditText) saveView
					.findViewById(R.id.phone_name);
			// ��ȡ�Ի����ϵ�ImageView���
			ImageView show = (ImageView) saveView.findViewById(R.id.show);
			show.setImageBitmap(bm);			// ��ʾ�ո��ĵõ���Ƭ
			camera.stopPreview();		//ֹͣԤ��
			isPreview = false;
			
			// ʹ�öԻ�����ʾsaveDialog���
			new AlertDialog.Builder(MainActivity.this).setView(saveView)
					.setPositiveButton("����", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							File file = new File("/sdcard/pictures/" + photoName
									.getText().toString() + ".jpg");//�����ļ�����
							try {
							file.createNewFile();								//����һ�����ļ�
							FileOutputStream fileOS = new FileOutputStream(file);	//����һ���ļ����������
							//��ͼƬ����ѹ��ΪJPEG��ʽ����������������
							bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOS);	
							fileOS.flush();									//���������е�����ȫ��д�����������
							fileOS.close();									//�ر��ļ����������
							isPreview = true;
							resetCamera();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

					}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
						
						public void onClick(DialogInterface dialog, int which) {
							isPreview = true;
							resetCamera();	//����Ԥ��
							
						}
					}).show();
		}
	};
	//����Ԥ��
	private void resetCamera(){
		if(isPreview){
			camera.startPreview();	//����Ԥ��
		}
	}
	//ֹͣԤ�����ͷ���Դ
	@Override
	protected void onPause() {
		if(camera!=null){
			camera.stopPreview();	//ֹͣԤ��
			camera.release();	//�ͷ���Դ
		}
		super.onPause();
	}
	
}