package com.divisait.webviewnative;

import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class WebViewNative extends CordovaPlugin{

	private final static String TAG_LOG = "WebViewNative";
	
	private static WebView m_webViewNative;
	private static LinearLayout m_lyParent;
	
	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException 
	{ 
		if(action.equals("createWebViewNative")) {
			String sUrl = args.getString(0);
			int iLeft = args.getInt(1);
			int iTop = args.getInt(2);
			int iWidth = args.getInt(3);
			int iHeight = args.getInt(4);
			
			createWebViewNative(sUrl, iLeft, iTop, iWidth, iHeight);
	    } else if (action.equals("destroyWebViewNative")) {
	    	destroyWebViewNative();
	    }
	    else if(action.equals("changeWebViewVisivility")) {
	    	changeWebViewVisivility(args.getBoolean(0));
	    }
	    else if (action.equals("isViewerAppInstalled")) {
	    	JSONObject successObj = new JSONObject();
	    	String sDataType = args.getString(0);
	    	if (this.isViewerAppInstalled(sDataType)) {
	    		successObj.put("status", PluginResult.Status.OK.ordinal());
				successObj.put("message", "Installed");
	    	} else {
	    		successObj.put("status", PluginResult.Status.NO_RESULT.ordinal());
				successObj.put("message", "Not installed");
	    	}
	    	callbackContext.success(successObj);
	    }
	    else {	    	
	    	return false;
	    }
		return true;
	}
	
	private void changeWebViewVisivility(final boolean bVisible) {
		final Activity actMain = this.cordova.getActivity();
		actMain.runOnUiThread(new Runnable() {
			@Override
			public void run() 
			{	
				try
				{			
					if (null!=m_webViewNative)
						m_webViewNative.setVisibility(bVisible?View.VISIBLE:View.INVISIBLE);
				}
				catch(Exception e) {
					Log.d(TAG_LOG, "changeWebViewNativeVisivility() ERROR " + e.getMessage());
				}
			}
		});
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void createWebViewNative(final String sUrl, final int iLeft, final int iTop, final int iWidth, final int iHeight)
	{
		final Activity actMain = this.cordova.getActivity();
		final Context context = this.webView.getContext();
		
		actMain.runOnUiThread(new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() 
			{	
				try
				{
					m_webViewNative = new WebView(context);
					m_webViewNative.loadUrl(sUrl);
					m_webViewNative.setWebViewClient(new WebViewClient());
					m_webViewNative.getSettings().setJavaScriptEnabled(true);
					m_webViewNative.setLayoutParams(
							new ViewGroup.LayoutParams(iWidth, iHeight));
					m_lyParent = new LinearLayout(context);
					m_lyParent.setPadding(iLeft, iTop, 0, 0);
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

	private void destroyWebViewNative() 
	{
		final Activity actMain = this.cordova.getActivity();
		actMain.runOnUiThread(new Runnable() {
			@Override
			public void run() 
			{	
				try
				{			
					if (null!=m_webViewNative)
						m_webViewNative.destroy();
					if (null != m_lyParent) {						
						m_lyParent.removeAllViews();
						m_lyParent.setVisibility(View.GONE);
					}
				}
				catch(Exception e) {
					Log.d(TAG_LOG, "hideWebViewNative() ERROR " + e.getMessage());
				}
			}
		});
	}
	
	private boolean isViewerAppInstalled(String sDataType)
	{
		boolean result = false;
		
		PackageManager packageManager = this.cordova.getActivity().getPackageManager();
		Intent testIntent = new Intent(Intent.ACTION_VIEW);
		testIntent.setType(sDataType);
		List<ResolveInfo> list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
		result = list.size() > 0;
		return result;
	}
}
