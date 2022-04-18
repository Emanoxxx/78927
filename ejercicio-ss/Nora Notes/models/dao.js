const mysql = require("mysql");
const noraDBCredentials = {
    "host"     : "emanoxxx.com",
    "user"     : process.env['MYSQL_USER'],
    "password" : process.env['MYSQL_PASSWORD'],
    "database" : "NoraNotes"
}
// Agregue las credenciales para acceder a su base de datos
const connection = mysql.createConnection({
    host: noraDBCredentials.host,
    user: noraDBCredentials.user,
    password: noraDBCredentials.password,
    database: noraDBCredentials.database,
});
const pool = mysql.createPool({
    host: noraDBCredentials.host,
    user: noraDBCredentials.user,
    password: noraDBCredentials.password,
    database: noraDBCredentials.database,
});
function connectDB() {
    connection.connect(function (err) {
        // en caso de error
        if (err) {
            console.log(err.code);
            console.log(err.fatal);
            return false;
        }
    });
    return true;
}

function query(sql, values) {
    return new Promise((resolve, reject) => {
        pool.getConnection(function (err, connection) {
            if (err) {
                reject({
                    Resultado: "Error",
                    Message: err,
                    Code: "DB-ERROR-01",
                    archivo: values,
                });
            } else {
                connection.query(sql, values, (err, rows) => {
                    if (err) {
                        reject({
                            Resultado: "Error",
                            Message: err,
                            Code: "DB-ERROR-02",
                            archivo: values,
                        });
                    } else {
                        resolve({
                            Resultado: "Success",
                            Message: "Consulta exitosa",
                            Data: rows,
                            archivo: values,
                        });
                    }
                    connection.release();
                });
            }
        });
    });
}
//Tabla sonido
const obtenerNotas = async (args) => {
    var resultado = null;
    // Realizar una consulta
    $query = "SELECT id,Titulo,Fecha from Nota where Propietario = " +connection.escape(args.Propietario);
    try {
        return await query($query);
    } catch (error) {
        return error;
    }
};
const getNota = async (args) => {
    var resultado = null;
    // Realizar una consulta
    $query =
        "SELECT * from Nota where id= " +
        connection.escape(args.id +"");
    try {
        return await query($query);
    } catch (error) {
        return error;
    }
};
const addNota = async (args) => {
    var resultado = null;
    // Realizar una consulta
    $query =
        "insert into Nota (Titulo, Fecha, Contenido, Propietario) values (" +
        connection.escape(args.Titulo) +
        "," +
        connection.escape(args.Fecha) +
        "," +
        connection.escape(args.Contenido) +
        "," +
        connection.escape(args.Propietario) +
        ")";
    console.log($query);
    try {
        return await query($query);
    } catch (error) {
        return error;
    }
};
const deleteNota = async (args) => {
    var resultado = null;
    // Realizar una consulta
    $query = "Delete from Nota where id = " + connection.escape(args.id);
    try {
        return await query($query);
    } catch (error) {
        return error;
    }
};
const modifyNota = async (args) => {
    var resultado = null;
    // Realizar una consulta
    $query =
        "UPDATE Nota set Titulo=" +
        connection.escape(args.Titulo) +
        ", Fecha=" +
        connection.escape(args.Fecha) +
        ", Contenido=" +
        connection.escape(args.Contenido) +
        " where id = " +
        connection.escape(args.id);
    try {
        return await query($query);
    } catch (error) {
        return error;
    }
};
//Tabla Nota
exports.getNota = getNota;
exports.obtenerNotas = obtenerNotas;
exports.addNota = addNota;
exports.deleteNota = deleteNota;
exports.modifyNota = modifyNota;
