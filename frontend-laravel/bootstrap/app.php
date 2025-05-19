<?php
use Illuminate\Http\Request;
use Symfony\Component\HttpKernel\Exception\HttpExceptionInterface;

use Illuminate\Foundation\Application;
use Illuminate\Foundation\Configuration\Exceptions;
use Illuminate\Foundation\Configuration\Middleware;

return Application::configure(basePath: dirname(__DIR__))
    ->withRouting(
        web: __DIR__ . '/../routes/web.php',
        commands: __DIR__ . '/../routes/console.php',
        health: '/up',
    )
    ->withMiddleware(function (Middleware $middleware) {
        //
    })
    ->withExceptions(function (Exceptions $exceptions) {
        $exceptions->render(function (Throwable $e, Request $request) {
            // Verifica se é uma exceção HTTP (404, 500, etc.)
            $status = $e instanceof HttpExceptionInterface ? $e->getStatusCode() : 500;
            $message = match ($status) {
                404 => 'Página não encontrada.',
                403 => 'Você não tem permissão para acessar isso.',
                default => 'Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.'
            };
            // Retorna a view genérica para qualquer erro HTTP
            return response()->view('errors.generico', [
                'exception' => $e,
                'status' => $status,
                'message' => $message,
            ], $status);
        });

    })->create();
