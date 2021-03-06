$(function(){
  // Audio Control
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
      let percent = (audio.currentTime / audio.duration) * 100;
      $("#current").css("width",`${percent}%`);
      let min = Math.floor(audio.currentTime / 60);
      let sec = Math.floor(audio.currentTime) % 60;
      if (sec < 10) $("#current-time").text(`${min}:0${sec}`);
      else $("#current-time").text(`${min}:${sec}`);
    },1000);
  }
  
  audio.onended = function(){
    $(this).attr("role","pausing").text("Play");
  }

  $("div[role='choose']").click(function(){
    console.log($(this).attr("link"));
    $("#play").attr("role","playing").text("Pause");
    audio.setAttribute("src",`music_src/${$(this).attr("link")}`);
    audio.load();
    audio.play();
    audio.onloadedmetadata = function() {
      $("#duration").text(`${Math.floor(audio.duration/60)}:${Math.floor(audio.duration) % 60 < 10 ? "0"+Math.floor(audio.duration) % 60:Math.floor(audio.duration) % 60}`);
    };
  });

  // Add song to love playlist
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
        if (d.trim() === "true"){ // add
          $(this).css("filter","invert(13%) sepia(92%) saturate(7237%) hue-rotate(360deg) brightness(103%) contrast(106%)");
        }
        else if(d.trim() === "false"){ // remove
          $(this).css("filter","invert(99%) sepia(57%) saturate(2%) hue-rotate(25deg) brightness(108%) contrast(100%)");
        }
      }
    });
  });

  // Mouse over 
  let accNav = $("#acc-nav");
  if (accNav.length > 0){
    accNav.mouseenter(function(e){
      $(this).find(".ul-c-1").css("display","block");
    })
    .mouseleave(function(e){
      $(this).find(".ul-c-1").css("display","none");
    })
  }

  $("#add-pl").click(function(){
    let plName = prompt("Nhập tên playlist: ").trim();
    let addBtn = $(this);
    if (plName.length != 0){
      $.ajax({
        type:"POST",
        url:"./newplaylist.htm",
        data:{
          plName: plName
        },
        success: d => {
          console.log(d);
          $(this).remove();
          let dA = d.split(/;/g);
          $("#playlist")
          .append(
            `<tr pl-id="${dA[1]}">
              <td class="pl-name">${dA[0]}</td>
              <td class="bg-success">Sửa tên</td>
              <td id="del" class="bg-danger">Xóa</td>
            </tr>`
          )
          .append(addBtn);
        }
      });
    }
  });

  $("td#del-pl").click(function(){
    let plId = $(this).parent().attr("pl-id");
    $.ajax({
      type:"POST",
      url:"./delplaylist.htm",
      data:{
        plId: plId
      },
      success: d => {
        console.log(d);
        $(this).parent().remove();
      }
    })
  })

})