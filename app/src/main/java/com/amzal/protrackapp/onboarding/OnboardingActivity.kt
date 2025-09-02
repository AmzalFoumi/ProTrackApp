package com.amzal.protrackapp.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.amzal.protrackapp.MainActivity
import com.amzal.protrackapp.R
import com.google.android.material.button.MaterialButton

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: MaterialButton
    private lateinit var btnPrev: MaterialButton
    private lateinit var btnSkip: Button
    private lateinit var dotsLayout: LinearLayout
    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide() // Hides the ActionBar
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        btnNext = findViewById(R.id.btnNext)
        btnPrev = findViewById(R.id.btnPrev)
        btnSkip = findViewById(R.id.btnSkip)
        dotsLayout = findViewById(R.id.dotsLayout)

        // Setup pages
        val items = listOf(
            OnboardingItem(R.drawable.brocollli, "Welcome", "This is page 1"),
            OnboardingItem(R.drawable.ic_launcher_foreground, "Track Your Progress", "This is page 2"),
            OnboardingItem(R.drawable.ic_launcher_foreground, "Get Started", "This is page 3")
        )

        adapter = OnboardingAdapter(items)
        viewPager.adapter = adapter

        setupDots(items.size)
        selectDot(0)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateButtons(position, items.size)
                selectDot(position)
            }
        })

        btnPrev.setOnClickListener {
            if (viewPager.currentItem > 0) viewPager.currentItem -= 1
        }

        btnNext.setOnClickListener {
            if (viewPager.currentItem == adapter.itemCount - 1) {
                goToMain()
            } else {
                viewPager.currentItem += 1
            }
        }

        btnSkip.setOnClickListener {
            goToMain()
        }
    }

    private fun setupDots(count: Int) {
        dotsLayout.removeAllViews()
        for (i in 0 until count) {
            val dot = ImageView(this)
            dot.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dot_unselected))
            val params = LinearLayout.LayoutParams(24, 24)
            params.setMargins(8, 0, 8, 0)
            dotsLayout.addView(dot, params)
        }
    }

    private fun selectDot(index: Int) {
        for (i in 0 until dotsLayout.childCount) {
            val dot = dotsLayout.getChildAt(i) as ImageView
            dot.setImageDrawable(
                ContextCompat.getDrawable(this,
                    if (i == index) R.drawable.dot_selected else R.drawable.dot_unselected
                )
            )
        }
    }

    private fun updateButtons(position: Int, totalPages: Int) {
        btnPrev.visibility = if (position == 0) View.GONE else View.VISIBLE
//        btnNext.text = if (position == totalPages - 1) "Get Started" else "→"
        if (position == totalPages - 1) {
            btnNext.text = getString(R.string.onboarding_get_started) // Use "Get Started" text
            btnNext.icon = null // Remove the icon programmatically
        } else {
            btnNext.text = getString(R.string.onboarding_next) // Use "Next" text
            btnNext.setIconResource(R.drawable.rounded_arrow_forward_24) // Set the icon resource
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}





//package com.amzal.protrackapp.onboarding
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.viewpager2.widget.ViewPager2
//import com.amzal.protrackapp.MainActivity
//import com.amzal.protrackapp.OnboardingAdapter
//import com.amzal.protrackapp.R
//
//class OnboardingActivity : AppCompatActivity() {
//
//    private lateinit var viewPager: ViewPager2
//    private lateinit var adapter: OnboardingAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_onboarding)
//
//        viewPager = findViewById(R.id.viewPager)
//        adapter = OnboardingAdapter(this)
//        viewPager.adapter = adapter
//
//        // 🔹 Listen for page changes
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//
//                // Update buttons depending on page
//                when (position) {
//                    0 -> setupPage1()
//                    1 -> setupPage2()
//                    2 -> setupPage3()
//                }
//            }
//        })
//    }
//
//    // 🔹 Page 1 Buttons
//    private fun setupPage1() {
//        val skip: TextView? = viewPager.findViewById(R.id.btnSkip1)
//        val next: Button? = viewPager.findViewById(R.id.btnNext1)
//
//        skip?.setOnClickListener { goToMain() }
//        next?.setOnClickListener { viewPager.currentItem = 1 }
//    }
//
//    // 🔹 Page 2 Buttons
//    private fun setupPage2() {
//        val skip: TextView? = viewPager.findViewById(R.id.btnSkip2)
//        val next: Button? = viewPager.findViewById(R.id.btnNext2)
//        val prev: Button? = viewPager.findViewById(R.id.btnPrev2)
//
//        skip?.setOnClickListener { goToMain() }
//        next?.setOnClickListener { viewPager.currentItem = 2 }
//        prev?.setOnClickListener { viewPager.currentItem = 0 }
//    }
//
//    // 🔹 Page 3 Buttons
//    private fun setupPage3() {
//        val skip: TextView? = viewPager.findViewById(R.id.btnSkip3)
//        val prev: Button? = viewPager.findViewById(R.id.btnPrev3)
//        val getStarted: Button? = viewPager.findViewById(R.id.btnGetStarted)
//
//        skip?.setOnClickListener { goToMain() }
//        prev?.setOnClickListener { viewPager.currentItem = 1 }
//        getStarted?.setOnClickListener { goToMain() }
//    }
//
//    private fun goToMain() {
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
//    }
//}










//package com.amzal.protrackapp.onboarding



//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.app.AppCompatActivity
//import androidx.viewpager2.widget.ViewPager2
//import com.amzal.protrackapp.MainActivity
//import com.amzal.protrackapp.OnboardingAdapter
//import com.amzal.protrackapp.R
//
//class OnboardingActivity : AppCompatActivity() {
//
//    private lateinit var viewPager: ViewPager2
//    private lateinit var adapter: OnboardingAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_onboarding)
//
//        viewPager = findViewById(R.id.viewPager)
//        adapter = OnboardingAdapter(this)
//        viewPager.adapter = adapter
//
//        // 🔹 Listen for page changes
//        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//
//                // Update buttons depending on page
//                when (position) {
//                    0 -> setupPage1()
//                    1 -> setupPage2()
//                    2 -> setupPage3()
//                }
//            }
//        })
//    }
//
//
//
//    fun goToSignIn() {
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
//    }
//
//    // 🔹 Page 1 Buttons
//    private fun setupPage1() {
//        val skip: TextView? = viewPager.findViewById(R.id.btnSkip1)
//        val next: Button? = viewPager.findViewById(R.id.btnNext1)
//
//        skip?.setOnClickListener { goToMain() }
//        next?.setOnClickListener { viewPager.currentItem = 1 }
//    }
//
//    // 🔹 Page 2 Buttons
//    private fun setupPage2() {
//        val skip: TextView? = viewPager.findViewById(R.id.btnSkip2)
//        val next: Button? = viewPager.findViewById(R.id.btnNext2)
//        val prev: Button? = viewPager.findViewById(R.id.btnPrev2)
//
//        skip?.setOnClickListener { goToMain() }
//        next?.setOnClickListener { viewPager.currentItem = 2 }
//        prev?.setOnClickListener { viewPager.currentItem = 0 }
//    }
//
//    // 🔹 Page 3 Buttons
//    private fun setupPage3() {
//        val skip: TextView? = viewPager.findViewById(R.id.btnSkip3)
//        val prev: Button? = viewPager.findViewById(R.id.btnPrev3)
//        val getStarted: Button? = viewPager.findViewById(R.id.btnGetStarted)
//
//        skip?.setOnClickListener { goToMain() }
//        prev?.setOnClickListener { viewPager.currentItem = 1 }
//        getStarted?.setOnClickListener { goToMain() }
//    }
//
//    private fun goToMain() {
//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
//    }
//}

