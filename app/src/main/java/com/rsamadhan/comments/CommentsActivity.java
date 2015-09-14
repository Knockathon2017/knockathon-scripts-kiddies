package com.rsamadhan.comments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.rsamadhan.R;
import com.rsamadhan.network.NetworkApi;
import com.rsamadhan.network.callbackrequest.ActivityCommentListCallback;
import com.rsamadhan.network.callbackrequest.CommentListCallback;
import com.rsamadhan.network.response.ActivityCommentsListData;
import com.rsamadhan.network.response.ComplaintListData;
import com.rsamadhan.network.response.Results;

import java.util.ArrayList;
import java.util.List;

import retrofit.RetrofitError;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class CommentsActivity extends AppCompatActivity {

    private RecyclerView mCommentsListView;
    private ActivityCommentsListAdapter mRecyclerListAdapter;
    private TextView mCommentsHeadView;
    private String mActivityId;
    private String mDomain;
    private ProgressDialog mDialog;
    private List<Results> mResultList;


    public static final String COMMENT_HEAD="Comment_Head";
    public static final String ACTIVITY_ID="activity_id";
    public static final String DOMAIN="domain";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_layout);
        mDialog=new ProgressDialog(this);
        mCommentsListView= (RecyclerView) findViewById(R.id.lv_comments_list);
        mCommentsHeadView= (TextView) findViewById(R.id.tv_comment_head);
        mCommentsHeadView.setText(getIntent().getStringExtra(COMMENT_HEAD));
        mActivityId=getIntent().getStringExtra(ACTIVITY_ID);
        mDomain=getIntent().getStringExtra(DOMAIN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: call a service
        getListOfComments();
    }
    public void showProgress(){
        mDialog.setMessage(getString(R.string.fetching_all_comment));
        mDialog.setCancelable(false);
        mDialog.show();
    }
    public void hideProgress(){
        mDialog.dismiss();
    }
    public void getListOfComments() {

        NetworkApi api=new NetworkApi();
        final Context context=this;
        showProgress();
        api.getAllActivityComments(new ActivityCommentListCallback() {
            @Override
            public void commentListSuccess(ActivityCommentsListData activityCommentsListData) {

                mRecyclerListAdapter =new ActivityCommentsListAdapter(activityCommentsListData.getActivityCommentsResult(),context);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
                mCommentsListView.setLayoutManager(layoutManager);
                mCommentsListView.setAdapter(mRecyclerListAdapter);
                hideProgress();
            }

            @Override
            public void commentListError(RetrofitError error) {
                hideProgress();
            }
        },mActivityId,mDomain);
    }
}
