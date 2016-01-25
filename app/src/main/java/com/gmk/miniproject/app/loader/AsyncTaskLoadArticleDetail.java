package com.gmk.miniproject.app.loader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;

import com.gmk.miniproject.app.model.ArticleModel;
import com.gmk.miniproject.app.service.DataHelper;
import com.gmk.miniproject.app.service.ServiceHelper;
import com.gmk.miniproject.app.util.Utils;

/**
 * Created by admin on 1/24/16 AD.
 */
public class AsyncTaskLoadArticleDetail extends AsyncTask<Integer, Integer, String> implements DialogInterface.OnDismissListener{
    private Activity activity;
    ProgressDialog dialog;
    boolean statusConnect = true;
    OnTaskComplete onTaskComplete;
    ArticleModel articleModelClick;

    public AsyncTaskLoadArticleDetail(Activity activity, OnTaskComplete onTaskComplete,ArticleModel articleModelClick) {
        this.activity = activity;
        this.onTaskComplete = onTaskComplete;
        this.articleModelClick = articleModelClick;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, "","Loading...", true);
        dialog.setCancelable(true);
        dialog.setOnDismissListener(this);
    }

    @Override
    protected String doInBackground(Integer... status) {

        if (Utils.isOnline(activity)) {
            statusConnect = true;

            //Load Article Detail
            DataHelper.getInstance().setArticleModelClick(ServiceHelper.getInstance().getArticleDetail(articleModelClick.getArticle_id()));
        }
        else
        {
            statusConnect = false;
        }

        return "";
    }

    @Override
    protected void onPostExecute(String result) {
        if (dialog != null) {
            dialog.dismiss();
        }

        if(statusConnect)
        {
            this.onTaskComplete.onComplete();
        }
        else
        {
            //No Internet
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
            alertDialogBuilder.setMessage("Please check your internet connection.");
            alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Close App
                    activity.finish();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        //Toast.makeText(this.activity,"Size:"+DataHelper.getInstance().getArrayListArticleDTO().size(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        cancel(true);
    }
}
