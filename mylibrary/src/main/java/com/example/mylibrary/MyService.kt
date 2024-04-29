package com.example.mylibrary

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process

class MyService : Service() {

  private val binder = MyServiceBinder()

  override fun onBind(intent: Intent): IBinder {
    return binder
  }

  val PID : Int = (binder as MyServiceBinder).getPID()

  inner class MyServiceBinder : IMyService.Stub() {
    override fun getPID(): Int {
      return Process.myPid()
    }

    override fun genRanStr(length : Int) : String {
      val prefix = (1..1).map { ('A'..'Z').random() }.joinToString("")
      val middle = (3..length).map { ('a'..'z').random() }.joinToString("")
      return "$prefix$middle!"
    }

    /** This is for Local binder call */
    fun getLocalService() : MyService {
      return this@MyService
    }
  }
}