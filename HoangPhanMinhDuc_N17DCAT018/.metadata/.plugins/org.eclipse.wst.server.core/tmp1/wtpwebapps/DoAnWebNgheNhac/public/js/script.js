$(function(){
      const audio = document.querySelector("audio");
      var start = undefined;
      
      audio.load();
      $("#play").click(function(){
        if ($(this).attr("role") === "pausing"){
          $(this).attr("role","playing").text("Pause");
          audio.play();
        }
        else if ($(this).attr("role") === "playing"){
          $(this).attr("role","pausing").text("Play");
          audio.pause();
        }
      });
      
      audio.onplaying = function(){
    	  start = setInterval(function(){
          console.log(audio.currentTime);
          let percent = (audio.currentTime / audio.duration) * 100;
          // console.log(percent);
          $("#current").css("width",`${percent}%`);
        },900);
      }
      
      audio.onended = function(){
        $(this).attr("role","pausing").text("Play");
      }

      $("div[role='love']").click(function(){
        const songId = $(this).siblings("div.title").attr("song-id");
        $.ajax({
          type:"POST",
          url:"./addtolovepl.htm",
          data:{
            songId:songId
          },
          success: (d) => {
        	console.log(d);
            if (d.trim() === "true"){
            	$(this).css("filter","invert(13%) sepia(92%) saturate(7237%) hue-rotate(360deg) brightness(103%) contrast(106%)");
            }
            else if(d.trim() === "false"){
            	$(this).removeAttr("style");
            }
          }
        });
      });
      
      $("div[role='choose']").click(function(){
       console.log($(this).attr("link"));
       $("#play").attr("role","playing").text("Pause");
       audio.setAttribute("src",`music_src/${$(this).attr("link")}`);
       audio.load();
       audio.play();
      });
})