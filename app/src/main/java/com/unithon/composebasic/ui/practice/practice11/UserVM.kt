package com.unithon.composebasic.ui.practice.practice11

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class UserVM : ViewModel() {

    // Flow는 데이터 스트림을 위한 용도
    // usersFlow 값이 변경되면 usersFlow를 collect하고 있는 변수에 전달됨.
    val usersFlow = MutableStateFlow<List<User>>(emptyList())

    init {
        Log.d("TAG", "UserVM() init called ")

//        viewModelScope.launch {
//            // 에러 발생 잡기 위한 runCatching 블럭
//            kotlin.runCatching {
//                UserRepo.fetchUsers()
//            }.onSuccess { fetchedUsers ->
//                Log.d("TAG", "UserVM() onSuccess ")
//                usersFlow.value = fetchedUsers
//            }.onFailure {
//                Log.d("TAG", "UserVM() Failed ")
//            }
//        }
    }

}