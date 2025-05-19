<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="{{ asset('css/style.css') }}">

    <title>Redefinir Senha</title>
</head>

<body>
    <div class="divLogin">

        <form action="/redefinir-senha" method="post">
            <h1>Redefinir Senha</h1>
            @csrf

            <input type="email" name="email" id="email" required placeholder="E-mail"><br><br>
            <button type="submit">Resetar Senha</button>
            @if (session('error'))
                <div id="divError">
                    {{ session('error') }}
                </div>
            @endif
            @if (session('success'))
                <div id="divSuccess">
                    {{ session('success') }}
                </div>
            @endif
            <p><a href="/login">Voltar</a></p>

        </form>

    </div>
</body>

</html>
