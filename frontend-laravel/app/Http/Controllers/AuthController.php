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

    public function login(Request $request)
    {
        $response = Http::post(env('API_URL') . '/login', [
            'email' => $request->email,
            'password' => $request->password,
        ]);

        if ($response->successful()) {
            $token = $response['token'];
            session(['token' => $token]);
            return redirect('/usuarios');
        }

        return redirect('/login')->with('error', 'Credenciais inválidas');
    }

    public function showReset()
    {
        return view('reset');
    }

    public function reset(Request $request)
    {
        $email = urlencode($request->email);
        $response = Http::post(
            env('API_URL') . '/reset-password/' . $email
        );

        if ($response->successful()) {
            return redirect('/login')->with('success', 'Email de redefinição enviado');
        }

        return back()->with('error', 'Erro ao enviar email');
    }
}
