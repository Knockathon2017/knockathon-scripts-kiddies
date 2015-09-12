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

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rsamadhan.common.ApplicationUtils;
import com.rsamadhan.hospitals.HospitalActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DomainListFragment extends Fragment implements
        TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private boolean isTTSSuccess;

    @Override
    public void onResume() {
        super.onResume();
        if (tts == null) {
            tts = new TextToSpeech(getActivity(), this);
        } else {
            if (isTTSSuccess) {
                speakOut();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_domain_list, container, false);
        setupRecyclerView(rv);
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(), getResources().getStringArray(R.array.images)));
    }

    private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }

    /**
     * Called to signal the completion of the TextToSpeech engine initialization.
     *
     * @param status {@link TextToSpeech#SUCCESS} or {@link TextToSpeech#ERROR}.
     */
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(ApplicationUtils.getSelectedLocale(PreferenceManager.getInstance(getActivity())));
            tts.setPitch(0.6f);
            tts.setSpeechRate(0.5f);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(getActivity(), getString(R.string.speech_not_supported), Toast.LENGTH_LONG).show();
            } else {
                isTTSSuccess = true;

                speakOut();
            }
        }
    }

    @TargetApi(21)
    private void speakOut() {
        if (Build.VERSION.SDK_INT >= 21) {
            String text = getString(R.string.select_below_txt);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.shutdown();
        }
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private String mValues[];

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;
            public int mBoundIndex;

            public final View mView;
            public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public String getValueAt(int position) {
            return mValues[position];
        }

        public SimpleStringRecyclerViewAdapter(Context context, String items[]) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mBoundString = mValues[position];
            holder.mBoundIndex = position;
            holder.mTextView.setText(mValues[position]);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    if (holder.mBoundIndex == 14) {
                        Intent intent = new Intent(context, HospitalActivity.class);
                        intent.putExtra(HospitalActivity.EXTRA_NAME, holder.mBoundString);
//                        intent.putExtra(DomainDetailActivity.EXTRA_INDEX,holder.mBoundIndex);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, DomainDetailActivity.class);
                        intent.putExtra(DomainDetailActivity.EXTRA_NAME, holder.mBoundString);
                        intent.putExtra(DomainDetailActivity.EXTRA_INDEX, holder.mBoundIndex);
                        context.startActivity(intent);
                    }
                }
            });

            Glide.with(holder.mImageView.getContext())
                    .load(Domains.getDomainImage(position))
                    .fitCenter()
                    .into(holder.mImageView);
        }

        @Override
        public int getItemCount() {
            return mValues.length;
        }
    }
}
