<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Erro {{ $status }}</title>
    <style>
        body {
            background-color: #1e1e2e;
            color: #f8f8f2;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            text-align: center;
            margin: 0;
        }

        a {
            color: #66d9ef;
            text-decoration: none;
        }

        a:hover {
            color: #3aa4b9;
        }

        h1 {
            font-size: 5rem;
            color: #e53e3e;
        }

        p {
            font-size: 1.25rem;
            margin: 0.5rem 0;
        }

        .container {
            max-width: 600px;
            padding: 2rem;
            border-radius: 8px;
            background-color: #2e2e3e;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
        }

        .emoji {
            font-size: 2rem;
        }

        @media (max-width: 600px) {
            h1 {
                font-size: 3rem;
            }
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>Erro {{ $status }}</h1>
        <p class="emoji">{{ $message }} 😞</p>
        volta para a <a href="{{ url()->previous() }}">última página</a>
        ou para o <a href="/menu">menu</a>
        <p><strong>{{ $exception->getMessage() }}</strong></p>
    </div>
</body>

</html>
