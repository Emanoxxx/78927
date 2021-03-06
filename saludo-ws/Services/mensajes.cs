using System;
using System.ServiceModel;
namespace WSDL.mensajes{
    [ServiceContract] public interface Mensajes {
        [OperationContract]
        string Saludar(string nombre);
        [OperationContract]
        string Mostrar(int id);
        [OperationContract]
        List<string> BuscarSaludos();
        [OperationContract]
        string Login(string usr, string psw);
    }
}
