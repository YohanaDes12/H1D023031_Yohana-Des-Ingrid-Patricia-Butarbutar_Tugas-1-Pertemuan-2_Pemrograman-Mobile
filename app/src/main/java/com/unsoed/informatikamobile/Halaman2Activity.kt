
package com.unsoed.informatikamobile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.unsoed.informatikamobile.databinding.ActivityHalaman2Binding

class Halaman2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHalaman2Binding
    private val latitude = "-7.429427"
    private val longitude = "109.338082"
    private val gMapsUrl = "http://maps.google.com/maps?q=loc:"
    private val packageMaps = "com.google.android.apps.maps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHalaman2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initListener()
    }

    private fun initLayout() {
        // Set icon dan text untuk layout location
        val locationIcon = binding.layoutLocation.root.findViewById<ImageView>(R.id.img_icon)
        val locationText = binding.layoutLocation.root.findViewById<TextView>(R.id.tv_layout)
        locationIcon.setImageResource(R.drawable.ic_location)
        locationText.setText(R.string.alamat)

        // Set icon dan text untuk layout email
        val emailIcon = binding.layoutEmail.root.findViewById<ImageView>(R.id.img_icon)
        val emailText = binding.layoutEmail.root.findViewById<TextView>(R.id.tv_layout)
        emailIcon.setImageResource(R.drawable.ic_email)
        emailText.setText(R.string.email)

        // Set icon dan text untuk layout Instagram
        val igIcon = binding.layoutIg.root.findViewById<ImageView>(R.id.img_icon)
        val igText = binding.layoutIg.root.findViewById<TextView>(R.id.tv_layout)
        igIcon.setImageResource(R.drawable.ic_himpunan)
        igText.setText(R.string.ig_himpunan)

        // Set icon dan text untuk layout phone
        val phoneIcon = binding.layoutPhone.root.findViewById<ImageView>(R.id.img_icon)
        val phoneText = binding.layoutPhone.root.findViewById<TextView>(R.id.tv_layout)
        phoneIcon.setImageResource(R.drawable.ic_phone)
        phoneText.setText(R.string.telepon)

        // Set icon dan text untuk layout website
        val websiteIcon = binding.layoutWebsite.root.findViewById<ImageView>(R.id.img_icon)
        val websiteText = binding.layoutWebsite.root.findViewById<TextView>(R.id.tv_layout)
        websiteIcon.setImageResource(R.drawable.ic_himpunan)
        websiteText.setText(R.string.website)

        // Set icon dan text untuk layout facebook
        val facebookIcon = binding.layoutFacebook.root.findViewById<ImageView>(R.id.img_icon)
        val facebookText = binding.layoutFacebook.root.findViewById<TextView>(R.id.tv_layout)
        facebookIcon.setImageResource(R.drawable.ic_himpunan)
        facebookText.setText(R.string.facebook)
    }

    private fun initListener() {
        // Open Maps
        binding.layoutLocation.root.setOnClickListener {
            val gMapsIntentUri = "$gMapsUrl$latitude,$longitude".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, gMapsIntentUri)
            startActivity(mapIntent.setPackage(packageMaps))
        }

        // Open Instagram
        binding.layoutIg.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = getString(R.string.ig_himpunan).toUri()
            }
            startActivity(intent)
        }

        // Open Email
        binding.layoutEmail.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${getString(R.string.email)}")
            }
            startActivity(intent)
        }

        // Dial Phone
        binding.layoutPhone.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${getString(R.string.telepon)}")
            }
            startActivity(intent)
        }

        // Website
        binding.layoutWebsite.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://informatika.unsoed.ac.id")
            }
            startActivity(intent)
        }

        // Facebook
        binding.layoutFacebook.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.facebook.com/informatikaunsoed")
            }
            startActivity(intent)
        }

        // Back button
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}