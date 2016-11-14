package com.liuguilin.latenight.fragment;
/*
 *  项目名：  LateNight 
 *  包名：    com.liuguilin.latenight.fragment
 *  文件名:   PictureFragment
 *  创建者:   LGL
 *  创建时间:  2016/11/11 13:07
 *  描述：    图片
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.liuguilin.gankclient.R;
import com.liuguilin.latenight.adapter.PictureAdapter;
import com.liuguilin.latenight.entity.Constants;
import com.liuguilin.latenight.entity.PictureData;
import com.liuguilin.latenight.util.L;
import com.liuguilin.latenight.util.PicassoUtils;
import com.liuguilin.latenight.view.CustomDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PictureFragment extends Fragment {

    private int page = 1;
    private ListView mListView;
    private ProgressBar progressBar;
    private List<PictureData> mList = new ArrayList<>();
    private List<String> mListImg = new ArrayList<>();
    private CustomDialog dialog;

    private ImageView iv_picture;
    //支持缩放
    private PhotoViewAttacher mAttacher;
    //屏幕宽高
    private int width, height;
    private WindowManager wm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, null);
        findView(view);
        return view;
    }

    //初始化View
    private void findView(View view) {

        wm = (WindowManager) getActivity().getSystemService(getActivity().WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();

        dialog = new CustomDialog(getActivity(), 0, 0, R.layout.dialog_picture, R.style.Theme_dialog, Gravity.CENTER, R.style.pop_anim_style);
        iv_picture = (ImageView) dialog.findViewById(R.id.iv_picture);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        mListView = (ListView) view.findViewById(R.id.mListView);

        getPic();

        //点击查看大图

    }

    private void getPic() {
        HttpParams params = new HttpParams();
        params.putHeaders("apikey", Constants.BAIDU_KEY);
        RxVolley.post(Constants.JOKE_PIC_URL + page, params, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                L.i("Pic:" + t);
                parsingJson(t);
            }
        });
    }

    private void parsingJson(String t) {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonBody = jsonObject.getJSONObject("showapi_res_body");
            JSONArray jsonArray = jsonBody.getJSONArray("contentlist");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = (JSONObject) jsonArray.get(i);
                String title = json.getString("title");
                String ct = json.getString("ct");
                String img = json.getString("img");

                PictureData data = new PictureData();
                data.setTitle(title);
                data.setTime(ct);
                data.setImg(img);
                mListImg.add(img);

                mList.add(data);
            }

            PictureAdapter adapter = new PictureAdapter(getActivity(), mList);
            mListView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);

            adapter.setOnPClickListener(new PictureAdapter.onPClickListener() {
                @Override
                public void onPClickListener(View view, int postion) {
                    L.i("img:" + mListImg.get(postion));
                    //Picasso.with(getActivity()).load(mListImg.get(postion)).into(iv_picture);
                    PicassoUtils.loadImageViewSize(getActivity(), mListImg.get(postion), width, height, iv_picture);
                    mAttacher = new PhotoViewAttacher(iv_picture);
                    mAttacher.update();
                    dialog.show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
