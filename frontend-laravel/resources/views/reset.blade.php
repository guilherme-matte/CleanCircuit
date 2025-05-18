<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Redefinir senha</title>
</head>

<body>
    <h1>Redefinir senha</h1>
    @if (session('error'))
        <p style="color: red">{{ session('error') }}</p>
    @endif
    @if (session('success'))
        <p style="color: green">{{ session('success') }}</p>
    @endif
    <form action="/redefinir-senha" method="post">
    @csrf
    <label>Email:</label><br>
    <input type="email" name="email" id="email"><br><br>
    <button type="submit">Enviar Link</button>
    </form>
    <p><a href="/login">Voltar</a></p>
</body>

</html>
