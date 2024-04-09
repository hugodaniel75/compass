import Foundation
import Capacitor
import CoreLocation

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CompassPlugin)
public class ExamplePlugin: CAPPlugin, CLLocationManagerDelegate {
    private let implementation = Compass()
    private var locationManager: CLLocationManager!

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }

    private var locationManager: CLLocationManager!

    @objc func requestCompassPermission(_ call: CAPPluginCall) {
        guard CLLocationManager.locationServicesEnabled() else {
            call.reject("Los servicios de ubicación están desactivados.")
            return
        }

        let locationManager = CLLocationManager()
        locationManager.requestWhenInUseAuthorization()

        call.resolve()
    }

    @objc func check(_ call: CAPPluginCall) {
        if CLLocationManager.headingAvailable() {
            call.resolve(true)
        } else {
            call.resolve(false)
        }
    }

    @objc func start(_ call: CAPPluginCall) {
        locationManager = CLLocationManager()
        locationManager.delegate = self
        locationManager.startUpdatingHeading()
        call.resolve()
    }

    @objc func stop(_ call: CAPPluginCall) {
        locationManager.stopUpdatingHeading()
        call.resolve()
    }

    public func locationManager(_ manager: CLLocationManager, didUpdateHeading newHeading: CLHeading) {
        let heading = newHeading.trueHeading
        let data: [String: Any] = ["heading": heading]
        notifyListeners("headingUpdate", data: data)
    }
}
