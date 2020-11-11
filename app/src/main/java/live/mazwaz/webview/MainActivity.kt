package live.mazwaz.webview

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WebViewInit()
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun WebViewInit(){
        webView.webViewClient = Callback()
        webView.apply {
            loadUrl("http://mes.pbrx.web.id")
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }

    override fun onBackPressed() {
        if(webView.canGoBack()) webView.goBack()
    }
    private inner class Callback : WebViewClient() {
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
            webView.loadUrl("file:///android_asset/error.html")
        }

        override fun shouldOverrideUrlLoading(
            view: WebView,
            request: WebResourceRequest
        ): Boolean {
            if(request.url.toString().startsWith("refresh")){
                webView.loadUrl("http://mes.pbrx.web.id")
            }
            return super.shouldOverrideUrlLoading(view, request)
        }

    }


}