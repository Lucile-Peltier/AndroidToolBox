package fr.isen.peltier.androidtoolbox.Model

import java.io.Serializable

class UserModel: Serializable {
    var name: NameModel? = null
    var email: String? = null
    var location: LocationModel? = null
    var picture: PictureModel? = null
}