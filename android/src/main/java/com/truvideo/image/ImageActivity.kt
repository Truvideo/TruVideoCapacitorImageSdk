package com.truvideo.image

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.getcapacitor.JSObject
import com.truvideo.image.ui.theme.AndroidTheme
import com.truvideo.sdk.image.ui.edit.activities.edit.TruvideoSdkImageEditContract
import com.truvideo.sdk.image.ui.edit.activities.edit.TruvideoSdkImageEditParams

class ImageActivity : ComponentActivity() {

    private var launcher : ActivityResultLauncher<TruvideoSdkImageEditParams>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        val inputPath = intent.getStringExtra("inputPath")
        var outputPath = intent.getStringExtra("outputPath")
        if(inputPath == null || outputPath == null){
            finish()
            return
        }
        if (!outputPath.endsWith(".png") && !outputPath.endsWith(".jpg") && !outputPath.endsWith(".jpeg")) {
            outputPath += ".png"
        }

        launcher = registerForActivityResult(TruvideoSdkImageEditContract()){ resultPath: String? ->
            val ret =  JSObject();
            ret.put("result",resultPath?: "")
            TruvideoSdkImagePlugin.mainCall!!.resolve(ret);
            finish()
        }
        launcher!!.launch(TruvideoSdkImageEditParams(inputPath, outputPath))
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTheme {
        Greeting("Android")
    }
}