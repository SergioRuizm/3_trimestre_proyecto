window.onload = function() {
    const id = localStorage.getItem("id_usuario");
    fetch("http://localhost:8080/api/matches/" + id)
    .then(response => response.json())
    .then(usuarios => {
        const lista = document.getElementById("lista");
        if (usuarios.length === 0) {
            lista.innerHTML = "<p>No tienes matches todavía</p>";
            return;
        }
        usuarios.forEach(usuario => {
            const div = document.createElement("div");
            div.className = "perfil-item";
            div.innerHTML = `<h3>${usuario.nombre}</h3><p>${usuario.ciudad || "Sin ciudad"}</p>`;
            lista.appendChild(div);
        });
    })
    .catch(error => {
        alert("Error al cargar los matches");
    });
}

function cerrarSesion() {
    localStorage.removeItem("id_usuario");
    window.location.href = "index.html";
}