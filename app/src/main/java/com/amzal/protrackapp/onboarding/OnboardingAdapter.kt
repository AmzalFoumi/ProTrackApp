package com.amzal.protrackapp.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amzal.protrackapp.R

class OnboardingAdapter(
    private val items: List<OnboardingItem>
) : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.imageOnboarding)
        private val title: TextView = itemView.findViewById(R.id.textTitle)
        private val description: TextView = itemView.findViewById(R.id.textDescription)

        fun bind(item: OnboardingItem) {
            image.setImageResource(item.imageRes)
            title.text = item.title
            description.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}













//package com.amzal.protrackapp
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//
//public class OnboardingAdapter(private val context: Context) :
//    RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
//
//    private val layouts = listOf(
//        R.layout.onboarding_page1,
//        R.layout.onboarding_page2,
//        R.layout.onboarding_page3
//    )
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(context).inflate(layouts[viewType], parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount() = layouts.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}
//
//    override fun getItemViewType(position: Int): Int = position
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
//}

