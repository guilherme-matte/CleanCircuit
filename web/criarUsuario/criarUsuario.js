document.querySelector("form").addEventListener("submit", function (event) {
    //o event listener faz a função ser carregada após o usuario clicar no submit
    event.preventDefault();
    let nome = document.getElementById("nome").value;
    let email = document.getElementById("email").value;
    let senha = document.getElementById("senha").value;
    let confirmarSenha = document.getElementById("confirmarSenha").value;

    if (senha !== confirmarSenha) {
        alert("As senhas não coincidem.");
        return;
    }
fetch("http://localhost:3000/usuario", {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
    },
    body: JSON.stringify({
        nome: nome,
        email: email,
        senha: senha,
    }),
})
    .then((response) => {
        if (response.ok) {
            alert("Usuário criado com sucesso!");
            window.location.href = "../login/login.html";
        } else {
            alert("Erro ao criar usuário.");
        }
    })
    .catch((error) => {
        console.error("Erro:", error);
    });
    //codigo para enviar reset de senha por email
});