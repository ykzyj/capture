package com.google.zxing.client.activity;

import java.io.File;
import java.io.FileInputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.client.android.R;

public class ResultActivity extends Activity {

		private TextView mTextView;
		private ImageView mImageView;
		
		private Button mButton;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.result);
			
			initComponent();
			
			mButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					  	Intent intent=new Intent(ResultActivity.this, CaptureActivity.class);
					    startActivityForResult(intent, 0);
				}
			});
		}
		
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			switch (resultCode) {
			case RESULT_OK:
				/*Bitmap mBitmap=data.getParcelableExtra("Bitmap");
				String txt=data.getStringExtra("text");*/
				
				Bundle bundle = data.getExtras();  
				String txt = bundle.getString( "result" );
				Bitmap bitmap_in = null;
				FileInputStream inStream = null;
				 String filePath=Environment.getExternalStorageDirectory().getPath()+"/temp.png";
				/*try {
				inStream = new FileInputStream(new File(filePath));
				bitmap_in=(Bitmap) BitmapFactory.decodeStream(inStream);
				Matrix matrix=new Matrix();
				matrix.setRotate(90);
				bitmap_in=Bitmap.createBitmap(bitmap_in, 0, 0, bitmap_in.getWidth(),
				bitmap_in.getHeight(), matrix, true);
				//img_camera_obtain.setImageBitmap(bitmap_in);
				} catch (FileNotFoundException e) {
				e.printStackTrace();
				}
				finally
				{
				try {
				inStream.close();
				} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				}*/
				 
				 File file = new File(filePath);
				 Uri uri = Uri.fromFile(file);
				 //view.setImageUri(uri); 
				
				mTextView.setText(txt);
				mImageView.setImageURI(uri);
				
				break;

			default:
				break;
			}
		}

		private void initComponent() {
			mTextView=(TextView) findViewById(R.id.result_text_view);
			mImageView=(ImageView) findViewById(R.id.result_image_view);
			mButton=(Button) findViewById(R.id.result_open_camera);
		}
}
