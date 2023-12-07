package org.botlibre.sdk.activity;

import org.botlibre.sdk.activity.actions.HttpConnectAction;
import org.botlibre.sdk.config.UserConfig;

import org.botlibre.sdk.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends LibreActivity {	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	/**
	 * Start a chat session with the selected instance and the user.
	 */
	public void connect(View view) {
        EditText text = (EditText) findViewById(R.id.userText);
        String user = text.getText().toString().trim();
        text = (EditText) findViewById(R.id.passwordText);
        String password = text.getText().toString().trim();
        
		UserConfig config = new UserConfig();
		config.user = user;
		config.password = password;
        HttpConnectAction action = new HttpConnectAction(this, config, true);
    	action.execute();
    }
	
	public void signUp(View view) {
		finish();
		
        Intent intent = new Intent(this, CreateUserActivity.class);
        startActivity(intent);
	}
}
