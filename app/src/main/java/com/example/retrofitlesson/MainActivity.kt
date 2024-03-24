package com.example.retrofitlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.retrofitlesson.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<TextView>(R.id.text)
        val btn = findViewById<Button>(R.id.button)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productAPI = retrofit.create(ProductAPI::class.java)

        btn.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val productAPI = productAPI.getProductById(3)
                runOnUiThread {
                    txt.text =(productAPI.title);
                }
        }
        }
    }
}