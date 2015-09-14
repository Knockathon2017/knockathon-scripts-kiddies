package com.rsamadhan.speech;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rsamadhan.common.PreferenceManager;
import com.rsamadhan.R;
import com.rsamadhan.common.ApplicationUtils;
import com.rsamadhan.location.LocationHandler;
import com.rsamadhan.network.callbackrequest.ComplaintCallback;
import com.rsamadhan.network.requests.EducationDomainRequest;
import com.rsamadhan.network.NetworkApi;
import com.rsamadhan.network.response.RequestResponse;

import java.util.ArrayList;
import java.util.Locale;

import retrofit.RetrofitError;

/**
 * Created by prathmeshs on 12-09-2015.
 */
public class SpeechFragment extends DialogFragment {


    private View mView;
    private String mDomainText;
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private LocationHandler mHandler;
    private ProgressBar mProgressBar;

    public SpeechFragment(String domVal){
        mDomainText =domVal;
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
        mProgressBar= (ProgressBar) mView.findViewById(R.id.pb_speech_progress);


        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        mHandler= LocationHandler.getInstance(getActivity());
        promptSpeechInput();
        mHandler.registerLocation();
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
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, localeSelected);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
            showProgressHideText();

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getActivity(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgressHideText() {

        mProgressBar.setVisibility(View.VISIBLE);
        txtSpeechInput.setVisibility(View.GONE);
    }
    private void showText(){
        txtSpeechInput.setVisibility(View.VISIBLE);
    }
    private void hideProgress(){
        mProgressBar.setVisibility(View.GONE);
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

        showText();

        switch (requestCode) {

            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    final ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtSpeechInput.setText(result.get(0));

                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            createComplaint(result.get(0));
                        }
                    });


                }
                break;
            }
        }
    }

    private void createComplaint(String s) {



        EducationDomainRequest request =new EducationDomainRequest();
        request.setComplaintContent(s);
        request.setDomain(mDomainText);
        Location location=mHandler.getUpdatedLocation();
        request.setMobileNumber(PreferenceManager.getInstance(getActivity()).getLoginId());
        if(location!=null){
            request.setLatitude(location.getLatitude() + "");
            request.setLongitude(location.getLongitude() + "");
        }


        NetworkApi api = new NetworkApi();
        api.registerComplaint(request, new ComplaintCallback() {
            @Override
            public void complaintSuccess(RequestResponse o) {
                hideProgress();
                dismiss();
                Toast.makeText(getActivity(), getString(R.string.success_problem_submit), Toast.LENGTH_LONG).show();
            }

            @Override
            public void complaintFail(RetrofitError error) {
                hideProgress();
                dismiss();
                Toast.makeText(getActivity(), getString(R.string.faile_problem_submit), Toast.LENGTH_LONG).show();
            }
        });

    }

}
