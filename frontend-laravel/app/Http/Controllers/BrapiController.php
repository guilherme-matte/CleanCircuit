<?php

namespace App\Http\Controllers;

use Http;
use Illuminate\Http\Request;

class BrapiController extends Controller
{
    public function buscar($sigla)
    {
        $response = Http::get(env('API_URL') . "/brapi/" . $sigla);
        $data = $response->json();

        if ($response->successful()) {
            $ativo = $data['status_res'];

            return response()->json([
                'sigla' => $ativo['symbol'] ?? '',
                'preco' => $ativo['regularMarketPrice'] ?? 0,
                'nome' => $ativo['longName'] ?? ($ativo['shortName'] ?? '')
            ]);
        }

        return response()->json(['error' => 'Ativo não encontrado'], 404);
    }
}
