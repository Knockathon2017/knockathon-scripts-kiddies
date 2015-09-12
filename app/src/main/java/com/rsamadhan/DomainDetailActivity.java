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
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rsamadhan.comments.CommentsActivity;
import com.rsamadhan.speech.SpeechFragment;

import org.w3c.dom.Text;


public class DomainDetailActivity extends AppCompatActivity {/*implements View.OnClickListener {

    public static final String EXTRA_NAME = "domain_name";
    public static final String EXTRA_INDEX = "domain_index";

    private FloatingActionButton mSpeakButton;

    private CardView mCardView1,mCardView2,mCardView3;
    private TextView mTextView1,mTextView2,mTextView3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mCardView1= (CardView) findViewById(R.id.cv_details1);
        mCardView2= (CardView) findViewById(R.id.cv_details2);
        mCardView3= (CardView) findViewById(R.id.cv_details3);

        mTextView1= (TextView) findViewById(R.id.tv_cv_1);
        mTextView2= (TextView) findViewById(R.id.tv_cv_2);
        mTextView3=   (TextView) findViewById(R.id.tv_cv_3);

        mCardView1.setOnClickListener(this);
        mCardView2.setOnClickListener(this);
        mCardView3.setOnClickListener(this);

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
            case R.id.cv_details1:
                launchCommentsPage(mTextView1.getText().toString());
                break;
            case R.id.cv_details2:
                launchCommentsPage(mTextView2.getText().toString());
                break;
            case R.id.cv_details3:
                launchCommentsPage(mTextView3.getText().toString());
                break;
        }
    }

    private void launchCommentsPage(String v) {

        Intent intent=new Intent(this, CommentsActivity.class);
        intent.putExtra(CommentsActivity.COMMENT_HEAD,v);
        startActivity(intent);
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
    }*/
}
