window.addEventListener('DOMContentLoaded', event => {

	cargarViajes();
	cargarUsuarios()

	//	$('#usuarios').DataTable();
	// Simple-DataTables
	// https://github.com/fiduswriter/Simple-DataTables/wiki

	/*const datatablesSimple = document.getElementById('usuarios');
	if (datatablesSimple) {
		new simpleDatatables.DataTable(datatablesSimple);
	}*/
	
});

async function cargarViajes() {

	const request = await fetch('api/viajes', {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			// 'Authorization': localStorage.token
		}
	});
	const viajes = await request.json();

	console.log('viajes: ', viajes);

	let listadoHtml = '';

	for (let viaje of viajes) {
		let viajes = 'viajes';

		let btnEliminar = `<a href="#" onClick="eliminar('${viajes}', '${viaje.id}')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>` + ` `;

		let btndetalle = '<a href="detalle.html?id=' + viaje.id + '" class="btn btn-info btn-circle btn-sm"><i class="fas fa-info-circle"></i></a>';

		let dueno = viaje.dueno == null ? ' - ' : viaje.dueno;

		let viajeHTML = '<tr><td>' + viaje.coche + '</td><td>' + viaje.horario + '</td><td>' + dueno + '</td><td>' + btnEliminar + btndetalle + '</td></tr>';

		listadoHtml += viajeHTML;
	}

	document.querySelector('#viajes tbody').outerHTML = listadoHtml;
}

async function cargarUsuarios() {

	const request = await fetch('api/usuarios', {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			// 'Authorization': localStorage.token
		}
	});
	const usuarios = await request.json();

	let listadoHtml = '';

	for (let usuario of usuarios) {

		let usuarios = 'usuarios';
		
		let btnEliminar = `<a href="#" onClick="eliminar('${usuarios}', '${usuario.id}')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>` + ` `;

		let btndetalle = '<a href="detalle.html?id=' + usuario.id + '" class="btn btn-info btn-circle btn-sm"><i class="fas fa-info-circle"></i></a>';

		let dueno = usuario.dueno == null ? ' - ' : usuario.dueno;

		let viajeHTML = '<tr><td>' + usuario.nombre + '</td><td>' + usuario.telefono + '</td><td>' + usuario.coche + '</td><td>' + btnEliminar + btndetalle + '</td></tr>';

		listadoHtml += viajeHTML;
	}

	document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

async function agregar(entidad) {
	let datos = {};
	if(entidad == 'usuarios'){
		datos.nombre = document.getElementById('txtNombre').value;
		datos.telefono = document.getElementById('txtTelefono').value;
		datos.coche = document.getElementById('txtCoche').value;
		console.log(datos);
	} else{
		datos.coche = document.getElementById('txtViaje').value;
		datos.horario = document.getElementById('txtHorario').value;
		datos.dueno = document.getElementById('txtDueno').value;
		console.log(datos);
	}

	const request = await fetch('api/'+ entidad, {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(datos)
	});
	const respuesta = await request.text();

	if (respuesta != 'FAIL') {
		alert("guardado correcto");
		location.reload();
	} else {
		alert("Algo ha fallado!")
	}

}

async function eliminar(entidad, id) {

	if (!confirm('¿Desea eliminar '+ entidad +' ' + id + ' ?')) {
		return;
	}

	const request = await fetch('api/'+entidad +'/' + id, {
		method: 'DELETE',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
		}
	});

	location.reload();
}
/*
async function detalleUsuario(id) {
	
	const request = await fetch('usuario/' + id, {
    method: 'GET',
    headers: {
		 'Accept': 'application/json',
	      'Content-Type': 'application/json',
	      'Authorization': localStorage.token
	      }
  });
  
  const usuario = await request.json();
  let ciudad = usuario.ciudad == null ? ' - ' : usuario.ciudad;
  window.location.href = 'detalle.html'
  alert("Datos usuario " + usuario.nombre + " Apellido: " + usuario.apellido + " Ciudad: " + ciudad)
  console.log("Detalles de "+ usuario);
}*/
