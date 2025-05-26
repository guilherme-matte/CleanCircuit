<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class MenuController extends Controller
{
    public function showMenu()
    {
        if (!session('logado')) {
            return redirect('/login') -> with('error', 'É preciso estar logado');
        }
        return view('menu');
    }
    
}
