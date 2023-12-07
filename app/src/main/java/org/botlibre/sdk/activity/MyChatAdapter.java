package org.botlibre.sdk.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.botlibre.sdk.R;

import java.util.ArrayList;

public class MyChatAdapter  extends RecyclerView.Adapter<MyChatAdapter.MyViewHolder> {

    Context context;
    ArrayList<ChatData> list;

    public MyChatAdapter(Context context, ArrayList<ChatData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_chats,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ChatData report1 = list.get(position);
        holder.questions.setText(report1.getQuestion());
        holder.answers.setText(report1.getAnswer());
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView questions, answers;
        public View view;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            questions = itemView.findViewById(R.id.txtview_question);
            answers = itemView.findViewById(R.id.txtview_answer);

//            itemView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View itemView) {
//
//
//                }
//            });
        }
    }
}
