package procressbar.zk.com.mapleadtest.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import procressbar.zk.com.mapleadtest.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView detailsWebView;
    private String mUrl;
    private Handler handler;
    private ProgressDialog pd;
    private String mTitle;


    public static void launch(Activity activity, String url, String title) {

        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mUrl = this.getIntent().getStringExtra("url");
        mTitle = this.getIntent().getStringExtra("title");
        detailsWebView = (WebView) findViewById(R.id.web_wv);
        detailsWebView.getSettings().setJavaScriptEnabled(true);
        detailsWebView.getSettings().setDomStorageEnabled(true);
        detailsWebView.requestFocus(View.FOCUS_DOWN);
        detailsWebView.getSettings().setUserAgentString("User-Agent");

        detailsWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        // 设置web视图客户端
        detailsWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {// 载入进度改变而触发

                if (progress == 100) {
                    handler.sendEmptyMessage(1);// 如果全部载入,隐藏进度对话框 }
                    detailsWebView.setVisibility(View.VISIBLE);
                }
                super.onProgressChanged(view, progress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }
        });

        detailsWebView.setVisibility(View.GONE);
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("数据载入中，请稍候！");


        handler = new Handler() {

            public void handleMessage(Message msg) {// 定义一个Handler，用于处理下载线程与UI间通讯
                super.handleMessage(msg);
                if (!Thread.currentThread().isInterrupted()) {
                    switch (msg.what) {
                        case 0:
                            pd.show();// 显示进度对话框
                            break;
                        case 1:
                            pd.hide();// 隐藏进度对话框，不可使用dismiss()、cancel(),否则再次调用show()时，显示的对话框小圆圈不会动。
                            break;
                    }
                }

            }

        };
        loadurl(detailsWebView, mUrl);
    }


    public void loadurl(final WebView view, final String url) {
        handler.post(new Runnable() {

            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                view.loadUrl(url);// 载入网页
            }
        });
    }


}
