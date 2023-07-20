package com.example.adminpanel

import android.os.Parcel
import android.os.Parcelable

data class Cell(
    val id: String,
    val size: Int,
    var status: String,
    val datetime: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(size)
        parcel.writeString(status)
        parcel.writeString(datetime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cell> {
        override fun createFromParcel(parcel: Parcel): Cell {
            return Cell(parcel)
        }

        override fun newArray(size: Int): Array<Cell?> {
            return arrayOfNulls(size)
        }
    }
}
