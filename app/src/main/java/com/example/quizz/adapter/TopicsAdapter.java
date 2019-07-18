package com.example.quizz.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.quizz.R;

import java.util.ArrayList;

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.TopicCustomViewHolder> {

    Context mContext;
    ArrayList<String> mArrayList;
    ArrayList<GradientDrawable> mGradientDrawables;

    public void addTopic(String topic) {
        mArrayList.add(topic);
    }

    public TopicsAdapter(Context context, ArrayList<String> arrayList) {
        mArrayList = arrayList;
        mContext =  context;
        mGradientDrawables = new ArrayList<>();

        fillGradientList(mContext);
    }

    private void fillGradientList(Context context) {
        mGradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_1_start),ContextCompat.getColor(context,R.color.gradient_1_end)));
        mGradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_2_start),ContextCompat.getColor(context,R.color.gradient_2_end)));
        mGradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_3_start),ContextCompat.getColor(context,R.color.gradient_3_end)));
        mGradientDrawables.add(getTempGradientDrawable(ContextCompat.getColor(context,R.color.gradient_4_start),ContextCompat.getColor(context,R.color.gradient_4_end)));
    }

    private GradientDrawable getTempGradientDrawable(int startColor, int endColor)
    {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[]{startColor, endColor});
        drawable.setDither(true);
        drawable.setGradientCenter(drawable.getIntrinsicWidth()/8 , drawable.getIntrinsicHeight()/2);
        drawable.setCornerRadius(20);
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setUseLevel(true);
        return drawable;
    }

    @NonNull
    @Override
    public TopicCustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.topic_item_list_view,viewGroup,false);
        return new TopicCustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopicCustomViewHolder topicCustomViewHolder, int position) {
        String topicName = mArrayList.get(position);
        topicCustomViewHolder.mTextView.setText(topicName);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            topicCustomViewHolder.mTopicRelativeLayout.setBackground(mGradientDrawables.get(position));
        }
        else
        {
            topicCustomViewHolder.mTopicRelativeLayout.setBackgroundDrawable(mGradientDrawables.get(position));
        }
        topicCustomViewHolder.mImageView.setImageResource(R.drawable.topics_list_icon);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class TopicCustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextView;
        private RelativeLayout mTopicRelativeLayout;

        public TopicCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.topicTextView);
            mImageView = (ImageView) itemView.findViewById(R.id.topicImageView);
            mTopicRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.topicRelativeLayout);
        }
    }
}
