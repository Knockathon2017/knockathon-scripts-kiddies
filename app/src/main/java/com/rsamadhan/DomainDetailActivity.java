/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rsamadhan;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rsamadhan.comments.CommentsActivity;
import com.rsamadhan.network.ComplaintListAdapter;
import com.rsamadhan.network.ComplaintListData;
import com.rsamadhan.network.NetworkApi;
import com.rsamadhan.network.callbackrequest.ComplaintListCallback;
import com.rsamadhan.speech.SpeechFragment;

import org.w3c.dom.Text;

import retrofit.RetrofitError;


public class DomainDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "domain_name";
    public static final String EXTRA_INDEX = "domain_index";

    private FloatingActionButton mSpeakButton;

    private String mCrimeDomain="crime";
    private String mAdminDomain="admin";
    private String mEducation="edu";

    private RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mRecyclerView= (RecyclerView) findViewById(R.id.rv_complaint_list);
        mLayoutManager=new LinearLayoutManager(this);

        mSpeakButton= (FloatingActionButton) findViewById(R.id.floating_ac_btn);
        mSpeakButton.setOnClickListener(this);

        Intent intent = getIntent();
        final String domainName = intent.getStringExtra(EXTRA_NAME);
        final int cheeseIndex= intent.getIntExtra(EXTRA_INDEX, 0);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(domainName);

        loadBackdrop(cheeseIndex);

        String domVal=mEducation;
        if(domainName.contains(mCrimeDomain)){
                  domVal=mCrimeDomain;
        }else if(domainName.contains(mAdminDomain)){

            domVal=mAdminDomain;
        }else if(domainName.contains(mEducation)){
            domVal=mEducation;
        }
        lanuchComplaintListService(domVal);
    }

    private void lanuchComplaintListService(String domVal) {
        NetworkApi api=new NetworkApi();
        final Context context=this;
        api.getComplaintList(new ComplaintListCallback() {
            @Override
            public void complaintListSuccess(ComplaintListData complaintListData) {
                mRecyclerView.setLayoutManager(mLayoutManager);
                ComplaintListAdapter adapter= new ComplaintListAdapter(complaintListData.getResults(),context);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void complaintListError(RetrofitError error) {

            }
        },"2302432",PreferenceManager.getInstance(this).getLoginId(),domVal);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void loadBackdrop(int index) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(this).load(Domains.getDomainImage(index)).centerCrop().into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();

        switch (id){
            case R.id.floating_ac_btn:
                launchSpeakDialog();
                break;
        }
    }


    private void launchSpeakDialog() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        SpeechFragment newFragment = new SpeechFragment();
        newFragment.show(ft, "dialog");
    }
}
