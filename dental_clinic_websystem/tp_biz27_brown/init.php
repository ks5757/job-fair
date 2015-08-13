<?php


	//init.php
	////////////////////////////////
	//         初期処理を記述
	////////////////////////////////
	
	//DB接続用の設定
	$host = "localhost";
	$db_name = "dental_clinic";
	$pass = "katoshin";
	$user = "katoshin";
	$dsn = "mysql:host={$host};dbname={$db_name};charset=utf8";
	
	//DBに接続
	try{
		$dbh = new PDO($dsn, $user, $pass);
	}catch(PDOException $e){
		echo $e->getMessage();	
	}
	
	//セッションの用意
	ob_start();
	session_start();
	
	//htmlspecialcharsの呼び出し簡略化関数
	function h($v){
		return htmlspecialchars($v);
	}
	
	