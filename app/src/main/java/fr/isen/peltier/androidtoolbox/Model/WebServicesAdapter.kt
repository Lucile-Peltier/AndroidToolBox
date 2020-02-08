package fr.isen.peltier.androidtoolbox.Model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.peltier.androidtoolbox.R
import kotlinx.android.synthetic.main.recycle_view_web_service_cell.view.*

class WebServicesAdapter(val results: ArrayList<UserModel>, val listener: OnUserRecyclerViewListener): RecyclerView.Adapter<WebServicesAdapter.WebServicesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebServicesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_web_service_cell, parent, false)
        return WebServicesViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return results.count()
    }

    override fun onBindViewHolder(holder: WebServicesViewHolder, position: Int) {
        val user = results[position]
        holder.bind(user, listener)
    }

    class WebServicesViewHolder(val view: View, val context: Context): RecyclerView.ViewHolder(view){

        fun bind(user: UserModel, listener: OnUserRecyclerViewListener) {

            view.nameRecycleView.text = "${user.name?.title}. ${user.name?.first} ${user.name?.last}"
            view.emailRecycleView.text = user.email

            Picasso
                .with(context)
                .load(user.picture?.large)
                .into(view.imageUserRecycleView)

            view.setOnClickListener {
                listener.onSelectUser(user)
            }

        }
    }

    interface OnUserRecyclerViewListener {
        fun onSelectUser(user: UserModel)
    }
}