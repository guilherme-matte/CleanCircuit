<!DOCTYPE html>
<html lang="pt-br">

<head>
    <link rel="icon" type="image/x-icon" href="{{ asset('images/favicon.ico') }}">
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
        <h2 class="content-center"><img src="{{ asset('images/logo.png') }} " alt=""></h2>
        <nav class="flex flex-col space-y-2">
            <a href="/menu" class="hover:text-[#5e60ce]">Dashboard</a>
            <a href="/perfil" class="hover:text-[#5e60ce]">Editar Perfil</a>
            <a href="/logout" class="hover:text-[#5e60ce]">Sair</a>
        </nav>
    </aside>
    <!-- Conteúdo -->
    <main class="flex-1 p-8 bg-[#1e1e2e]">
        <h1 class="text-3xl font-bold mb-6">Resumo da Carteira</h1>
        <!-- Cards com indicadores -->

        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md">
                <h3 class="text-lg mb-2">Valor Total</h3>
                <p class="text-2xl font-bold">R$ {{ number_format($resumo['Valor Atual'], 2, ',', '.') }}</p>
            </div>
            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md">
                <h3 class="text-lg mb-2">Variação</h3>
                <p title="Valor real: R$ {{ number_format($resumo['Valor Atual'] - $resumo['Valor Aplicado'], 2, ',', '.') }}"
                    class="text-2xl font-bold {{ $resumo['Variacao'] >= 0 ? 'text-green-400' : 'text-red-400' }}">
                    {{ number_format($resumo['Variacao'], 2, ',', '.') . '%' }}
                </p>
            </div>

            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md">
                <h3 class="text-lg mb-2">Valor Aplicado</h3>
                <p class="text-2xl font-bold">R$ {{ number_format($resumo['Valor Aplicado'], 2, ',', '.') }}</p>
            </div>
        </div>

        <!-- Ativos agrupados por tipo -->
        <div class="space-y-10">
            @foreach ($resumo as $tipo => $ativos)
                @if (is_array($ativos) && !empty($ativos))
                    <section class="bg-[#2e2e3e] p-6 rounded-xl shadow-md mb-8">
                        <h2 class="text-xl font-semibold mb-4">{{ $tipo }}</h2>

                        <div class="overflow-x-auto">
                            <table class="table-auto w-full text-left text-sm">
                                <thead>
                                    <tr class="text-[#a6accd] border-b border-[#444]">
                                        <th class="p-2">Sigla</th>
                                        <th class="p-2">Nome</th>
                                        <th class="p-2">Cotas</th>
                                        <th class="p-2">Preço Médio</th>
                                        <th class="p-2">Valor de Mercado</th>
                                        <th class="p-2">Valor total</th>
                                        <th class="p-2">Variação</th>
                                        <th class="p-2">Dividendos</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    @foreach ($ativos as $ativo)
                                        @php
                                            $valorAplicado = $ativo['valorAplicado'];
                                            $valorMercado = $ativo['valorAtual'];
                                            if ($tipo == 'Stocks' || $tipo == 'Reits' || $tipo == 'Criptomoedas') {
                                                $cotas = $ativo['cotasFracionadas'];
                                            } else {
                                                $cotas = $ativo['cotas'];
                                            }

                                            $precoMedio = $valorAplicado / $cotas;
                                            $lucro = $ativo['lucroPrejuizo'];
                                            $valorTotal = $valorMercado * $cotas;
                                            $valorAplicadoTotal = $precoMedio * $cotas;
                                            $variacao =
                                                $valorAplicado > 0
                                                    ? (($valorMercado - $precoMedio) / $precoMedio) * 100
                                                    : 0;
                                        @endphp
                                        <tr class="border-b border-[#444]">
                                            <td class="p-2">{{ strtoupper($ativo['sigla']) }}</td>
                                            <td class="p-2">{{ $ativo['nome'] }}</td>
                                            <td class="p-2">{{ $cotas}}</td>

                                            <td title="Valor total aplicado: R$ {{ number_format($valorAplicadoTotal, 2, ',', '.') }}"
                                                class="p-2 relative group cursor-pointer">
                                                R$ {{ number_format($precoMedio, 2, ',', '.') }}

                                            </td>

                                            {{-- Valor de Mercado --}}
                                            <td class="p-2">R$ {{ number_format($valorMercado, 2, ',', '.') }}</td>
                                            <td class="p-2">R$ {{ number_format($valorTotal, 2, ',', '.') }}</td>

                                            {{-- Variação --}}
                                            <td class="p-2 relative group cursor-pointer">
                                                <span title="Valor real: R$ {{ number_format($lucro, 2, ',', '.') }}"
                                                    class="{{ $variacao >= 0 ? 'text-green-400' : 'text-red-400' }}">
                                                    {{ number_format($variacao, 2, ',', '.') }}%
                                                </span>

                                            </td>

                                            {{-- Dividendos --}}
                                            <td class="p-2">R$
                                                {{ number_format($ativo['dividendos'], 2, ',', '.') }}</td>
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
