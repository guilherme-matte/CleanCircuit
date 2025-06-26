<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Clean Circuit</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .fade-out {
            transition: opacity 1s ease-in-out;
            opacity: 0;
        }
    </style>
</head>

<body class="flex min-h-screen text-white bg-[#1e1e2e]">
    <!-- Alerta de erro -->
    @if ($mensagem)
        <div id="alertaSucesso" class="fixed top-5 right-5 bg-green-600 text-white px-4 py-2 rounded shadow-md z-50">
            {{ $mensagem }}
        </div>

        <script>
            setTimeout(() => {
                const alerta = document.getElementById('alertaSucesso');
                if (alerta) {
                    alerta.classList.add('fade-out');
                    setTimeout(() => alerta.remove(), 1000);
                }
            }, 4000);
        </script>
    @endif

    <!-- Menu Lateral -->
    <aside class="w-64 bg-[#2e2e3e] p-6 space-y-4">
        <h2 class="text-2xl font-bold mb-6 text-[#f8f8f2]">Clean Circuit</h2>
        <nav class="flex flex-col space-y-2">
            <a href="/menu" class="hover:text-[#5e60ce]">Dashboard</a>
            <a href="/perfil" class="hover:text-[#5e60ce]">Editar Perfil</a>
            <a href="/logout" class="hover:text-[#5e60ce]">Sair</a>
        </nav>
    </aside>

    <!-- Conteúdo -->
    <main class="flex-1 p-8 bg-[#1e1e2e]">
        <h1 class="text-3xl font-bold mb-6">Visão Geral da Carteira</h1>
        <!-- Cards com indicadores -->
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md">
                <h3 class="text-lg mb-2">Valor Total</h3>
                <p class="text-2xl font-bold">R$ 120.000,00</p>
            </div>
            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md">
                <h3 class="text-lg mb-2">Rentabilidade</h3>
                <p class="text-2xl font-bold text-green-400">+12,4%</p>
            </div>
            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md">
                <h3 class="text-lg mb-2">Dividendos (12m)</h3>
                <p class="text-2xl font-bold">R$ 4.300,00</p>
            </div>
        </div>

        <!-- Ativos agrupados por tipo -->
        <!-- Ativos agrupados por tipo -->
        <div class="space-y-10">
            @foreach ($resumo as $tipo => $ativos)
                @if (!empty($ativos))
                    <section class="bg-[#2e2e3e] p-6 rounded-xl shadow-md mb-8">
                        <h2 class="text-xl font-semibold mb-4">{{ $tipo }}</h2>

                        <div class="overflow-x-auto">
                            <table class="table-auto w-full text-left text-sm">
                                <thead>
                                    <tr class="text-[#a6accd] border-b border-[#444]">
                                        <th class="p-2">Sigla</th>
                                        <th class="p-2">Nome</th>
                                        <th class="p-2">Cotas</th>
                                        <th class="p-2">Valor Total</th>
                                        <th class="p-2">Dividendos</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @foreach ($ativos as $ativo)
                                        <tr class="border-b border-[#444]">
                                            <td class="p-2">{{ strtoupper($ativo['sigla']) }}</td>
                                            <td class="p-2">{{ $ativo['nome'] }}</td>
                                            <td class="p-2">{{ $ativo['cotas'] }}</td>
                                            <td class="p-2">R$ {{ number_format($ativo['valorTotal'], 2, ',', '.') }}
                                            </td>
                                            <td class="p-2">R$ {{ number_format($ativo['dividendos'], 2, ',', '.') }}
                                            </td>
                                        </tr>
                                    @endforeach
                                </tbody>
                            </table>
                        </div>
                    </section>
                @endif
            @endforeach
        </div>
    </main>
</body>

</html>
