//
//  WebViewNative.m
//  idcsalud
//
//  Created by administrador on 18/03/15.
//
//

#import "WebViewNative.h"
#import "AppDelegate.h"

@implementation WebViewNative

/**
 *  Required arguments as follows to work propertly
 *  URL: url in format http://www.gogole.com
 *  left position in pixels (int value is required)
 *  top position in pixels (int value is required)
 *  width size in pixels (int value is required)
 *  height size in pixels (int value is required)
 */
- (void) showWebViewNative:(CDVInvokedUrlCommand *)command
{
    NSLog(@"showWebViewNative command %@", [command argumentAtIndex:0]);
    
    // reading arguments
    NSString *sUrl = [[NSString alloc] initWithString:[command argumentAtIndex:0]];
    int iLeft = [[command argumentAtIndex:1] intValue];
    int iTop = [[command argumentAtIndex:2] intValue];
    int iWidth = [[command argumentAtIndex:3] intValue];
    int iHeight = [[command argumentAtIndex:4] intValue];
    
    // creating native webview
    self.uiWebView = [[UIWebView alloc]initWithFrame:CGRectMake(iLeft, iTop, iWidth, iHeight)];
    NSURL *nsurl = [NSURL URLWithString:sUrl];
    NSURLRequest *nsrequest=[NSURLRequest requestWithURL:nsurl];
    [self.uiWebView loadRequest:nsrequest];

    // adding to the main controler
    [AppDelegate loadNativeViewInTopOfView:self.uiWebView];
}

- (void) hideWebViewNative:(CDVInvokedUrlCommand *)command;
{
    if (nil != self.uiWebView) {
        [self.uiWebView removeFromSuperview];
        self.uiWebView = nil;
    }

}

@end
