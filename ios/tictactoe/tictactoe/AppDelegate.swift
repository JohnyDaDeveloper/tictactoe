import UIKit
import SharedFramework

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        willFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil
    ) -> Bool {
        SharedFrameworkLoader.load()
        StartSharedUseCase().invoke()
        return true
    }
}
