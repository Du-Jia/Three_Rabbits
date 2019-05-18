package com.example.cly.word;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Fragment fragment1=new first_main_fragment();
    Fragment fragment2=new second_main_fragment();
    Fragment fragment3=new second_main_fragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        TextView first=(TextView) findViewById( R.id.first );
        TextView second=(TextView) findViewById( R.id.second );
        TextView three=(TextView) findViewById( R.id.three );
        first.setOnClickListener( this );
        second.setOnClickListener( this );
        three.setOnClickListener( this );
        replaceFragment( new first_main_fragment() );
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.first:
                replaceFragment( fragment1 );
                break;
            case R.id.second:
                replaceFragment( fragment2 );
                break;
            case R.id.three:
                replaceFragment( fragment3 );
                break;
            default:
                break;
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        //Right_Fragment rightFragment=(Right_Fragment)getSupportFragmentManager().findFragmentById( R.id.fragment_right_ ); //从布局文件当中获取碎片
        FragmentTransaction transaction=fragmentManager.beginTransaction();//开启事件
        transaction.replace( R.id.main_layout,fragment );
        transaction.addToBackStack( null );//设置可返回
        transaction.commit();//提交事件
    }
}
