import os
import Shared
import Foundation

public class IosLogWriter: LogWriter {
    
    public init() {}
    
    let logger = Logger(
        subsystem: Bundle.main.bundleIdentifier ?? "Jidelna.cz",
        category: "Shared"
    )
    
    public func log(
        severity: LogSeverity,
        tag: String,
        throwable: KotlinThrowable?,
        messageBlock: @escaping () -> String
    ) {
        let message = if let throwable = throwable {
            "(\(tag)) \(messageBlock()): \(throwable.getStacktraceString())"
        } else {
            "(\(tag)) \(messageBlock())"
        }
        
        switch severity {
        case .verbose, .debug:
            logger.debug("\(message, privacy: .public)")
        case .info:
            logger.info("\(message, privacy: .public)")
        case .warn:
            logger.warning("\(message, privacy: .public)")
        case .error:
            logger.error("\(message, privacy: .public)")
        }
    }
}
