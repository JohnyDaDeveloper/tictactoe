// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "IosApp",
    platforms: [
        .iOS(.v17)
    ],
    products: [
        .library(
            name: "IosApp",
            targets: ["IosApp"]
        ),
    ],
    dependencies: [
        .package(name: "IosModel", path: "../IosModel")
    ],
    targets: [
        .target(
            name: "IosApp",
            dependencies: [
                "IosModel",
            ]
        ),
    ]
)
