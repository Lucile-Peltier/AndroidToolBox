package fr.isen.peltier.androidtoolbox

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.peltier.androidtoolbox.Model.UserModel
import kotlinx.android.synthetic.main.recycle_view_web_service_cell.view.*

class WebServicesAdapter(val results: ArrayList<UserModel>): RecyclerView.Adapter<WebServicesAdapter.WebServicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebServicesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_web_service_cell, parent, false)
        return WebServicesViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return results.count()
    }

    override fun onBindViewHolder(holder: WebServicesViewHolder, position: Int) {
        val user = results[position]
        holder.bind(user)
    }

    class WebServicesViewHolder(val view: View, val context: Context): RecyclerView.ViewHolder(view) {
        fun bind(user: UserModel) {

            view.nameRecycleView.text = "${user.name?.title}. ${user.name?.first} ${user.name?.last}"
            view.emailRecycleView.text = user.email

            Picasso
                .with(context)
                .load(user.picture?.large)
                .into(view.imageUserRecycleView)
        }
    }
}