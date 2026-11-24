package com.example.kamussederhana

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class DaftarFragment : Fragment(R.layout.fragment_daftar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = KamusDatabase.getInstance(requireContext()).kataDao()
        val txtJumlah = view.findViewById<TextView>(R.id.txtJumlah)
        val rvDaftar = view.findViewById<RecyclerView>(R.id.rvDaftar)

        val adapter = KataAdapter()
        rvDaftar.layoutManager = LinearLayoutManager(requireContext())
        rvDaftar.adapter = adapter

        // Daftar ikut berubah otomatis saat data ditambah dari tab Tambah
        viewLifecycleOwner.lifecycleScope.launch {
            dao.ambilSemua().collect { daftar ->
                adapter.submitList(daftar)
                txtJumlah.text = "Jumlah kata: ${daftar.size}"
            }
        }
    }
}