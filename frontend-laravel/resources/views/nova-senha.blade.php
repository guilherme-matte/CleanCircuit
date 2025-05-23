<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Criar Nova Senha</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        input:-webkit-autofill {
            background-color: #2e2e3e !important;
            -webkit-box-shadow: 0 0 0 1000px #2e2e3e inset !important;
            -webkit-text-fill-color: #f8f8f2 !important;
            transition: background-color 5000s ease-in-out 0s;
        }
    </style>
</head>

<body class="m-0 font-sans flex h-screen items-center justify-center text-[#f8f8f2] bg-[#1e1e2e]">

    <div style="    box-shadow: 0 0 15px rgba(0, 0, 0, 0.5)"
        class="bg-[#2e2e3e] p-8 rounded-xl w-full max-w-md flex flex-col gap-5">
        @if (session('error'))
            <label class="bg-red-400 text-center p-2 rounded">{{ session('error') }}</label>
        @endif

        <h1 class="text-2xl font-semibold text-center underline mb-4">Criar Nova Senha</h1>

        <form action="/criar-nova-senha" method="post" class="flex flex-col gap-4">
            @csrf

            <div class="flex flex-col">
                <label for="novaSenha" class="mb-1 text-gray-300">Digite sua nova senha</label>
                <input type="password" name="novaSenha" id="novaSenha" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <div class="flex flex-col">
                <label for="novaSenhaConf" class="mb-1 text-gray-300">Confirme sua nova senha</label>
                <input type="password" name="novaSenhaConf" id="novaSenhaConf" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <button type="submit"
                class="h-11 bg-[#5e60ce] text-white rounded-md cursor-pointer text-base transition-colors duration-300 hover:bg-[#4b4caa] border-none">
                Criar nova senha
            </button>
        </form>
    </div>

</body>

</html>
