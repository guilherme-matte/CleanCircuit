<?php
use App\Http\Controllers\AuthController;

Route::get('/login', [AuthController::class, 'showLogin']);
Route::post('/login', [AuthController::class, 'login']);
Route::get('/redefinir-senha', [AuthController::class, 'showReset']);
Route::post('/redefinir-senha', [AuthController::class, 'reset']);
