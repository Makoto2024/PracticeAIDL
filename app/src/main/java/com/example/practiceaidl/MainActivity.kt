package com.example.practiceaidl

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mylibrary.IMyService
import com.example.mylibrary.MyService
import com.example.practiceaidl.ui.theme.PracticeAIDLTheme

class MainActivity : ComponentActivity() {
  var remoteService : IMyService? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PracticeAIDLTheme {
        MainTheme(this)
      }
    }
  }
  override fun onStart() {
    super.onStart()
    Intent(this, MyService::class.java).also {
      bindService(it, connection, BIND_AUTO_CREATE)
    }
  }
  override fun onStop() {
    super.onStop()
    unbindService(connection)
  }

  fun getRandomString(length : Int) : String {
    val defaultRet = "Remote Service Not Ready!"
    return try {
      remoteService?.genRanStr(length) ?: defaultRet
    } catch (e : RemoteException) {
      defaultRet
    }
  }

  private val connection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName, service: IBinder) {
      Toast.makeText(
        this@MainActivity,
        "Connected to remote service!",
        Toast.LENGTH_SHORT
      ).show()
      remoteService = IMyService.Stub.asInterface(service)
    }

    override fun onServiceDisconnected(name: ComponentName) {
      Toast.makeText(
        this@MainActivity,
        "Disconnected to remote service!!!",
        Toast.LENGTH_LONG
      ).show()
      remoteService = null
    }
  }
}

@Composable
fun MainTheme(mainActivity: MainActivity) {
  var buttonTxt by remember { mutableStateOf("Random!") }
  val buttonTxtSize = buttonTxt.length

  // A surface container using the 'background' color from the theme
  Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
    Row(
      verticalAlignment = Alignment.Bottom,
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier.fillMaxSize().background(Color.Gray)
    ) {
      RandomButton(buttonTxt){ buttonTxt = mainActivity.getRandomString(buttonTxtSize) }
    }
  }
}

@Composable
fun RandomButton(buttonTxt: String, onClick: () -> Unit) {
  Button(
    modifier = Modifier.size(width = 200.dp, height = 100.dp),
    onClick = { onClick() }
  ) {
    Text(buttonTxt, fontSize = 20.sp)
  }
}
