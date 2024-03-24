package com.example.retrofitlesson.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitlesson.R
import com.example.retrofitlesson.databinding.ListItemBinding
import com.example.retrofitlesson.retrofit.Product

class ProductAdapter: ListAdapter<Product, ProductAdapter.Holder>(Comparator()) {
    class Holder(view: View):RecyclerView.ViewHolder(view){
        private val binding = ListItemBinding.bind(view)
        fun bind(product: Product)= with(binding){
            title.text = product.title
            description.text=product.description
        }
    }

    class Comparator: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(p0: Product, p1: Product): Boolean {
            return p0.id == p1.id
        }

        override fun areContentsTheSame(p0: Product, p1: Product): Boolean {
            return p0 == p1
        }

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
       val view = LayoutInflater.from(p0.context)
           .inflate(R.layout.list_item, p0, false)
        return Holder(view)
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(getItem(p1))
    }
}