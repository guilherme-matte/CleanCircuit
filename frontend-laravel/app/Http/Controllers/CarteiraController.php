<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Http;

class CarteiraController extends Controller
{
    public function showCarteira()
    {
        return view('carteira');
    }
    public function resumoCarteira()
    {
        $id = session('id');
        $response = Http::get(env('API_URL') . '/carteira/' . $id);
        $data = $response->json();
        if ($response->successful()) {
            return redirect('/carteira')->with('success', $data['status_res']);
        }
        return redirect('/menu')->with('error', $data['status_res']);
    }
}
