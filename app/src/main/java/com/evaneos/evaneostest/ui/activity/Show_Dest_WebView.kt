package com.evaneos.evaneostest.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.evaneos.evaneostest.R
import com.evaneos.evaneostest.viewmodels.Show_Dest_WebViewModel

class Show_Dest_WebView : AppCompatActivity() {

    private var webView: WebView? = null
    private lateinit var mDest_WebView: Show_Dest_WebViewModel
    private lateinit var wv_progressBar: ProgressBar
    private lateinit var error_wv: TextView
    private var destName: String = ""
    private var destNameToolbar: Toolbar? = null
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_dest_web_view)


        initData()
        retrieveDataFromIntent()
        toolBarAction()

        mDest_WebView = ViewModelProvider(this)[Show_Dest_WebViewModel::class.java]


        mDest_WebView.progressBar.observe(this) { show ->
            wv_progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }

        mDest_WebView.errorMessage.observe(this) { text ->
            error_wv.text = text
            error_wv.visibility = View.VISIBLE

        }


        mDest_WebView.getDestinationsDetails(id).observe(this) {
            accessOnWebView(it!!.url)
        }

    }

    //initialisation des Ui avec les variables
    private fun initData() {
        destNameToolbar = findViewById(R.id.WebView_Toolbar)
        wv_progressBar = findViewById(R.id.wv_progress_bar)
        error_wv = findViewById(R.id.erreurWV)
        webView = findViewById(R.id.destWebview)
    }

    //Rajouter des actions au Toolbar
    private fun toolBarAction() {
        setSupportActionBar(destNameToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = destName
        destNameToolbar!!.setNavigationOnClickListener {

            val intent = Intent(this@Show_Dest_WebView, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    //Retrouve les Data passer dans les Extras
    private fun retrieveDataFromIntent() {
        id = intent.getLongExtra("destinationid", id)
        destName = intent.getStringExtra("destinationName").toString()
    }

    //Accède au site Web de la destination
    private fun accessOnWebView(url: String) {
        webView!!.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                view.visibility = View.INVISIBLE
                wv_progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                view.visibility = View.VISIBLE
                wv_progressBar.visibility = View.INVISIBLE
            }

        }
        val webSettings = webView!!.settings
        webSettings.javaScriptEnabled = true
        webView!!.loadUrl(url)
    }

    //Retour vers la page précédente
    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }
}