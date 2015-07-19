/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/cindy/project/.wilsonflying/test_module/java/workspace/testAIDLCallBack/src/com/wilsonflying/testaidlcallback/IMyCallBack.aidl
 */
package com.wilsonflying.testaidlcallback;
public interface IMyCallBack extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.wilsonflying.testaidlcallback.IMyCallBack
{
private static final java.lang.String DESCRIPTOR = "com.wilsonflying.testaidlcallback.IMyCallBack";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.wilsonflying.testaidlcallback.IMyCallBack interface,
 * generating a proxy if needed.
 */
public static com.wilsonflying.testaidlcallback.IMyCallBack asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.wilsonflying.testaidlcallback.IMyCallBack))) {
return ((com.wilsonflying.testaidlcallback.IMyCallBack)iin);
}
return new com.wilsonflying.testaidlcallback.IMyCallBack.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_performAction:
{
data.enforceInterface(DESCRIPTOR);
this.performAction();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.wilsonflying.testaidlcallback.IMyCallBack
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void performAction() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_performAction, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_performAction = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void performAction() throws android.os.RemoteException;
}
