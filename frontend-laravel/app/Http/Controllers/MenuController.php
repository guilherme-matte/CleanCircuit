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
                'mensagem' => $data['status_msg']??null
            ]);
        }
        throw new \Exception($data['status_msg'] ?? 'erro ao buscar carteira');
    }

}
