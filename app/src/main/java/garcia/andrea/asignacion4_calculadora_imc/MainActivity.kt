package garcia.andrea.asignacion4_calculadora_imc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Declaración de variables
        var txtResultado: TextView = findViewById(R.id.tvResultado)
        var txtEstado: TextView = findViewById(R.id.tvEstado)

        // Declaradión de los editText
        val etEstatura: EditText = findViewById(R.id.etEstatura)
        val etPeso: EditText = findViewById(R.id.etPeso)
        val btnCalcula: Button = findViewById(R.id.btnCalcular)

        btnCalcula.setOnClickListener{
            if(!etEstatura.text.isBlank() || !etPeso.text.isBlank()) {

                // Se calcula el indice de masa corporal y se ubica el resultado
                val imcNum = calculaIMC(etEstatura.text.toString().toDouble(),
                    etPeso.text.toString().toDouble())
                txtResultado.setText(imcNum.toString())

                // Se obtiene el estado del usuario
                val estado = obtenEstado(imcNum)
                txtEstado.setText(estado)

                // Se  le añade el color dependiente del resultado
                when(estado) {
                    "Bajo peso" -> txtEstado.setBackgroundResource(R.color.colorBrown)
                    "Saludable" -> txtEstado.setBackgroundResource(R.color.colorGreen)
                    "Sobrepeso" -> txtEstado.setBackgroundResource(R.color.colorGreenish)
                    "Obesidad de grado 1" -> txtEstado.setBackgroundResource(R.color.colorYellow)
                    "Obesidad de grado 2" -> txtEstado.setBackgroundResource(R.color.colorOrange)
                    "Obesidad de grado 4" -> txtEstado.setBackgroundResource(R.color.colorRed)
                }
            }
        }


    }

    fun calculaIMC(altura: Double, peso: Double): Double {
        val imc: Double = (peso / (Math.pow(altura, 2.0)))
        return imc
    }

    fun obtenEstado(imc: Double): String {
        when {
            imc < 10.5 -> return "Bajo peso"
            imc >= 18.5 && imc <= 24.9 -> return "Saludable"
            imc > 24.9 && imc <= 29.9 -> return "Obesidad de grado 1"
            imc > 29.9 && imc <= 34.9 -> return "Obesidad de grado 2"
            imc >= 40 -> return "Obesidad de grado 3"
        }
        return "error"
    }
}
