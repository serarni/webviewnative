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
- (void) createWebViewNative:(CDVInvokedUrlCommand *)command
{
    NSLog(@"createWebViewNative() command %@", [command argumentAtIndex:0]);
    
    // reading arguments
    NSString *sUrl = [[NSString alloc] initWithString:[command argumentAtIndex:0]];
    int iLeft = [[command argumentAtIndex:1] intValue];
    int iTop = [[command argumentAtIndex:2] intValue];
    int iWidth = [[command argumentAtIndex:3] intValue];
    int iHeight = [[command argumentAtIndex:4] intValue];
    
    // creating native webview
    self.uiWebView = [[UIWebView alloc]initWithFrame:CGRectMake(iLeft, iTop, iWidth, iHeight)];
    [self.uiWebView setBackgroundColor:UIColor.clearColor];
    self.uiWebView.opaque = NO;
    self.uiWebView.delegate = self;
    self.uiWebView.hidden = YES;
    
    NSURL *nsurl = [NSURL URLWithString:sUrl];
    NSURLRequest *nsrequest=[NSURLRequest requestWithURL:nsurl];
    [self.uiWebView loadRequest:nsrequest];

    // adding to the main controler
    [AppDelegate loadNativeViewInTopOfView:self.uiWebView];
}

- (void) destroyWebViewNative:(CDVInvokedUrlCommand *)command;
{
    NSLog(@"destroyWebViewNative()");
    
    if (nil != self.uiWebView) {
        [self changeViewVisivilityWithAnimation:self.uiWebView visible:NO completition:^(BOOL finished) {
            [self.uiWebView removeFromSuperview];
            self.uiWebView = nil;
        }];
    }

}

- (void) changeWebViewVisivility:(CDVInvokedUrlCommand *)command
{
    NSLog(@"changeWebViewVisivility()");
    
    if (nil != self.uiWebView) {
        BOOL bVisible = [[command argumentAtIndex:0] boolValue];
        [self changeViewVisivilityWithAnimation:self.uiWebView visible:bVisible completition:NULL];
    }
}

- (void) changeViewVisivilityWithAnimation:(UIView*)view visible:(BOOL)bVisible completition:(void (^)(BOOL finished))completion
{
    NSLog(@"changeWebViewVisivility()");
    
    if (view && view.hidden == bVisible)
    {
        [UIView transitionWithView:view
                          duration:0.4
                           options:UIViewAnimationOptionTransitionCrossDissolve
                        animations:NULL
                        completion:completion];
        
        [view setHidden:!bVisible];
    }
}

- (void)webViewDidFinishLoad:(UIWebView *)webView
{
    NSLog(@"webViewDidFinishLoad()");
    [self changeViewVisivilityWithAnimation:webView visible:YES completition:NULL];
}


@end
