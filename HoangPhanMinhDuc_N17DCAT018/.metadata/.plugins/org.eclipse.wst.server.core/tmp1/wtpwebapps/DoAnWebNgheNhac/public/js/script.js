$(function(){
	$("ul[role='play'] li:nth-child(1)").click(function(){
		let audio = document.getElementById("audio");
		$("#musicSrc").attr("src",`music_src/${$(this).parent().attr("link")}`);
		audio.load();
		audio.onloadeddata = function(){
			this.play();
		}
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

	$("li[role='addPL']").click(function(){
		$.ajax({
			type:"POST",
			url:"AddToPlayList",
			data:{
				songId: $(this).parent().attr("song-id")
			},
			success: function(res){
				console.log(res);
			}
		})
	})
});
