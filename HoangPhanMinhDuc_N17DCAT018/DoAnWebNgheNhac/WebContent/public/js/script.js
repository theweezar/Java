$(function(){
	$("ul[role='play']").click(function(){
		let audio = document.getElementById("audio");
		$("#musicSrc").attr("src",`music_src/${$(this).attr("link")}.mp3`);
		audio.load();
		audio.play();
	});

	$("#login").click(function(){
		$("#sign").attr("style","display: block");
		$("#loginForm").attr("style","display: block");
	});

	$("#register").click(function(){
		$("#sign").attr("style","display: block");
		$("#registerForm").attr("style","display: block");
	})

	$("#closeBtn1,#closeBtn2").click(function(){
		$("#sign").attr("style","display: none");
		$("#loginForm").attr("style","display: none");
		$("#registerForm").attr("style","display: none");
	});
});
