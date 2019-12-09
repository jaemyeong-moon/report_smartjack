package com.example.myapplication

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.reflect.Method

object VolleyService {

    val testUrl = "https://eq0lwb7e8e.execute-api.ap-northeast-2.amazonaws.com/default/android-dev-recruit"
//    val testUrl = " http://httpbin.org/post"

    fun testPost(context: Context,value: String, success: (Boolean) -> Unit) {

        val myJson = JSONObject()
        myJson.put("value",value)
        myJson.put("name","Jaemyeong")
        myJson.put("phone","01098905314")
        val requestBody = myJson.toString()
        println("Request : $requestBody")
        val testRequest = object : StringRequest(Method.POST, testUrl , Response.Listener { response ->
            println("서버 Response 수신: $response")
            success(true)
        }, Response.ErrorListener { error ->
            Log.d("ERROR", "서버 Response 가져오기 실패: $error")
            success(false)
        }) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray()
            }
        }

        Volley.newRequestQueue(context).add(testRequest)
    }
}