<?php
use App\Http\Controllers\AuthController;

Route::get('/login', [AuthController::class, 'showLogin']);
Route::post('/login', [AuthController::class, 'login']);
Route::get('/redefinir-senha', [AuthController::class, 'showReset']);
Route::post('/redefinir-senha', [AuthController::class, 'reset']);
Route::get('/logout', [AuthController::class, 'logout']);
Route::post('/criar-nova-senha', [AuthController::class, 'novaSenha']);
Route::get('/criar-nova-senha', [AuthController::class, 'showNovaSenha']);

use App\Http\Controllers\MenuController;
Route::get('/menu', [MenuController::class, 'showMenu']);

use App\Http\Controllers\PerfilController;
Route::get('/perfil', [PerfilController::class, 'showPerfil']);
Route::get('/perfil', [PerfilController::class, 'carregarPerfil']);
Route::post('/perfil/save', [PerfilController::class, 'salvarPerfil']);
