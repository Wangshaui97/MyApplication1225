package com.bawei.www.day1.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.www.day1.Apis;
import com.bawei.www.day1.R;
import com.bawei.www.day1.bean.GoodsBean;
import com.bawei.www.day1.preonster.IPresonter;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements IView {

    private IPresonter iPresonter;
    private TextView item_title;
    private Banner baner;
    private List<String> list;
    private SimpleDraweeView draweeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iPresonter = new IPresonter(this);


        iPresonter.setRequest(Apis.URL);


        item_title = findViewById(R.id.item_title);
        baner = findViewById(R.id.baner);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(iPresonter!=null){
            iPresonter.onDistouch();
        }
    }

    @Override
    public void setSuccess(Object data) {
        final GoodsBean goodsBean = (GoodsBean) data;

        item_title.setText(goodsBean.getData().getTitle());
        String images = goodsBean.getData().getImages();
        Pattern pen = Pattern.compile("\\|");
        String[] split = pen.split(images);

        list = new ArrayList<>();
        for (int i = 0; i <split.length ; i++) {
            list.add(split[i].replace("https","http"));
        }

        draweeView = findViewById(R.id.draweeView);
        draweeView.setImageURI(list.get(0));

        baner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);

        baner.setImageLoader(new ImageLoaderInterface<ImageView>() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
               Glide.with(context).load(list.get(0)).into(imageView);

            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView =new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return null;
            }
        });
        baner.setImages(list);
        baner.start();

    }
}
