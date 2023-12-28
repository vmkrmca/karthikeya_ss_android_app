package com.mani.demoapplicationone

import android.app.Activity
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity


class DashBoardActivity : Activity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        loadingURLIntoWebView()
    }

    private fun loadingURLIntoWebView() {
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }
        }

        webView.webViewClient = object : WebViewClient() {

            var progressDialog: ProgressDialog? = null

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }

            //Show loader on url load
            override fun onLoadResource(view: WebView?, url: String?) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = ProgressDialog(this@DashBoardActivity)
                    progressDialog!!.setMessage("Loading...")
                    progressDialog!!.show()
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressDialog!!.dismiss()
            }

        }
        webView.loadUrl(resources.getString(R.string.web_url_link))
    }
}
