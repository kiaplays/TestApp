package com.example.testproject.single.users

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testproject.network.PostsService
import com.example.testproject.network.RetrofitInstance
import com.example.testproject.network.model.PostResponse
import com.example.testproject.network.model.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val postsService: PostsService
): ViewModel() {

    var usersList = MutableLiveData<List<UserResponse>>(listOf())
    var loading = MutableLiveData<Boolean>(false)
    init {
        getUsers()
    }
    fun getUsers(){
        loading.value = true
        val call = postsService.getUsers()
        call.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                if (response.isSuccessful && response.body() != null) {
                    response.body()?.let {
                        loading.value = false
                        usersList.value = it
                    }
                }
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                t.printStackTrace()
                loading.value = false
            }
        }
        )
    }
}