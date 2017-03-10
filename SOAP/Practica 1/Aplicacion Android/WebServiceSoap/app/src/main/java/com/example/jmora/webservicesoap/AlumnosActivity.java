package com.example.jmora.webservicesoap;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.Proxy;

public class AlumnosActivity extends AppCompatActivity {

    ListView lvAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        lvAlumnos = (ListView)findViewById(R.id.lvAlumnos);

        TareaConsulta tareaConsulta = new TareaConsulta();
        tareaConsulta.execute();
    }

    private class TareaConsulta extends AsyncTask<String,Integer,Boolean>
    {
        boolean resul = true;
        private Alumno[] listaAlumnos;

        @Override
        protected Boolean doInBackground(String... params) {

            final String NAMESPACE = "http://joelmora.net/";
            final String URL="http://joelmora.net/WebService1.asmx";
            final String METHOD_NAME = "ListadoAlumnos";
            final String SOAP_ACTION = "http://joelmora.net/ListadoAlumnos";

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope envelope =
                    new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapObject resSoap =(SoapObject)envelope.getResponse();

                listaAlumnos = new Alumno[resSoap.getPropertyCount()];

                for (int i = 0; i < listaAlumnos.length; i++)
                {
                    SoapObject ic = (SoapObject)resSoap.getProperty(i);

                    Alumno cli = new Alumno();
                    cli.dni_alumno = Integer.parseInt(ic.getProperty(0).toString());
                    cli.nombre = ic.getProperty(1).toString();
                    cli.apellido = ic.getProperty(2).toString();
                    cli.edad = Integer.parseInt(ic.getProperty(3).toString());
                    cli.sexo = ic.getProperty(4).toString();
                    cli.divicion = ic.getProperty(5).toString();

                    listaAlumnos[i] = cli;
                }
            }
            catch (Exception e)
            {
                resul = false;
            }

            return resul;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (resul)
            {
                //Rellenamos la lista con los nombres de los clientes
                final String[] datos = new String[listaAlumnos.length];

                for(int i=0; i<listaAlumnos.length; i++)
                    datos[i] = listaAlumnos[i].nombre;

                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>(AlumnosActivity.this,
                                android.R.layout.simple_list_item_1, datos);

                lvAlumnos.setAdapter(adaptador);
            }
            else
            {
                Toast.makeText(AlumnosActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
