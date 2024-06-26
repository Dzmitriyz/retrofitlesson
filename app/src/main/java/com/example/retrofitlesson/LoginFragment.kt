package com.example.retrofitlesson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.retrofitlesson.databinding.FragmentLoginBinding
import com.example.retrofitlesson.retrofit.AuthRequest
import com.example.retrofitlesson.retrofit.MainAPI
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var mainAPI:MainAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRetrofit()
        binding.apply {
            button.setOnClickListener {
                findNavController().navigate(R.id.action_LoginFragment_to_ProductsFragment)
        }
            bSignIn.setOnClickListener(){
                auth(AuthRequest(
                    LoginEdit.text.toString(),
                        passwordEdit.text.toString())
                )
            }
        }
    }

    private fun initRetrofit(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        mainAPI = retrofit.create(MainAPI::class.java)
    }

    private fun auth(authRequest: AuthRequest){
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainAPI.auth(authRequest)
            val message = response.errorBody()?.string()?.let {JSONObject(it).getString("message")}
            requireActivity().runOnUiThread{
                binding.errorTxt.text = message
                val user =response.body()
                if(user != null){
                    Picasso.get().load(user.image).into(binding.imageView)
                    binding.nameTxt.text = user.firstName
                    binding.button.visibility = View.VISIBLE
                    viewModel.token.value = user.token
                }
            }
        }
    }

}