'use strict';
window.addEventListener('DOMContentLoaded', () => {
	getCarreras();
	getEstudiantes();
});


let carreras = [];
let estudiantes = [];

let btnCarreras = document.getElementById("btnCarreras");
btnCarreras.addEventListener("click", () => {
	if(carreras.length != 0) {		
		listar(carreras);
	}
}); 

let btnEstudiantes = document.getElementById("btnEstudiantes");
btnEstudiantes.addEventListener("click", () => {
	if(estudiantes.length != 0) {		
		listar(estudiantes);
	}
});

function getCarreras() {
	let endpoint = 'http://localhost:8080/Entregable3/rest/carreras'; 
    fetch(endpoint)
    .then(response => {
            return response.json();
    }).then(r => {
        if(r != null){
			for (let item of r) {	
				carreras.push(item);				
  			}       		
        }
    }).catch(error => console.log(error));
}

function getEstudiantes() {
	let endpoint = 'http://localhost:8080/Entregable3/rest/estudiantes'; 
    fetch(endpoint)
    .then(response => {
            return response.json();
    }).then(r => {
        if(r != null){
			for (let item of r) {	
				estudiantes.push(item);				
  			}       		
        }
    }).catch(error => console.log(error));
}

function listar(array){
	let respuesta = document.getElementById("respuesta");
	respuesta.innerHTML = '';
	
	for(let item of array) {
       	respuesta.innerHTML += 
  		`<li>${item.nombre}</li>`;
	}
}

let btnCrearEstudiante = document.getElementById("crearEstudiante");
btnCrearEstudiante.addEventListener("click", postEstudiante);
function postEstudiante(){
	let dni = document.getElementById("dni").value;
	let nombre = document.getElementById("nombre").value;
	let apellido = document.getElementById("apellido").value;
	let anios = document.getElementById("anios").value;
	let genero = document.getElementById("genero").value;
	let ciudad = document.getElementById("ciudad").value;
	let nroLibreta = document.getElementById("nroLibreta").value;
	
	let endpoint = 'http://localhost:8080/Entregable3/rest/estudiantes';
	
	let alumno = {
		"dni" : dni,
		"nombre" : nombre,
		"apellido" : apellido,
		"anios" : anios,
		"genero" : genero, 
		"nroLibreta" : nroLibreta,
		"ciudad" : ciudad
	};
	
	console.log(alumno);
	fetch(endpoint, {
            "method": 'POST',
            "headers": { "Content-Type": "application/json" },
            "body": JSON.stringify(alumno)
        }).then(function (r) {
            if (!r.ok) {
				console.log("r no ok");
                //alert("Error al enviar los datos, intente nuevamente");
            }
            else {
				console.log("r ok");
			}
        }).then(function () {
            console.log("segundo then ");
        }).catch(function (e) {
            console.log(e);
        });



}

let btnCrearCarrera = document.getElementById("crearCarrera");
btnCrearCarrera.addEventListener("click", postCarrera);
function postCarrera(){

	let nombre = document.getElementById("nombreCarrera").value;
	
	let endpoint = 'http://localhost:8080/Entregable3/rest/carreras';
	
	let nuevaCarrera = {
		"nombre" : nombre
	};
	
	console.log(nuevaCarrera);
	fetch(endpoint, {
            "method": 'POST',
            "headers": { "Content-Type": "application/json" },
            "body": JSON.stringify(nuevaCarrera)
        }).then(function (r) {
            if (!r.ok) {
				console.log("r no ok");
                //alert("Error al enviar los datos, intente nuevamente");
            }
            else {
				console.log("r ok");
			}
        }).then(function () {
            console.log("segundo then ");
        }).catch(function (e) {
            console.log(e);
        });



}