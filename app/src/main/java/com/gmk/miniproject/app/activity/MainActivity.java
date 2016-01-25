package com.gmk.miniproject.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gmk.miniproject.app.R;
import com.gmk.miniproject.app.adapter.ArticleAdapter;
import com.gmk.miniproject.app.loader.AsyncTaskLoadArticle;
import com.gmk.miniproject.app.loader.OnTaskComplete;
import com.gmk.miniproject.app.service.DataHelper;

public class MainActivity extends Activity implements OnTaskComplete,AdapterView.OnItemClickListener{

    public ListView listView;
    public ArticleAdapter articleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        hideTitleBar();

        /* Set Content layout */
        setContentView(R.layout.activity_main);

        //init ListView
        listView = (ListView)findViewById(R.id.listViewArticle);

        //Load Article
        AsyncTaskLoadArticle asynTaskLoadArticle = new AsyncTaskLoadArticle(this,this);
        asynTaskLoadArticle.execute();

//        Button button = (Button)findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Sample call Service
//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//
//                //OK List Article
//                ArrayList<ArticleModel> arrayArticles = ServiceHelper.getInstance().getListArticles();
//                Toast.makeText(MainActivity.this, "arrayArticles.size:" + arrayArticles.size(), Toast.LENGTH_SHORT).show();
//
//                //OK Get Detail
//                //ArticleModel articleModel = serviceHelper.getArticleDetail("244425");
//                //Toast.makeText(MainActivity.this, "arrayArticles.content:" + articleModel.getContent(), Toast.LENGTH_LONG).show();
//                //Log.d("arrayArticles.size:", arrayArticles.size()+"");
//            }
//        });
    }

    private void hideTitleBar() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete() {

        //Load Complete
        articleAdapter = new ArticleAdapter(MainActivity.this, DataHelper.getInstance().getArrayListArticleDTO());
        listView.setAdapter(articleAdapter);
        listView.setOnItemClickListener(this);

        //Toast.makeText(this, "Main Activity Size:" + DataHelper.getInstance().getArrayListArticleDTO().size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Call Activity
        DetailActivity.articleModelClick = DataHelper.getInstance().getArrayListArticleDTO().get(position);
        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "Title" + articleClick.getImage(), Toast.LENGTH_SHORT).show();
    }
}
