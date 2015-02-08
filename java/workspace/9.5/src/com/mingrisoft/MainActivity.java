package com.mingrisoft;



import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		FrameLayout ll=(FrameLayout)findViewById(R.id.linearLayout1);
		ll.addView(new MyView(this));
	
	}
	public class MyView extends View{

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint paint=new Paint();	//����һ������
			paint.setAntiAlias(true);	//����ʹ�ÿ���ݹ���
			paint.setColor(0xFFFF6600);	//���û�����ɫ
			paint.setTextSize(18);	//�������ִ�С
			paint.setStyle(Style.STROKE);	//������䷽ʽΪ���
			//����Բ��·��
			Path pathCircle=new Path();//������ʵ����һ��path����
			pathCircle.addCircle(70, 70, 40, Path.Direction.CCW);	//�����ʱ���Բ��·��
			canvas.drawPath(pathCircle, paint);	//����·��
			//��������·��
			Path pathLine=new Path();		//������ʵ����һ��Path����
			pathLine.moveTo(150, 100);		//������ʼ��
			pathLine.lineTo(200, 45);		//���õ�һ��ֱ�ߵĽ�����
			pathLine.lineTo(250, 100);		//���õڶ���ֱ�ߵĽ�����
			pathLine.lineTo(300, 80);		//���õ�3��ֱ�ߵĽ�����
			canvas.drawPath(pathLine, paint);	//����·��
			//����������·��
			Path pathTr=new Path();	//������ʵ����һ��path����
			pathTr.moveTo(350,80);	//������ʼ��
			pathTr.lineTo(400, 30);	//���õ�һ���ߵĽ����㣬Ҳ�ǵڶ����ߵ���ʼ��
			pathTr.lineTo(450, 80);	//���õڶ����ߵĽ����㣬Ҳ�ǵ�3���ߵ���ʼ��
			pathTr.close();			//�պ�·��
			canvas.drawPath(pathTr, paint);	//����·��
			//������·���Ļ�������
			String str="����������ˮ����׳ʿһȥ�ⲻ����";
			Path path=new Path();		//������ʵ����һ��path����
			path.addCircle(550, 100, 48, Path.Direction.CW);		//���˳ʱ���Բ��·��		
			paint.setStyle(Style.FILL);//���û��ʵ���䷽ʽ
			canvas.drawTextOnPath(str, path,0, -18, paint);	//������·������
			super.onDraw(canvas);
		}
		
		
	}
}