<?php
$username = $_POST['username'];
$password = $_POST['password'];
$repPasword = $_POST['repPassword'];
$email = $_POST['email'];
$cpf = $_POST['cpf'];
$data = [
  'nomeCompleto' => $username,
  'senha' => $password,
  'cpf' => $cpf,
  'email' => $email
];

if ($password !== $repPasword) {
  echo 'As senhas não conferem 

  <br>
  <a href="criarUsuario.html">Voltar</a>';
  exit;
}

$payload = json_encode($data);

$url = 'http://localhost:8080/user';

$ch = curl_init($url);

curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
curl_setopt($ch, CURLOPT_HTTPHEADER, [
  'Content-Type: application/json',
  'Content-Length: ' . strlen($payload)
]);
$response = curl_exec($ch);
if (curl_errno($ch)) {
  echo 'erro: ' . curl_errno($ch);
} else {
  echo 'resposta: ' . $response;
}
curl_close($ch);
