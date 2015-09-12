package com.rsamadhan.comments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rsamadhan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class CommentsActivity extends AppCompatActivity {

    private RecyclerView mCommentsListView;
    private CommentsListAdapter mRecyclerListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
        mCommentsListView= (RecyclerView) findViewById(R.id.lv_comments_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: call a service

        List<String> commentList=new ArrayList<String>();
        commentList.add("First Comment");
        commentList.add("Second Comment");
        commentList.add("Third Comment");
        commentList.add("First Comment");
        commentList.add("Second Comment");
        commentList.add("Third Comment");
        commentList.add("First Comment");
        commentList.add("Second Comment");
        commentList.add("Third Comment");
        commentList.add("First Comment");
        commentList.add("Second Comment");
        commentList.add("Third Comment");

        mRecyclerListAdapter =new CommentsListAdapter(commentList,this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mCommentsListView.setLayoutManager(layoutManager);
        mCommentsListView.setAdapter(mRecyclerListAdapter);

    }
}
