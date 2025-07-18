<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Editar Perfil</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://unpkg.com/imask"></script>
    <link rel="icon" type="image/x-icon" href="{{ asset('images/favicon.ico') }}">
    <style>
        input:-webkit-autofill {
            background-color: #2e2e3e !important;
            -webkit-box-shadow: 0 0 0 1000px #2e2e3e inset !important;
            -webkit-text-fill-color: #f8f8f2 !important;
            transition: background-color 5000s ease-in-out 0s;
        }
    </style>
</head>

<body class="flex min-h-screen text-white bg-[#1e1e2e]">

    <!-- Menu Lateral -->
    <aside
        class="w-64 bg-[#2e2e3e] p-6 space-y-4 fixed top-0 left-0 h-full overflow-y-auto flex flex-col justify-between">
        <div>
            <h2 class="content-center">
                <a href="/menu">
                    <img src="{{ asset('images/logo.png') }}" alt="Logo" class="cursor-pointer">
                </a>
            </h2>
            <nav class="flex flex-col space-y-2 mt-4">
                <!-- apenas dashboard -->
            </nav>
        </div>

        <!-- USUÁRIO -->
        <div
            class="flex items-center flex-nowrap gap-3 bg-[#232336] rounded-lg p-3 mt-6 hover:bg-[#35355a] transition relative overflow-hidden select-none">
            <!-- Foto e nome levam ao perfil -->
            <a href="/perfil" class="flex items-center gap-3 flex-1 min-w-0">
                <div class="w-10 h-10 rounded-full bg-[#444] flex items-center justify-center overflow-hidden shrink-0">
                    @if (session('urlProfileImage'))
                        <img src="{{ env('API_URL') . '/' . session('urlProfileImage') }}" alt="Avatar"
                            class="w-10 h-10 object-cover rounded-full">
                    @else
                        <svg class="w-7 h-7 text-gray-300" fill="none" stroke="currentColor" stroke-width="2"
                            viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round"
                                d="M5.121 17.804A9 9 0 1112 21a9 9 0 01-6.879-3.196z" />
                            <path stroke-linecap="round" stroke-linejoin="round" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
                        </svg>
                    @endif
                </div>
                <span class="text-white font-semibold truncate max-w-[110px] overflow-hidden">
                    {{ session('nome') ?? 'Usuário' }}
                </span>
            </a>




            <!-- Submenu -->
            <a href="/logout" title="Sair"
                class="flex items-center gap-2 px-4 py-2 text-red-500 hover:bg-[#35355a] hover:text-red-600 transition text-sm">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round"
                        d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a2 2 0 01-2 2H7a2 2 0 01-2-2V7a2 2 0 012-2h4a2 2 0 012 2v1" />
                </svg>
                Sair
            </a>

        </div>

    </aside>

    <!-- Conteúdo -->
    <main class="ml-64 p-8 bg-[#1e1e2e] flex-1 flex justify-center items-center">
        <div class="bg-[#2e2e3e] p-8 rounded-xl w-full max-w-md flex flex-col gap-5 shadow-lg">

            @if (session('error'))
                <label class="bg-red-400 text-center rounded p-2">{{ session('error') }}</label>
            @elseif (session('success'))
                <label class="bg-green-400 text-center rounded p-2">{{ session('success') }}</label>
            @endif

            <h1 class="text-center text-2xl font-bold mb-4">Editar Perfil</h1>
            <form action="/perfil/save" method="post" enctype="multipart/form-data" class="flex flex-col gap-4">
                @csrf

                <img id="preview"
                    src="{{ env('API_URL') . (session('hasProfileImage') ? '/' . $perfil['urlProfileImage'] : '/uploads/default.png') }}"
                    alt="Preview da imagem" class="w-40 h-40 object-cover rounded-full mx-auto mb-2" />
                <label
                    class="inline-block bg-indigo-600 text-white rounded px-4 py-2 cursor-pointer hover:bg-indigo-700 transition">
                    Selecionar arquivo
                    <input type="file" class="hidden" id="imagem" name="imagem" />
                </label>
                <span id="fileName" class="ml-3 text-gray-200"></span>

                <input type="text" name="nomeCompleto" placeholder="Nome completo"
                    value="{{ $perfil['nomeCompleto'] ?? '' }}" required
                    class="bg-[#1e1e2e] border border-[#444] h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none rounded px-3" />

                <input type="email" name="email" placeholder="E-mail" value="{{ $perfil['email'] ?? '' }}" required
                    class="bg-[#1e1e2e] border border-[#444] h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none rounded px-3" />
                <input type="date" name="date" placeholder="Data de nascimento"
                    value="{{ $perfil['date'] ?? '' }}" required
                    class="bg-[#1e1e2e] border border-[#444] h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none rounded px-3" />
                <input type="tel" id="telefone" name="telefone" placeholder="(--) (- ---- ----)"
                    value="{{ $perfil['telefone'] ?? '' }}" required
                    class="bg-[#1e1e2e] border border-[#444] h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none rounded px-3" />
                <button type="submit"
                    class="h-11 bg-[#5e60ce] text-white rounded-md cursor-pointer text-base transition-colors duration-300 hover:bg-[#4b4caa] border-none font-semibold">
                    Salvar
                </button>
            </form>
        </div>
    </main>

    <script>
        var telefoneInput = document.getElementById('telefone');
        var maskOptions = {
            mask: '(00) 0 0000-0000'
        };
        IMask(telefoneInput, maskOptions);

        const inputImagem = document.getElementById('imagem');
        const preview = document.getElementById('preview');
        const fileNameSpan = document.getElementById('fileName');

        function truncateFileName(name, maxLength = 20) {
            if (name.length <= maxLength) return name;
            return name.slice(0, maxLength - 3) + '...';
        }
        inputImagem.addEventListener('change', () => {
            if (inputImagem.files.length > 0) {
                const originalName = inputImagem.files[0].name;
                fileNameSpan.textContent = truncateFileName(originalName, 20);

                const reader = new FileReader();
                reader.onload = (e) => {
                    preview.src = e.target.result;
                    preview.classList.remove('hidden');
                };
                reader.readAsDataURL(inputImagem.files[0]);
            } else {
                fileNameSpan.textContent = '';

            }
        });
    </script>

</body>

</html>
