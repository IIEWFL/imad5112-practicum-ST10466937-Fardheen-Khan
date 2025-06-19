package vcmsa.ci.playlistapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var tvOutput: TextView
    private lateinit var btnDisplaySongs: Button
    private lateinit var btnAverage: Button
    private lateinit var btnReturn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        tvOutput = findViewById(R.id.tvOutput)
        btnDisplaySongs = findViewById(R.id.btnDisplaySongs)
        btnAverage = findViewById(R.id.btnAverage)
        btnReturn = findViewById(R.id.btnReturn)

        val songs = intent.getStringArrayListExtra("songs") ?: arrayListOf()
        val artists = intent.getStringArrayListExtra("artists") ?: arrayListOf()
        val ratings = intent.getSerializableExtra("ratings") as? ArrayList<Int> ?: arrayListOf()
        val comments = intent.getStringArrayListExtra("comments") ?: arrayListOf()

        btnDisplaySongs.setOnClickListener {
            val builder = StringBuilder()
            for (i in songs.indices) {
                builder.append("🎵 ${songs[i]} by ${artists[i]}\n⭐ Rating: ${ratings[i]}/5\n💬 Comment: ${comments[i]}\n\n")
            }
            tvOutput.text = builder.toString()
        }

        btnAverage.setOnClickListener {
            if (ratings.isNotEmpty()) {
                var total = 0
                for (rate in ratings) {
                    total += rate
                }
                val avg = total.toDouble() / ratings.size
                tvOutput.text = "📊 Average Rating: %.2f / 5".format(avg)
            } else {
                tvOutput.text = "No songs rated yet."
            }
        }

        btnReturn.setOnClickListener {
            finish()
        }
    }
}
