package id.co.sistema.catatanlhj.ROOM.Transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trans_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val income: Int = 0,
    val spend: Int = 0,
    val notes: String,
    val jenis: String,
    val nominal: Int = 0
)