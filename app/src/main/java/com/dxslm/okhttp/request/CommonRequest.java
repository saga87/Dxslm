package com.dxslm.okhttp.request;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author vision
 * @function build the request
 */
public class CommonRequest {
    /**
     * create the key-value Request
     * 的Post请求
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params) {
        return createPostRequest(url, params, null);
    }

    /**可以带请求头的Post请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params, RequestParams headers) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody mFormBody = mFormBodyBuild.build();
        Headers mHeader = mHeaderBuild.build();
        Request request = new Request.Builder().url(url).
                post(mFormBody).
                headers(mHeader)
                .build();
        return request;
    }

    /**
     * ressemble the params to the url
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params) {

        return createGetRequest(url, params, null);
    }

    /**
     * 可以带请求头的Get请求
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params, RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();
        return new Request.Builder().
                url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .headers(mHeader)
                .build();
    }

    /**
     *  get请求Request
     * @return
     */
    public static Request createMonitorRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("&");
        if (params != null && params.hasParams()) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).get().build();
    }

    /**
     * 图片上传请求Request
     * mImgUrls 图片的urls
     * params 请求参数
     * @return
     */
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static Request CommonRequest(String url, List<String> mImgUrls,RequestParams params) {
        // mImgUrls为存放图片的url集合
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < mImgUrls.size(); i++) {
            File f = new File(mImgUrls.get(i));
            if (f != null) {
                builder.addFormDataPart("photo" + i, f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));
            }
        }
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        MultipartBody requestBody = builder.build();

        return new Request.Builder().url(url).post(requestBody).build();
    }
}