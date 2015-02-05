#!/usr/bin/env python

import wx

wx.App_GetComCtl32Version()
print "wx demo"
app = wx.App()
win = wx.Frame(None)
win.Show()
app.MainLoop()
