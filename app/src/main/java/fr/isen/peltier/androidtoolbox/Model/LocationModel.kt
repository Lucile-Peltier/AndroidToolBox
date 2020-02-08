package fr.isen.peltier.androidtoolbox.Model

import java.io.Serializable

class LocationModel: Serializable {
    var city: String? = null
    var state: String? = null
    var country: String? = null
    var street: StreetModel? = null
}

class StreetModel: Serializable {
    var name: String? = null
}