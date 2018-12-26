package com.bawei.www.myapplication1225.Noto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bawei.www.myapplication1225.R;



public class NotoActivity extends AppCompatActivity {

    @MineAnnotation(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noto);

        FindViewId.findById(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotoActivity.this,"toast",Toast.LENGTH_SHORT).show();
            }
        });

    }


}
