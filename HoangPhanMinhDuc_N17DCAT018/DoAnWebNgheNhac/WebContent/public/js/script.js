$(function(){
	$("ul[role='play']").click(function(){
		console.log($(this).attr("song-id"));
		$("#musicSrc").attr("src",`music_src/${$(this).attr("link")}.mp3`);
	});
})