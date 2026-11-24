package com.example.kamussederhana

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.menu_tambah -> TambahFragment()
                R.id.menu_cari -> CariFragment()
                R.id.menu_daftar -> DaftarFragment()
                else -> return@setOnItemSelectedListener false
            }
            pasangFragment(fragment)
            true
        }

        // Saat pertama dibuka, pilih menu Tambah.
        // Hanya saat savedInstanceState == null, agar tidak menimpa fragment yang sudah ada
        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.menu_tambah
        }
    }

    private fun pasangFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}