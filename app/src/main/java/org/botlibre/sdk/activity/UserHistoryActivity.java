package org.botlibre.sdk.activity;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.botlibre.sdk.R;

import java.util.ArrayList;


public class UserHistoryActivity extends AppCompatActivity {


	ArrayList<ChatData> chatlist;

	MyChatAdapter myAdapter;
	RecyclerView recycler_history;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userchat);
		recycler_history = (RecyclerView) findViewById(R.id.recycler_history);

		recycler_history.setHasFixedSize(true);
		recycler_history.setLayoutManager(new LinearLayoutManager(this));

		chatlist = new ArrayList<>();
		myAdapter = new MyChatAdapter(this,chatlist);
		recycler_history.setAdapter(myAdapter);

		getchatlist();
	}

	public void getchatlist() {
		DatabaseReference database, conv;

		database = FirebaseDatabase.getInstance().getReference("users");
		FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

		conv = database.child(currentFirebaseUser.getUid()).child("ChatHistory");

		conv.addListenerForSingleValueEvent(new ValueEventListener() {

			@Override
			public void onDataChange(@NonNull DataSnapshot snapshot) {
				for (DataSnapshot childSnapshot : snapshot.getChildren()) {
					ChatData datas = null;
					try {
						datas = childSnapshot.getValue(ChatData.class);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					chatlist.add(datas);
				}

				myAdapter.notifyDataSetChanged();
			}

			@Override
			public void onCancelled(@NonNull DatabaseError error) {
				throw error.toException(); // 👈 never ignore errors
			}

		});

	}
}