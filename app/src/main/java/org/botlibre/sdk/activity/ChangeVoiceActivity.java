package org.botlibre.sdk.activity;

import java.util.Arrays;

import org.botlibre.sdk.config.VoiceConfig;

import org.botlibre.sdk.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


public class ChangeVoiceActivity extends VoiceActivity {

	public void save(View view) {
		VoiceConfig config = new VoiceConfig();
        config.instance = MainActivity.instance.id;

        Spinner spin = (Spinner) findViewById(R.id.voiceSpin);
		config.voice = MainActivity.voices[Arrays.asList(MainActivity.voiceNames).indexOf(spin.getSelectedItem().toString())];
        spin = (Spinner) findViewById(R.id.languageSpin);
        config.language = spin.getSelectedItem().toString();
        spin = (Spinner) findViewById(R.id.voiceModSpin);
		config.mod = spin.getSelectedItem().toString();
		EditText text = (EditText) findViewById(R.id.pitchText);
		config.pitch = text.getText().toString();
		text = (EditText) findViewById(R.id.speechRateText);
		config.speechRate = text.getText().toString();		
		CheckBox checkbox = (CheckBox) findViewById(R.id.deviceVoiceCheckBox);
		config.nativeVoice = checkbox.isChecked();
		MainActivity.deviceVoice = config.nativeVoice;
		MainActivity.voice = config;
		MainActivity.customVoice = true;
		
    	SharedPreferences.Editor cookies = getPreferences(Context.MODE_PRIVATE).edit();
    	cookies.putString("voice", MainActivity.voice.voice);
    	cookies.putString("language", MainActivity.voice.language);
    	cookies.putString("nativeVoice", String.valueOf(MainActivity.voice.nativeVoice));
    	cookies.commit();
    	
    	finish();
		
	}
}
