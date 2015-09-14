package com.rsamadhan.comments;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsamadhan.R;
import com.rsamadhan.common.PreferenceManager;
import com.rsamadhan.network.response.ActivityCommentsResult;
import com.rsamadhan.network.response.Results;

import java.util.List;


public class ActivityCommentsListAdapter extends RecyclerView.Adapter {

    private List<ActivityCommentsResult> mActivityCommentsResults;
    private Context mContext;
    private String mPhoneNumber;

    public ActivityCommentsListAdapter(List<ActivityCommentsResult> activityCommentsResults, Context context){
        mActivityCommentsResults=activityCommentsResults;
        mContext=context;
        mPhoneNumber= PreferenceManager.getInstance(mContext).getLoginId();
        formatPhoneNumber();
    }

    private void formatPhoneNumber() {
        String number=mPhoneNumber.substring(mPhoneNumber.length()-5,mPhoneNumber.length()-1);
        mPhoneNumber="XXXXX"+number;
    }

    public class CarViewHolder extends RecyclerView.ViewHolder{

        public CardView cardView;
        public TextView commentView;
        public TextView phoneView;
        public TextView createDate;


        public CarViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
            commentView= (TextView) itemView.findViewById(R.id.tv_comment_view);
            phoneView= (TextView) itemView.findViewById(R.id.tv_phone_view);
            createDate= (TextView) itemView.findViewById(R.id.tv_createDate_view);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.comment_row_view, parent,false);
        CarViewHolder holder=new CarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CarViewHolder carViewHolder= (CarViewHolder) holder;
        carViewHolder.commentView.setText(mActivityCommentsResults.get(position).getComment());
        carViewHolder.phoneView.setText(mPhoneNumber);
     //   carViewHolder.createDate.setText(getConvertedDate(mActivityCommentsResults.get(position).));
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public int getItemCount() {
        return mActivityCommentsResults.size();
    }
}
