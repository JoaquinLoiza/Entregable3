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
let selectCarreras = document.getElementById("carreras");
let selectEstudiantes = document.getElementById("estudiantes");
let btnAsignar = document.getElementById("asignar");
let btnBuscarLU=document.getElementById("btnBuscarLU");
let btnGenero=document.getElementById("btnGenero");
//---------Event Listeners---------
btnAsignar.addEventListener("click", asignarCarrera);
btnDto.addEventListener("click", reporte);
btnBy.addEventListener("click", getbycarrerabyciudad);
btnCrearEstudiante.addEventListener("click", postEstudiante);
btnBuscarLU.addEventListener("click", getEstudianteByLU);
btnCrearCarrera.addEventListener("click", postCarrera);
btnGenero.addEventListener("click", getByGenero);
btnEstudiantes.addEventListener("click", () => listar(estudiantes));
selectCarreras.addEventListener("click", () => rellenarSelects(carreras, estudiantes));
btnCarreras.addEventListener("click", () => {	
	listar(carreras);
	rellenarSelects(carreras, estudiantes);
});

//---------Metodos---------

function getByGenero(){
	let div = document.getElementById("divGenero");
	let genero=document.getElementById("generoF").value;
	div.innerHTML='';
	let endpoint = url+'estudiantes/genero/'+genero;
	fetch(endpoint)
    .then(response => {
            return response.json();
    }).then(r => {
        if(r != null){
			for (let item of r) {	
				div.innerHTML+=`<li>${item.nombre}</li>`;				
  			}       		
        }
    }).catch(error => console.log(error));
}
	

function getEstudianteByLU(){
	let div = document.getElementById("divLibreta");
	let inputLibreta=document.getElementById("inputNroLU").value;

	let endpoint = url+'estudiantes/nroLibreta/'+inputLibreta;
	
	
	fetch(endpoint)
    .then(response => {
            return response.json();
    }).then(r => {
        if(r != null){
				div.innerHTML+= `<li>${r.nombre}</li>`;      		
        }
    }).catch(error => console.log(error));
	
}
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
	let div = document.getElementById("respuesta");
    fetch(endpoint)
    .then(response => {
            return response.json();
    }).then(r => {
        if(r != null){
		div.innerHTML = "";
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

function rellenarSelects(carreras, estudiantes){
	selectCarreras.innerHTML = '';
	selectEstudiantes.innerHTML = '';
	for(let item of carreras) {
       	selectCarreras.innerHTML += 
  		`<option value="${item.idCarrera}">${item.nombre}</option>`;
	}
	for(let item of estudiantes) {
       	selectEstudiantes.innerHTML += 
  		`<option value="${item.dni}">${item.dni}</option>`;
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

function asignarCarrera(){
	let idEstudiante = selectEstudiantes.value;
	let idCarrera = selectCarreras.value;
	let anio = document.getElementById("anioGraduacion").value;
	let anios = document.getElementById("anioAntiguedad").value;
	let g = parseInt(divGenero);
	
	let estudiante = estudiantes.find(element => element.dni = idEstudiante);
	let carrera = carreras.find(element => element.idCarrera = idCarrera);

	let carreraEstudiante = {
		"estudiante": {
			"dni": estudiante.dni,
			"nombre": estudiante.nombre,
			"apellido": estudiante.apellido,
			"anios": estudiante.anios,
			"genero": estudiante.genero,
			"nroLibreta": estudiante.nroLibreta,
			"cuidad": estudiante.cuidad
		},
		"carrera": {
			"idCarrera": carrera.idCarrera,
			"nombre": carrera.nombre,
			"carreraEstudiante": []
		},
		"graduado": g,
		"aniosAntiguedad": anios,
		"anioGraduacion": anio
	}
	
	console.log(carreraEstudiante);
	fetch(url+'carreraEstudiante', {
            "method": 'POST',
            "headers": { "Content-Type": "application/json" },
            "body": JSON.stringify(carreraEstudiante)
        }).then(function (r) {
			console.log(r);
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