package com.example.jmora.webservicesoap;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by JMora on 08/03/2017.
 */

public class Alumno implements KvmSerializable {

    public int dni_alumno;
    public String nombre;
    public String apellido;
    public int edad;
    public String sexo;
    public String divicion;

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

    @Override
    public Object getProperty(int i) {

        switch (i)
        {
            case 0:
                return dni_alumno;
            case 1:
                return nombre;
            case 2:
                return apellido;
            case 3:
                return edad;
            case 4:
                return sexo;
            case 5:
                return divicion;
        }

        return null;
    }

    @Override
    public int getPropertyCount() {
        return 6;
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i)
        {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "dni_alumno";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "nombre";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "apellido";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "edad";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "sexo";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "divicion";
                break;
            default:
                break;
        }
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i)
        {
            case 0:
                dni_alumno = Integer.parseInt(o.toString());
                break;
            case 1:
                nombre = o.toString();
                break;
            case 2:
                apellido = o.toString();
                break;
            case 3:
                edad = Integer.parseInt(o.toString());
                break;
            case 4:
                sexo = o.toString();
                break;
            case 5:
                divicion = o.toString();
                break;
            default:
                break;
        }
    }


}
