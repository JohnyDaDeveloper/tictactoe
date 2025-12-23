// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "IosModel",
    platforms: [
        .iOS(.v17)
    ],
    products: [
        .library(
            name: "IosModel",
            targets: ["IosModel"]
        ),
    ],
    dependencies: [
        .package(url: "https://github.com/hmlongco/Factory", exact: "2.5.3"),
    ],
    targets: [
        .target(
            name: "IosModel",
            dependencies: [
                .product(name: "FactoryKit", package: "Factory"),
            ]
        ),
    ]
)
