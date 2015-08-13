
<?php

 	//login.php
	
	//初期処理
	require_once('./init.php');
 
 ////////////////////////////////////
 //  会員メニューへのログイン処理  
 ////////////////////////////////////
 
 
   //IDとpasswordを取得する
 
	$id = h($_POST['id']);
	$passwd = h($_POST['passwd']);
 
 	//ログインのエラーフラグ
 	$error_flag = false;
	
	//IDとpasswordをvalidateする
	
	//IDは半角数8文字
	if(false === ctype_digit($id)){
		$error_flag = true;	
		$id_digit_error = true;
	}
	if(8 != mb_strlen($id)){
		$error_flag = true;	
		$id_length_error = true;
	}
	
	//passwordは半角英数8文字
	if(false === ctype_alnum($passwd)){
		$error_flag = true;	
		$passwd_alnum_error = true;
	}
	
	if(8 != mb_strlen($id)){
		$error_flag = true;
		$passwd_length_error = true;
	}
	
	if($error_flag){
		header('Location: ./contact.html');	
	}
	
	//DBからidで会員情報を引っ張ってくる
	$sql = "select * from login where login_id = :login_id";
	
	$pre = $dbh->prepare($sql);
	
	$pre->bindValue(':login_id', $id);
	
	$res = $pre->execute();
	
	//var_dump($res);
	
	
	$array = $pre->fetch(PDO::FETCH_ASSOC);
	
	//本来はDBにはパスワードは生で入れず、passward_hash()でハッシュ値を生成
	//して挿入するが、簡易実装版の為、DBに直でデータを登録しちているので
	//ハッシュ値は入っておらず、今回はpassward_hash()とpassward_verify()
	//の一連の処理は省略する
	
	//登録されたパスワードと入力されたパスワードが一致しなければ、contact.htmlに突き返す
	if($array['login_passwd'] != $passwd){
		header('Location: ./contact.html');
		exit();
	}
	
	$_SESSION['uid'] = $id;
		session_regenerate_id();
		header('Location: ./menu.html');