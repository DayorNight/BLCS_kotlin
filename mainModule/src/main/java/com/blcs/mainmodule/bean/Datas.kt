package com.blcs.mainmodule.bean
import androidx.databinding.ObservableField

data class User(var firstName: String, val lastName: String, val location: String?)

/*可观察字段*/
class User1 {
    val firstName = ObservableField<String>()
    val lastName = ObservableField<String>()
    val location = ObservableField<String>()
}

///*可观察对象*/
//class User3 : BaseObservable() {
//    @get:Bindable
//    var firstName: String? = null
//        set(firstName) {
//            field = firstName
////            notifyPropertyChanged(BR.firstName)
//        }
//
//    @get:Bindable
//    var lastName: String? = null
//        set(lastName) {
//            field = lastName
////            notifyPropertyChanged(BR.lastName)
//        }
//}