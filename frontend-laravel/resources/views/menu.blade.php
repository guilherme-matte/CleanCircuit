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

        input[type=number]::-webkit-inner-spin-button,
        input[type=number]::-webkit-outer-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }

        input[type=number] {
            -moz-appearance: textfield;
        }

        select::-ms-expand {
            display: none;
        }

        select {
            appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            background-image: none;
        }
    </style>
</head>

<body class="flex min-h-screen text-white bg-[#1e1e2e]">
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

    <aside
        class="w-64 bg-[#2e2e3e] p-6 space-y-4 fixed top-0 left-0 h-full overflow-y-auto flex flex-col justify-between">
        <div>
            <h2 class="content-center">
                <a href="/menu">
                    <img src="{{ asset('images/logo.png') }}" alt="Logo" class="cursor-pointer">
                </a>
            </h2>
            <nav class="flex flex-col space-y-2 mt-4">
            </nav>
        </div>

        <!-- user -->
        <div
            class="flex items-center flex-nowrap gap-3 bg-[#232336] rounded-lg p-3 mt-6 hover:bg-[#35355a] transition relative overflow-hidden select-none">
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





            <a href="/logout" title="Sair"
                class="flex items-center gap-2 px-3 py-1.5 bg-red-600 border-2 border-red-700 rounded-md text-red-200 hover:bg-red-700 hover:border-red-800 transition-colors text-sm">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" stroke-width="2" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round"
                        d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a2 2 0 01-2 2H7a2 2 0 01-2-2V7a2 2 0 012-2h4a2 2 0 012 2v1" />
                </svg>

            </a>

        </div>

    </aside>
    <main class="ml-64 p-8 bg-[#1e1e2e] flex-1">
        <h1 class="text-3xl font-bold mb-6">Resumo da Carteira</h1>

        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-10">
            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md" title="Valor real gasto na carteira">
                <h3 class="text-lg mb-2">Valor Aplicado</h3>
                <p class="text-2xl font-bold">R$ {{ number_format($resumo['Valor Aplicado'], 2, ',', '.') }}</p>
            </div>
            <div class="bg-[#2e2e3e] p-6 rounded-xl shadow-md"
                title="Valor Atual da carteira, considerando a variação do mercado">
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


        </div>

        <!-- Ativos agrupados por tipo -->
        @php
            $tiposValidos = ['Ações', 'Fiis', 'ETFs', 'Criptomoedas', 'Reits', 'Stocks', 'Renda Fixa'];
        @endphp

        <div class="space-y-10">
            @foreach ($tiposValidos as $tipo)
                @php
                    $ativos = $resumo[$tipo] ?? [];
                @endphp
                <section class="bg-[#2e2e3e] p-0 pt-6 pb-6 rounded-xl shadow-md mb-8">
                    <div class="flex justify-between items-center mb-4">
                        <h2 class="text-xl font-semibold text-center w-full">{{ $tipo }}</h2>
                        @if (!empty($ativos))
                            <button onclick="abrirModal('{{ $tipo }}')"
                                class="absolute right-10 w-8 h-8 flex items-center justify-center rounded-full bg-[#2e2e3e] text-white border border-[#444] hover:bg-[#252536] transition duration-200">
                                +
                            </button>
                        @endif
                    </div>
                    <div class="border-t-4 border-[#5e60ce] w-full mb-6"></div>
                    <div class="px-6">
                        @if (!empty($ativos))
                            <div class="overflow-x-auto">
                                <table class="table-auto w-full text-left text-sm">
                                    <thead>
                                        <tr class="text-[#a6accd] border-b border-[#444]">
                                            <th class="p-2 text-center">Sigla</th>
                                            <th class="p-2 text-center">Nome</th>
                                            <th class="p-2 text-center">Cotas</th>
                                            <th class="p-2 text-center">Preço Médio</th>
                                            <th class="p-2 text-center">Valor de Mercado</th>
                                            <th class="p-2 text-center">Valor total</th>
                                            <th class="p-2 text-center">Variação</th>
                                            <th class="p-2 text-center">Dividendos</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        @foreach ($ativos as $ativo)
                                            @php
                                                $valorAplicado = $ativo['valorAplicado'];
                                                $valorMercado = $ativo['valorAtual'];
                                                if ($tipo == 'Stocks' || $tipo == 'Reits' || $tipo == 'Criptomoedas') {
                                                    $currency = 'USD';
                                                    $cotas = $ativo['cotasFracionadas'];
                                                } else {
                                                    $currency = 'BRL';
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
                                                <td class="p-2 text-center">{{ strtoupper($ativo['sigla']) }}</td>
                                                <td class="p-2 text-center">{{ $ativo['nome'] }}</td>
                                                <td class="p-2 text-center">{{ $cotas }}</td>
                                                <td title="Valor total aplicado: R$ {{ number_format($valorAplicadoTotal, 2, ',', '.') }}"
                                                    class="p-2 relative group cursor-pointer text-center">
                                                    R$ {{ number_format($precoMedio, 2, ',', '.') }}
                                                </td>
                                                <td class="p-2 text-center">R$
                                                    {{ number_format($valorMercado, 2, ',', '.') }}</td>
                                                <td class="p-2 text-center">R$
                                                    {{ number_format($valorTotal, 2, ',', '.') }}</td>
                                                <td class="p-2 relative group cursor-pointer text-center">
                                                    <span
                                                        title="Valor real: R$ {{ number_format($lucro, 2, ',', '.') }}"
                                                        class="{{ $variacao >= 0 ? 'text-green-400' : 'text-red-400' }}">
                                                        {{ number_format($variacao, 2, ',', '.') }}%
                                                    </span>
                                                </td>
                                                <td class="p-2 text-center">R$
                                                    {{ number_format($ativo['dividendos'], 2, ',', '.') }}</td>
                                            </tr>
                                        @endforeach
                                    </tbody>
                                </table>
                            </div>
                        @else
                            <div class="flex flex-col items-center justify-center py-10">
                                <p class="text-gray-400 mb-4">Nenhum ativo cadastrado.</p>
                                <button onclick="abrirModal('{{ $tipo }}')"
                                    class="w-12 h-12 flex items-center justify-center rounded-full bg-[#2e2e3e] text-white border border-[#444] hover:bg-[#252536] text-3xl transition duration-200">
                                    +
                                </button>
                            </div>
                        @endif
                </section>
            @endforeach
        </div>
    </main>
    <!-- Modal -->
    <div id="modalAtivo" class="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50 hidden">
        <div class="bg-[#2e2e3e] text-white rounded-xl shadow-lg p-6 w-full max-w-md relative">
            <button onclick="fecharModal()"
                class="absolute top-2 right-3 text-xl text-white hover:text-red-400">&times;</button>
            <h2 id="modalTitulo" class="text-2xl mb-4 font-bold">Adicionar Ativo</h2>
            <form action="/menu" method="POST" class="space-y-4" id="formAtivo" novalidate>
                @csrf
                <input type="hidden" name="tipo" id="tipoInput" value="">
                <div class="relative">
                    <label for="sigla" class="block text-sm">Sigla</label>
                    <input autocomplete="off" type="text" name="sigla" id="sigla" autocomplete="off"
                        class="w-full rounded bg-[#1e1e2e] border border-[#444] p-2" required>
                    <ul id="sugestoes"
                        class="absolute z-50 w-full bg-[#2e2e3e] border border-[#444] rounded mt-1 hidden"></ul>
                    <span class="text-red-400 text-xs hidden" id="erroSigla">Campo obrigatório</span>
                </div>
                <div class="relative">
                    <label for="cotas" class="block text-sm">Cotas</label>
                    <input type="number" step="any" name="cotas" id="cotas" autocomplete="off"
                        class="w-full rounded bg-[#1e1e2e] border border-[#444] p-2" required>
                    <span class="text-red-400 text-xs hidden" id="erroCotas">Campo obrigatório</span>
                </div>
                <div class="relative">
                    <label for="valorCota" class="block text-sm">Valor por Cota</label>
                    <input type="number" step="any" name="valorCota" id="valorCota" autocomplete="off"
                        class="w-full rounded bg-[#1e1e2e] border border-[#444] p-2" required>
                    <span class="text-red-400 text-xs hidden" id="erroValorCota">Campo obrigatório</span>
                </div>
                <div class="relative">
                    <label for="tipoMovimento" class="block text-sm">Tipo</label>
                    <select name="tipoMovimento" id="tipoMovimento"
                        class="w-full rounded bg-[#1e1e2e] border border-[#444] p-2 pr-8 appearance-none" required>
                        <option value="">Selecione</option>
                        <option value="compra">Compra</option>
                        <option value="venda">Venda</option>
                    </select>
                    <span class="pointer-events-none absolute right-3 top-9 flex items-center h-5 text-gray-400">
                        <svg class="w-4 h-4" fill="none" stroke="currentColor" stroke-width="2"
                            viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M19 9l-7 7-7-7" />
                        </svg>
                    </span>
                    <span class="text-red-400 text-xs hidden" id="erroTipo">Campo obrigatório</span>
                </div>
                <div>
                    <label for="valorTotal" class="block text-sm">Valor Total</label>
                    <input type="number" step="any" id="valorTotal" name="valorTotal" readonly
                        class="w-full rounded bg-[#1e1e2e] border border-[#444] p-2 text-gray-400">
                </div>
                <div class="text-right">
                    <button type="submit"
                        class="bg-green-600 hover:bg-green-700 text-white font-semibold px-4 py-2 rounded">
                        Salvar
                    </button>
                </div>
            </form>
        </div>
    </div>
    <script>
        function abrirModal(tipo) {
            document.getElementById('modalAtivo').classList.remove('hidden');
            document.getElementById('tipoInput').value = tipo;
            document.getElementById('modalTitulo').innerText = "Adicionar " + tipo;


        }

        function fecharModal() {
            document.getElementById('modalAtivo').classList.add('hidden');
            document.getElementById('sigla').value = '';
            document.getElementById('cotas').value = '';
            document.getElementById('valorCota').value = '';
            document.getElementById('tipoMovimento').selectedIndex = 0;
            document.getElementById('valorTotal').value = '';
            document.getElementById('tipoInput').value = '';



            sugestoes.innerHTML = '';
            sugestoes.classList.add('hidden');
        }
        const inputSigla = document.getElementById('sigla');
        const sugestoes = document.getElementById('sugestoes');

        let buscaDisparadaPeloClique = false;

        const inputCotas = document.getElementById('cotas');
        const inputValorCota = document.getElementById('valorCota');
        const inputValorTotal = document.getElementById('valorTotal');

        function atualizarValorTotal() {
            const cotas = parseFloat(inputCotas.value);
            const valorCota = parseFloat(inputValorCota.value);

            if (!isNaN(cotas) && !isNaN(valorCota)) {
                inputValorTotal.value = (cotas * valorCota).toFixed(2);
            } else {
                inputValorTotal.value = '';
            }
        }
        inputCotas.addEventListener('input', atualizarValorTotal);
        inputValorCota.addEventListener('input', atualizarValorTotal);

        // Função para buscar dados do ativo
        function buscarAtivo(sigla) {
            if (!sigla) return;

            fetch(`/cotacao/${encodeURIComponent(sigla)}`)
                .then(response => {
                    if (!response.ok) throw new Error('Erro ao buscar ativo');
                    return response.json();
                })
                .then(data => {
                    document.getElementById('valorCota').value = data.preco;
                    console.log('Nome do ativo:', data.nome);
                })
                .catch(err => {
                    console.error(err);
                    alert('Ativo não encontrado');
                });
        }

        // Evento input para autocomplete
        let selectedSuggestionIndex = -1;

        inputSigla.addEventListener('input', function() {
            const valor = this.value.trim();
            selectedSuggestionIndex = -1;

            if (valor.length < 2) {
                sugestoes.innerHTML = '';
                sugestoes.classList.add('hidden');
                return;
            }

            fetch(`http://localhost:8080/brapi/autocomplete/${encodeURIComponent(valor)}`)
                .then(response => response.json())
                .then(data => {
                    const resultados = data.status_res || [];

                    if (resultados.length > 0) {
                        sugestoes.innerHTML = '';
                        resultados.forEach((sigla, idx) => {
                            const item = document.createElement('li');
                            item.textContent = sigla;
                            item.classList.add('p-2', 'cursor-pointer', 'hover:bg-[#3a3a4a]');
                            item.setAttribute('data-index', idx);

                            item.addEventListener('mousedown', (e) => {
                                e.preventDefault();
                                inputSigla.value = sigla;
                                sugestoes.innerHTML = '';
                                sugestoes.classList.add('hidden');
                                buscaDisparadaPeloClique = true;
                                buscarAtivo(sigla);
                            });

                            sugestoes.appendChild(item);
                        });
                        sugestoes.classList.remove('hidden');
                    } else {
                        sugestoes.innerHTML = '';
                        sugestoes.classList.add('hidden');
                    }
                });
        });

        inputSigla.addEventListener('keydown', function(e) {
            const items = sugestoes.querySelectorAll('li');
            if (sugestoes.classList.contains('hidden') || items.length === 0) return;

            if (e.key === 'ArrowDown') {
                e.preventDefault();
                selectedSuggestionIndex = (selectedSuggestionIndex + 1) % items.length;
                updateSuggestionHighlight(items);
            } else if (e.key === 'ArrowUp') {
                e.preventDefault();
                selectedSuggestionIndex = (selectedSuggestionIndex - 1 + items.length) % items.length;
                updateSuggestionHighlight(items);
            } else if (e.key === 'Enter') {
                if (selectedSuggestionIndex >= 0 && selectedSuggestionIndex < items.length) {
                    e.preventDefault();
                    items[selectedSuggestionIndex].dispatchEvent(new MouseEvent('mousedown'));
                }
            } else if (e.key === 'Tab') {
                sugestoes.innerHTML = '';
                sugestoes.classList.add('hidden');
            }
        });

        function updateSuggestionHighlight(items) {
            items.forEach((item, idx) => {
                if (idx === selectedSuggestionIndex) {
                    item.classList.add('bg-[#3a3a4a]');
                } else {
                    item.classList.remove('bg-[#3a3a4a]');
                }
            });
        }

        // Evento blur para buscar se usuário digitou direto
        inputSigla.addEventListener('blur', function() {
            if (buscaDisparadaPeloClique) {
                // Se já buscou pelo clique, limpa a flag e não busca novamente
                buscaDisparadaPeloClique = false;
                return;
            }

            const sigla = this.value.trim();

            if (sigla.length >= 2) {
                buscarAtivo(sigla);
            }
        });
        inputSigla.addEventListener('keydown', function(e) {
            if (e.key === 'Tab') {
                sugestoes.innerHTML = '';
                sugestoes.classList.add('hidden');
            }
        });
        // Esconder lista de sugestões ao clicar fora
        document.addEventListener('click', function(e) {
            if (!inputSigla.parentElement.contains(e.target)) {
                sugestoes.innerHTML = '';
                sugestoes.classList.add('hidden');
            }
        });

        const formAtivo = document.getElementById('formAtivo');
        formAtivo.addEventListener('submit', function(e) {
            let valido = true;
            // Sigla
            if (!inputSigla.value.trim()) {
                valido = false;
                inputSigla.classList.add('border-red-500');
                document.getElementById('erroSigla').classList.remove('hidden');
            } else {
                inputSigla.classList.remove('border-red-500');
                document.getElementById('erroSigla').classList.add('hidden');
            }
            // Cotas
            if (!inputCotas.value.trim()) {
                valido = false;
                inputCotas.classList.add('border-red-500');
                document.getElementById('erroCotas').classList.remove('hidden');
            } else {
                inputCotas.classList.remove('border-red-500');
                document.getElementById('erroCotas').classList.add('hidden');
            }
            // ValorCota
            if (!inputValorCota.value.trim()) {
                valido = false;
                inputValorCota.classList.add('border-red-500');
                document.getElementById('erroValorCota').classList.remove('hidden');
            } else {
                inputValorCota.classList.remove('border-red-500');
                document.getElementById('erroValorCota').classList.add('hidden');
            }
            // Tipo
            const tipoMovimento = document.getElementById('tipoMovimento');
            if (!tipoMovimento.value) {
                valido = false;
                tipoMovimento.classList.add('border-red-500');
                document.getElementById('erroTipo').classList.remove('hidden');
            } else {
                tipoMovimento.classList.remove('border-red-500');
                document.getElementById('erroTipo').classList.add('hidden');
            }
            if (!valido) {
                e.preventDefault();
            }
        });
    </script>

</body>

</html>
