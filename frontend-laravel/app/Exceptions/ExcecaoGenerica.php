<?php

namespace App\Exceptions;

use Throwable;
use Illuminate\Http\Request;

class ExcecaoGenerica extends \Exception
{
    public function render(Request $request)
    {
        return response()->view('errors.generico', [
            'message' => $this->getMessage() ?: 'Ocorreu um erro inesperado.',
            'exception' => $this
        ], 500);
    }
}

