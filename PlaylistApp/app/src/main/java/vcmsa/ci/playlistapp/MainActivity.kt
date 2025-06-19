package vcmsa.ci.playlistapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val songs = ArrayList<String>()
    private val artists = ArrayList<String>()
    private val ratings = ArrayList<Int>()
    private val comments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnViewDetails = findViewById<Button>(R.id.btnViewDetails)
        val btnExit = findViewById<Button>(R.id.btnExit)

        btnAdd.setOnClickListener {
            val songTitle = findViewById<EditText>(R.id.etSong).text.toString().trim()
            val artistName = findViewById<EditText>(R.id.etArtist).text.toString().trim()
            val ratingStr = findViewById<EditText>(R.id.etRating).text.toString().trim()
            val comment = findViewById<EditText>(R.id.etComment).text.toString().trim()

            try {
                val rating = ratingStr.toInt()

                if (songTitle.isEmpty() || artistName.isEmpty() || comment.isEmpty()) {
                    Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (rating < 1 || rating > 5) {
                    Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (songs.size < 4) {
                    songs.add(songTitle)
                    artists.add(artistName)
                    ratings.add(rating)
                    comments.add(comment)
                    Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Playlist full (4 songs only)", Toast.LENGTH_SHORT).show()
                }

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid rating (must be a number)", Toast.LENGTH_SHORT).show()
            }
        }

        btnViewDetails.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putStringArrayListExtra("songs", songs)
            intent.putStringArrayListExtra("artists", artists)
            intent.putExtra("ratings", ratings)
            intent.putStringArrayListExtra("comments", comments)
            startActivity(intent)
        }

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}
