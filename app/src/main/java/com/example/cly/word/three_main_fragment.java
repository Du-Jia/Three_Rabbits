package com.example.cly.word;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class three_main_fragment extends Fragment implements View.OnClickListener{

    TextView collect;
    TextView addition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.three_main, container, false );
        collect=(TextView) view.findViewById( R.id.collect );
        addition=(TextView) view.findViewById( R.id.addition );
        addition.setOnClickListener( this );
        collect.setOnClickListener( this );
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.collect:
                CollectActivity.actionStart(getContext());
                break;
            case R.id.addition:
                InsertDialog();
                break;
            default:
                break;
        }
    }
    private void InsertDialog(){
        final TableLayout tableLayout=(TableLayout)getLayoutInflater().inflate(R.layout.insert,null);
        new AlertDialog.Builder(getContext())
                .setTitle( "新增文章" )
                .setView(tableLayout)
                .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String strId=((EditText)tableLayout.findViewById( R.id.editid )).getText().toString();
                        String strTitle=((EditText)tableLayout.findViewById( R.id.edittitle )).getText().toString();
                        String strContent=((EditText)tableLayout.findViewById(R.id.editcontent)).getText().toString();
                        String strtype=((EditText)tableLayout.findViewById(R.id.edittype)).getText().toString();
                        Insert(strId,strTitle,strContent,strtype);
                    }
                } )
                .setNegativeButton( "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                } )
                .create()
                .show();
    }
    private void Insert(String strid, String strtitle, String strcontent,String strtype) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
        int strids=Integer.parseInt( strid );
        int strtypes=Integer.parseInt( strtype );
        String path = "http://192.168.43.93:8080/Web/Test3";
        HttpUtil.addition(strids,strtitle,strcontent,strtypes,path,new first_first_fragment.HttpCallbackListener(){
            @Override
            public void onFinish(String response) {//成功时的处理方法
                Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onError(Exception e){//失败时的处理方法

            }
        });
    }
}
