window.onload = function() {
    fetch("http://localhost:8080/api/tecnologias")
    .then(response => response.json())
    .then(tecnologias => {
        const div = document.getElementById("tecnologias");
        tecnologias.forEach(tech => {
            const label = document.createElement("label");
            label.innerHTML = `<input type="checkbox" value="${tech.id}"> ${tech.nombre}`;
            div.appendChild(label);
        });
    })
    .catch(error => {
        console.log("Error cargando tecnologias");
    });
}

document.querySelector("input[type='submit']").addEventListener("click", function() {

    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const ciudad = document.getElementById("ciudad").value;
    const direccion = document.getElementById("direccion").value;
    const usuario = document.getElementById("usuario").value;
    const email = document.getElementById("email").value;
    const contrasena = document.getElementById("password").value;
    const descripcion = document.getElementById("descripcion").value;

    if (nombre === "" || apellido === "" || ciudad === "" || usuario === "" || email === "" || contrasena === "") {
        alert("Rellena todos los campos");
        return;
    }

    const tecnologiasSeleccionadas = Array.from(
        document.querySelectorAll("#tecnologias input:checked")
    ).map(cb => cb.value);

    fetch("http://localhost:8080/api/registro", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            nombre: nombre,
            apellido: apellido,
            ciudad: ciudad,
            direccion: direccion,
            usuario: usuario,
            email: email,
            contrasena: contrasena,
            descripcion: descripcion,
            tecnologias: tecnologiasSeleccionadas
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Registro exitoso");
            window.location.href = "login.html";
        } else {
            alert("Error al registrarse");
        }
    })
    .catch(error => {
        alert("No se puede conectar con el servidor");
    });
});