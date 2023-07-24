package com.example.adminpanel

import android.os.Parcel
import android.os.Parcelable

    // Клас Cell, який реалізує інтерфейс Parcelable
data class Cell(
    val id: String,
    val size: Int,
    var status: String,
    val datetime: String
) : Parcelable {

    // Другий конструктор, який створює об'єкт Cell з даних, переданих через Parcel
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    // Функція для запису даних класу в Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(size)
        parcel.writeString(status)
        parcel.writeString(datetime)
    }

    // Функція яка повертає допустимий тип опису об'єкта
    override fun describeContents(): Int {
        return 0
    }

    // Об'єкт сповіщувач, що реалізує інтерфейс Parcelable.Creator<Cell>
    companion object CREATOR : Parcelable.Creator<Cell> {

    // Функція для створення об'єкта Cell з Parcel
        override fun createFromParcel(parcel: Parcel): Cell {
            return Cell(parcel)
        }

    // Функція для створення масиву об'єктів Cell заданого розміру
        override fun newArray(size: Int): Array<Cell?> {
            return arrayOfNulls(size)
        }
    }
}
