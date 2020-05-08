package com.example.homework2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Fragment_News extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    TextView txt;
    private Button btn1,btn2,btn3,btn4,btn5;
    public WebView myWebView;
    private String mParam1;

    public Fragment_News() {
    }

    public static Fragment_News newInstance(String param1) {
        Fragment_News fragment = new Fragment_News();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        Toast.makeText(getActivity(),"Loading...", Toast.LENGTH_SHORT).show();
        txt =(TextView) v.findViewById(R.id.contentnews);
        btn1 = (Button)v.findViewById((R.id.btn_news1));
        btn2 = (Button)v.findViewById((R.id.btn_news2));
        btn3 = (Button)v.findViewById((R.id.btn_news3));
        btn4 = (Button)v.findViewById((R.id.btn_news4));
        btn5 = (Button)v.findViewById((R.id.btn_news5));



        myWebView= (WebView)v.findViewById(R.id.newsweb);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);   //to make webview size fit the layout
        webSettings.setUseWideViewPort(true);
        myWebView.getSettings().setMinimumFontSize(40);    //increase the font because default is small

        Thread t = new Thread(new Runnable() {

            String[] loadurls = new String[5];
            Document doc;
            Elements[] elements = new Elements[5];
            String[] title = new  String[5];
            @Override
            public void run() {

                try {
                    doc = Jsoup.connect("http://ybu.edu.tr/muhendislik/bilgisayar/").get();

                    elements[0] = doc.select("a[id=ContentPlaceHolder1_ctl01_rpData_hplink_0]");
                    title[0] = elements[0].attr("title");

                    elements[1] = doc.select("a[id=ContentPlaceHolder1_ctl01_rpData_hplink_1]");
                    title[1] = elements[1].attr("title");

                    elements[2] = doc.select("a[id=ContentPlaceHolder1_ctl01_rpData_hplink_2]");
                    title[2] = elements[2].attr("title");

                    elements[3] = doc.select("a[id=ContentPlaceHolder1_ctl01_rpData_hplink_3]");
                    title[3] = elements[3].attr("title");

                    elements[4] = doc.select("a[id=ContentPlaceHolder1_ctl01_rpData_hplink_4]");
                    title[4] = elements[4].attr("title");

                    for(int k = 0; k<=4; k++)
                    {
                        loadurls[k] = ("http://www.ybu.edu.tr/muhendislik/bilgisayar/")+ elements[k].attr("href");
                    }


                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            btn1.setText(title[0]);
                            btn2.setText(title[1]);
                            btn3.setText(title[2]);
                            btn4.setText(title[3]);
                            btn5.setText(title[4]);
                        }
                    });
                }
                catch (IOException e) {
                    e.printStackTrace();
                }



                btn1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        myWebView.loadUrl(loadurls[0]);


                    }
                });


                btn2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        myWebView.loadUrl(loadurls[1]);

                    }
                });



                btn3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        myWebView.loadUrl(loadurls[2]);

                    }
                });

                btn4.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        myWebView.loadUrl(loadurls[3]);

                    }
                });

                btn5.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        myWebView.loadUrl(loadurls[4]);

                    }
                });



            }
        });
        t.start();

        return v;
    }
}
