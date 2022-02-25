using System;
using WSDL.mensajes;

namespace WSDL.operaciones
{
    public class Operaciones : Mensajes
    {
        public string Saludar(string nombre){
            string s = "Hola"+nombre;
            return s;
        }
        public string Mostrar(int id){
            return "x";
        }
        public string Login(string usr, string psw){
            return "Hola "+usr;
        }
    }
}