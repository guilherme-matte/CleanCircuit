<?php

namespace App\Providers;

use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        $this->app->singleton(ExceptionHandler::class, function ($app) {
        return new CustomExceptionHandler($app->make(ExceptionHandler::class));
    });
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        $this->app->singleton(ExceptionRenderer::class, function () {
            return new class implements ExceptionRenderer {
                public function render(Throwable $e, $request)
                {
                    // Você pode logar se quiser
                    report($e);

                    return response()->view('errors.generico', [
                        'message' => 'Ocorreu um erro inesperado.',
                        'exception' => $e
                    ], 500);
                }
            };
        });
    }
}
