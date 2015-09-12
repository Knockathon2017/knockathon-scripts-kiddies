package com.rsamadhan.hospitals;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.rsamadhan.R;
import com.rsamadhan.comments.CommentsListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class HospitalActivity extends AppCompatActivity {

    private RecyclerView mCommentsListView;
    private CommentsListAdapter mRecyclerListAdapter;
    private TextView mCommentsHeadView;
    public static final String EXTRA_NAME = "domain_name";
    public static final String EXTRA_INDEX = "domain_index";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_layout);


        Intent intent = getIntent();
        final String domainName = intent.getStringExtra(EXTRA_NAME);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.tb_comments);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        /*CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(domainName);*/

        mCommentsListView= (RecyclerView) findViewById(R.id.lv_comments_list);
        mCommentsHeadView= (TextView) findViewById(R.id.tv_comment_head);
        mCommentsHeadView.setText(domainName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: call a service

        List<String> commentList=new ArrayList<String>();
        commentList.add("Uttar Pradesh Agra Malhotra Nursing & Maternity Home Allopathic Malhotra Nursing & Maternity Home (P) Ltd.Malhotra Test Tube Baby Center, 84, M G Road, Agra, Uttar Pradesh, Phone- (0562) 2260275, 2260276, 2260277, Helpline: 098970 82171, 098970 99336, 093191 03993");
        commentList.add("Uttar Pradesh Agra Pushpanjali Hospital & Research Centre Pushpanjali Palace, Delhi Gate, Agra, Uttar Pradesh, Phone- (0562) 4024004, Emergency: 08954893903");
        commentList.add("Uttar Pradesh Agra Amit Jaggi Memorial Hospital Vaibhav Nagar, Agra, Uttar Pradesh, Phone- (0562) 2230515, (0562) 2330600");
        commentList.add("Uttar Pradesh Agra Lotus Super Speciality Hospital Allopathic Diwani Crossing, Agra, Uttar Pradesh, Phone- (0562) 4057365, 4064945");
       /* commentList.add("Second Comment");
        commentList.add("Third Comment");
        commentList.add("First Comment");
        commentList.add("Second Comment");
        commentList.add("Third Comment");
        commentList.add("First Comment");
        commentList.add("Second Comment");
        commentList.add("Third Comment");*/

        mRecyclerListAdapter =new CommentsListAdapter(commentList,this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        mCommentsListView.setLayoutManager(layoutManager);
        mCommentsListView.setAdapter(mRecyclerListAdapter);

    }
}
