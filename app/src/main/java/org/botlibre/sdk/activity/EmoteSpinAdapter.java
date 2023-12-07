package org.botlibre.sdk.activity;

import java.util.List;

import org.botlibre.sdk.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EmoteSpinAdapter extends ArrayAdapter<EmotionalState> {
	 
	Activity activity;
 
    public EmoteSpinAdapter(Activity activity, int resourceId, List<EmotionalState> items) {
        super(activity, resourceId, R.id.nameView, items);
        this.activity = activity;
    }
 
    class ImageListViewHolder {
        ImageView imageView;
        TextView nameView;
    }
    
    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	ImageListViewHolder holder = null;
    	EmotionalState state = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater)this.activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.emote_list, null);
            holder = new ImageListViewHolder();
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            holder.nameView = (TextView) convertView.findViewById(R.id.nameView);
            convertView.setTag(holder);
        } else {
            holder = (ImageListViewHolder) convertView.getTag();
        }
        
        holder.nameView.setText(state.name().toLowerCase());
        int image = R.drawable.emote;
        if (state == EmotionalState.LIKE) {
        	image = R.drawable.like;
        } else if (state == EmotionalState.LOVE) {
        	image = R.drawable.love;
        } else if (state == EmotionalState.DISLIKE) {
        	image = R.drawable.dislike;
        } else if (state == EmotionalState.HATE) {
        	image = R.drawable.dislike;
        } else if (state == EmotionalState.HAPPY) {
        	image = R.drawable.happy;
        } else if (state == EmotionalState.ECSTATIC) {
        	image = R.drawable.laughter;
        } else if (state == EmotionalState.SAD) {
        	image = R.drawable.dislike;
        } else if (state == EmotionalState.CRYING) {
        	image = R.drawable.sad;
        } else if (state == EmotionalState.LAUGHTER) {
        	image = R.drawable.laughter;
        } else if (state == EmotionalState.ANGER) {
        	image = R.drawable.anger;
        } else if (state == EmotionalState.RAGE) {
        	image = R.drawable.rage;
        } else if (state == EmotionalState.AFRAID) {
        	image = R.drawable.fear;
        } else if (state == EmotionalState.PANIC) {
        	image = R.drawable.fear;
        } else if (state == EmotionalState.BORED) {
        	image = R.drawable.bored;
        } else if (state == EmotionalState.SURPRISE) {
        	image = R.drawable.surprise;
        }
        holder.imageView.setImageResource(image);
 
        return convertView;
    }
}