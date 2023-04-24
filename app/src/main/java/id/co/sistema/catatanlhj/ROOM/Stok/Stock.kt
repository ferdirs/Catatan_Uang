package id.co.sistema.catatanlhj.ROOM.Stok

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "stock_table")
data class Stock(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nama_item:String,
    val harga_item:Int,
    val quantity: Int
):Parcelable
