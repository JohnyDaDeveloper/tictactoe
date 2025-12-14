import Shared

class DiWrapper {
    
    public static func get<T: Any>() -> T {
        let resolved = DiResolver.shared.resolve(clazz: T.self)
        
        guard let casted = resolved as? T else {
            fatalError("Expected \(type(of: T.self)), but got \(type(of: resolved))")
        }
        
        return casted
    }
    
    public static func getting<T: Any>(_ type: T.Type) -> T {
        get()
    }
}
