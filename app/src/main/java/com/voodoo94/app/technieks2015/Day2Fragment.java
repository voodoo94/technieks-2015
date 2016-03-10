package com.voodoo94.app.technieks2015;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreesha on 3/5/2015.
 */
public class Day2Fragment extends android.support.v4.app.Fragment {
    private ProgressDialog mProgressDialog;

    public String[] mDataName;
    public String[] mDataUrl;
    public String[] mDataDesc;
    public String[] mImageUrl;

    public String mJsonString;

    private ListView lv;

    private NetworkUtility mNet;

    public static String url = "http://technieks.com/data/day2.json";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day2, container, false);

        lv = (ListView) view.findViewById(R.id.day2_lv);


        mNet = new NetworkUtility();
        if (!mNet.is_connected(getActivity())) {
            Toast.makeText(getActivity(), "No Internet Connection...", Toast.LENGTH_LONG).show();
        } else {
            new JSONParse().execute();
        }


        return  view;
    }

    private class JSONParse extends AsyncTask<String, Void, Void> {

        @Override
        public void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog = ProgressDialog.show(getActivity(), "Please wait", "Loading...");
        }

        @Override
        public Void doInBackground(String... args) {
            try {
                DefaultHttpClient mHttpClient = new DefaultHttpClient();
                HttpGet mHttpPost = new HttpGet(url);
                HttpResponse mHttpResponse = mHttpClient.execute(mHttpPost);
                HttpEntity mHttpEntity = mHttpResponse.getEntity();
                mJsonString = EntityUtils.toString(mHttpEntity);
                mJsonString = mJsonString.substring(3);
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        public void onPostExecute(Void result) {

            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }

            super.onPostExecute(result);

            Gson gson = new Gson();

            Type mType = new TypeToken<List<JsonFile>>(){}.getType();
            List<JsonFile> details = gson.fromJson(mJsonString, mType);

            Log.d("LOG_TAG", Integer.toString(details.toArray().length));

            mDataName = new String[details.toArray().length];
            mImageUrl = new String[details.toArray().length];
            mDataUrl =  new String[details.toArray().length];
            mDataDesc = new String[details.toArray().length];


            for (int i = 0; i < details.size(); i++) {
                final String name = details.get(i).getName();
                final String desc = details.get(i).getDesc();
                final String img_url = details.get(i).getImgUrl();
                final String url = details.get(i).getRegUrl();

                mDataName[i] = name;
                mImageUrl[i] = img_url;
                mDataDesc[i] = desc;
                mDataUrl[i] = url;

                Log.d("LOG_TAG", "frag1: " + name + " " + img_url);
            }


            try {
                List<RowItem> mList = new ArrayList<RowItem>();

                for (int i = 0; i < mDataName.length; i++) {
                    RowItem mItem = new RowItem(mDataName[i], mImageUrl[i]);
                    mList.add(mItem);
                }

                CustomAdapter mCustomAdapter = new CustomAdapter(getActivity(), mList, R.layout.fragment_day2);
                lv.setAdapter(mCustomAdapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent mEventIntent = new Intent(getActivity(), EventDetail.class);

                        Bundle mBundle = new Bundle();

                        mBundle.putString("name", mDataName[position]);
                        mBundle.putString("url", mDataUrl[position]);
                        mBundle.putString("imgUrl", mImageUrl[position]);
                        mBundle.putString("desc", mDataDesc[position]);

                        mEventIntent.putExtras(mBundle);

                        startActivity(mEventIntent);
                    }
                });
            } catch (Exception e) {
                ;
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
