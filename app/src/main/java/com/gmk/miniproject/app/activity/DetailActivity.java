package com.gmk.miniproject.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmk.miniproject.app.R;
import com.gmk.miniproject.app.loader.AsyncTaskLoadArticleDetail;
import com.gmk.miniproject.app.loader.OnTaskComplete;
import com.gmk.miniproject.app.model.ArticleModel;
import com.gmk.miniproject.app.service.DataHelper;
import com.gmk.miniproject.app.service.ServiceHelper;
import com.gmk.miniproject.app.util.Utils;
import com.squareup.picasso.Picasso;

public class DetailActivity extends Activity implements OnTaskComplete,View.OnClickListener{

    private TextView tvTitle;
    private TextView tvDetail;
    private ImageView imvContent;
    private Button buttonMap;

    public static ArticleModel articleModelClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide TitleBar
        hideTitleBar();

        setContentView(R.layout.activity_detail);

        //Init View
        init();

        //Load Detail Content
        AsyncTaskLoadArticleDetail asyncTaskLoadArticleDetail = new AsyncTaskLoadArticleDetail(this,this,articleModelClick);
        asyncTaskLoadArticleDetail.execute();
    }

    private void init() {
        tvTitle = (TextView)findViewById(R.id.tvTitle);
        tvDetail = (TextView)findViewById(R.id.tvDetail);
        imvContent = (ImageView)findViewById(R.id.imvDetail);
        buttonMap = (Button)findViewById(R.id.btnMap);
        buttonMap.setOnClickListener(this);
    }

    private void hideTitleBar() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

        //Complete Load
        articleModelClick = DataHelper.getInstance().getArticleModelClick();
        tvTitle.setText(articleModelClick.getTitle());
        tvDetail.setText(Html.fromHtml(articleModelClick.getContent()));

        //Check Image Path
        if(Utils.checkStringNullEmpty(articleModelClick.getImage())) {
            //Load Image
            Picasso.with(this).load(ServiceHelper.BASE_URL + articleModelClick.getImage()).into(imvContent);
        }
        else
        {
            imvContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == buttonMap)
        {
            Intent intent = new Intent(this,MapsActivity.class);
            startActivity(intent);
        }
    }
}
