using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace WebServiceTutorial
{
    public class Alumno
    {
        public int dni_alumno { get; set; }
        public String nombre{ get; set; }
        public String apellido { get; set; }
        public int edad { get; set; }
        public String sexo { get; set; }
        public String divicion { get; set; }

        public Alumno()
        {
            this.dni_alumno = 0;
            this.nombre = "";
            this.apellido = "";
            this.edad = 0;
            this.sexo = "";
            this.divicion = "";
        }

        public Alumno(int dni_alumno, String nombre, String apellido, int edad, String sexo, String divicion)
        {
            this.dni_alumno = dni_alumno;
            this.nombre = nombre;
            this.apellido = apellido;
            this.edad = edad;
            this.sexo = sexo;
            this.divicion = divicion;
        }

    }

}