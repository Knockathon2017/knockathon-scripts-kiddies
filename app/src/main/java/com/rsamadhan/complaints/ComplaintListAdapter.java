package com.rsamadhan.complaints;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsamadhan.R;
import com.rsamadhan.comments.CommentsActivity;
import com.rsamadhan.network.response.Results;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prathmesh on 12-09-2015.
 */
public class ComplaintListAdapter extends RecyclerView.Adapter {

    private List<Results> mComplainList;
    private Context mContext;

    public ComplaintListAdapter(List<Results> complainList,Context context){
        if(complainList==null){
            mComplainList=new ArrayList<>();
        }else{
            mComplainList =complainList;
        }
        mContext=context;
    }

    public void updateList(ArrayList<Results> results) {
        if(results!=null){
            mComplainList=results;
        }
    }

    public class CarViewHolder extends RecyclerView.ViewHolder{

        public TextView cardDetails;
        public TextView cardHead;
        public CarViewHolder(View itemView) {
            super(itemView);
            cardDetails= (TextView) itemView.findViewById(R.id.tv_cv_detail);
            cardHead= (TextView) itemView.findViewById(R.id.tv_cv_head);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.complaint_row, parent,false);
        CarViewHolder holder=new CarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final CarViewHolder carViewHolder= (CarViewHolder) holder;
        carViewHolder.cardDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCommentsPage(carViewHolder.cardDetails.getText().toString());
            }
        });
        carViewHolder.cardHead.setText(mComplainList.get(position).getActivity_title());
        carViewHolder.cardDetails.setText(mComplainList.get(position).getActivity_description());
        if(mComplainList.get(position).getStatus().equals("Close")){
            carViewHolder.cardDetails.setTextColor(Color.RED);
        }else{
            carViewHolder.cardDetails.setTextColor(Color.GREEN);
        }

    }
    private void launchCommentsPage(String v) {

        Intent intent=new Intent(mContext, CommentsActivity.class);
        intent.putExtra(CommentsActivity.COMMENT_HEAD, v);
        mContext.startActivity(intent);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public int getItemCount() {
        return mComplainList.size();
    }
}

