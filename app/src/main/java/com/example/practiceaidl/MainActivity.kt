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
import com.example.practiceaidl.ui.theme.PracticeAIDLTheme

class MainActivity : ComponentActivity() {
  // TODO("Should store a remote service's binder here!")

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
    TODO("BindService using intent here!")
  }
  override fun onStop() {
    super.onStop()
    TODO("UnbindService here!")
  }

  fun getRandomString(length : Int) : String {
    TODO("Call function of the remote service")
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
