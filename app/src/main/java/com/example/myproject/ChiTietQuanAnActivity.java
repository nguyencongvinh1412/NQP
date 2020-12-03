package com.example.myproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.annotations.Nullable;

public class ChiTietQuanAnActivity extends AppCompatActivity
{
    TextView txtTenquanan,txtDiaChi,txtThoigianhoatdong,txtTrangthaihoatdong,txtSoThich,txtSoAnh,txtSoBinhLuan,txtShare;
    ImageView ImageAnhquan;
    Restaurant QuanAnModel;
   @Override
    protected void onCreate(@Nullable Bundle saveInstanceState)
    {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.layout_main_chitietquanan);
        Restaurant QuanAnModel  =getIntent().getParcelableExtra("quanan");
        txtTenquanan = (TextView)findViewById(R.id.txtTenquanan);
        txtDiaChi =(TextView)findViewById(R.id.txtDiaChi);
        txtThoigianhoatdong =(TextView)findViewById(R.id.txtThoigianhoatdong);
        txtTrangthaihoatdong=(TextView)findViewById(R.id.txtTrangthaihoatdong);
        txtSoThich = (TextView)findViewById(R.id.txtSoThich);
        txtSoBinhLuan=(TextView)findViewById(R.id.txtSoBinhLuan);
        txtShare=(TextView)findViewById(R.id.txtShare);
        ImageAnhquan =(ImageView)findViewById(R.id.ImageAnhquan);
    }
   // @Override
    protected void OnStart()
    {
        super.onStart();
    }
}
