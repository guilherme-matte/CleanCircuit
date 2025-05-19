<?php
namespace App\Exceptions;

use Illuminate\Contracts\Debug\ExceptionHandler;
use Throwable;
use Illuminate\Http\Response;

class CustomExceptionHandler implements ExceptionHandler
{
    protected ExceptionHandler $handler;

    public function __construct(ExceptionHandler $handler)
    {
        $this->handler = $handler;
    }

    public function report(Throwable $e): void
    {
        $this->handler->report($e);
    }

    public function shouldReport(Throwable $e): bool
    {
        return $this->handler->shouldReport($e);
    }

    public function render($request, Throwable $e): Response
    {
        // Você pode personalizar por tipo, ou sempre usar uma view genérica
        return response()->view('errors.generico', [
            'message' => 'Ocorreu um erro inesperado.',
            'exception' => $e,
        ], 500);
    }

    public function renderForConsole($output, Throwable $e): void
    {
        $this->handler->renderForConsole($output, $e);
    }
}
