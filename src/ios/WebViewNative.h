//
//  WebViewNative.h
//  idcsalud
//
//  Created by sarnillas on 18/03/15.
//
//

#import <Cordova/CDV.h>

@interface WebViewNative: CDVPlugin

@property UIWebView * uiWebView;

// show a native web View to avoid problems with iframes in ios apps in phonegap
- (void) createWebViewNative:(CDVInvokedUrlCommand *)command;

- (void) destroyWebViewNative:(CDVInvokedUrlCommand *)command;

- (void) changeWebViewVisivility:(CDVInvokedUrlCommand *)command;


@end
