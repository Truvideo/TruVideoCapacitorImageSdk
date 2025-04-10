// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "TruvideoCapacitorImageSdk",
    platforms: [.iOS(.v14)],
    products: [
        .library(
            name: "TruvideoCapacitorImageSdk",
            targets: ["TruvideoSdkImagePlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "7.0.0")
    ],
    targets: [
        .target(
            name: "TruvideoSdkImagePlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/TruvideoSdkImagePlugin"),
        .testTarget(
            name: "TruvideoSdkImagePluginTests",
            dependencies: ["TruvideoSdkImagePlugin"],
            path: "ios/Tests/TruvideoSdkImagePluginTests")
    ]
)