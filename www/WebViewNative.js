/*
 WebViewNative.js
 Copyright 2015 Divisa IT. All rights reserved.
 http://www.divisait.com
 
 WebViewNative Cordova Plugin (com.divisait.webviewnative)
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to
 deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 sell copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 */

var WebViewNative = module.exports;

WebViewNative.showWebViewNative = function (sUrl, iLeft, iTop, iWidth, iHeight, success, errorCallback) {
    try {
        cordova.exec(success, error, 'WebViewNative', 'showWebViewNative', [sUrl, iLeft, iTop, iWidth, iHeight]);
    }
    catch(error){
        console.log("WebViewNative.showNativeWebView() error " + error);
        errorCallback();
    }
};

WebViewNative.hideWebViewNative = function (success, error) {
    try {
        cordova.exec(success, error, 'WebViewNative', 'hideWebViewNative', [""]);
    }
    catch(error){
        console.log("WebViewNative.showNativeWebView() error " + error);
        errorCallback();
    }
};

WebViewNative.isViewerAppInstalled = function (sDataType, onResultCallback) {
    cordova.exec(
             onResultCallback,      // A callback function that deals with the JSON object from the CDVPluginResult instance
             onResultCallback,      // An error handler
             'WebViewNative',       // What class to target messages to (method calls = message in ObjC)
             'isViewerAppInstalled',// Which method to call
             [ sDataType]   // These go in the CDVInvokedUrlCommand instance's.arguments property
         );
}
