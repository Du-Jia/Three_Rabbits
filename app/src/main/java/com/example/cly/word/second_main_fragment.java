package com.example.cly.word;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class second_main_fragment extends Fragment {

    GeneralDBHelper mDBHelper;
    WordAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.second_main, container, false );
        RecyclerView wordTitleRecyclerView = (RecyclerView)view.findViewById( R.id.word_title_recycler_view );
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        wordTitleRecyclerView.setLayoutManager(layoutManager);
        adapter=new WordAdapter(getNews());
        wordTitleRecyclerView.setAdapter(adapter);
        //setHasOptionsMenu(true);
        registerForContextMenu( wordTitleRecyclerView );//注册上下文菜单
        mDBHelper=new GeneralDBHelper( getActivity());
        return view;
    }
    class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder>{
        private List<News> mWordList;
        private int mPosition = -1;

        public int getPosition() {
            return mPosition;
        }
        public WordAdapter(List<News> mWordList){
            this.mWordList=mWordList;
        }
        public void removeItem(int position) {
            mWordList.remove(position);
            notifyDataSetChanged();
        }
        public News getItem(int position){
            return mWordList.get( position );
        }
        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newstitleText;
            TextView newstimeText;
            public ViewHolder(View view){
                super(view);
                newstitleText=(TextView)view.findViewById( R.id.news_title );
                newstimeText=(TextView)view.findViewById( R.id.news_time );
            }
        }
        public void update(List<News> mWordList){
            this.mWordList=mWordList;
            notifyDataSetChanged();
        }
        public WordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view=LayoutInflater.from( parent.getContext() ).inflate( R.layout.news_item,parent,false );
            final WordAdapter.ViewHolder holder=new WordAdapter.ViewHolder( view );
            view.setOnClickListener( new View.OnClickListener(){
                public void onClick(View v){
                    News word=mWordList.get( holder.getAdapterPosition() );
                    //WordContentActivity.actionStart( getActivity(),word.getName(),word.getMeaning(),word.getSample(),word.getUpdate() );
                }
            } );
            return holder;

        }

        @Override
        public void onBindViewHolder(final WordAdapter.ViewHolder holder, final int position) {
            News news=mWordList.get(position);
            holder.newstitleText.setText(news.getNews_title());
            holder.newstimeText.setText(news.getNews_time());
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mPosition =holder.getAdapterPosition();
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mWordList.size();
        }

    }
    private List<News> getNews() {
        List<News> wordList = new ArrayList<>();
        GeneralDBHelper dbHelpermDBHelper = new GeneralDBHelper(getActivity());
        SQLiteDatabase db = dbHelpermDBHelper.getWritableDatabase();
        Cursor c;
        c = db.query(DataBase.TABLE_NAME1, null, "collect = 'yes'", null, null, null,null);
        if (c.moveToFirst()) {
            do {
                int id = c.getInt(c.getColumnIndex("news_id"));
                String news_title = c.getString(c.getColumnIndex("news_title"));
                String news_time = c.getString(c.getColumnIndex("news_time"));
                News news = new News();
                news.setNews_id(id);
                news.setNews_title(news_title);
                news.setNews_time(news_time);
                wordList.add(news);
            } while (c.moveToNext());
        }
        return wordList;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        new MenuInflater(getActivity()).inflate(R.menu.menu_context_test, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        News news;
        int id;
        switch(item.getItemId()){
            case R.id.collect:
                news=adapter.getItem(adapter.getPosition());
                id=news.getNews_id();
                changecollect(id,"no");
                adapter.update(getNews());
                break;
        }
        return true;
    }
    private void changecollect(int id,String collect){
        SQLiteDatabase db=mDBHelper.getReadableDatabase();
        String sql="update "+DataBase.TABLE_NAME1+" set collect=? where news_id=?";
        String t=id+"";
        db.execSQL( sql,new String[]{collect,t} );
    }
}
