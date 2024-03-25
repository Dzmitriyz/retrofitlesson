package com.example.retrofitlesson

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class LoginViewModel : ViewModel {
    val token: MutableLiveData<String>()
}