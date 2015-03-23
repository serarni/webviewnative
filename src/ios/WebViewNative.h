//
//  WebViewNative.h
//  idcsalud
//
//  Created by administrador on 18/03/15.
//
//

#import <Cordova/CDV.h>

@interface WebViewNative: CDVPlugin

@property UIWebView * uiWebView;

// show a native web View to avoid problems with iframes in ios apps in phonegap
- (void) showWebViewNative:(CDVInvokedUrlCommand *)command;

- (void) hideWebViewNative:(CDVInvokedUrlCommand *)command;
@end
