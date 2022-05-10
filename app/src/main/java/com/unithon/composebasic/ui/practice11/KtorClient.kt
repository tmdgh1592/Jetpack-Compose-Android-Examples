package com.unithon.composebasic.ui.practice11

import android.util.Log
import io.ktor.client.*
//import io.ktor.client.features.*
//import io.ktor.client.features.json.*
//import io.ktor.client.features.json.serializer.*
//import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

object KtorClient {

    // 제이슨 설정
    private val json = Json {
        encodeDefaults = true // 인코딩을 하겠다고 설정
        ignoreUnknownKeys = true // 모르는 key가 들어왔을 때 무시
    }

    // http client 설정
    val httpClient = HttpClient {
        // Json 설정
//        install(JsonFeature) {
//            serializer = KotlinxSerializer(json)
//        }
//
//        // 로깅 설정
//        install(Logging) {
//            logger = object : Logger {
//                override fun log(message: String) {
//                    Log.d("TAG", "api log: $message")
//                }
//            }
//        }
//
//        install(HttpTimeout) {
//            requestTimeoutMillis = 10000
//            connectTimeoutMillis = 10000
//            socketTimeoutMillis = 10000
//        }
//
//        // 기본적인 api 호출시 넣는 것들. 즉, 기본 세팅
//        defaultRequest {
//            // contentType, accept = application/xml
//            contentType(ContentType.Application.Json)
//            accept(ContentType.Application.Json)
//        }
    }
}