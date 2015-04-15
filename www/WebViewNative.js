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

WebViewNative.createWebViewNative = function (sUrl, iLeft, iTop, iWidth, iHeight, success, errorCallback) {
    try {
        cordova.exec(success, error, 'WebViewNative', 'createWebViewNative', [sUrl, iLeft, iTop, iWidth, iHeight]);
    }
    catch(error){
        console.log("WebViewNative.createWebViewNative() error " + error);
        errorCallback();
    }
};

WebViewNative.destroyWebViewNative = function (success, error) {
    try {
        cordova.exec(success, error, 'WebViewNative', 'destroyWebViewNative', [""]);
    }
    catch(error){
        console.log("WebViewNative.destroyWebViewNative() error " + error);
    }
};

WebViewNative.changeWebViewVisivility = function (bVisible, success, error) {
    try {
        cordova.exec(success, error, 'WebViewNative', 'changeWebViewVisivility', [bVisible]);
    }
    catch(error){
        console.log("WebViewNative.changeWebViewVisivility() error " + error);
    }
};

WebViewNative.isViewerAppInstalled = function (sDataType, onResultCallback) {
    try {
        cordova.exec(onResultCallback, onResultCallback, 'WebViewNative', 'isViewerAppInstalled', [ sDataType]);
    }
    catch(error){
        console.log("WebViewNative.isViewerAppInstalled() error " + error);
    }
}
