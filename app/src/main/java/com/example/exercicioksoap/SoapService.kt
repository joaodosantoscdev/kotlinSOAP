import com.example.exercicioksoap.Position
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class SoapService {

    companion object {
        private const val URL = "https://webhook.site/40136fce-2acf-4505-b8eb-a01780e009f0"
    }

    fun sendPosition(position: Position): String? {
        return try {
            // Cria a solicitação SOAP
            val request = SoapObject("", "")

            // Adiciona propriedades ao objeto SoapObject
            request.addProperty("position", position)

            // Cria o envelope SOAP
            val envelope = SoapSerializationEnvelope(SoapSerializationEnvelope.VER11)
            envelope.setOutputSoapObject(request)

            // Cria o transporte HTTP
            val transport = HttpTransportSE(URL)
            transport.call("", envelope) // URL de ação em branco

            // Obtém a resposta (se houver)
            envelope.response?.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
