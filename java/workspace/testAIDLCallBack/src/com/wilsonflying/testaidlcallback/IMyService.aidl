
package com.wilsonflying.testaidlcallback;

import com.wilsonflying.testaidlcallback.IMyCallBack;

interface IMyService{

	int add(int a, int b);
	void registerCallBack(IMyCallBack cb);
	void invokeCallBack();
}