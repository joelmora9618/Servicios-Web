package com.example.jmora.webservicesoap;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class AltaAlumnoActivity extends AppCompatActivity implements View.OnClickListener {

    int dniAux,
        edadAux;
    EditText etDni,
             etNombre,
             etApellido,
             etEdad,
             etSexo,
             etDivicion;
    Button   btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_alumno);

        etDni = (EditText)findViewById(R.id.etDni);
        etNombre = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText)findViewById(R.id.etApellido);
        etEdad = (EditText)findViewById(R.id.etEdad);
        etSexo = (EditText)findViewById(R.id.etSexo);
        etDivicion = (EditText)findViewById(R.id.etDivicion);
        btnEnviar = (Button)findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TareaAsincrona tareaAsincrona = new TareaAsincrona();
        tareaAsincrona.execute();
    }

    private class TareaAsincrona extends AsyncTask<String,Integer,Boolean>{

        boolean result = true;

        @Override
        protected Boolean doInBackground(String... params) {

            final String NAMESPACE = "http://tempuri.org/";
            final String URL="http://10.0.2.15:54656/WebService1.asmx";
            final String METHOD_NAME = "NuevoAlumnoSimple";
            final String SOAP_ACTION = "http://tempuri.org/NuevoAlumnoSimple";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            dniAux = Integer.parseInt(etDni.getText().toString());
            edadAux = Integer.parseInt((etEdad.getText().toString()));
            request.addProperty("dni_alumno", dniAux);
            request.addProperty("nombre", etNombre.getText().toString());
            request.addProperty("apellido", etApellido.getText().toString());
            request.addProperty("edad", edadAux);
            request.addProperty("sexo", etSexo.getText().toString());
            request.addProperty("divicion", etDivicion.getText().toString());

            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
                String res = resultado_xml.toString();

                if(!res.equals("1"))
                {
                    result = false;
                }
                //Se procesa el resultado devuelto
                //...
            }
            catch (Exception e)
            {
                result = false;
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(result)
            {
                Toast.makeText(AltaAlumnoActivity.this,"Insertado OK", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(AltaAlumnoActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        }
    }


    }


