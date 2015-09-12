package com.rsamadhan.speech;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.rsamadhan.PreferenceManager;
import com.rsamadhan.R;
import com.rsamadhan.common.ApplicationUtils;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by prathmeshs on 12-09-2015.
 */
public class SpeechFragment extends DialogFragment {


    private View mView;
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    public SpeechFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog=super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView=inflater.inflate(R.layout.activity_speechtotext,container);
        txtSpeechInput = (TextView) mView.findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton)mView.findViewById(R.id.btnSpeak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        promptSpeechInput();
        return mView;
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Locale selectedLocale =ApplicationUtils.getSelectedLocale(PreferenceManager.getInstance(getActivity()));
        String localeSelected;
        if(selectedLocale.getLanguage().equals("hi")){
                localeSelected="hi-IN";
        }else{
            localeSelected=selectedLocale.getLanguage()+"-"+selectedLocale.getCountry();
        }
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,localeSelected);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    /**
     * Receiving speech input
     * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));

                }
                break;
            }
        }
    }

}