package com.wzx.studyhelper.common.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wzx.studyhelper.R;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.ui.chat.adapter.FriendRequestAdpter;

import java.util.ArrayList;

public class Test2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<FriendsRequestDB> mDatas;

    public static int TEST2 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        mRecyclerView = ((RecyclerView) findViewById(R.id.recyclerview));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatas = new ArrayList<>();

//        for (int i = 0; i < 100; i++) {
//            mDatas.add(new FriendsRequestDB(String.valueOf(i),String.valueOf(i),String.valueOf(i),String.valueOf(i),i));
//        }
        FriendRequestAdpter friendRequestAdpter = new FriendRequestAdpter(mDatas);
        mRecyclerView.setAdapter(friendRequestAdpter);



        Log.d("AAA", "onCreate: " + TEST2);

    }
}
