<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Editar Perfil</title>
    <script src="https://cdn.tailwindcss.com"></script>

    <link rel="stylesheet" href="style.css"> <!-- se quiser separar depois -->
    <style>
        input:-webkit-autofill {
            background-color: #2e2e3e !important;
            -webkit-box-shadow: 0 0 0 1000px #2e2e3e inset !important;
            -webkit-text-fill-color: #f8f8f2 !important;
            transition: background-color 5000s ease-in-out 0s;
        }
    </style>
</head>

<body>

    <body class="m-0 font-sans flex h-screen text-[#f8f8f2] bg-[#121212]">

        <div class="menuLateral w-48 bg-[#1e1e2e] p-5">
            <h2 class="text-xl mb-5 text-[#f8f8f2]">Menu</h2>
            <a href="/menu" class="block text-[#f8f8f2] my-2 no-underline hover:text-[#5e60ce]">Página Inicial</a>
            <a href="/perfil" class="block text-[#f8f8f2] my-2 no-underline hover:text-[#5e60ce]">Editar Perfil</a>
            <a href="/logout" class="block text-[#f8f8f2] my-2 no-underline hover:text-[#5e60ce]">Sair</a>
        </div>

        <div class="conteudo flex-1 bg-[#5a4d7a] p-10 flex justify-center items-center">
            <div class="formContainer bg-[#2e2e3e] p-8 rounded-xl w-full max-w-md flex flex-col gap-5">
                <h1 class="text-center text-2xl underline">Editar Perfil</h1>
                <form action="/perfil/save" method="post" enctype="multipart/form-data" class="flex flex-col gap-4">
                    @csrf

                    <img id="preview" src="#" alt="Preview da imagem"
                        class="max-w-[120px] max-h-[120px] rounded-full mx-auto mb-2 hidden" />

                    <label
                        class="inline-block bg-indigo-600 text-white rounded px-4 py-2 cursor-pointer hover:bg-indigo-700 transition">
                        Selecionar arquivo
                        <input type="file" class="hidden" id="imagem" name="imagem" />
                    </label>
                    <span id="fileName" class="ml-3 text-gray-200"></span>

                    <input type="text" name="nomeCompleto" placeholder="Nome completo"
                        value="{{ $perfil['nomeCompleto'] ?? '' }}" required
                        class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />

                    <input type="email" name="email" placeholder="E-mail" value="{{ $perfil['email'] ?? '' }}"
                        required
                        class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
                    <input type="date" name="data" placeholder="Data de nascimento"
                        value="{{ $perfil['date'] ?? '' }}" required
                        class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
                    <input type="tel" name="telefone" placeholder="(--) (- ---- ----)"
                        value="{{ $perfil['telefone'] ?? '' }}" required
                        class="bg-transparent border-b border-gray-300 h-11 text-[#f8f8f2] text-base placeholder-gray-400 outline-none" />
                    <button type="submit"
                        class="h-11 bg-[#5e60ce] text-white rounded-md cursor-pointer text-base transition-colors duration-300 hover:bg-[#4b4caa] border-none">
                        Salvar
                    </button>
                </form>
            </div>
        </div>

        <script>
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
                    preview.src = '#';
                    preview.classList.add('hidden');
                }
            });
        </script>

    </body>

</html>

</body>

</html>
