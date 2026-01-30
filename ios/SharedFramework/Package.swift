// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "SharedFramework",
    platforms: [
        .iOS(.v18)
    ],
    products: [
        .library(
            name: "SharedFramework",
            targets: ["SharedFramework"]
        ),
    ],
    dependencies: [
        .package(url: "https://github.com/hmlongco/Factory", exact: "2.5.3"),
        .package(name: "IosModel", path: "../IosModel"),
    ],
    targets: [
        .target(
            name: "SharedFramework",
            dependencies: [
                "Shared",
                "IosModel",
                .product(name: "FactoryKit", package: "Factory"),
            ]
        ),
        .binaryTarget(
            name: "Shared",
            path: "../../multiplatform/shared/build/XCFrameworks/lib/Shared.xcframework"
        ),
    ]
)
