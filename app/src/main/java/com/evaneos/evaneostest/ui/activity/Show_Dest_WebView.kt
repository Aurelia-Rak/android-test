package com.evaneos.evaneostest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.evaneos.evaneostest.R
import com.evaneos.evaneostest.viewmodels.Show_Dest_WebViewModel

class Show_Dest_WebView : AppCompatActivity() {

    private var webView: WebView? = null
    private var destUrl : String =""
    private lateinit var mDest_WebView: Show_Dest_WebViewModel
    private var destName: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_dest_web_view)

        val destNameToolbar: Toolbar = findViewById(R.id.WebView_Toolbar)
        setSupportActionBar(destNameToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        destNameToolbar.setNavigationOnClickListener {

            val intent = Intent(this@Show_Dest_WebView, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val id = intent.getLongExtra("destinationid", 0)

        webView = findViewById(R.id.destWebview)
        mDest_WebView = ViewModelProvider(this)[Show_Dest_WebViewModel::class.java]

        mDest_WebView.getDestinationsDetails(id).observe(this) {
            destUrl = it.url
            supportActionBar!!.title = it.name


            webView!!.webViewClient = WebViewClient()
            val webSettings = webView!!.settings
            webSettings.javaScriptEnabled = true
            webView!!.loadUrl(destUrl)
        }

    }


    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
}