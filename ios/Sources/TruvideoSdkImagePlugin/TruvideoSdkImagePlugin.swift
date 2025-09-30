import Foundation
import Capacitor
import TruvideoSdkImage

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(TruvideoSdkImagePlugin)
public class TruvideoSdkImagePlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "TruvideoSdkImagePlugin"
    public let jsName = "TruvideoSdkImage"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "echo", returnType: CAPPluginReturnPromise),
        CAPPluginMethod(name: "editImage", returnType: CAPPluginReturnPromise)
    ]


    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": value
        ])
    }
    @objc func editImage(_ call: CAPPluginCall) {
        let inputPath = call.getString("inputPath") ?? ""
        let outputPath = call.getString("outputPath") ?? ""
        launchImageEdit(inputPath: inputPath, outputPath: fileURLString(from: outputPath), call)
    }
    func fileURLString(from outputPath: String) -> String {
        if outputPath.hasPrefix("file://") {
            return outputPath
        } else {
            return "file://\(outputPath)"
        }
    }
    func launchImageEdit(inputPath: String,outputPath: String,_ call: CAPPluginCall) {
           Task{
               guard let rootViewController = await UIApplication.shared.keyWindow?.rootViewController else {
                   print("E_NO_ROOT_VIEW_CONTROLLER", "No root view controller found")
                   let error = NSError(domain: "com.TruvideoImageSDk.ImageSDK", code: 500, userInfo: [NSLocalizedDescriptionKey: "No root view controller found"])
                   call.reject("E_NO_ROOT_VIEW_CONTROLLER", "No root view controller foundh",error)
                   return
               }
               if let imageURL = URL(string: inputPath) ,let outputURL = URL(string:fileURLString(from: outputPath)) {
                        let output: TruvideoSdkImageFileDescriptor = .custom(rawPath: outputURL.absoluteString)
                        let configuration = TruvideoSdkImageEditorPreset(imageURL: imageURL, output: output)
         
                   
                   await rootViewController.presentTruvideoSdkImageEditorView(preset: configuration, onComplete: { result in
                       if let editedImageUrl: URL = result.editedImageURL {
                           do{
                                call.resolve(["result": editedImageUrl.absoluteString])
                           }
                       } else{
                           let error = NSError(domain:"com.TruvideoImageSDk.ImageSDK", code: 500, userInfo: [NSLocalizedDescriptionKey: "There is no result URL"])
                           call.reject("NO_URL_Found", "There is no result URL", error)
                       }
                   })
               } else{
                   let error = NSError(domain:"com.TruvideoImageSDk.ImageSDK", code: 500, userInfo: [NSLocalizedDescriptionKey: "Failed to get directory path"])
                   call.reject("NO_PATH_Found", "Failed to get directory path", error)
               }
           }
       }
    
}
