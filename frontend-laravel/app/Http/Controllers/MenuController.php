<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class MenuController extends Controller
{
    public function showMenu()
    {
        if (!session('logado')) {
            return redirect('/login')->with('error', 'É preciso estar logado');
        }
        $cpf = session('cpf');
        $response = Http::get(env('API_URL') . '/carteira/' . $cpf);
        $data = $response->json();
        if ($response->successful()) {
            return view('menu', [
                'resumo' => $data['status_res'] ?? [],
                'mensagem' => $data['status_msg'] ?? null
            ]);
        }
        throw new \Exception($data['status_msg'] ?? 'erro ao buscar carteira');
    }
    public function cadastrarAtivo(Request $request)
    {
        $tipo = match ($request->tipo) {
            'Ações' => 'acao',
            'Fiis' => 'fii',
            'ETFs' => 'etf',
            'Reits' => 'reits',
            'Stocks' => 'stock',
            'Criptomoedas' => 'cripto',
            default => back()->with('error', 'Tipo de ativo inválido.')

        };

        $response = Http::post(env('API_URL') . '/' . $tipo . '/' . session('cpf'), [
            'sigla' =>  strtoupper($request->sigla),
            'cotas' => $request->cotas,
            'valorCota' => $request->valorCota,
            'tipo' => $request->tipoMovimento
        ]);
        $data = $response->json();

        if ($response->successful()) {
            return redirect('/menu')->with('mensagem', $data['status_msg'] ?? null);
        }
        throw new \Exception($data['status_msg'] ?? 'erro ao cadastrar ativo');
    }
}
