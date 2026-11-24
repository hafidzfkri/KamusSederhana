package com.example.kamussederhana

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface KataDao {

    @Insert
    suspend fun tambah(kata: Kata)

    // Flow memberi kabar ke layar setiap kali isi tabel berubah
    @Query("SELECT * FROM kata ORDER BY indonesia ASC")
    fun ambilSemua(): Flow<List<Kata>>

    @Query("SELECT * FROM kata WHERE indonesia LIKE :kunci ORDER BY indonesia ASC")
    suspend fun cari(kunci: String): List<Kata>
}