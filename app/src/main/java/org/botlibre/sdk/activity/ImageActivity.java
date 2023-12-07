package org.botlibre.sdk.activity;

import org.botlibre.sdk.activity.actions.HttpGetImageAction;

import org.botlibre.sdk.R;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageActivity extends LibreActivity {	
	public static String image;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.activity_image);
		
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        HttpGetImageAction.fetchImage(this, image, (ImageView)findViewById(R.id.imageView));
	}
}
