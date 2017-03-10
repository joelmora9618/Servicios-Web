using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace WebServiceTutorial
{
    /// <summary>
    /// Summary description for WebService1
    /// </summary>
    [WebService(Namespace = "http://joelmora.net/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class WebService1 : System.Web.Services.WebService
    {

        [WebMethod]
        public void NuevoAlumnoSimple(int dni_alumno, string nombre, string apellido, int edad, string sexo, string divicion)
        {
            SqlConnection con = new SqlConnection();

            con.ConnectionString = "Data Source=WKS831L;" +
                                   "Initial Catalog=practica7;" +
                                   "Integrated Security=SSPI;";
            con.Open();

            string sql = "INSERT INTO alumno (dni_alumno, nombre, apellido, edad, sexo, divicion) VALUES (@dni_alumno, @nombre, @apellido, @edad, @sexo, @divicion)";

            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.Add("@dni_alumno", System.Data.SqlDbType.Int).Value = dni_alumno;
            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.NVarChar).Value = nombre;
            cmd.Parameters.Add("@apellido", System.Data.SqlDbType.NVarChar).Value = apellido;
            cmd.Parameters.Add("@edad", System.Data.SqlDbType.Int).Value = edad;
            cmd.Parameters.Add("@sexo", System.Data.SqlDbType.NVarChar).Value = sexo;
            cmd.Parameters.Add("@divicion", System.Data.SqlDbType.NVarChar).Value = divicion;

            cmd.ExecuteNonQuery();

            con.Close();
        }

        [WebMethod]
        public Alumno[] ListadoAlumnos()
        {
            SqlConnection con = new SqlConnection();

            con.ConnectionString = "Data Source=WKS831L;" +
                                   "Initial Catalog=practica7;" +
                                   "Integrated Security=SSPI;";
            con.Open();

            string sql = "SELECT dni_alumno, nombre, apellido, edad, sexo, divicion FROM alumno";

            SqlCommand cmd = new SqlCommand(sql, con);

            SqlDataReader reader = cmd.ExecuteReader();

            List<Alumno> lista = new List<Alumno>();

            while (reader.Read())
            {
                lista.Add(
                     new Alumno(reader.GetInt32(0),
                                reader.GetString(1),
                                reader.GetString(2),
                                reader.GetInt32(3),
                                reader.GetString(4),
                                reader.GetString(5)));
            }

            con.Close();

            return lista.ToArray();
        }

        [WebMethod]
        public int NuevoAlumnoObjeto(Alumno alumno)
        {
            SqlConnection con = new SqlConnection();

            con.ConnectionString = "Data Source=WKS831L;" +
                                   "Initial Catalog=practica7;" +
                                   "Integrated Security=SSPI;";
            con.Open();

            string sql = "INSERT INTO alumno (dni_alumno, nombre, apellido, edad, sexo, divicion) VALUES (@dni_alumno, @nombre, @apellido, @edad, @sexo, @divicion)";

            SqlCommand cmd = new SqlCommand(sql, con);

            cmd.Parameters.Add("@dni_alumno", System.Data.SqlDbType.Int).Value = alumno.dni_alumno;
            cmd.Parameters.Add("@nombre", System.Data.SqlDbType.NVarChar).Value = alumno.nombre;
            cmd.Parameters.Add("@apellido", System.Data.SqlDbType.NVarChar).Value = alumno.apellido;
            cmd.Parameters.Add("@edad", System.Data.SqlDbType.Int).Value = alumno.edad;
            cmd.Parameters.Add("@sexo", System.Data.SqlDbType.NVarChar).Value = alumno.sexo;
            cmd.Parameters.Add("@divicion", System.Data.SqlDbType.NVarChar).Value = alumno.divicion;

            int res = cmd.ExecuteNonQuery();

            con.Close();

            return res;
        }

    }
}
