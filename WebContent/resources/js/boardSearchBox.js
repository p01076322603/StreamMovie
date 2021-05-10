$(function () {

  function focused() {

    $(".board_search").animate({
      "width": "150px",
      "paddingLeft": "10px",
      "borderWidth": 1
    }, function () { });

    $(".b_sicon").hide();
    $(".b_sicon2").show();
  }

  function defocused() {

    $(".board_search").animate({
      "width": "0",
      "padding": "0",
      "borderWidth": 0
    }, function () { });

    $(".b_sicon").animate({
      "left": "0"
    }, function () { });

    $(".b_sicon").show();
    $(".b_sicon2").hide();
  }

  $(".b_sicon").click(function () {
    focused();
    $(".board_search").focus();
    $(".board_search").val("");
  });
  
  $(".board_search").blur(function () {
    setTimeout(defocused, 200);
  });
  
});
