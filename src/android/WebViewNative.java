package com.divisait.webviewnative;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebViewNative extends CordovaPlugin{

	private final static String TAG_LOG = "WebViewNative";
	
	private WebView m_webViewNative;
	private LinearLayout m_lyParent;
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException 
	{
		if(action.equals("showWebViewNative")){
			String sUrl = args.getString(0);
			int iLeft = args.getInt(1);
			int iTop = args.getInt(2);
			int iWidth = args.getInt(3);
			int iHeight = args.getInt(4);
			
			showWebViewNative(sUrl, iLeft, iTop, iWidth, iHeight);
			
	        return true;
	    } else if (action.equals("hideWebViewNative")){
	    	hideWebViewNative();
	    }
		
		return false;
	}
	
	@SuppressLint("SetJavaScriptEnabled")
	private void showWebViewNative(final String sUrl, final int iLeft, final int iTop, final int iWidth, final int iHeight)
	{
		final Activity actMain = this.cordova.getActivity();
		final Context context = this.webView.getContext();
		
		actMain.runOnUiThread(new Runnable() {
			@Override
			public void run() 
			{	
				try
				{					
					m_webViewNative = new WebView(context);
					m_webViewNative.loadUrl(sUrl);
					m_webViewNative.setWebViewClient(new WebViewClient());
					m_webViewNative.getSettings().setJavaScriptEnabled(true);
					ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(iWidth, iHeight);
					params.setMargins(iLeft, iTop, 0, 0);
					m_webViewNative.setLayoutParams(params);
					
					m_lyParent = new LinearLayout(context);
					m_lyParent.setPadding(iLeft, iTop, 0, 0);
					View view = new View(context);
					view.setVisibility(View.INVISIBLE);
					view.setLayoutParams(new ViewGroup.LayoutParams(1, iTop));
					m_lyParent.setHorizontalGravity(LinearLayout.VERTICAL);
					m_lyParent.addView(view);
					m_lyParent.addView(m_webViewNative);
					
					actMain.getWindow().addContentView(m_lyParent, 
							new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
				}
				catch(Exception e){
					Log.d(TAG_LOG, "showWebViewNative() ERROR " + e.getMessage());
				}
			}
		});
		
	}

	private void hideWebViewNative() 
	{
		final Activity actMain = this.cordova.getActivity();
		actMain.runOnUiThread(new Runnable() {
			@Override
			public void run() 
			{	
				try
				{					
					m_webViewNative.destroy();
					m_lyParent.removeAllViews();
					m_lyParent.setVisibility(View.GONE);
				}
				catch(Exception e) {
					Log.d(TAG_LOG, "hideWebViewNative() ERROR " + e.getMessage());
				}
			}
		});
	}

}
