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
    public function showCriarPerfil()
    {
        return view('criar-perfil');
    }
    public function criarPerfil(Request $request)
    {
        $response = Http::post(env('API_URL') . '/user', [
            'nomeCompleto' => $request->nomeCompleto,
            'cpf' => $request->cpf,
            'email' => $request->email,
            'senha' => $request->password,
            'date' => $request->dataNasc,
            'telefone' => $request->telefone,
        ]);
        $data = $response->json();
        if ($response->successful()) {

            if ($data['status_code'] == 409) {
                return redirect('/criar-perfil')->with('error', $data['status_msg']);
            }
            return redirect('/login')->with('success', $data['status_msg']);
        }
        return redirect('/criar-perfil')->with('error', $data['status_msg']);
    }
    public function carregarPerfil()
    {
        $email = session('email');

        if (!$email) {
            return redirect('login')->with('error', 'É preciso estar logado');
        }
        $response = Http::get(env('API_URL') . '/user/' . urlencode($email));

        if ($response->successful()) {
            $data = $response->json();
            session()->put('hasProfileImage', $data['status_res']['hasProfileImage']);
            return view('perfil', ['perfil' => $data['status_res']['userEntity']]);
        }
        return redirect('/menu')->with('error', 'Erro ao buscar dados do perfil');
    }
    public function salvarPerfil(Request $request)
    {
        $img = null;
        if ($request->hasFile('imagem')) {
            $img = Http::attach(
                'file',
                file_get_contents($request->file('imagem')->getRealPath()),
                $request->file('imagem')->getClientOriginalName()
            )->post(env('API_URL') . '/user/' . urlencode($request->email) . '/upload');
        }

        $response = Http::put(env('API_URL') . '/user/' . urlencode($request->email), [
            'email' => $request->email,
            'nomeCompleto' => $request->nomeCompleto,
            'telefone' => $request->telefone,
            'date' => $request->date,
        ]);
        if ($response->successful() && ($img === null || $img->successful())) {
            return redirect('/perfil')->with('success', 'Salvo com sucesso');

        }
        return redirect('/menu')->with('error', 'erro ao salvar perfil');
    }
}
