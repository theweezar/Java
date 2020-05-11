$(function(){
      const audio = document.querySelector("audio");
      
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
    	  
      }
      
      audio.onended = function(){
        $(this).attr("role","pausing").text("Play");
      }
})