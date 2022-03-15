using System;
using WSDL.mensajes;

namespace WSDL.operaciones
{
    public class Operaciones : Mensajes
    {
        public List<string> saludos = new List<string>();
        public string Saludar(string nombre){
            string s = "Hola "+nombre+" te saluda";
            saludos.Add(s);
            return s;
        }
        public string Mostrar(int id){
            return "x";
        }
        public List<string> BuscarSaludos(){
            return saludos;
        }
        public string Login(string usr, string psw){
            return "Hola "+usr;
        }
    }
}