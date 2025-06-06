<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clean Circuit</title>
    <script src="https://cdn.tailwindcss.com"></script>

</head>

<body>
    @if (session('error'))
        {{ session('error') }}
    @endif

    <div class="menuLateral w-48 bg-[#1e1e2e] p-5">
        <h2 class="text-xl mb-5 text-[#f8f8f2]">Menu</h2>
        <a href="/menu" class="block text-[#f8f8f2] my-2 no-underline hover:text-[#5e60ce]">Página Inicial</a>
        <a href="/perfil" class="block text-[#f8f8f2] my-2 no-underline hover:text-[#5e60ce]">Editar Perfil</a>
        <a href="/logout" class="block text-[#f8f8f2] my-2 no-underline hover:text-[#5e60ce]">Sair</a>
    </div>
    <div class="conteudo flex-1 bg-[#5a4d7a] p-10 flex justify-center items-center">
        <div class="formContainer bg-[#2e2e3e] p-8 rounded-xl w-full max-w-md flex flex-col gap-5">

            {{ session('id') }}
        </div>
    </div>
</body>

</html>
