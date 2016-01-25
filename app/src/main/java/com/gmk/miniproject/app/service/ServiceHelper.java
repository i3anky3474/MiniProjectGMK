package com.gmk.miniproject.app.service;

import com.gmk.miniproject.app.model.ArticleModel;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by admin on 1/23/16 AD.
 */
public class ServiceHelper {

    private OkHttpClient okHttpClient;
    public static String BASE_URL = "http://mobiletest.gomeekisystems.com";
    private String URL_GetListArticles = "http://mobiletest.gomeekisystems.com/category_sport.json";
    private String URL_GetArticaleDetail = "http://mobiletest.gomeekisystems.com/";

    private static ServiceHelper ourInstance = new ServiceHelper();

    public static ServiceHelper getInstance() {
        return ourInstance;
    }

    private ServiceHelper() {
        okHttpClient = new OkHttpClient();
    }

    public String getStringURL(String url) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return response.body().string();

//        //Read Http
//        Request.Builder builder = new Request.Builder();
//        Request request = builder.url(url).build();
//
//        try {
//            Response response = okHttpClient.newCall(request).execute();
//            if (response.isSuccessful()) {
//                return response.body().string();
//            } else {
//                //return "Not Success - code : " + response.code();
//                return null;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            //return "Error - " + e.getMessage();
//            return null;
//        }

        //Get Http
        Request.Builder builder = new Request.Builder();
        Request request = builder.url(url).build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArticleModel getArticleDetail(String article_id) {
        ArticleModel articleModel = new ArticleModel();
        try {
            String contentString = getStringURL(URL_GetArticaleDetail+article_id+".json");
            JSONObject jsonObj = new JSONObject(contentString);
            if (jsonObj.has("status")) {
                JSONObject jsonStatus = jsonObj.getJSONObject("status");
                String code = jsonStatus.getString("code");
                if(code.equals("200"))
                {
                    if (jsonObj.has("data")) {
                        JSONObject jsonArticle = (JSONObject)jsonObj.get("data");
                        if (jsonArticle.has("content")) {
                            String content = jsonArticle.getString("content");
                            articleModel.setContent(content);
                        }
                        if (jsonArticle.has("id")) {
                            String id = jsonArticle.getString("id");
                            articleModel.setArticle_id(id);
                        }
                        if (jsonArticle.has("coordinates")) {
                            JSONObject jsonCoordinates = jsonArticle.getJSONObject("coordinates");
                            if (jsonCoordinates.has("lat")) {
                                String lat = jsonCoordinates.getString("lat");
                                articleModel.setLat(lat);
                            }
                            if (jsonCoordinates.has("long")) {
                                String lon = jsonCoordinates.getString("long");
                                articleModel.setLon(lon);
                            }
                        }
                        if (jsonArticle.has("image")) {
                            String image = jsonArticle.getString("image");
                            articleModel.setImage(image);
                        }
                        if (jsonArticle.has("title")) {
                            String title = jsonArticle.getString("title");
                            articleModel.setTitle(title);
                        }
                        if (jsonArticle.has("type")) {
                            String type = jsonArticle.getString("type");
                            articleModel.setType(type);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articleModel;
    }

    public ArrayList<ArticleModel> getListArticles()
    {
        //Sample
        ArrayList<ArticleModel> arrayArticleModel = new ArrayList<ArticleModel>();

        JSONObject jsonObj;
        try {
            String content = getStringURL(URL_GetListArticles);
            jsonObj = new JSONObject(content);
            if (jsonObj.has("status")) {
                JSONObject jsonStatus = (JSONObject)jsonObj.get("status");
                String code = jsonStatus.getString("code");
                if(code.equals("200"))
                {
                    if (jsonObj.has("data")) {
                        JSONObject jsonData = (JSONObject) jsonObj.get("data");
                        if (jsonData.has("articles")) {
                            JSONArray jsonArticles = jsonData.getJSONArray("articles");
                            if (jsonArticles != null) {
                                //Log.d("jsonArticles:", jsonArticles.toString());
                                for(int i=0;i<jsonArticles.length();i++)
                                {
                                    ArticleModel articleModel = new ArticleModel();
                                    JSONObject jsonArticle = (JSONObject)jsonArticles.get(i);
                                    if(jsonArticle!=null) {
                                        if (jsonArticle.has("id")) {
                                            String id = jsonArticle.getString("id");
                                            articleModel.setArticle_id(id);
                                        }
                                        if (jsonArticle.has("date")) {
                                            String date = jsonArticle.getString("date");
                                            articleModel.setDate(date);
                                        }
                                        if (jsonArticle.has("title")) {
                                            String title = jsonArticle.getString("title");
                                            articleModel.setTitle(title);
                                        }
                                        if (jsonArticle.has("short_description")) {
                                            String short_description = jsonArticle.getString("short_description");
                                            articleModel.setShort_description(short_description);
                                        }
                                        if (jsonArticle.has("image")) {
                                            String image = jsonArticle.getString("image");
                                            articleModel.setImage(image);
                                        }
                                        arrayArticleModel.add(articleModel);
                                    }
                                }
                            } else {
                                //Log.d("jsonArticles:", "NULL");
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayArticleModel;
    }

}
