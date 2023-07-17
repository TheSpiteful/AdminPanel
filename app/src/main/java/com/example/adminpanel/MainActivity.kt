import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.example.adminpanel.R
import com.example.adminpanel.Requests.GetCellData
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_panel)

        progressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = ProgressBar.INVISIBLE

        val btnGetRequest = findViewById<Button>(R.id.btnGetRequest)
        btnGetRequest.setOnClickListener {
            progressBar.visibility = ProgressBar.VISIBLE
            GlobalScope.launch(Dispatchers.IO) {
                val getCellData = GetCellData()
                val result = getCellData.sendRequest()

                launch(Dispatchers.Main) {
                    progressBar.visibility = ProgressBar.INVISIBLE
                    showResult(result)
                }
            }
        }
    }

    private fun showResult(result: String) {
        val intent = Intent(this, RequestsActivity::class.java)
        intent.putExtra("result", result)
        startActivity(intent)
    }
}
