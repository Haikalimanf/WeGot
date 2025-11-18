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
import com.codelabs.wegot.databinding.ActivityNotificationsBinding
import com.codelabs.wegot.databinding.ActivityRegisterBinding
import com.codelabs.wegot.ui.MainActivity
import com.google.android.material.appbar.MaterialToolbar

class NotificationsActivity : AppCompatActivity() {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var binding: ActivityNotificationsBinding

    data class NotificationItem(
        val icon: String,
        val title: String,
        val description: String,
        val time: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerNotifications)
        supportActionBar?.hide()

        val dummyData = listOf(
            NotificationItem(
                "⚠️",
                "Hari Ini Belum Ada Jadwal Aktif",
                "Silakan buat jadwal pencacahan untuk slot siang sebelum jam operasional berakhir.",
                "5 menit yang lalu"
            ),
            NotificationItem(
                "⚠️",
                "Prediksi Siap!",
                "Dari telur 500 g & sampah 60,00 kg, estimasi hasil 1.800 g. Simpan ke riwayat atau buat jadwal.",
                "20 menit yang lalu"
            ),
            NotificationItem(
                "♻️",
                "Saatnya panen",
                "Kandang 2 dan 1 sudah saatnya untuk panen hasil maggot",
                "1 jam yang lalu"
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