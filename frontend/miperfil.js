window.onload = function() {
    const id = localStorage.getItem("id_usuario");
    fetch("http://localhost:8080/api/usuario/" + id)
    .then(response => response.json())
    .then(usuario => {
        document.getElementById("nombre").innerText = usuario.nombre || "";
        document.getElementById("usuario").innerText = "@" + usuario.usuario || "";
        document.getElementById("email").innerText = usuario.email || "";
        document.getElementById("ciudad").innerText = usuario.ciudad || "Sin ciudad";
        document.getElementById("descripcion").innerText = usuario.descripcion || "Sin descripción";
         
        const tecDiv = document.getElementById("tecnologias");
    tecDiv.innerHTML = "";
    if (usuario.tecnologias) {
        usuario.tecnologias.split(",").forEach(tech => {
            const span = document.createElement("span");
            span.innerText = tech;
            tecDiv.appendChild(span);
        });
    }

    })

    .catch(error => {
        alert("Error al cargar el perfil");
    });
}

function cerrarSesion() {
    localStorage.removeItem("id_usuario");
    window.location.href = "index.html";
}