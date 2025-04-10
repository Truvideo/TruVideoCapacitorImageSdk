package com.truvideo.image;

import android.content.Intent;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "TruvideoSdkImage")
public class TruvideoSdkImagePlugin extends Plugin {


    static PluginCall mainCall;
    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value",value);
        call.resolve(ret);
    }

    @PluginMethod
    public void editImage(PluginCall call) {
        String inputPath = call.getString("inputPath");
        String outputPath = call.getString("outputPath");
        mainCall = call;
        getContext().startActivity(new Intent(getContext(), ImageActivity.class).putExtra("inputPath",inputPath).putExtra("outputPath",outputPath));

//        JSObject ret = new JSObject();
//        //ret.put("value",value);
//        call.resolve(ret);
    }

}
