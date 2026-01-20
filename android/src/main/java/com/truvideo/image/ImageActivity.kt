package com.truvideo.image

import android.os.Bundle
import android.util.Log
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
import java.io.File
import java.util.logging.Logger

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
            TruvideoSdkImagePlugin.mainCall!!.reject("Input or Output path is invalid")
            finish()
            return
        }
        if (!outputPath.endsWith(".png") && !outputPath.endsWith(".jpg") && !outputPath.endsWith(".jpeg")) {
            outputPath += ".png"
        }
        if(!File(inputPath).exists()){
            TruvideoSdkImagePlugin.mainCall!!.reject("Input path not exist")
            finish()
            return
        }

        Log.d("inputPath","in $inputPath")
        Log.d("resulPath","out $outputPath")
        try{
            launcher = registerForActivityResult(TruvideoSdkImageEditContract()){ resultPath: String? ->
                val ret =  JSObject();
                ret.put("result",resultPath?: "")
                TruvideoSdkImagePlugin.mainCall!!.resolve(ret);
                finish()
            }
            launcher!!.launch(TruvideoSdkImageEditParams(inputPath, outputPath))
        }catch (e : Exception){
            TruvideoSdkImagePlugin.mainCall!!.reject(e.message)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidTheme {
        Greeting("Android")
    }
}