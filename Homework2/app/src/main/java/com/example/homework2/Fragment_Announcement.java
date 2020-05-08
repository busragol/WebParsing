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

public class Fragment_Announcement extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txt;
    private Button btn1,btn2,btn3,btn4,btn5,btn6;
    public WebView myWebView;

    public Fragment_Announcement() {
    }
    public static Fragment_Announcement newInstance(String param1, String param2) {
        Fragment_Announcement fragment = new Fragment_Announcement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_announcement, container, false);
        Toast.makeText(getActivity(),"Loading...", Toast.LENGTH_SHORT).show();
        myWebView= (WebView)v.findViewById(R.id.mainweb);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);//if it is false,javascripts are not open or work
        webSettings.setLoadWithOverviewMode(true);//zooms out the content to fit on screen by width
        webSettings.setUseWideViewPort(true);//the value of the width specified in the tag is used
        myWebView.getSettings().setMinimumFontSize(40);//set the minimum font size,the default is 8
        txt =(TextView) v.findViewById(R.id.textAn);

        btn1 = (Button)v.findViewById((R.id.btn_an1));
        btn2 = (Button)v.findViewById((R.id.btn_an2));
        btn3 = (Button)v.findViewById((R.id.btn_an3));
        btn4 = (Button)v.findViewById((R.id.btn_an4));
        btn5 = (Button)v.findViewById((R.id.btn_an5));
        btn6 = (Button)v.findViewById((R.id.btn_an6));

        Thread t = new Thread(new Runnable() {

            String[] loadurls = new String[6];
            Document doc;
            Elements element;
            Elements[] elements = new Elements[6];
            String[] title = new  String[6];

            @Override
            public void run() {
                try {
                    doc = Jsoup.connect("http://ybu.edu.tr/muhendislik/bilgisayar/").get();
                    element = doc.select("div.caContent");

                    elements[0] = element.select("a[id=ContentPlaceHolder1_ctl02_rpData_hplink_0]" );
                    title[0] = elements[0].attr("title");

                    elements[1] = element.select("a[id=ContentPlaceHolder1_ctl02_rpData_hplink_1]");
                    title[1] = elements[1].attr("title");

                    elements[2] = element.select("a[id=ContentPlaceHolder1_ctl02_rpData_hplink_2]");
                    title[2] = elements[2].attr("title");

                    elements[3] = element.select("a[id=ContentPlaceHolder1_ctl02_rpData_hplink_3]");
                    title[3] = elements[3].attr("title");

                    elements[4] = element.select("a[id=ContentPlaceHolder1_ctl02_rpData_hplink_4]");
                    title[4] = elements[4].attr("title");

                    elements[5] = element.select("a[id=ContentPlaceHolder1_ctl02_rpData_hplink_5]");
                    title[5] = elements[5].attr("title");


                    for(int k = 0; k<=5; k++)
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
                            btn6.setText(title[5]);



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

                btn6.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        myWebView.loadUrl(loadurls[5]);

                    }
                });


            }


        });
        t.start();
        return v;


    }

  /*  Button btn1,btn2;
    TextView text1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.fragment_menu,container,false);
        btn1=(Button)view.findViewById(R.id.btn1);
        btn2=(Button)view.findViewById(R.id.btn2);
        text1=(TextView)view.findViewById(R.id.text1);


        return view;


    }*/

}
