import Foundation

@objc public class Compass: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
