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
import com.evaneos.data.model.DestinationDetails
import com.evaneos.evaneostest.R
import com.evaneos.evaneostest.model.entity.UIStateResponse
import com.evaneos.evaneostest.utils.Constants
import com.evaneos.evaneostest.viewmodels.DestWebViewModelFactory
import com.evaneos.evaneostest.viewmodels.Show_Dest_WebViewModel

class Show_Dest_WebView : AppCompatActivity() {
    private var webView: WebView? = null
    private lateinit var mDest_WebView: Show_Dest_WebViewModel
    private lateinit var wv_progressBar: ProgressBar
    private lateinit var error_wv: TextView
    private var destName: String = ""
    private lateinit var response: UIStateResponse.Success<DestinationDetails>
    private var destNameToolbar: Toolbar? = null
    private var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_dest_web_view)

        initView()
        initVariable()
        toolBarAction()

        mDest_WebView = ViewModelProvider(
            this,
            DestWebViewModelFactory(id)
        ).get(Show_Dest_WebViewModel::class.java)

        mDest_WebView.viewState_wv.observe(this) { uiState ->
            when (uiState) {
                is UIStateResponse.Loading -> {
                    wv_progressBar.visibility = View.VISIBLE
                    error_wv.visibility = View.GONE
                }
                is UIStateResponse.Error -> {
                    error_wv.visibility = View.VISIBLE
                    wv_progressBar.visibility = View.GONE
                    error_wv.text = uiState.errorMessage
                }
                is UIStateResponse.Success<*> -> {
                    wv_progressBar.visibility = View.GONE
                    error_wv.visibility = View.GONE
                    response = uiState as UIStateResponse.Success<DestinationDetails>
                    onUrlAccess(response.content.url)
                }
            }
        }
    }

    //initialisation des Ui avec les variables
    private fun initView() {
        destNameToolbar = findViewById(R.id.WebView_Toolbar)
        wv_progressBar = findViewById(R.id.wv_progress_bar)
        error_wv = findViewById(R.id.erreurWV)
        webView = findViewById(R.id.destWebview)
    }

    //Rajouter des actions au Toolbar
    private fun toolBarAction() {
        setSupportActionBar(destNameToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = destName
        destNameToolbar?.setNavigationOnClickListener {
            val intent = Intent(this@Show_Dest_WebView, MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    //Retrouve les Data passer dans les Extras
    private fun initVariable() {
        id = intent.getLongExtra(Constants.INTENT_EXTRA_ID, id)
        destName = intent.getStringExtra(Constants.INTENT_EXTRA_NAME).toString()
    }

    //Accède au site Web de la destination
    private fun onUrlAccess(url: String) {
        webView?.webViewClient = object : WebViewClient() {
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

        val webSettings = webView?.settings
        webSettings?.javaScriptEnabled = true
        webView?.loadUrl(url)
    }

    //Retour vers la page précédente
    override fun onBackPressed() {
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            super.onBackPressed()
        }
    }
}