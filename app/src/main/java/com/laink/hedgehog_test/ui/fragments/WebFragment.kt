package com.laink.hedgehog_test.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.laink.hedgehog_test.R
import com.laink.hedgehog_test.util.Constants.Companion.BUNDLE_WEB_VIEW_STATE
import com.laink.hedgehog_test.util.Constants.Companion.DOCUMENTATION_URL
import kotlinx.android.synthetic.main.web_fragment.*


class WebFragment : Fragment(R.layout.web_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            web_view.apply {
                webViewClient = WebViewClient()
                loadUrl(DOCUMENTATION_URL)
            }
        } else {
            savedInstanceState.getBundle(BUNDLE_WEB_VIEW_STATE)?.let { web_view.restoreState(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        web_view.saveState(bundle)
        outState.putBundle(BUNDLE_WEB_VIEW_STATE, bundle)
    }

}