package com.example.rachid.myapplication;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.sql.DatabaseMetaData;

public class MainActivity extends AppCompatActivity {
    private EditText editMessage;
    private DatabaseReference metaData;
    private RecyclerView mMessageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editMessage = (EditText) findViewById(R.id.editMessageE);
        // referance to the data base
        metaData = FirebaseDatabase.getInstance().getReference().child("Messages");
        mMessageList = (RecyclerView) findViewById(R.id.messageRec);
        mMessageList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mMessageList.setLayoutManager(linearLayoutManager);



    }
    // to handle the send button clicked
    public  void sendButtonClicked (View view)
    {
        // to check the if the string is empty
        final String messageValue = editMessage.getText().toString().trim();
        if (!TextUtils.isEmpty((messageValue)))
        {
            final  DatabaseReference newPost = metaData.push();
            newPost.child("content").setValue(messageValue);
        }
    }
   //  to recycle the database adapter
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter <Message,Message_View_Holder>  FBRA = new FirebaseRecyclerAdapter<Message, Message_View_Holder>(
                Message.class,
                R.layout.single_message_layout,
                Message_View_Holder.class,
                metaData

        ){
            @Override
            protected void populateViewHolder(Message_View_Holder viewHolder, Message model, int position) {
                viewHolder.setContent(String.valueOf(model));

            }
        };
        mMessageList.setAdapter(FBRA);
    }

    // new class that is going to extend recycleview class
    public  static class  Message_View_Holder extends RecyclerView.ViewHolder

    {
        View view;
        // matching constructor for our class Message_View_Holder
        public Message_View_Holder(View itemView) {
            super(itemView);
            view =itemView;
        }
        public void setContent (String content)
        {
            // getting a reference to the text view  from activity_main.xml class
            TextView message_content = (TextView) view.findViewById(R.id.messageText);
            // set the message content
            message_content.setText(content);
        }


    }
}
