package psti.unram.parcelabel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var btnPindahActivity: Button
    private lateinit var btnPindahWithData: Button
    private lateinit var btnSms: Button
    private lateinit var btnParcelable: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPindahActivity = findViewById(R.id.btnPindahActivity)
        btnPindahWithData = findViewById(R.id.btnPindahWithData)
        btnSms = findViewById(R.id.btnSms)
        btnParcelable = findViewById(R.id.btnParcelable)

        btnPindahActivity.setOnClickListener(this)
        btnPindahWithData.setOnClickListener(this)
        btnSms.setOnClickListener(this)
        btnParcelable.setOnClickListener(this)
    }

    override fun onClick(btn: View?) {
        when (btn) {
            btnPindahActivity -> {
                val intent = Intent(this, SubActivity1::class.java)
                startActivity(intent)
            }
            btnPindahWithData -> {
                val intent = Intent(this, SubActivity2::class.java)
                intent.putExtra(SubActivity2.EXTRA_STRING, "Data dari activity main")
                startActivity(intent)
            }
            btnSms -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("smsto:08189234728"))
                startActivity(intent)
            }
            btnParcelable -> {
                val intent = Intent(this, SubActivity2::class.java)
                intent.putExtra(SubActivity2.EXTRA_PARCEL, Mahasiswa("F1D021021", "Sena"))
                startActivity(intent)
            }
        }
    }
}