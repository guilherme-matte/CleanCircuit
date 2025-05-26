<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Criar Conta</title>
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

    <div style="box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);"
        class="bg-[#2e2e3e] p-8 rounded-xl w-full max-w-md flex flex-col gap-5">
        <h1 class="text-2xl font-semibold text-center underline mb-4">Criar Conta</h1>

        <form action="/criar-perfil" method="post" class="flex flex-col gap-4" onsubmit="return verificarSenha()">
            @csrf
            @if (session('error'))
                <label class="bg-red-400 text-center p-2 rounded">{{ session('error') }}</label>
            @elseif (session('success'))
                <label class="bg-green-400 text-center p-2 rounded">{{ session('success') }}</label>
            @endif
            <div class="flex flex-col">
                <label for="cpf" class="mb-1 text-gray-300">CPF</label>
                <input type="text" name="cpf" id="cpf" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <div class="flex flex-col">
                <label for="email" class="mb-1 text-gray-300">E-mail</label>
                <input type="email" name="email" id="email" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <div class="flex flex-col">
                <label for="nomeCompleto" class="mb-1 text-gray-300">Nome Completo</label>
                <input type="text" name="nomeCompleto" id="nomeCompleto" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <div class="flex flex-col">
                <label for="telefone" class="mb-1 text-gray-300">Telefone</label>
                <input type="tel" name="telefone" id="telefone" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <div class="flex flex-col">
                <label for="dataNasc" class="mb-1 text-gray-300">Data de Nascimento</label>
                <input type="date" name="dataNasc" id="dataNasc" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <div class="flex flex-col">
                <label for="password" class="mb-1 text-gray-300">Senha</label>
                <input type="password" name="password" id="password" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
            </div>

            <div class="flex flex-col">
                <label for="passwordConf" class="mb-1 text-gray-300">Confirmar Senha</label>
                <input type="password" name="passwordConf" id="passwordConf" required
                    class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
                <span id="erroSenha" class="text-red-400 text-sm mt-1 hidden">As senhas não coincidem.</span>
            </div>

            <button type="submit"
                class="h-11 bg-[#5e60ce] text-white rounded-md cursor-pointer text-base transition-colors duration-300 hover:bg-[#4b4caa] border-none">
                Criar Conta
            </button>
        </form>
    </div>

    <script>
        function verificarSenha() {
            const senha = document.getElementById("password").value;
            const confirmar = document.getElementById("passwordConf").value;
            const erro = document.getElementById("erroSenha");

            if (senha !== confirmar) {
                erro.classList.remove("hidden");
                return false; // impede envio do form
            }

            erro.classList.add("hidden");
            return true;
        }
    </script>

</body>

</html>
