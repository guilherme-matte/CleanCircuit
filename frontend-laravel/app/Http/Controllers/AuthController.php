<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class AuthController extends Controller
{
    public function showLogin()
    {
        return view('login');
    }

    public function showNovaSenha()
    {

        return view('nova-senha');
    }
    public function showReset()
    {
        return view('reset');
    }

    public function novaSenha(Request $request)
    {
        $senha = $request->input('novaSenha');
        $senhaConf = $request->input('novaSenhaConf');
        $token = $request->input('token');

        if ($senha != $senhaConf) {
            return redirect('/criar-nova-senha')->with('error', 'Senhas não conferem, tente novamente.');
        }

        $response = Http::post(env('API_URL') . '/new-password', [
            'newPassword' => $senha,
            'token' => $token,
        ]);

        $data = $response->json();
        if ($response->successful()) {
            return redirect('/login')->with('success', $data['status_msg']);
        }
        return redirect('/criar-nova-senha')->with('error', $data['status_msg'] . ' token: ' . $token);

    }

    public function login(Request $request)
    {

        $response = Http::post(env('API_URL') . '/login', [
            'email' => $request->email,
            'password' => $request->password,
        ]);
        $data = $response->json();
        if ($response->successful()) {

            session([
                'email' => $request->email,
                'logado' => true
            ]);
            if ($data['status_msg'] == "Login realizado com sucesso, crie uma nova senha.") {
                return redirect('/criar-nova-senha')->with('success', $data['status_msg']);

            }
            return redirect('/menu')->with('success', $data['status_msg']);
        }

        return redirect('/login')->with('error', $data['status_msg']);
    }


    public function reset(Request $request)
    {
        $email = urlencode($request->email);
        $response = Http::post(
            env('API_URL') . '/reset-password/' . $email
        );
        $data = $response;
        if ($response->successful()) {
            return redirect('/login')->with('success', $data['status_msg']);
        }

        return back()->with('error', $data['status_msg']);
    }
    public function logout()
    {
        session()->flush();
        return redirect('/login')->with('success', 'Log-out realizado com sucesso. Volte sempre!');
    }
}
