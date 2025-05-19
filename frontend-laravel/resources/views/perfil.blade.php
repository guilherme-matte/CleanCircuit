<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Editar perfil</title>
</head>

<body>
    Editar perfil


    Olá {{ session('email') }}
    <p><a href="/logout">Sair</a></p>

    <form action="/perfil/save" method="post">
    </form>
    @csrf
    <input type="text" name="nomeCompleto" id="nomeCompleto" placeholder="Nome Completo" required
        value="{{ $perfil['nome'] ?? '' }}">
    <input type="email" name="email" id="email" placeholder="E-mail" required
        value="{{ $perfil['email'] ?? '' }}">
    <button type="submit">Salvar</button>
</body>

</html>
