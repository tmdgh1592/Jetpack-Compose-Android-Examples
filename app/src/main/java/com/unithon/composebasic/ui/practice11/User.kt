package com.unithon.composebasic.ui.practice11

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val avatar: String,
    val createdAt: String,
    val id: String,
    val name: String
)