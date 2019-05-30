package com.wzx.studyhelper.common.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjq.toast.ToastUtils;
import com.wzx.studyhelper.R;
import com.wzx.studyhelper.db.bean.FriendsRequestDB;
import com.wzx.studyhelper.ui.chat.adapter.FriendRequestAdpter;

import java.util.ArrayList;

public class Test2Activity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Person> mDatas;

    public static int TEST2 = 1;
    private TestAdapter mTestAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        findViewById(R.id.appbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatas.add(new Person("hahha"));
                mTestAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition(mDatas.size()-1);

            }
        });
        mRecyclerView = ((RecyclerView) findViewById(R.id.recyclerview));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,OrientationHelper.VERTICAL,true));

        mDatas = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            mDatas.add(new Person("Android"+ i));
        }


        mTestAdapter = new TestAdapter(mDatas);

        mTestAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.show(mDatas.get(position));
            }
        });

        mRecyclerView.setAdapter(mTestAdapter);

        mRecyclerView.scrollToPosition(mDatas.size()-1);



    }
}
