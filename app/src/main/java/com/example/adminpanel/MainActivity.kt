import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adminpanel.Requests.GetCellData
import com.example.adminpanel.R
import com.example.adminpanel.Adapter.CellAdapter
import com.example.adminpanel.Cell
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_panel)

        val btnGetRequest = findViewById<Button>(R.id.btnGetRequest)
        btnGetRequest.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val getCellData = GetCellData()
                val result = getCellData.sendRequest()

                launch(Dispatchers.Main) {
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

class RequestsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cellAdapter: CellAdapter
    private lateinit var cells: ArrayList<Cell>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cells_result)

        recyclerView = findViewById(R.id.recyclerViewCells)
        recyclerView.layoutManager = LinearLayoutManager(this)

        cells = ArrayList()
        cellAdapter = CellAdapter(cells)
        recyclerView.adapter = cellAdapter

        val result = intent.getStringExtra("result")
        if (result != null) {
            processResponse(result)
        }
    }

    private fun processResponse(responseData: String) {
        try {
            val jsonResponse = JSONObject(responseData)
            val jsonCells = jsonResponse.getJSONArray("cells")

            cells.clear()

            for (i in 0 until jsonCells.length()) {
                val jsonCell = jsonCells.getJSONObject(i)
                val id = jsonCell.getString("id")
                val size = jsonCell.getInt("size")
                val status = jsonCell.getString("status")
                val datetime = jsonCell.getLong("datetime")

                val cell = Cell(id, size, status, datetime)
                cells.add(cell)
            }

            cellAdapter.notifyDataSetChanged()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
