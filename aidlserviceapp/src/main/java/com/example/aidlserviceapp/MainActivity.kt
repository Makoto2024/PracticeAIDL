package com.example.aidlserviceapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aidlserviceapp.ui.theme.PracticeAIDLTheme
import com.example.mylibrary.MyService

class MainActivity : ComponentActivity() {
  private var isBound = false
  private lateinit var localService: MyService

  private fun repaintUI() {
    setContent {
      PracticeAIDLTheme {
        MainTheme(if (isBound) localService else null)
      }
    }
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    repaintUI()
  }
  override fun onStart() {
    super.onStart()
    // Bind to the local service MyService
    Intent(this, MyService::class.java).also { intent ->
      bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }
  }
  override fun onStop() {
    super.onStop()
    unbindService(connection)
    isBound = false
  }

  private val connection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName, service: IBinder) {
      val binder = service as MyService.MyServiceBinder
      isBound = true
      localService = binder.getLocalService()
      Log.i("TAG", "localService is initialized!!!! $localService")
      repaintUI()
    }

    override fun onServiceDisconnected(name: ComponentName) {
      isBound = false
    }
  }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
  Text(
    text = "Hello $name!",
    modifier = modifier
  )
}

@Composable
fun MainTheme(localService : MyService?) {
  var number by remember { mutableIntStateOf(0) }

  // A surface container using the 'background' color from the theme
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    number = localService?.PID?: 0
    Greeting("My PID is $number")
  }
}