document.querySelector("input[type='submit']").addEventListener("click", function() {

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (email === "" || password === "") {
        alert("Rellena todos los campos");
        return;
    }

    fetch("http://localhost:8080/api/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            email: email,
            contrasena: password
        })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            localStorage.setItem("id_usuario", data.id);
            alert("Inicio de sesión exitoso");
            window.location.href = "perfiles.html";
        } else {
            alert("Credenciales incorrectas");
        }
    })
    .catch(error => {
        alert("Error al iniciar sesión");
    });
});