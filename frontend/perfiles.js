window.onload = function() {
    const id = localStorage.getItem("id_usuario");
    fetch("http://localhost:8080/api/perfiles/" + id)
    .then(response => response.json())
    .then(usuarios => {
        const contenedor = document.getElementById("contenedor");
        usuarios.forEach(usuario => {
            const div = document.createElement("div");
            div.className = "container";
            div.innerHTML = `
                <h3>${usuario.nombre}</h3>
                <p>${usuario.ciudad || "Sin ciudad"}</p>
                <p>${usuario.descripcion || "Sin descripción"}</p>
                <div class="tecnologias">${usuario.tecnologias ? usuario.tecnologias.split(",").map(t => `<span>${t}</span>`).join("") : ""}</div>
                <div class="botones">
                    <button onclick="match(${usuario.id})">Match</button>
                    <button onclick="like(${usuario.id})">Like</button>
                </div>
            `;
            contenedor.appendChild(div);
        });
    })
    .catch(error => {
        alert("No se puede conectar con el servidor");
    });
}

function like(idRecibe) {
    const idManda = localStorage.getItem("id_usuario");
    fetch("http://localhost:8080/api/like", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            usuarioManda: parseInt(idManda),
            usuarioRecibe: idRecibe
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Like dado!");
        }
    })
    .catch(error => {
        alert("Error al dar like");
    });
}

function match(idRecibe) {
    const idManda = localStorage.getItem("id_usuario");
    fetch("http://localhost:8080/api/match", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            usuarioManda: parseInt(idManda),
            usuarioRecibe: idRecibe
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Match!");
        }
    })
    .catch(error => {
        alert("Error al hacer match");
    });
}

function verMiPerfil() {
    window.location.href = "miperfil.html";
}

function cerrarSesion() {
    localStorage.removeItem("id_usuario");
    window.location.href = "index.html";
}

function toggleMenu() {
    const dropdown = document.getElementById("dropdown");
    dropdown.style.display = dropdown.style.display === "none" ? "block" : "none";
}
let matchesAnteriores = -1;

setInterval(function() {
    const id = localStorage.getItem("id_usuario");
    fetch("http://localhost:8080/api/matches/total/" + id)
    .then(response => response.json())
    .then(data => {
        if (matchesAnteriores === -1) {
            matchesAnteriores = data.total;
            return;
        }
        if (data.total > matchesAnteriores) {
            mostrarNotificacion("¡Tienes un nuevo match!");
        }
        matchesAnteriores = data.total;
    });
}, 5000);

function mostrarNotificacion(mensaje) {
    const notif = document.createElement("div");
    notif.innerText = mensaje;
    notif.style.cssText = `
        position: fixed;
        bottom: 20px;
        right: 20px;
        background: #ff3d9a;
        color: white;
        padding: 15px 20px;
        border-radius: 10px;
        font-size: 15px;
        z-index: 1000;
        box-shadow: 2px 2px 8px rgba(0,0,0,0.2);
    `;
    document.body.appendChild(notif);
    setTimeout(() => notif.remove(), 4000);
}