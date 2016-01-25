package com.gmk.miniproject.app.service;

import com.gmk.miniproject.app.model.ArticleModel;

import java.util.ArrayList;

/**
 * Created by admin on 1/24/16 AD.
 */
public class DataHelper {
    private static DataHelper ourInstance = new DataHelper();

    public static DataHelper getInstance() {
        return ourInstance;
    }

    private ArrayList<ArticleModel> arrayListArticleDTO;
    private ArticleModel articleModelClick;

    private DataHelper() {

        //init
        arrayListArticleDTO = new ArrayList<ArticleModel>();
    }

    public ArrayList<ArticleModel> getArrayListArticleDTO() {
        return arrayListArticleDTO;
    }

    public void setArrayListArticleDTO(ArrayList<ArticleModel> arrayListArticleDTO) {
        this.arrayListArticleDTO = arrayListArticleDTO;
    }

    public ArticleModel getArticleModelClick() {
        return articleModelClick;
    }

    public void setArticleModelClick(ArticleModel articleModelClick) {
        this.articleModelClick = articleModelClick;
    }
}
