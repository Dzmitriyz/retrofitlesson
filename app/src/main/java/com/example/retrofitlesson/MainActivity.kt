package com.example.retrofitlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.retrofitlesson.retrofit.ProductAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txt = findViewById<TextView>(R.id.text)
        val btn = findViewById<Button>(R.id.button)


        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productAPI = retrofit.create(ProductAPI::class.java)

        btn.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val productAPI = productAPI.getProductById()
                runOnUiThread {
                    txt.text =(productAPI.title);
                }
        }

        }

    }
}