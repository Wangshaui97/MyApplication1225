package com.bawei.www.day2dome.adpter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.www.day2dome.R;
import com.bawei.www.day2dome.bean.ShopBean;
import com.bawei.www.day2dome.msgeventbus.MsgEnent;
import com.bawei.www.day2dome.view.DatailActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class RecycviewAdpter extends RecyclerView.Adapter<RecycviewAdpter.ViewHolder> {


    private Context context;
    private List<ShopBean.DataBean> mlist;
    private int index=0;

    public RecycviewAdpter(Context context) {
        this.context = context;
        mlist = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder vh;
        if(index==0){
            View view = View.inflate(context, R.layout.rview_item, null);
            vh = new ViewHolder(view);
            return vh;
        }else {
            View view = View.inflate(context, R.layout.rview_item_g, null);
            vh = new ViewHolder(view);
            return vh;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
       if(index==0){
           viewHolder.itemTitle.setText(mlist.get(i).getTitle());
           String images = mlist.get(i).getImages();
           Pattern pen = Pattern.compile("\\|");
           String[] split = pen.split(images);
           Uri uri = Uri.parse(split[0]);
           viewHolder.itemImg.setImageURI(uri);
       }else{
           viewHolder.gitemTitle.setText(mlist.get(i).getTitle());
           String images = mlist.get(i).getImages();
           Pattern pen = Pattern.compile("\\|");
           String[] split = pen.split(images);
           Uri uri = Uri.parse(split[0]);
           viewHolder.gitemImg.setImageURI(uri);
       }


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DatailActivity.class));
                EventBus.getDefault().postSticky(new MsgEnent(i));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public void setData(List<ShopBean.DataBean> shopBeanData) {
        this.mlist = shopBeanData;
        notifyDataSetChanged();
    }

    public void setGData(List<ShopBean.DataBean> shopBeanData, int i) {
        this.mlist=shopBeanData;
        this.index=i;
        notifyDataSetChanged();
    }

    public void setLData(List<ShopBean.DataBean> shopBeanData, int i) {
        this.mlist=shopBeanData;
        this.index=i;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView itemImg,gitemImg;
        TextView itemTitle,gitemTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           if(index==0){
               itemImg = itemView.findViewById(R.id.item_img);
               itemTitle = itemView.findViewById(R.id.item_title);
           }else {
               gitemImg = itemView.findViewById(R.id.gitem_img);
               gitemTitle = itemView.findViewById(R.id.gitem_title);
           }


        }
    }
}
