package com.example.shoppingapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SharedViewModel: ViewModel() {
    private var userid = FirebaseAuth.getInstance().currentUser?.uid.toString()
    private var _uid = MutableLiveData(userid)
    var uid: LiveData<String> = _uid

    fun saveUid(newUid: String){
        _uid.value = newUid
    }
}