package com.unithon.composebasic.ui.practice.practice01.utils

data class RandomUser(
    val name : String = "최승호",
    val description: String = "오늘도 빡코딩 하고 계신가요?",
    val profileImage: String = "https://randomuser.me/api/portraits/women/57.jpg"
)

object DummyDataProvider {
    val userList = List<RandomUser>(200) {
        RandomUser()
    }
}