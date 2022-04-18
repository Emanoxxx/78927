/*jslint node: true */
"use strict";


var soap = require('soap');
var express = require('express');
var fs = require('fs');
const dao=require('./models/dao');

// the splitter function, used by the service
function splitter_function(args) {
    console.log('splitter_function');
    console.log(args);
    var splitter = args.Contenido;
    var splitted_msg = args.message.split(splitter);
    var result = [];
    for(var i=0; i<splitted_msg.length; i++){
      result.push(splitted_msg[i]);
    }
    return {
        result: result
        }
}
async function add_function(args) {
  var result = await dao.addNota(args);
  return {
      result: result
      }
}
async function delete_function(args) {
  var result = await dao.deleteNota(args);
  return {
    result: result
    }
}
async function mofify_function(args) {
  var result = await dao.modifyNota(args);
  return {
    result: result
  }
}
async function get_function(args) {
  var result = await dao.getNota(args);
  return {
    Nota: result.Data
  }
}
async function obtenerNotas_function(args) {
  var result = await dao.obtenerNotas(args);
  var notas = result.Data;
  return {Result: result.Resultado,Message: result.Message,Nota: notas}
}

// the service
var serviceObject = {
  NoraNotesService: {
        NoraNotesServiceSoapPort: {
            AddNote: add_function,
            DeleteNote: delete_function,
            ModifyNote: mofify_function,
            GetNote: get_function,
            ObtenerNotas: obtenerNotas_function
        }
    }
};

// load the WSDL file
var xml = fs.readFileSync('service.wsdl', 'utf8');
// create express app
var app = express();



// Launch the server and listen
var port = 8000;
app.listen(port, function () {
  console.log('Listening on port ' + port);
  var wsdl_path = "/noranotes";
  soap.listen(app, wsdl_path, serviceObject, xml);
  console.log("Check http://localhost:" + port + wsdl_path +"?wsdl to see if the service is working");
});