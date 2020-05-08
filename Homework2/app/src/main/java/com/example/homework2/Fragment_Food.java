package com.example.homework2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Fragment_Food extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private TextView txt,txt2;
    public WebView mWebView;

    public Fragment_Food() {
    }

    public static Fragment_Food newInstance(String param1) {
        Fragment_Food fragment = new Fragment_Food();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)

    {

        View v = inflater.inflate(R.layout.fragment_food, container, false);



        mWebView = (WebView) v.findViewById(R.id.foodweb);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Toast.makeText(getActivity(), "Loading...", Toast.LENGTH_SHORT).show();
        txt = (TextView) v.findViewById(R.id.textFood);
        txt2 = (TextView) v.findViewById(R.id.table);

        Thread t = new Thread(new Runnable() {
            Document doc;
            Elements element;
            String urlMenu;
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://web.archive.org/web/20190406185041/https://aybu.edu.tr/sks/").get();
                    final Element foodTable = doc.select("table").get(2);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String test = foodTable.text();
                            txt2.setText(test);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        t.start();
        return v;
    }

}
