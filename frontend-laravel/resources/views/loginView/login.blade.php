<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="{{ asset('css/style.css') }}">

    <title>Login</title>
</head>

<body>
    <div class="divLogin">


        <form action="/login" method="post">
            @csrf
            <h1>Login</h1>

            <div>

                <input type="email" id="email" name="email" placeholder="E-mail" required>
            </div>
            <div>

                <input type="password" id="password" name="password" placeholder="Senha" required>
            </div>
            <button type="submit">Login</button>
            <div>
            <p><a href="/redefinir-senha">Esqueci minha senha</a></p>
<p><a href="/criar-perfil">Criar conta</a></p>
            </div>
        </form>
        <div class="divMensagem">
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
        </div>
    </div>

</body>
</html>
