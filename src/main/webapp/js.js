'use strict';
window.addEventListener('DOMContentLoaded', () => {
	getCarreras();
	getEstudiantes();
});

//---------Variables---------
const url = 'http://localhost:8080/Entregable3/rest/';
let carreras = [];
let estudiantes = [];
let btnCarreras = document.getElementById("btnCarreras");
let btnEstudiantes = document.getElementById("btnEstudiantes");
let btnCrearEstudiante = document.getElementById("crearEstudiante");
let btnCrearCarrera = document.getElementById("crearCarrera");
let btnDto = document.getElementById("btndto");
let btnBy = document.getElementById("btnby");
//---------Event Listeners---------
btnDto.addEventListener("click", reporte);
btnBy.addEventListener("click", getbycarrerabyciudad);
btnCrearEstudiante.addEventListener("click", postEstudiante);
btnCrearCarrera.addEventListener("click", postCarrera);
btnCarreras.addEventListener("click", () => {
	if(carreras.length != 0) {		
		listar(carreras);
	}
}); 

btnEstudiantes.addEventListener("click", () => {
	if(estudiantes.length != 0) {		
		listar(estudiantes);
	}
});

//---------Metodos---------
function getCarreras() {
	let endpoint = url+'carreras'; 
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

function reporte() {
	let endpoint = url+'carreraEstudiante/reporte';
	let div = document.getElementById("divdto");
    fetch(endpoint)
    .then(response => {
            return response.json();
    }).then(r => {
        if(r != null){
			for (let item of r) {
				div.innerHTML += `<li> Carrera = ${item.c.nombre}</li>`;
				div.innerHTML += `<p>Inscriptos = `;
				for(let x of item.inscriptos){
					div.innerHTML += `${x.nombre}.`;
				}
				for(let y of item.anioGraduados){
					div.innerHTML += `<p>${y}</p>`;
				}	
  			}       		
        }
    }).catch(error => console.log(error));
}

function getbycarrerabyciudad(){
	let div = document.getElementById("divbusqueda");
	let inputCarrera = document.getElementById("inputidcarrera").value;
	let inputCuidad = document.getElementById("inputciudad").value;
	let endpoint = url+'estudiantes/'+inputCarrera+'/'+inputCuidad;
	
	div.innerHTML = endpoint;
	
	fetch(endpoint)
    .then(response => {
            return response.json();
    }).then(r => {
        if(r != null){
			for (let item of r) {
				div.innerHTML+= `<li>${item.nombre}</li>`;
  			}       		
        }
    }).catch(error => console.log(error));
}
function getEstudiantes() {
	let endpoint = url+'estudiantes'; 
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

function postEstudiante(){
	
	let dni = document.getElementById("dni").value;
	let nombre = document.getElementById("nombre").value;
	let apellido = document.getElementById("apellido").value;
	let anios = document.getElementById("anios").value;
	let genero = document.getElementById("genero").value;
	let ciudad = document.getElementById("ciudad").value;
	let nroLibreta = document.getElementById("nroLibreta").value;
	
	let alumno = {
		"dni" : dni,
		"nombre" : nombre,
		"apellido" : apellido,
		"anios" : anios,
		"genero" : genero, 
		"nroLibreta" : nroLibreta,
		"cuidad" : ciudad
	};
	
	fetch(url+'estudiantes', {
            "method": 'POST',
            "headers": { "Content-Type": "application/json" },
            "body": JSON.stringify(alumno)
        }).then(function (r) {
            if (!r.ok) {
                alert("Error al enviar los datos, intente nuevamente");
            }
            else {
				console.log("r ok");
			}
        }).then(function () {

        }).catch(function (e) {
            console.log(e);
        });
}


function postCarrera(){

	let nombre = document.getElementById("nombreCarrera").value;
	
	let nuevaCarrera = {
		"nombre" : nombre
	};
	
	fetch(url+'carreras', {
            "method": 'POST',
            "headers": { "Content-Type": "application/json" },
            "body": JSON.stringify(nuevaCarrera)
        }).then(function (r) {
            if (!r.ok) {
                alert("Error al enviar los datos, intente nuevamente");
            }
            else {
				console.log("r ok");
			}
        }).then(function () {
	
        }).catch(function (e) {
            console.log(e);
        });
}