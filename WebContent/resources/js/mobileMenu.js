const mobileDimmedBackground = document.querySelector(".mobile_dimmed");
const mobileMenuIcon = document.querySelector("#mobile_menu_icon");

function toggleSlideMenu() {
  if (mobileDimmedBackground.classList.contains("off")) {
    mobileDimmedBackground.classList.remove("off");
    mobileMenuIcon.classList.add("fixed");
    document.querySelector(".mobile_slide").classList.add("on");
    document.querySelector("#mobile_menu_icon i").classList.remove("fa-bars");
    document.querySelector("#mobile_menu_icon i").classList.add("fa-times");
    document.querySelector(".fa-times").style.color = "rgb(50, 50, 50)";

  } else {
    mobileDimmedBackground.classList.add("off");
    mobileMenuIcon.classList.remove("fixed");
    document.querySelector(".mobile_slide").classList.remove("on");
    document.querySelector("#mobile_menu_icon i").classList.add("fa-bars");
    document.querySelector("#mobile_menu_icon i").classList.remove("fa-times");
    document.querySelector(".fa-bars").style.color = "white";
  }
}

function init() {
  mobileMenuIcon.addEventListener("click", toggleSlideMenu);
  
  mobileDimmedBackground.addEventListener('click', function(e){
    mobileMenuIcon.click();
  });
}

init();