<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clean Circuit</title>
</head>

<body>
    @if (session('error'))
        {{ session('error') }}
    @endif

    <p><a href="/logout">sair</a></p>
    <p><a href="/perfil">editar perfil</a></p>
</body>

</html>
