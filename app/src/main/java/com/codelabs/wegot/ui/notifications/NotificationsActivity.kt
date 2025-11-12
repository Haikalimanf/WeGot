package com.codelabs.wegot.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
// Import AppCompatActivity sebagai ganti Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
// Impor R dari package aplikasi Anda
import com.codelabs.wegot.R
import com.codelabs.wegot.ui.MainActivity
import com.google.android.material.appbar.MaterialToolbar

class NotificationsActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar

    data class NotificationItem(
        val icon: String,
        val title: String,
        val description: String,
        val time: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_notifications)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerNotifications)

        val dummyData = listOf(
            NotificationItem(
                "🐛",
                "Kondisi Maggot Stabil",
                "Populasi maggot pada wadah 3 dalam kondisi baik dan aktif makan.",
                "5 menit yang lalu"
            ),
            NotificationItem(
                "⚠️",
                "Peringatan Suhu Tinggi",
                "Suhu kandang mencapai 34°C. Disarankan menambahkan semprotan air agar tetap lembap.",
                "20 menit yang lalu"
            ),
            NotificationItem(
                "♻️",
                "Sampah Baru Tersedia",
                "Terdeteksi 2 kg sampah organik baru pada tempat sampah dapur.",
                "1 jam yang lalu"
            ),
            NotificationItem(
                "📅",
                "Jadwal Pencacahan Hari Ini",
                "Jangan lupa melakukan pencacahan sampah untuk slot siang sebelum jam 15.00.",
                "3 jam yang lalu"
            ),
        )

        val notificationAdapter = NotificationAdapter(dummyData)
        recyclerView.adapter = notificationAdapter

        recyclerView.layoutManager = LinearLayoutManager(this)

        toolbar = findViewById(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }


    inner class NotificationAdapter(private val items: List<NotificationItem>) :
        RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

        inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val icon: TextView = itemView.findViewById(R.id.notificationIcon)
            val title: TextView = itemView.findViewById(R.id.notificationTitle)
            val description: TextView = itemView.findViewById(R.id.notificationDescription)
            val time: TextView = itemView.findViewById(R.id.notificationTime)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_notification, parent, false)
            return NotificationViewHolder(view)
        }

        override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
            val item = items[position]
            holder.icon.text = item.icon
            holder.title.text = item.title
            holder.description.text = item.description
            holder.time.text = item.time
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }
}