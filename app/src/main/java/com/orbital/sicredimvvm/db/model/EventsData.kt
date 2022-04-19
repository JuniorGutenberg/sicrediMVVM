package com.orbital.sicredimvvm.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Eventos")
@Parcelize
class EventsData(
    @PrimaryKey(autoGenerate = true) val idPrimary: Int,
    @SerializedName("id") var id:String?,
    @SerializedName("title") var title:String?,
    @SerializedName("price") var price:Double?,
    @SerializedName("latitude") var latitude:Double?,
    @SerializedName("longitude") var longitude:Double?,
    @SerializedName("image") var image:String?,
    @SerializedName("description") var description:String?,
    @SerializedName("date") var data:Long?
):Parcelable