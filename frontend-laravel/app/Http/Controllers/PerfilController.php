<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class PerfilController extends Controller
{
    public function showPerfil()
    {
        if (!session('logado')) {
            return redirect('/login')->with('error', 'É preciso estar logado');
        }
        return view('perfil');
    }
    public function carregarPerfil()
    {
        $email = session('email');

        if (!$email) {
            return redirect('login')->with('error', 'É preciso estar logado');
        }
        $response = Http::get(env('API_URL') . '/perfil/' . urlencode($email));

        if ($response->successful()) {
            $data = $response->json();
            return view('perfil', ['perfil' => $data]);
        }
        return redirect('/menu')->with('error', 'Erro ao buscar dados do perfil');
    }
}
